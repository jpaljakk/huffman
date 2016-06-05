/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.jpaljakk.huffmancompressor;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author juha
 *
 * Console User Interface for Huffman Compressor
 */
public class HuffmanUI {

    private String userChoices = "\n\n\n1. Compress file\n"
            + "2. Compress file to different output file\n"
            + "3. Decompress file\n"
            + "4. Decompress to different output file\n"
            + "5. Print Huffman codes of file\n"
            + "X. Quit program or get back to this menu from submenus";

    private Scanner reader = new Scanner(System.in);

    private boolean insideSubmenu = false;

    public void mainMenu() throws IOException {
        System.out.println(userChoices);
        String choice = reader.nextLine();
        if (choice.equals("1")) {
            compress();
        } else if (choice.equals("2")) {
            compressWithDifferentOutput();
        } else if (choice.equals("3")) {
            decompress();
        } else if (choice.equals("4")) {
            decompressWithDifferentOutput();
        } else if (choice.equals("5")) {
            printCodes();
        } else if (choice.equals("X")) {
            comeBackOrExit();
        } else {
            System.out.println("\n\nChoice not regognized, press enter to continue to main menu");
            reader.nextLine();
            mainMenu();
        }
    }

    private void compress() throws IOException {
        insideSubmenu = true;
        File file = askInputFile("compress");
        if (file == null) {
            comeBackOrExit();
        } else {
            HuffmanCompressor.compress(file, file);
        }
    }

    private void compressWithDifferentOutput() throws IOException {
        insideSubmenu = true;
        File file = askInputFile("input for compress");
        if (file == null) {
            comeBackOrExit();
        } else {
            System.out.println("\nType filename for output file");
            File output = new File(reader.nextLine());
            if (output.getName().equals("X")) {
                comeBackOrExit();
            } else if (output.exists()) {
                System.out.println("\n\nOutput file already exists, cannot overwrite");
            } else {
                HuffmanCompressor.compress(file, output);
            }
        }
    }

    private void decompress() throws IOException {
        insideSubmenu = true;
        File file = askInputFile("decompress");
        if (file == null) {
            comeBackOrExit();
        } else {
            HuffmanCompressor.decompress(file, file);
        }
    }

    private void decompressWithDifferentOutput() throws IOException {
        insideSubmenu = true;
        File file = askInputFile("input for decompress");
        if (file == null) {
            comeBackOrExit();
        } else {
            System.out.println("\nType filename for output file");
            File output = new File(reader.nextLine());
            if (output.getName().equals("X")) {
                comeBackOrExit();
            } else if (output.exists()) {
                System.out.println("\n\nOutput file already exists, cannot overwrite");
            } else {
                HuffmanCompressor.decompress(file, output);
            }
        }
    }

    private void printCodes() {
        insideSubmenu = true;
    }

    private File askInputFile(String postFix) {
        System.out.println("\n\n\n"
                + "Type filename to " + postFix + " and press Enter");
        File file = new File(reader.nextLine());
        while (!file.exists()) {
            if (file.getName().equals("X")) {
                return null;
            }
            System.out.println("\nFile cannot be found");
            System.out.println("\n\n\n"
                    + "Type filename for " + postFix + " and press Enter");
            file = new File(reader.nextLine());
        }
        return file;
    }

    private void comeBackOrExit() throws IOException {
        if (insideSubmenu) {
            insideSubmenu = false;
            mainMenu();
        } else {
            System.out.println("\n\nThank you for using HuffmanCompressor,\n\n");
        }
    }
}
