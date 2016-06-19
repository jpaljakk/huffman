/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.jpaljakk.huffmancompressor.algorithm;


import fi.helsinki.jpaljakk.huffmancompressor.structures.InternalNode;
import fi.helsinki.jpaljakk.huffmancompressor.structures.Leaf;
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
public class CodeTreeTest {


    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }
    public CodeTreeTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getCode method, of class CodeTree.
     */
    @Test
    public void testGetCode() {

        InternalNode node = new InternalNode(new Leaf(1), new Leaf(2));
        CodeTree tree = new CodeTree(node, 256);
        int one = tree.getCode(1).get(0);
        int two = tree.getCode(2).get(0);
        assertEquals( 0, one);
        assertEquals( 1, two);
    }

}
