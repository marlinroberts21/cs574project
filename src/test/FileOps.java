package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import dk.brics.automaton.Automaton;

public class FileOps {

	public static void storeAutomaton (Automaton a, String fileName) throws IOException {
		FileOutputStream fileStream = new FileOutputStream (fileName);
		
		a.store(fileStream);
				
		fileStream.close();
		fileStream = null;
	}
	
	public static Automaton loadAutomaton (String fileName) throws IOException, ClassCastException, ClassNotFoundException {
		FileInputStream fileIn = new FileInputStream (fileName);
		
		Automaton returnAutomation = Automaton.load(fileIn);
		
		fileIn.close();
		fileIn = null;
		return returnAutomation;
		
	}
	
}
