/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.jpaljakk.huffmancompressor.algorithm;

import fi.helsinki.jpaljakk.huffmancompressor.streams.BitInputStream;
import fi.helsinki.jpaljakk.huffmancompressor.structures.InternalNode;
import fi.helsinki.jpaljakk.huffmancompressor.structures.Leaf;
import fi.helsinki.jpaljakk.huffmancompressor.structures.Node;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author juha
 *
 * HuffmanDecoder that reads from bit inputstream and decodes codes back to
 * symbols
 */
public class HuffmanDecoder {
    
    private BitInputStream input;

    public CodeTree codeTree;

    /**
     * Constructor for HuffmanDecoder
     *
     * @param in Bitinputstream that decoder reads
     */
    public HuffmanDecoder(BitInputStream in) {

        input = in;
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

    /**
     * Decompress file
     *
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

    /**
     * Reads and decodes next symbol
     *
     * @return decoded symbol
     * @throws IOException
     */
    public int read() throws IOException {

        if (codeTree == null) {
            throw new NullPointerException("Codetree is null");
        }

        InternalNode currentNode = codeTree.root;
        while (true) {
            int temp = input.readNoEof();
            Node nextNode;
            if (temp == 0) {
                nextNode = currentNode.leftChild;
            } else if (temp == 1) {
                nextNode = currentNode.rightChild;
            } else {
                throw new AssertionError();
            }

            if (nextNode.isLeaf()) {
                return ((Leaf) nextNode).symbol;
            } else if (!nextNode.isLeaf()) {
                currentNode = (InternalNode) nextNode;
            } else {
                throw new AssertionError();
            }
        }
    }
}
