/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.jpaljakk.huffmancompressor.algorithm;

import fi.helsinki.jpaljakk.huffmancompressor.streams.BitOutputStream;
import fi.helsinki.jpaljakk.huffmancompressor.structures.List;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author juha
 *
 * HuffmanEncoder encodes and writes to output bitstream
 *
 */
public class HuffmanEncoder {

    /**
     * Compress file
     *
     * @param inputFile File to compress
     * @param outputFile Compressed output file
     * @throws IOException
     */
    public static void compress(File inputFile, File outputFile) throws IOException {
        boolean tmpFile = false;
        if (inputFile.getName().equals(outputFile.getName())) {
            outputFile = new File(inputFile.getName() + ".tmp");
            tmpFile = true;
        }
        FrequencyTable freq = getFrequencies(inputFile);
        freq.increment(256); // EOF symbol gets frequency of 1
        CodeTree code = freq.buildCodeTree();
        CanonicalCode canonCode = new CanonicalCode(code, 257);
        code = canonCode.toCodeTree();
        InputStream in = new BufferedInputStream(new FileInputStream(inputFile));
        BitOutputStream out = new BitOutputStream(new BufferedOutputStream(new FileOutputStream(outputFile)));
        try {
            writeCode(out, canonCode);
            compress(code, in, out);
        } finally {
            out.close();
            in.close();
        }
        if (tmpFile) {
            String name = inputFile.getName();
            inputFile.delete();
            outputFile.renameTo(new File(name));
        }
    }

    private static void compress(CodeTree code, InputStream in, BitOutputStream out) throws IOException {
        HuffmanEncoder encoder = new HuffmanEncoder(out);
        encoder.codeTree = code;
        while (true) {
            int b = in.read();
            if (b == -1) {
                break;
            }
            encoder.write(b);
        }
        encoder.write(256); // EOF symbol
    }

    private static FrequencyTable getFrequencies(File file) throws IOException {
        FrequencyTable freq = new FrequencyTable(new int[257]);
        InputStream input = new BufferedInputStream(new FileInputStream(file));
        while (true) {
            int b = input.read();
            if (b == -1) {
                break;
            }
            freq.increment(b);
        }
        input.close();
        return freq;
    }

    private static void writeCode(BitOutputStream out, CanonicalCode canonCode) throws IOException {
        for (int i = 0; i < canonCode.getSymbolLimit(); i++) {
            int val = canonCode.getCodeLength(i);
            if (val >= 256) {
                throw new RuntimeException("Code is too long");
            }
            for (int j = 7; j >= 0; j--) {
                out.write((val >>> j) & 1);
            }
        }
    }

    private BitOutputStream output;

    public CodeTree codeTree;

    /**
     * Constructor for HuffmanEncoder
     *
     * @param output BitOutputStream that encoder can write
     */
    public HuffmanEncoder(BitOutputStream output) {
        this.output = output;
    }

    /**
     * Encode and write symbols to output bitstream
     *
     * @param symbol Symbol that is to be encoded to output bitstream
     * @throws IOException
     */
    public void write(int symbol) throws IOException {
        if (codeTree == null) {
            throw new NullPointerException("Codetree is null");
        }
        List<Integer> bits = codeTree.getCode(symbol);
        for (int x = 0; x < bits.size(); x++) {
            output.write(bits.get(x));
        }
    }
}
