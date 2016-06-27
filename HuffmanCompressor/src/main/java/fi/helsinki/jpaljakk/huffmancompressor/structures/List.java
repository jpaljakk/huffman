/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.jpaljakk.huffmancompressor.structures;

/**
 *
 * @author juha
 *
 * Generic List
 *
 * @param <T> Type of the objects in this list
 */
public class List<T> {

    private int size = 0;
    private int maxSize = 10;
    private T[] list;
    protected Object[] lista;


    /**
     * Constructor that has other list as parameter
     *
     * @param parameter List that is used to construct new List
     */
    public List(List<T> parameter) {

        for (int x = 0; x < parameter.size(); x++) {
            add(parameter.get(x));
        }
    }

    /**
     * Constructor without parameters
     */
    public List() {
//

    }

    /**
     * Add object to the list
     *
     * @param element Object to be added to this list
     */
    public void add(T element) {

        if (size == 0) {
            list = (T[]) new Object[maxSize];

        } else if (size == maxSize) {
            maxSize *= 2;

            T[] tmp = (T[]) new Object[maxSize];
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
     *  Get object from specified location
     * 
     * @param index location of the object received
     * @return Object from index location
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
     * @param index index number thats going to be removed from the list
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
}