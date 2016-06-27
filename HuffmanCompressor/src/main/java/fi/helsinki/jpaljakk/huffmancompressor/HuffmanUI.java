/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.jpaljakk.huffmancompressor;

import fi.helsinki.jpaljakk.huffmancompressor.algorithm.HuffmanDecoder;
import fi.helsinki.jpaljakk.huffmancompressor.algorithm.HuffmanEncoder;
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
            + "5. Performance Test - Encode file and Decode it back and count the time\n"
            + "X. Quit program or get back to this menu from submenus";

    private Scanner reader = new Scanner(System.in);

    private boolean insideSubmenu = false;
    private boolean exit=false;

    public void mainMenu()  {
        insideSubmenu = false;
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
            performanceTest();
        } else if (choice.equals("X")||choice.equals("x")) {
            comeBackOrExit();
        } else {
            System.out.println("\n\nChoice not regognized, press enter to continue to main menu");
            reader.nextLine();
            mainMenu();
        }
        if(!exit){
            System.out.println("\nPress enter to continue");
            reader.nextLine();
            mainMenu();
        }
    }
    
    private void compress() {
        insideSubmenu = true;
        File file = askInputFile("compress");
        if (file == null) {
            comeBackOrExit();
        } else {
            try{
                HuffmanEncoder.compress(file, file);
            } catch(Exception e){
                System.out.println("Compression failed : "+e.toString());
            }
        }
    }

    private void compressWithDifferentOutput() {
        insideSubmenu = true;
        File file = askInputFile("input for compress");
        if (file == null) {
            comeBackOrExit();
        } else {
            System.out.println("\nType filename for output file");
            try{
                File output = new File(reader.nextLine());
                if (output.getName().equals("X")||output.getName().equals("x")) {
                    comeBackOrExit();
                } else if (output.exists()) {
                    System.out.println("\n\nOutput file already exists, cannot overwrite");
                } else {
                    HuffmanEncoder.compress(file, output);
                }
            } catch(Exception e){
                System.out.println("Compression failed : "+e.toString());
            }
        }
    }

    private void decompress() {
        insideSubmenu = true;
        File file = askInputFile("decompress");
        if (file == null) {
            comeBackOrExit();
        } else {
            try{
            HuffmanDecoder.decompress(file, file);
            } catch(Exception e){
                System.out.println("Compression failed : "+e.toString());
            }
        }
    }

    private void decompressWithDifferentOutput() {
        insideSubmenu = true;
        File file = askInputFile("input for decompress");
        if (file == null) {
            comeBackOrExit();
        } else {
            System.out.println("\nType filename for output file");
            try{
            File output = new File(reader.nextLine());
            if (output.getName().equals("X")||output.getName().equals("x")) {
                comeBackOrExit();
            } else if (output.exists()) {
                System.out.println("\n\nOutput file already exists, cannot overwrite");
            } else {
                HuffmanDecoder.decompress(file, output);
            }
            } catch(Exception e){
                System.out.println("Decompression failed : "+e.toString());
            }
        }
    }

    private void performanceTest()  {
        insideSubmenu = true;
        File file = askInputFile("use for testing performance");
        if (file == null) {
            comeBackOrExit();
        } else {
            runTest(file);
        }
    }

    private void runTest(File file) {
        System.out.println("\n\n\n");
        try{
            File temp = new File("tmp.tmp");
            long encoderStartTime = System.currentTimeMillis();
            HuffmanEncoder.compress(file, temp);
            long encoderEndTime = System.currentTimeMillis();
            long decoderStartTime = System.currentTimeMillis();
            HuffmanDecoder.decompress(temp, file);
            long decoderEndTime = System.currentTimeMillis();
            temp.delete();
            long encoderTime = encoderEndTime - encoderStartTime;
            long decoderTime = decoderEndTime - decoderStartTime;
            System.out.println("Encoding took: " + encoderTime + "ms.");
            System.out.println("File size " + file.length() + " bytes  -  " + (file.length() / encoderTime) + " bytes per ms");
            System.out.println("Decoding took: " + decoderTime + "ms.");
            System.out.println("File size " + file.length() + " bytes  -  " + (file.length() / decoderTime) + " bytes per ms");
        } catch(Exception e){
                System.out.println("Performance test failed : "+e.toString());
            }
    }

    private File askInputFile(String postFix) {
        System.out.println("\n\n\n"
                + "Type filename to " + postFix + " and press Enter");
        File file = new File(reader.nextLine());
        while (!file.exists()) {
            if (file.getName().equals("X")||file.getName().equals("x")) {
                return null;
            }
            System.out.println("\nFile cannot be found");
            System.out.println("\n\n\n"
                    + "Type filename for " + postFix + " and press Enter");
            file = new File(reader.nextLine());
        }
        return file;
    }

    private void comeBackOrExit() {
        if (insideSubmenu) {
            insideSubmenu = false;
            mainMenu();
        } else {
            exit=true;
            System.out.println("\n\nThank you for using HuffmanCompressor\n");
        }
    }
}
