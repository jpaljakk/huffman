/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.jpaljakk.huffmancompressor;

import java.lang.reflect.Array;
import java.util.Iterator;

/**
 *
 * @author juha
 *
 * Generic List
 *
 * @param <T> Type of the objects in this list
 */
public class List<T> implements Iterable<T>, Iterator<T> {

    private int size = 0;
    private int maxSize = 10;
    private T[] list;

    /**
     * Constructor with size parameter
     *
     * @param <Integer>
     * @param initialSize size of the constructed list
     */
    public <Integer> List(Integer initialSize) {

        Number n = (Number) initialSize;
        maxSize = n.intValue();
    }

    /**
     * Constructor without parameters
     */
    public List() {
    }

    /**
     * Add object to the list
     *
     * @param element Object to be added to this list
     */
    public void add(T element) {
        if (size == 0) {
            list = (T[]) Array.newInstance(element.getClass(), maxSize);
        } else if (size == maxSize) {
            maxSize *= 2;
            T[] tmp = (T[]) Array.newInstance(element.getClass(), maxSize);
            for (int x = 0; x < list.length; x++) {
                tmp[x] = list[x];
            }
            list = tmp;
        }
        list[size++] = element;
    }

    /**
     * Insert object in the list in the specific location
     *
     * @param index index number where object is going to be inserted
     * @param element Object to be inserted in list
     */
    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        list[index] = element;

    }

    /**
     *
     * @param index
     * @return
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return list[index];
    }

    /**
     * Gives size of the list
     *
     * @return size of the list
     */
    public int size() {
        return this.size;
    }

    /**
     * Remove item from list
     *
     * @param index index number thats going to be removed
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        size--;
        for (int x = index; x < size; x++) {
            list[x] = list[x + 1];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T next() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
