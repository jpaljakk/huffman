/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.jpaljakk.huffmancompressor;

import java.io.IOException;

/**
 *
 * @author juha
 *
 * Huffman Compressor encoder/decoder with console UI
 */
public class HuffmanCompressor {

    /**
     * Main
     *
     * @param args not used
     */
    public static void main(String[] args) {

        HuffmanUI ui = new HuffmanUI();
        ui.mainMenu();
    }
}
