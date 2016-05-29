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
public class CanonicalCodeTest {
    
    public CanonicalCodeTest() {
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
     * Test of getSymbolLimit method, of class CanonicalCode.
     */
    @Test
    public void testGetSymbolLimit() {
        System.out.println("getSymbolLimit");
        CanonicalCode instance = null;
        int expResult = 0;
        int result = instance.getSymbolLimit();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCodeLength method, of class CanonicalCode.
     */
    @Test
    public void testGetCodeLength() {
        System.out.println("getCodeLength");
        int symbol = 0;
        CanonicalCode instance = null;
        int expResult = 0;
        int result = instance.getCodeLength(symbol);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toCodeTree method, of class CanonicalCode.
     */
    @Test
    public void testToCodeTree() {
        System.out.println("toCodeTree");
        CanonicalCode instance = null;
        CodeTree expResult = null;
        CodeTree result = instance.toCodeTree();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
