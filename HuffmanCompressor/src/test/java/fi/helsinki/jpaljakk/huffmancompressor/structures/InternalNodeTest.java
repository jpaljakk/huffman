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
public class InternalNodeTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    public InternalNodeTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of isLeaf method, of class InternalNode.
     */
    @Test
    public void testIsLeaf() {

        InternalNode instance = new InternalNode(null, null);
        boolean expResult = false;
        boolean result = instance.isLeaf();
        assertEquals(expResult, result);
    }

    /**
     * Test of child nodes, of class InternalNode.
     */
    @Test
    public void testChildValues() {
        int left = 100;
        int right = 10;
        InternalNode instance = new InternalNode(new Leaf(left), new Leaf(right));
        Leaf leftChild = (Leaf) instance.leftChild;
        Leaf rightChild = (Leaf) instance.rightChild;
        assertEquals(left + right, leftChild.symbol + rightChild.symbol);
    }
}
