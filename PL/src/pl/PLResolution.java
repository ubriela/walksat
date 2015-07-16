/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl;

import java.util.Iterator;
import java.util.Vector;
import pl.SentenceGenerator.Clause;
import pl.SentenceGenerator.Sentence;

/**
 *
 * @author ubriela
 */
public class PLResolution {

    static int count = 0;	//  runtime test

    /**
     * The input sentence is satisfiable or not
     * @param sentence
     * @return 
     */
    static boolean isSatisfiable(Sentence sentence) {
	boolean isSatisfiable = false;
	Vector<Clause> clauses = sentence.copyClauses();
	Vector<Clause> news = new Vector<Clause>();

	count = 0;
	while (true) {
	    count++;
	    //	For each pair of clauses Cj, Cj in clauses
	    for (int i = 0; i < clauses.size() - 1; i++) {
		for (int j = i + 1; j < clauses.size(); j++) {
		    Vector<Clause> resolvents = PLResolve(clauses.get(i), clauses.get(j));

		    if (isContainEmpty(resolvents)) {
			return false;
		    }
		    news.addAll(resolvents);
		}
	    }

	    if (isContain(clauses, news)) {
		return true;
	    }
	    addNewsToClauses(clauses, news);
	}
    }

    /**
     * return all posible clauses obtained by resolving its two inputs
     * @param ci
     * @param cj
     * @return 
     */
    public static Vector<Clause> PLResolve(Clause ci, Clause cj) {
	SentenceGenerator sg = new SentenceGenerator();
	Vector<Clause> resolvents = new Vector<Clause>();
	for (int i = 0; i < ci.symbols.size(); i++) {
	    for (int j = 0; j < cj.symbols.size(); j++) {
		int index_i = ci.symbols.get(i).index;
		int index_j = cj.symbols.get(j).index;
		if (index_i == index_j
			&& ci.symbols.get(i).isNeg != cj.symbols.get(j).isNeg) {
		    Clause clause = sg.new Clause();
		    for (int k = 0; k < ci.symbols.size(); k++) {
			if (ci.symbols.get(k).index != index_i) {
			    clause.symbols.add(sg.new Symbol(ci.symbols.get(k).index, ci.symbols.get(k).isNeg));
			}
		    }
		    for (int k = 0; k < cj.symbols.size(); k++) {
			if (cj.symbols.get(k).index != index_j) {
			    clause.symbols.add(sg.new Symbol(cj.symbols.get(k).index, cj.symbols.get(k).isNeg));
			}
		    }
		    clause = clause.factoring();
		    //  any clause in which two complementary literals appear can be discarded
		    if (clause != null) {
			resolvents.add(clause);
		    }
		}
	    }
	}
	return resolvents;
    }

    /**
     * if resolvents contains the empty clause then return true
     * @param resolvents
     * @return 
     */
    private static boolean isContainEmpty(Vector<Clause> resolvents) {
	Iterator it = resolvents.iterator();
	while (it.hasNext()) {
	    Clause clause = (Clause) it.next();
	    if (clause.symbols.size() == 0) {
		return true;
	    }
	}
	return false;
    }

    /**
     * check whether a set of clauses contains another set
     * @param clauses
     * @param news
     * @return 
     */
    public static boolean isContain(Vector<Clause> clauses, Vector<Clause> news) {
	boolean isContain = true;
	for (int i = 0; i < news.size(); i++) {
	    Clause clause = news.get(i);
	    boolean tmp = false;
	    for (int j = 0; j < clauses.size(); j++) {
		if (clause.equal(clauses.get(j))) {
		    tmp = true;
		    break;
		}
	    }
	    if (tmp == false) {
		return false;
	    }
	}
	return isContain;
    }

    /**
     * append a set of clauses to another set
     * @param clauses
     * @param news 
     */
    private static void addNewsToClauses(Vector<Clause> clauses, Vector<Clause> news) {
	for (int i = 0; i < news.size(); i++) {
	    Clause clause = news.get(i);
	    boolean isContain = false;
	    for (int j = 0; j < clauses.size(); j++) {
		if (clause.equal(clauses.get(j))) {
		    isContain = true;
		    break;
		}
	    }
	    if (!isContain) {
		clauses.add(clause);
	    }
	}
    }
}
