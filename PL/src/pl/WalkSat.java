/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl;

import java.util.Iterator;
import java.util.Random;
import java.util.Vector;
import pl.SentenceGenerator.Clause;
import pl.SentenceGenerator.Sentence;

/**
 *
 * @author ubriela
 */
public class WalkSat {

    static int count = 0;	//  runtime test

    /**
     * 
     * @param sentence, a set of clauses in propositional logic
     * @param p, the probability of choosing to do a "random walk" move, typically around 0.5
     * @param max_flips, number of flips allowed before giving up
     * @return 
     */
    public static boolean[] isSatisfiable(Sentence sentence, float p, int max_flips) {
	boolean isSatisfiable;
	boolean[] model = getModel();
	count = 0;

	for (int i = 0; i < max_flips; i++) {
	    count++;
	    Iterator it = sentence.clauses.iterator();
	    isSatisfiable = true;

	    Vector<Clause> falseClauses = new Vector<Clause>();
	    while (it.hasNext()) {
		Clause clause = (Clause) it.next();
		if (!isModelSatisfiesClause(model, clause)) {
		    isSatisfiable = false;
		    falseClauses.add(clause);
		}
	    }
	    if (isSatisfiable) {
		return model;
	    }

	    Random rand = new Random();
	    Clause falseClause = falseClauses.get(rand.nextInt(falseClauses.size()));

	    //	with probability p flip the value in model of a randomly selected symbol from clause
	    //  else flip whichever symbol in clause maximizes the number of satisfied clauses
	    int flipIndex;
	    if (randBoolean(p)) {
		flipIndex = randomFlipIndex(falseClause);
	    } else {
		flipIndex = maximizeSatisfiedClausesIndex(model, falseClause, sentence);

	    }
	    model[flipIndex] = !model[flipIndex];
	}

	return null;
    }

    /**
     * 
     * @return a random assignment of true/false to the symbols in clauses
     */
    public static boolean[] getModel() {
	boolean[] model = new boolean[SentenceGenerator.N];

	for (int i = 0; i < model.length; i++) {
	    Random rand = new Random();
	    model[i] = rand.nextBoolean();
	}
	return model;
    }

    /**
     * Returns a random boolean value with probability p
     * of being true and probability 1-p of being false
     * p should be in the range 0.0 - 1.0
     */
    public static boolean randBoolean(double p) {
	return (Math.random() < p);
    }

    /**
     * does model satisfy a clause?
     * @param model
     * @param clause
     * @return 
     */
    public static boolean isModelSatisfiesClause(boolean[] model, Clause clause) {
	for (int i = 0; i < clause.symbols.size(); i++) {
	    boolean isTrue = false;
	    if (clause.symbols.get(i).isNeg) {
		isTrue = !model[clause.symbols.get(i).index];
	    } else {
		isTrue = model[clause.symbols.get(i).index];
	    }

	    if (isTrue) {
		return true;
	    }
	}
	return false;
    }

    /**
     * get randomly selelected symbol from clause
     * @param falseClause
     * @return 
     */
    private static int randomFlipIndex(Clause falseClause) {
	Random rand = new Random();
	int index = rand.nextInt(SentenceGenerator.K);
	return falseClause.symbols.get(index).index;
    }

    /**
     * maximizes the number of satisfied clauses
     * @param model
     * @param sentence
     * @return 
     */
    private static int maximizeSatisfiedClausesIndex(boolean[] model, Clause falseClause, Sentence sentence) {
	int max = 0;
	int index = 0;
	for (int i = 0; i < falseClause.symbols.size(); i++) {
	    boolean[] tmp = model.clone();
	    int count = 0;
	    tmp[falseClause.symbols.get(i).index] = !tmp[falseClause.symbols.get(i).index];

	    Iterator it = sentence.clauses.iterator();
	    while (it.hasNext()) {
		Clause clause = (Clause) it.next();
		if (isModelSatisfiesClause(model, clause)) {
		    count++;
		}
	    }
	    if (count > max) {
		index = falseClause.symbols.get(i).index;
		max = count;
	    }
	}
	return index;
    }
}
