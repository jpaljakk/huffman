/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.jpaljakk.huffmancompressor.algorithm;

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
public class FrequencyTableTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    public FrequencyTableTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getSymbolLimit method, of class FrequencyTable.
     */
    @Test
    public void testGetSymbolLimit() {

        int[] freq = new int[100];
        FrequencyTable instance = new FrequencyTable(freq);
        int result = instance.getSymbolLimit();
        assertEquals(100, result);
    }

    /**
     * Test of get and set method, of class FrequencyTable.
     */
    @Test
    public void testGetAndSet() {
        int[] freq = new int[100];
        FrequencyTable instance = new FrequencyTable(freq);
        instance.set(10, 5);
        int result = instance.get(10);
        assertEquals(5, result);
    }

    /**
     * Test of increment method, of class FrequencyTable.
     */
    @Test
    public void testIncrement() {
        int[] freq = new int[100];
        FrequencyTable instance = new FrequencyTable(freq);
        instance.set(10, 5);
        instance.increment(10);
        int result = instance.get(10);
        assertEquals(6, result);
    }

}
