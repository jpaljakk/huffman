/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.jpaljakk.huffmancompressor.structures;

import java.lang.reflect.Array;

/**
 *
 * @author juha
 *
 * Generic PriorityQueue implemented with minimum heap
 * @param <T> type of the objects in queue
 */
public class PriorityQueue<T extends Comparable<T>> {

    private static final int FRONT = 1;

    private T[] Heap;
    private int size = 0;

    /**
     * Default constructor for PriorityQueue
     */
    public PriorityQueue() {

        this.size = 0;

    }

    /**
     * Give size of this queue
     *
     * @return size of the queue
     */
    public int size() {

        return this.size;
    }

    private int parent(int pos) {

        if (pos / 2 == 0) {
            return 1;
        }
        return pos / 2;
    }

    private int leftChild(int pos) {

        return (2 * pos);
    }

    private int rightChild(int pos) {

        return (2 * pos) + 1;
    }

    private boolean isLeaf(int pos) {

        if (pos >= (size / 2) && pos <= size) {
            return true;
        }
        return false;
    }

    private void swap(int fpos, int spos) {

        T tmp;
        tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }

    private void minHeapify(int pos) {

        if (!isLeaf(pos)) {
            if (rightChild(pos) <= size) {
                if (Heap[pos].compareTo(Heap[leftChild(pos)]) > 0 || Heap[pos].compareTo(Heap[rightChild(pos)]) > 0) {
                    if (Heap[leftChild(pos)].compareTo(Heap[rightChild(pos)]) < 0) {
                        swap(pos, leftChild(pos));
                        minHeapify(leftChild(pos));
                    } else {
                        swap(pos, rightChild(pos));
                        minHeapify(rightChild(pos));
                    }
                }
            } else if (leftChild(pos) <= size && Heap[pos].compareTo(Heap[leftChild(pos)]) > 0) {
                swap(pos, leftChild(pos));
                minHeapify(leftChild(pos));
            }
        }
    }

    /**
     * Add object to queue
     *
     * @param element Object to be added to queue
     */
    public void add(T element) {

        if (size == 0) {
            Heap = (T[]) Array.newInstance(element.getClass(), 258);
        }
        Heap[++size] = element;
        int current = size;
        minHeapify(current);
        while (Heap[current].compareTo(Heap[parent(current)]) < 0) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    /**
     * Remove first object from this queue
     *
     * @return first object on this queue
     */
    public T remove() {

        T popped = Heap[FRONT];
        Heap[FRONT] = Heap[size--];
        minHeapify(FRONT);
        return popped;
    }
}
