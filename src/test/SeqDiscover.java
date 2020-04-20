package test;

import static org.junit.Assert.*;
import dk.brics.automaton.Automaton;
import dk.brics.string.stringoperations.*;

public class SeqDiscover {
	
	static Automaton a1,b1,c1;
		
	public static void main(String[] args) {
		
//		System.out.println("String:");
//		a1 = Automaton.makeString("ABC");
//		for (String s : a1.getFiniteStrings()) {
//			System.out.println(s);
//			}
		
		
		System.out.println("\nwReplace1:");
		a1 = Automaton.makeString("ABC");
		a1.setInfo("a1");
		a1 = OpsWrapper.wReplace1(a1, 'B', 'C');
		//a1 = tlc.op(a1);
//		for (String s : a1.getFiniteStrings()) {
//			System.out.println(s);
//			}
 /*
*/
		System.out.println("\nToLowerCase:");
		a1 = Automaton.makeString("ABC");
		a1.setInfo("a1");
		UnaryOperation tlc  = new ToLowerCase();
		a1 = tlc.op(a1);
//		for (String s : tlc.op(a1).getFiniteStrings()) {
//			System.out.println(s);
//			}
		
		System.out.println("\nReverse:");
		a1 = Automaton.makeString("ABC");
		a1.setInfo("a1");
		UnaryOperation rev  = new Reverse();
		a1 = rev.op(a1);
//		for (String s : rev.op(a1).getFiniteStrings()) {
//			System.out.println(s);
//			}
		
		System.out.println("\nSubstrings:");
		a1 = Automaton.makeString("ABC");
		a1.setInfo("a1");
		UnaryOperation sub  = new Substring();
		a1 = sub.op(a1);
//		for (String s : sub.op(a1).getFiniteStrings()) {
//			System.out.println(s);
//			}
		
		System.out.println("\nInsert:");
		a1 = Automaton.makeString("ABC");
		a1.setInfo("a1");
		b1 = Automaton.makeString("Z");
		b1.setInfo("b1");
		BinaryOperation ins  = new Insert();
		a1 = ins.op(a1,b1);
//		for (String s : ins.op(a1,b1).getFiniteStrings()) {
//			System.out.println(s);
//			}
		
		System.out.println("\nPrefix:");
		a1 = Automaton.makeString("ABC");
		a1.setInfo("a1");
		UnaryOperation pre  = new Prefix();
		a1 = pre.op(a1);
//		for (String s : pre.op(a1).getFiniteStrings()) {
//			System.out.println(s);
//			}
		
		System.out.println("\nPostfix:");
		a1 = Automaton.makeString("ABC");
		a1.setInfo("a1");
		UnaryOperation post  = new Postfix();
		a1 = post.op(a1);
//		for (String s : post.op(a1).getFiniteStrings()) {
//			System.out.println(s);
//			}
		
		System.out.println("\nReplace2(A,?):");
		a1 = Automaton.makeString("ABC");
		a1.setInfo("a1");
		UnaryOperation rep2  = new Replace2('A');
		a1 = rep2.op(a1);
				
		
		System.out.println("\nReplace3(?,A):");
		a1 = Automaton.makeString("ABC");
		a1.setInfo("a1");
		UnaryOperation rep3  = new Replace3('A');
		a1 = rep3.op(a1);
		
		System.out.println("\nReplace4(?,?):");
		a1 = Automaton.makeString("ABC");
		a1.setInfo("a1");
		UnaryOperation rep4  = new Replace4();
		a1 = rep4.op(a1);
		
		System.out.println("\nReplace5(int,int,String):");
		a1 = Automaton.makeString("ABC");
		a1.setInfo("a1");
		b1 = Automaton.makeString("ABC");
		b1.setInfo("b1");
		BinaryOperation rep5  = new Replace5();
		c1 = rep5.op(a1,b1);
		
		
		System.out.println("\nReplace6(String,String):");
		a1 = Automaton.makeString("ABC");
		a1.setInfo("a1");
		UnaryOperation rep6  = new Replace6("test","string");
		a1 = rep6.op(a1);
		
		System.out.println("\nCharAt1:");
		a1 = Automaton.makeString("ABC");
		a1.setInfo("a1");
		UnaryOperation ca1 = new CharAt1(2);
		a1 = ca1.op(a1);
		
		System.out.println("\nCharAt2:");
		a1 = Automaton.makeString("ABC");
		a1.setInfo("a1");
		UnaryOperation ca2 = new CharAt2();
		a1 = ca2.op(a1);
		//System.out.println("a1.hash: " + a1.hashCode());
		
		System.out.println("\nContains:");
		a1 = Automaton.makeString("ABC");
		a1.setInfo("a1");
		b1 = Automaton.makeString("ABC");
		b1.setInfo("b1");
		BinaryOperation cont = new Contains();
		c1 = cont.op(a1,b1);
		
		System.out.println("\nDelete:");
		a1 = Automaton.makeString("ABC");
		a1.setInfo("a1");
		UnaryOperation del = new Delete();
		a1 = del.op(a1);
		
		System.out.println("\nDeleteCharAt:");
		a1 = Automaton.makeString("ABC");
		a1.setInfo("a1");
		UnaryOperation delca = new DeleteCharAt();
		a1 = delca.op(a1);
		
		System.out.println("\nToUpperCase:");
		a1 = Automaton.makeString("abc");
		a1.setInfo("a1");
		UnaryOperation tuc  = new ToUpperCase();
		a1 = tuc.op(a1);
		
		System.out.println("\nTrim:");
		a1 = Automaton.makeString("   abc  ");
		a1.setInfo("a1");
		UnaryOperation trim  = new Trim();
		a1 = trim.op(a1);
		
	}

}
