/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.jpaljakk.huffmancompressor.structures;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author juha
 */
public class PriorityQueueTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    public PriorityQueueTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of size method, of class PriorityQueue.
     */
    @Test
    public void testSize() {
        PriorityQueue<Integer> instance = new PriorityQueue();
        assertEquals(instance.size(), 0);
        instance.add(10);
        int result = instance.size();
        assertEquals(result, 1);
    }

    /**
     * Test of add method, of class PriorityQueue.
     */
    @Test
    public void testAdd() {
        PriorityQueue<Integer> instance = new PriorityQueue();
        instance.add(10);
        instance.add(5);
        int result = instance.size();
        assertEquals(result, 2);
    }

    /**
     * Test of remove method, of class PriorityQueue.
     */
    @Test
    public void testRemove() {

        PriorityQueue<Integer> instance = new PriorityQueue();
        instance.add(10);
        instance.add(5);
        int result = instance.remove();
        assertEquals(result, 5);

    }

}
