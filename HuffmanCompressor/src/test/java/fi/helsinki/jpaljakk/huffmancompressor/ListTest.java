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
public class ListTest {
    
    
    public ListTest() {
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
     * Test of add method, of class List.
     */
    @Test
    public void testAdd() {
       
        List<Integer> instance = new List();
        int result1=instance.size();
        instance.add(5);
        int result2=instance.size();
        int result3=instance.get(0);
        assertEquals(0,result1);
        assertEquals(1,result2);
        assertEquals(5,result3);
        
    }

    /**
     * Test of set method, of class List.
     */
    @Test
    public void testSet() {

        List<Integer> instance = new List();
        instance.add(5);
        instance.set(0, 6);
        int result=instance.get(0);
        instance.set(6, result);

    }

    /**
     * Test of get method, of class List.
     */
    @Test
    public void testGet() {

        List<Integer> instance = new List();
        instance.add(5);
        int result = instance.get(0);
        assertEquals(5, result);
    }

    /**
     * Test of size method, of class List.
     */
    @Test
    public void testSize() {
        
        List<Integer> instance = new List();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        instance.add(1);
        expResult = 1;
        result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of remove method, of class List.
     */
    @Test
    public void testRemove() {
        int index = 0;
        List<Integer> instance = new List();
        instance.add(1);
        instance.add(2);        
        int result1=instance.get(0);
        int size=instance.size();
        instance.remove(0);
        int result2=instance.get(0);
        assertEquals(size,instance.size()+1);
        assertEquals(1+2,result1+result2);
    }
    
}
