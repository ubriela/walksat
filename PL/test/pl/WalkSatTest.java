/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl;

import java.math.BigInteger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.SentenceGenerator.Clause;
import pl.SentenceGenerator.Sentence;
import pl.SentenceGenerator.Symbol;
import sun.security.util.BigInt;

/**
 *
 * @author ubriela
 */
public class WalkSatTest {
    
    public WalkSatTest() {
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

    /**
     * Test of isSatisfiable method, of class WalkSat.
     */
    @Test
    public void testIsSatisfiable() {
	System.out.println("isSatisfiable");
	Sentence sentence = null;
	SentenceGenerator sg = new SentenceGenerator();
	sentence = sg.generateSentence();
	float p = 0.5F;
	int max_flips = 250;
	WalkSat instance = new WalkSat();
	boolean expResult = false;
	
	boolean[] result = instance.isSatisfiable(sentence, p, max_flips);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of randBoolean method, of class WalkSat.
     */
    @Test
    public void testRandBoolean() {
	System.out.println("randBoolean");
	double p = 0.0;
	boolean expResult = false;
	boolean result = WalkSat.randBoolean(p);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getModel method, of class WalkSat.
     */
    @Test
    public void testGetModel() {
	System.out.println("getModel");
	WalkSat instance = new WalkSat();
	boolean[] expResult = null;
	boolean[] result = instance.getModel();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of isModelSatisfiesClause method, of class WalkSat.
     */
    @Test
    public void testIsModelSatisfiesClause() {
	System.out.println("isModelSatisfiesClause");
	boolean[] model = null;
	Clause clause = null;
	WalkSat instance = new WalkSat();
	boolean expResult = false;
	
	model = WalkSat.getModel();
	boolean result = instance.isModelSatisfiesClause(model, clause);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }
}
