/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.jpaljakk.huffmancompressor.streams;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
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
public class BitOutputStreamTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    public BitOutputStreamTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of write method, of class BitOutputStream.
     */
    @Test
    public void testWrite() throws Exception {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BitOutputStream instance = new BitOutputStream(out);
        for (int x = 0; x < 16; x++) {
            instance.write(x % 2);
        }
        assertEquals(out.toString(), "UU");
        out.close();
        instance.close();
    }

    /**
     * Test of close method, of class BitOutputStream.
     */
    @Test
    public void testClose() throws Exception {

        OutputStream out = new ByteArrayOutputStream();
        BitOutputStream instance = new BitOutputStream(out);
        instance.close();
    }

}
