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
public class CanonicalCodeTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    public CanonicalCodeTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getSymbolLimit method, of class CanonicalCode.
     */
    @Test
    public void testGetSymbolLimit() {

        CanonicalCode instance = new CanonicalCode(new int[256]);
        int expResult = 256;
        int result = instance.getSymbolLimit();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCodeLength method, of class CanonicalCode.
     */
    @Test
    public void testGetCodeLength() {
        int[] array = new int[256];
        array[100] = 9;
        CanonicalCode instance = new CanonicalCode(array);
        int expResult = 9;
        int result = instance.getCodeLength(100);
        assertEquals(expResult, result);
    }

}
