package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import dk.brics.automaton.Automaton;



public class SerializeTest {
	
	
	static Automaton a1,a2;

	public static void main(String[] args) throws IOException, ClassCastException, ClassNotFoundException {
		
//		a1 = Automaton.makeString("ABC");
//		
//		FileOutputStream fileStream = new FileOutputStream ("a1Test.aut");
//		
//		a1.store(fileStream);
//				
//		fileStream.close();
		
		FileInputStream fileIn = new FileInputStream ("cmp\\replace1_4");
		
		a2 = Automaton.load(fileIn);
		
		fileIn.close();
		
		//System.out.println("a1 == a2 : " + a1.equals(a2));
		System.out.println(a2.getShortestExample(true));

	}

}
