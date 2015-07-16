/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl;

import pl.SentenceGenerator.Symbol;
import pl.SentenceGenerator.Clause;
import pl.SentenceGenerator.Sentence;
import java.util.Iterator;
import java.util.Vector;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ubriela
 */
public class SentenceGeneratorTest {
    
    public SentenceGeneratorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateRandomSentences method, of class SentenceGenerator.
     */
    @Test
    public void testGenerateRandomSentences() {
	System.out.println("generateRandomSentences");
	int n = 21;
	Vector expResult = null;
	SentenceGenerator sg = new SentenceGenerator();
	Vector result = sg.generateRandomSentences(n);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of generateSentences method, of class SentenceGenerator.
     */
    @Test
    public void testGenerateSentences() {
	System.out.println("generateSentences");
	SentenceGenerator instance = new SentenceGenerator();
	Vector expResult = null;
	Vector result = instance.generateSentences();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of generateSentence method, of class SentenceGenerator.
     */
    @Test
    public void testGenerateSentence() {
	System.out.println("generateSentence");
	SentenceGenerator instance = new SentenceGenerator();
	Sentence expResult = null;
	Sentence result = instance.generateSentence();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

}
