/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.jpaljakk.huffmancompressor.algorithm;

import fi.helsinki.jpaljakk.huffmancompressor.structures.InternalNode;
import fi.helsinki.jpaljakk.huffmancompressor.structures.Leaf;
import fi.helsinki.jpaljakk.huffmancompressor.structures.Node;
import fi.helsinki.jpaljakk.huffmancompressor.structures.PriorityQueue;

/**
 *
 * @author juha
 *
 * Table of symbol frequencies
 */
public class FrequencyTable {

    private int[] frequencies;

    /**
     * Constructor for table of symbol frequencies
     *
     * @param freqs Table of symbol frequencies
     */
    public FrequencyTable(int[] freqs) {

        if (freqs.length < 2 || freqs == null) {
            throw new IllegalArgumentException("Frequencies null or too low");
        }
        frequencies = freqs;
    }

    /**
     * Gives limit of symbols
     *
     * @return Number of symbols
     */
    public int getSymbolLimit() {
        return frequencies.length;
    }

    /**
     * Gives frequency of the given symbol
     *
     * @param symbol Symbol which frequency is asked
     * @return Frequency of the given symbol
     */
    public int get(int symbol) {
        if (symbol < 0 || symbol >= frequencies.length) {
            throw new IllegalArgumentException("Symbol out of range");
        }
        return frequencies[symbol];
    }

    /**
     * Sets the frequency of the given symbol
     *
     * @param symbol Symbol which frequency is set
     * @param freq Frequency of the given symbol
     */
    public void set(int symbol, int freq) {
        if (symbol < 0 || symbol >= frequencies.length) {
            throw new IllegalArgumentException("Symbol out of range");
        }
        frequencies[symbol] = freq;
    }

    /**
     * Increments frequency of the given symbol by one
     *
     * @param symbol Symbol which frequency is incremented
     */
    public void increment(int symbol) {
        if (symbol < 0 || symbol >= frequencies.length) {
            throw new IllegalArgumentException("Symbol out of range");
        }
        frequencies[symbol]++;
    }

    /**
     * Builds and returns optimal code tree for these frequencies
     *
     * @return Build code tree
     */
    public CodeTree buildCodeTree() {
        // Frequency tie is broken by which tree contains the lowest symbol.
        PriorityQueue<NodeWithFrequency> pqueue = new PriorityQueue<NodeWithFrequency>();

        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) {
                pqueue.add(new NodeWithFrequency(new Leaf(i), i, frequencies[i]));
            }
        }

        // Pad with zero-frequency symbols until queue has at least 2 items
        for (int i = 0; i < frequencies.length && pqueue.size() < 2; i++) {
            if (i >= frequencies.length || frequencies[i] == 0) {
                pqueue.add(new NodeWithFrequency(new Leaf(i), i, 0));
            }
        }
        if (pqueue.size() < 2) {
            throw new AssertionError();
        }

        // Repeatedly tie together two nodes with the lowest frequency
        while (pqueue.size() > 1) {
            NodeWithFrequency nf1 = pqueue.remove();
            NodeWithFrequency nf2 = pqueue.remove();
            pqueue.add(new NodeWithFrequency(
                    new InternalNode(nf1.node, nf2.node),
                    Math.min(nf1.lowestSymbol, nf2.lowestSymbol),
                    nf1.frequency + nf2.frequency));
        }

        // Return the remaining node
        return new CodeTree((InternalNode) pqueue.remove().node, frequencies.length);
    }

    private static class NodeWithFrequency implements Comparable<NodeWithFrequency> {

        public Node node;
        public int lowestSymbol;
        public int frequency;

        public NodeWithFrequency(Node node, int lowestSymbol, int freq) {
            this.node = node;
            this.lowestSymbol = lowestSymbol;
            this.frequency = freq;
        }

        @Override
        public int compareTo(NodeWithFrequency other) {
            if (frequency < other.frequency) {
                return -1;
            } else if (frequency > other.frequency) {
                return 1;
            } else if (lowestSymbol < other.lowestSymbol) {
                return -1;
            } else if (lowestSymbol > other.lowestSymbol) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
