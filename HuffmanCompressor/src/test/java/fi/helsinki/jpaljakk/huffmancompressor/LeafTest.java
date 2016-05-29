/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.jpaljakk.huffmancompressor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juha
 */
public class LeafTest {
    
    public LeafTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isLeaf method, of class Leaf.
     */
    @Test
    public void testIsLeaf() {
        
        Leaf instance = new Leaf(10);
        boolean expResult = true;
        boolean result = instance.isLeaf();
        assertEquals(expResult, result);       
    }
    /**
     * Test of symbol value, of class Leaf.
     */
    @Test
    public void testSymbolValue() {
        Leaf instance = new Leaf(200);
        assertEquals(200, instance.symbol);
    }
    
}
