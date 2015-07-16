/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl;

import java.util.Iterator;
import java.util.Vector;
import pl.SentenceGenerator.Sentence;

/**
 *
 * @author ubriela
 */
public class PL {

    static String filename1 = "./src/pl/exp1_pl.xls";
    static String filename2 = "./src/pl/exp1_ws.xls";
    static String content1 = new String();
    static String content2 = new String();
    static String content3 = new String();
    static String content4 = new String();
    static float p = 0.5F;
    static int max_flips = 250;
    static int sentence_number = 21;
    static int sentence_number3_a = 50;
    static int sentence_number3_b = 100;
    static int number_n = 4;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	// TODO code application logic here

	experiment1();
	experiment2();
	experiment3_a();    //	Test 50 random sentences
	experiment3_b();    //	Test 21 satisfiable random sentences
    }

    public static void experiment1() {
	content1 = "";
	content2 = "";
	for (int m = 4; m <= 32; m++) {
	    SentenceGenerator.M = m;
	    SentenceGenerator.K = 3;
	    SentenceGenerator.N = number_n;
	    SentenceGenerator sg = new SentenceGenerator();
	    Vector<Sentence> senteces = sg.generateRandomSentences(sentence_number);


	    int count1 = 0;
	    int count2 = 0;
	    Iterator it = senteces.iterator();
	    while (it.hasNext()) {
		Sentence sentence = (Sentence) it.next();

		/**
		 * PL-RESOLUTION
		 */
		boolean result1 = PLResolution.isSatisfiable(sentence);
		if (result1) {
		    count1++;
		}

		/**
		 * WALK-SAT
		 */
		boolean[] result2 = WalkSat.isSatisfiable(sentence, p, max_flips);
		if (result2 != null) {
		    count2++;
		}
	    }

	    String line1 = String.format("%.2g", (double) m / SentenceGenerator.N) + ";" + String.format("%.2g", (double) count1 / senteces.size()) + "\n";
	    content1 += line1;

	    String line2 = String.format("%.2g", (double) m / SentenceGenerator.N) + ";" + String.format("%.2g", (double) count2 / senteces.size()) + "\n";
	    content2 += line2;
	}

	FileTools.filename = filename1;
	FileTools.createFile();
	FileTools.writeToFile(content1);

	FileTools.filename = filename2;
	FileTools.createFile();
	FileTools.writeToFile(content2);

	System.out.println("Experiment 1 completed");
    }

    private static void experiment2() {

	for (int k = 2; k <= 4; k++) {
	    content1 = "";
	    content2 = "";

	    int upper = 0;
	    switch (k) {
		case 2:
		    upper = 24;
		    break;
		case 3:
		    upper = 28;
		    break;
		case 4:
		    upper = 16;
		    break;
	    }
	    for (int m = 4; m <= upper; m++) {
		SentenceGenerator sg = new SentenceGenerator();
		SentenceGenerator.N = number_n;
		SentenceGenerator.M = m;
		SentenceGenerator.K = k;
		Vector<Sentence> senteces = sg.generateRandomSentences(sentence_number);


		int count1 = 0;
		int count2 = 0;
		Iterator it = senteces.iterator();
		while (it.hasNext()) {

		    Sentence sentence = (Sentence) it.next();
		    /**
		     * PL-RESOLUTION
		     */
		    boolean result1 = PLResolution.isSatisfiable(sentence);
		    if (result1) {
			count1++;
		    }

		    /**
		     * WALK-SAT
		     */
		    boolean[] result2 = WalkSat.isSatisfiable(sentence, p, max_flips);
		    if (result2 != null) {
			count2++;

		    }

		}

		String line1 = String.format("%.2g", (double) m / SentenceGenerator.N) + ";" + String.format("%.2g", (double) count1 / senteces.size()) + "\n";
		content1 += line1;

		String line2 = String.format("%.2g", (double) m / SentenceGenerator.N) + ";" + String.format("%.2g", (double) count2 / senteces.size()) + "\n";
		content2 += line2;
	    }

	    FileTools.filename = "./src/pl/exp2_k" + k + "_pl.xls";
	    FileTools.createFile();
	    FileTools.writeToFile(content1);

	    FileTools.filename = "./src/pl/exp2_k" + k + "_ws.xls";
	    FileTools.createFile();
	    FileTools.writeToFile(content2);
	}

	System.out.println("Experiment 2 completed");
    }

    private static void experiment3_a() {
	content1 = "";
	content2 = "";
	content3 = "";
	content4 = "";
	for (int m = 4; m <= 28; m++) {
	    SentenceGenerator sg = new SentenceGenerator();
	    SentenceGenerator.M = m;
	    SentenceGenerator.K = 3;
	    SentenceGenerator.N = number_n;
	    Vector<Sentence> sentences = sg.generateRandomSentences(sentence_number3_a);
	    System.out.println(m + " " + sentences.size());

	    int total = 0;
	    double avg_ws = (double) 0.0;
	    double avg_pl = (double) 0.0;
	    double avg_ws_time = (double) 0.0;
	    double avg_pl_time = (double) 0.0;
	    Iterator it = sentences.iterator();
	    while (it.hasNext()) {
		total++;
		Sentence sentence = (Sentence) it.next();
		long start = System.nanoTime();
		boolean[] model = WalkSat.isSatisfiable(sentence, p, max_flips);
		long end = System.nanoTime();
		long microseconds = (end - start) / 1000;
		
		avg_ws = (avg_ws * (total - 1) + WalkSat.count) / total;
		avg_ws_time = (avg_ws_time * (total - 1) + microseconds) / total;

		start = System.nanoTime();
		PLResolution.isSatisfiable(sentence);
		end = System.nanoTime();
		microseconds = (end - start) / 1000;
		avg_pl = (avg_pl * (total - 1) + PLResolution.count) / total;
		avg_pl_time = (avg_pl_time * (total - 1) + microseconds) / total;

	    }

	    String line1 = String.format("%.2g", (double) m / SentenceGenerator.N) + ";" + String.format("%.2g", avg_pl) + "\n";
	    content1 += line1;
	    String line2 = String.format("%.2g", (double) m / SentenceGenerator.N) + ";" + String.format("%.3g", avg_ws) + "\n";
	    content2 += line2;
	    String line3 = String.format("%.2g", (double) m / SentenceGenerator.N) + ";" + String.valueOf(avg_pl_time) + "\n";
	    content3 += line3;
	    String line4 = String.format("%.2g", (double) m / SentenceGenerator.N) + ";" + String.valueOf(avg_ws_time) + "\n";
	    content4 += line4;
	}

	FileTools.filename = "./src/pl/exp3_pl.xls";
	FileTools.createFile();
	FileTools.writeToFile(content1);

	FileTools.filename = "./src/pl/exp3_ws.xls";
	FileTools.createFile();
	FileTools.writeToFile(content2);

	FileTools.filename = "./src/pl/exp3_pl_time.xls";
	FileTools.createFile();
	FileTools.writeToFile(content3);

	FileTools.filename = "./src/pl/exp3_ws_time.xls";
	FileTools.createFile();
	FileTools.writeToFile(content4);

    }
    //  Analysis 21 sentences

    private static void experiment3_b() {
	content1 = "";
	content2 = "";
	content3 = "";
	content4 = "";
	for (int m = 4; m <= 32; m++) {
	    Vector<Sentence> sentence_21 = new Vector<Sentence>();

	    int total = 0;
	    double avg_pl = (double) 0.0;
	    double avg_ws = (double) 0.0;
	    double avg_ws_time = (double) 0.0;
	    double avg_pl_time = (double) 0.0;
	    // generate 21 sentences

	    int loop = 0;
	    while (true) {
		loop++;
		SentenceGenerator sg = new SentenceGenerator();
		SentenceGenerator.M = m;
		SentenceGenerator.K = 3;
		SentenceGenerator.N = number_n;

		Vector<Sentence> sentences = sg.generateRandomSentences(sentence_number3_b);

		Iterator l = sentences.iterator();

		while (l.hasNext()) {
		    loop++;
		    Sentence sentence = (Sentence) l.next();
		    //  if the sentence is not in sentence_21
		    boolean isExist = false;
		    Iterator it = sentence_21.iterator();
		    while (it.hasNext()) {
			Sentence s = (Sentence) it.next();
			if (PLResolution.isContain(s.clauses, sentence.clauses)) {
			    isExist = true;
			    break;
			}
		    }
		    if (isExist) {
			continue;
		    }

		    //  if the sentence is satisfiable in WS
		    long start = System.nanoTime();
		    boolean[] model = WalkSat.isSatisfiable(sentence, p, max_flips);
		    long end = System.nanoTime();
		    long microseconds = (end - start) / 1000;

		    if (model != null) {
			total++;
			avg_ws_time = (avg_ws_time * (total - 1) + microseconds) / total;
			sentence_21.add(sentence);
			start = System.nanoTime();
			PLResolution.isSatisfiable(sentence);
			end = System.nanoTime();
			microseconds = (end - start) / 1000;
			avg_pl = (avg_pl * (total - 1) + PLResolution.count) / total;
			avg_ws = (avg_ws * (total - 1) + WalkSat.count) / total;
			avg_pl_time = (avg_pl_time * (total - 1) + microseconds) / total;

			if (total == sentence_number3_b) {
			    break;
			}
		    }
		}

		if (total == sentence_number3_b || sentences.size() < sentence_number3_b || loop > 1000) {
		    System.out.println(m + " " + sentence_21.size());
		    break;
		}
	    }


	    String line1 = String.format("%.2g", (double) m / SentenceGenerator.N) + ";" + String.format("%.2g", avg_pl) + "\n";
	    content1 += line1;
	    String line2 = String.format("%.2g", (double) m / SentenceGenerator.N) + ";" + String.format("%.2g", avg_ws) + "\n";
	    content2 += line2;
	    String line3 = String.format("%.2g", (double) m / SentenceGenerator.N) + ";" + String.valueOf(avg_pl_time) + "\n";
	    content3 += line3;
	    String line4 = String.format("%.2g", (double) m / SentenceGenerator.N) + ";" + String.valueOf(avg_ws_time) + "\n";
	    content4 += line4;
	}

	FileTools.filename = "./src/pl/exp3_satisfied_pl.xls";
	FileTools.createFile();
	FileTools.writeToFile(content1);

	FileTools.filename = "./src/pl/exp3_satisfied_ws.xls";
	FileTools.createFile();
	FileTools.writeToFile(content2);

	FileTools.filename = "./src/pl/exp3_satisfied_pl_time.xls";
	FileTools.createFile();
	FileTools.writeToFile(content3);

	FileTools.filename = "./src/pl/exp3_satisfied_ws_time.xls";
	FileTools.createFile();
	FileTools.writeToFile(content4);

	System.out.println("Experiment 3 completed");
    }
}