/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl;

import java.util.Vector;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.SentenceGenerator.Clause;
import pl.SentenceGenerator.Sentence;

/**
 *
 * @author ubriela
 */
public class PLResolutionTest {

    public PLResolutionTest() {
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
     * Test of isSatisfiable method, of class PLResolution.
     */
    @Test
    public void testIsSatisfiable() {
	System.out.println("isSatisfiable");
	SentenceGenerator sg = new SentenceGenerator();
	Sentence sentence = sg.generateSentence();
	PLResolution instance = new PLResolution();
	boolean expResult = false;
	boolean result = instance.isSatisfiable(sentence);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of PLResolve method, of class PLResolution.
     */
    @Test
    public void testPLResolve() {
	System.out.println("PLResolve");
	Clause ci = null;
	Clause cj = null;
	SentenceGenerator sg = new SentenceGenerator();
	PLResolution instance = new PLResolution();


	PLResolution pl = new PLResolution();
	ci = sg.new Clause();
//	ci.symbols.add(sg.new Symbol (0, true));
//	ci.symbols.add(sg.new Symbol (1, true));
	ci.symbols.add(sg.new Symbol(2, true));

	Clause test = ci.factoring();

	cj = sg.new Clause();
//	cj.symbols.add(sg.new Symbol (0, true));
//	cj.symbols.add(sg.new Symbol (1, true));
	cj.symbols.add(sg.new Symbol(2, false));


	Vector expResult = null;
	Vector result = instance.PLResolve(ci, cj);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of isContain method, of class PLResolution.
     */
    @Test
    public void testIsContain() {
	System.out.println("isContain");
	Vector<Clause> clauses = new Vector<Clause>();
	Vector<Clause> news = new Vector<Clause>();
	SentenceGenerator sg = new SentenceGenerator();
	PLResolution instance = new PLResolution();
	boolean expResult = false;


	Clause ci = null;
	Clause cj = null;

	ci = sg.new Clause();
	ci.symbols.add(sg.new Symbol(0, true));
	ci.symbols.add(sg.new Symbol(1, true));
	ci.symbols.add(sg.new Symbol(2, false));

	Clause ck = sg.new Clause();
	ck.symbols.add(sg.new Symbol(0, true));
	ck.symbols.add(sg.new Symbol(1, false));
	ck.symbols.add(sg.new Symbol(2, false));

	clauses.add(ci);
	clauses.add(ck);

	cj = sg.new Clause();
	cj.symbols.add(sg.new Symbol(0, true));
	cj.symbols.add(sg.new Symbol(1, false));
	cj.symbols.add(sg.new Symbol(2, true));
	
	news.add(cj);

	boolean tmp = ci.equal(cj);
	boolean tmp2 = ck.equal(cj);


	boolean result = instance.isContain(clauses, news);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }
}
