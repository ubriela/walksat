/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ubriela
 */
public class FileTools {

    static String filename = "";
    int N = 0;

    public static void writeToFile(String line) {
	try {
	    FileWriter fstream = new FileWriter(filename, true);
	    BufferedWriter out = new BufferedWriter(fstream);
	    out.write(line);
	    out.close();
	} catch (IOException ex) {
	    Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public static void createFile() {
	try {
	    FileWriter fstream = new FileWriter(filename);
	    BufferedWriter out = new BufferedWriter(fstream);
	    out.write("x;y\n");
	    out.close();
	} catch (IOException ex) {
	    Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
}
