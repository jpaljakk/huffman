/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.jpaljakk.huffmancompressor.streams;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author juha
 *
 * Input Stream of bits from another input stream
 */
public class BitInputStream {

    private InputStream input;
    private int nextBits;
    private int bitsRemaining;
    private boolean endOfStream;

    /**
     * Constructor for BitInputStream
     *
     * @param in input stream to connect
     */
    public BitInputStream(InputStream in) {
        if (in == null) {
            throw new NullPointerException("Argument is null");
        }
        input = in;
        bitsRemaining = 0;
        endOfStream = false;
    }

    private int read() throws IOException {
        if (endOfStream) {
            return -1;
        }
        if (bitsRemaining == 0) {
            nextBits = input.read();
            if (nextBits == -1) {
                endOfStream = true;
                return -1;
            }
            bitsRemaining = 8;
        }
        bitsRemaining--;
        return (nextBits >>> bitsRemaining) & 1;
    }

    /**
     * Read next bit from the stream. Throws EOFException at the end of stream
     *
     * @return integer, 1 or 0, represeting next bit
     * @throws IOException
     */
    public int readNoEof() throws IOException {
        int result = read();
        if (result != -1) {
            return result;
        } else {
            throw new EOFException("End of stream");
        }
    }

    /**
     * Close this and the underlying stream
     *
     * @throws IOException
     */
    public void close() throws IOException {
        input.close();
    }
}
