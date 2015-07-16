/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author ubriela
 */
public class SentenceGenerator {

    static int K = 3;
    static int M = 4;
    static String[] symbols = {"a", "b", "c", "d"};
    static int N = symbols.length;
    static Vector<Clause> clauses = new Vector<Clause>();
    static Vector<Sentence> sentences = new Vector<Sentence>();

    public SentenceGenerator() {
    }

    public class Symbol {

	int index;
	boolean isNeg = false;

	public Symbol(int index, boolean isNeg) {
	    this.index = index;
	    this.isNeg = isNeg;
	}

	public int getIndex() {
	    return index;
	}

	public void setIndex(int index) {
	    this.index = index;
	}

	public boolean isIsNeg() {
	    return isNeg;
	}

	public void setIsNeg(boolean isNeg) {
	    this.isNeg = isNeg;
	}
    }

    public class Clause {

	Vector<Symbol> symbols = new Vector<Symbol>();

	public Clause() {
	}

	public Clause(Vector<Symbol> symbols) {
	    Iterator it = symbols.iterator();
	    while (it.hasNext()) {
		Symbol s = (Symbol) it.next();
		this.symbols.add(new Symbol(s.index, s.isNeg));
	    }
	}

	public Clause generateClause(Clause clause, int[] a) {
	    Clause c = new Clause(clause.symbols);

	    for (int i = 0; i < a.length; i++) {
		if (a[i] == 1) {
		    c.symbols.get(i).isNeg = true;
		}
	    }
	    return c;
	}

	public Vector<Symbol> getSymbols() {
	    return symbols;
	}

	public void setSymbols(Vector<Symbol> symbols) {
	    this.symbols = symbols;
	}

	public Clause factoring() {
	    Clause clause = new Clause();
	    Clause tmp = new Clause();
	    for (int i = 0; i < this.symbols.size(); i++) {
		boolean isFactor = false;
		for (int j = 0; j < this.symbols.size(); j++) {
		    if (this.symbols.get(i).index == this.symbols.get(j).index
			    && this.symbols.get(i).isNeg == this.symbols.get(j).isNeg && j != i) {
			isFactor = true;
			boolean isHas = false;	//  filtering

			Iterator it = tmp.symbols.iterator();
			while (it.hasNext()) {
			    Symbol s = (Symbol) it.next();
			    if (this.symbols.get(i).index == s.index
				    && this.symbols.get(i).isNeg == s.isNeg) {
				isHas = true;
			    }
			}
			if (!isHas) {
			    tmp.symbols.add(this.symbols.get(i));
			}
			break;
		    } else if (this.symbols.get(i).index == this.symbols.get(j).index
			    && this.symbols.get(i).isNeg != this.symbols.get(j).isNeg && j != i) {
			return null;	//  any clause in which two complementary literals appear can be discarded
		    }
		}

		if (!isFactor) {
		    clause.symbols.add(new Symbol(this.symbols.get(i).index, this.symbols.get(i).isNeg));
		}
	    }
	    clause.symbols.addAll(tmp.symbols);
	    return clause;
	}

	public boolean equal(Clause clause) {
	    boolean isEqual = true;
	    if (this.symbols.size() != clause.symbols.size()) {
		return false;
	    }

	    for (int i = 0; i < clause.symbols.size(); i++) {
		Symbol s = clause.symbols.get(i);
		boolean tmp = false;
		for (int j = 0; j < this.symbols.size(); j++) {
		    if (this.symbols.get(j).index == s.index
			    && this.symbols.get(j).isNeg == s.isNeg) {
			tmp = true;
			break;
		    }
		}
		if (!tmp) {
		    return false;
		}
	    }
	    return isEqual;
	}
    }

    public class ClauseGenerator {

	/**
	 * 
	 * @param k: kCNF
	 * @param n: symbols
	 * @return 
	 */
	public Vector<Clause> generateClauses() {
	    Vector<Clause> clauses = new Vector<Clause>();

	    int[] indices;
	    CombinationGenerator x = new CombinationGenerator(N, K);

	    while (x.hasMore()) {

		Clause clause = new Clause();
		indices = x.getNext();
		for (int i = 0; i < indices.length; i++) {
		    Symbol s = new Symbol(indices[i], false);
		    clause.symbols.add(s);
		}

		//  consider nagation
		int[] a = new int[K];
		do {
		    Clause c = clause.generateClause(clause, a);
		    clauses.add(c);
		} while ((a = BinaryCombinationGenerator.next(a, 2)) != null);
	    }

	    return clauses;
	}
    }

    public class Sentence {

	Vector<Clause> clauses = new Vector<Clause>();

	public Sentence() {
	}

	public Sentence(Vector<Clause> clauses) {
	    this.clauses = clauses;
	}

	public Vector<Clause> getClauses() {
	    return clauses;
	}

