/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.jpaljakk.huffmancompressor.streams;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author juha
 *
 * A Steam for bits to be written to. Connects to other outputstream.
 */
public class BitOutputStream {

    private OutputStream output;
    private int currentByte;
    private int bitsInCurrentByte;

    /**
     * Constructor for BitOutputStream
     *
     * @param out byte outputStream that bits are written
     */
    public BitOutputStream(OutputStream out) {

        output = out;
        currentByte = 0;
        bitsInCurrentByte = 0;
    }

    /**
     * Write bit to stream
     *
     * @param b integer, 0 or 1, to be written as bit.
     * @throws IOException
     */
    public void write(int b) throws IOException {
        if (!(b == 0 || b == 1)) {
            throw new IllegalArgumentException("Integer not 1 or 0");
        }
        currentByte = currentByte << 1 | b;
        bitsInCurrentByte++;
        if (bitsInCurrentByte == 8) {
            output.write(currentByte);
            bitsInCurrentByte = 0;
        }
    }

    /**
     * Close this and the underlying stream. And fill the last byte with zeros
     * if not full.
     *
     * @throws IOException
     */
    public void close() throws IOException {
        while (bitsInCurrentByte != 0) {
            write(0);
        }
        output.close();
    }
}
