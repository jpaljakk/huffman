/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.jpaljakk.huffmancompressor.streams;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
public class BitInputStreamTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    public BitInputStreamTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of readNoEof method, of class BitInputStream.
     */
    @Test
    public void testReadNoEof() throws Exception {

        InputStream in = new ByteArrayInputStream("dadadadadad".getBytes());

        BitInputStream instance = new BitInputStream(in);
        int expResult = 'd';
        int result = 0;
        for (int x = 0; x < 8; x++) {
            result = result << 1 | instance.readNoEof();
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of close method, of class BitInputStream.
     */
    @Test
    public void testClose() throws Exception {

        InputStream in = new ByteArrayInputStream("dadadadadad".getBytes());
        BitInputStream instance = new BitInputStream(in);
        instance.close();
    }

}