	public void setClauses(Vector<Clause> clauses) {
	    this.clauses = clauses;
	}

	public Vector<Clause> copyClauses() {
	    Vector<Clause> clauses = new Vector<Clause>();
	    Iterator it = this.clauses.iterator();
	    while (it.hasNext()) {
		Clause clause = (Clause) it.next();
		Iterator it2 = clause.symbols.iterator();
		Clause c = new Clause();
		while (it2.hasNext()) {
		    Symbol s = (Symbol) it2.next();
		    c.symbols.add(new Symbol(s.index, s.isNeg));
		}
		clauses.add(c);
	    }
	    return clauses;
	}
    }

    public Vector<Sentence> generateRandomSentences(int n) {
	Vector<Sentence> sentences = new Vector<Sentence>();
	ClauseGenerator cg = new ClauseGenerator();
	clauses = cg.generateClauses();

	BigInteger count;
	CombinationGenerator x = new CombinationGenerator(clauses.size(), M);
	count = x.getTotal();

	int[] indices = new int[M];
	while (true) {
	    //	Generate a random sentence
	    for (int i = 0; i < M; i++) {
		Random rand = new Random();
		indices[i] = rand.nextInt(clauses.size());
		for (int j = 0; j < i; j++) {
		    if (indices[i] == indices[j]) {
			i--;
		    }
		}
	    }

	    //	add new sentence
	    Sentence sentence = new Sentence();
	    for (int i = 0; i < indices.length; i++) {
		sentence.clauses.add(clauses.get(indices[i]));
	    }

	    //	check contain
	    Iterator it = sentences.iterator();
	    boolean isContain = false;
	    while (it.hasNext()) {
		Sentence s = (Sentence) it.next();
		if (PLResolution.isContain(s.clauses, sentence.clauses)) {
		    isContain = true;
		    break;
		}
	    }

	    if (!isContain) {
		sentences.add(sentence);

		if (sentences.size() == n || sentences.size() == count.intValue()) {
		    break;
		}
	    }

	}

	return sentences;
    }

    public Sentence generateSentence() {
	ClauseGenerator cg = new ClauseGenerator();
	clauses = cg.generateClauses();
	Sentence sentence = new Sentence();
	int[] indices;
	CombinationGenerator x = new CombinationGenerator(clauses.size(), M);
	indices = x.getNext();
	for (int i = 0; i < indices.length; i++) {
	    sentence.clauses.add(clauses.get(indices[i]));
	}
	return sentence;
    }

    public Vector<Sentence> generateSentences() {
	Vector<Sentence> sentences = new Vector<Sentence>();

	int[] indices;
	CombinationGenerator x = new CombinationGenerator(clauses.size(), M);

	while (x.hasMore()) {
	    Sentence sentence = new Sentence();
	    indices = x.getNext();
	    for (int i = 0; i < indices.length; i++) {
		sentence.clauses.add(clauses.get(indices[i]));
	    }
	    sentences.add(sentence);
	}

	return sentences;
    }
}

//    public Vector<Sentence> generateRandomSentences(int n) {
//	Vector<Sentence> sentences = new Vector<Sentence>();
//	ClauseGenerator cg = new ClauseGenerator();
//	clauses = cg.generateClauses();
//
//	int[] indices;
//	CombinationGenerator x = new CombinationGenerator(clauses.size(), M);
//	BigInteger count = x.getTotal();
//	System.out.println("Number of sentence: " + count);
//
//
//	ArrayList<BigInteger> al = new ArrayList<BigInteger>();
//	int loop = (n <= count.intValue()) ? n : count.intValue();
//	while (al.size() < loop) {
//	    int value = 0;
//	    if (count.intValue() < 1000000) {
//		Random rand = new Random();
//		value = rand.nextInt((count.intValue()));
//	    } else {
//		Random rand = new Random();
//		value = rand.nextInt(1000000);
//	    }
//
//	    if (!al.contains(BigInteger.valueOf(value))) {
//		al.add(BigInteger.valueOf(value));
//	    }
//	}
//	Collections.sort(al);
//	Iterator it = al.iterator();
//	long index = 0;
//	BigInteger value = (BigInteger) it.next();
//	while (x.hasMore()) {
//	    indices = x.getNext();
//	    if (value.equals(BigInteger.valueOf(index))) {
//		Sentence sentence = new Sentence();
//		for (int i = 0; i < indices.length; i++) {
//		    sentence.clauses.add(clauses.get(indices[i]));
//		}
//		sentences.add(sentence);
//		if (it.hasNext()) {
//		    value = (BigInteger) it.next();
//		}
//		if (sentences.size() == n) {
//		    break;
//		}
//	    }
//	    index++;
//
//	    //	debug
//	    if (index % 100000 == 0) {
//		System.out.println(">>" + index);
//	    }
//	}
//
//	return sentences;
//    }