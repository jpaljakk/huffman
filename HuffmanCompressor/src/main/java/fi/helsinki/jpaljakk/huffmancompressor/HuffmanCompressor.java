package fi.helsinki.jpaljakk.huffmancompressor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author juha
 * 
 * Huffman Compressor encoder/decoder with console UI
 */
public class HuffmanCompressor {

    /**
     * Main
     * @param args not used
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        HuffmanUI ui = new HuffmanUI();
        ui.mainMenu();
    }

    /**
     * Compress file
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
        freq.increment(256);  // EOF symbol gets frequency of 1
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

    /**
     * Decompress file
     * @param inputFile File to be decompressed
     * @param outputFile Decompressed output file
     * @throws IOException
     */
    public static void decompress(File inputFile, File outputFile) throws IOException {

        boolean tmpFile = false;
        if (inputFile.getName().equals(outputFile.getName())) {
            outputFile = new File(inputFile.getName() + ".tmp");
            tmpFile = true;
        }
        BitInputStream in = new BitInputStream(new BufferedInputStream(new FileInputStream(inputFile)));
        OutputStream out = new BufferedOutputStream(new FileOutputStream(outputFile));

        CanonicalCode canonCode = readCode(in);
        CodeTree code = canonCode.toCodeTree();
        decompress(code, in, out);

        if (tmpFile) {
            String name = inputFile.getName();
            inputFile.delete();
            outputFile.renameTo(new File(name));
        }
        out.close();
        in.close();
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
        encoder.write(256);  // EOF symbol
    }

    private static void decompress(CodeTree code, BitInputStream in, OutputStream out) throws IOException {
        HuffmanDecoder decoder = new HuffmanDecoder(in);
        decoder.codeTree = code;
        while (true) {
            int symbol = decoder.read();
            if (symbol == 256) {
                break;
            }
            out.write(symbol);
        }
    }

    private static CanonicalCode readCode(BitInputStream in) throws IOException {
        int[] codeLengths = new int[257];
        for (int i = 0; i < codeLengths.length; i++) {
            int val = 0;
            for (int j = 0; j < 8; j++) {
                val = val << 1 | in.readNoEof();
            }
            codeLengths[i] = val;
        }
        return new CanonicalCode(codeLengths);
    }
}
