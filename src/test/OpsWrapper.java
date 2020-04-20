package test;

import static org.junit.Assert.*;
import dk.brics.automaton.Automaton;
import dk.brics.string.stringoperations.*;

public class OpsWrapper {
	
	static Automaton a1,b1,c1;

	public static void main(String[] args) {


		System.out.println("String:");
		a1 = Automaton.makeString("ABC");
		for (String s : a1.getFiniteStrings()) {
			System.out.println(s);
			}
		
		System.out.println("\nwReplace1:");
		a1 = Automaton.makeString("ABC");
		a1 = OpsWrapper.wReplace1(a1, 'B', 'C');
		//a1 = tlc.op(a1);
		for (String s : a1.getFiniteStrings()) {
			System.out.println(s);
			}
		
		System.out.println("\nToLowerCase:");
		UnaryOperation tlc  = new ToLowerCase();
		//a1 = tlc.op(a1);
		for (String s : tlc.op(a1).getFiniteStrings()) {
			System.out.println(s);
			}
		
		System.out.println("\nReverse:");
		UnaryOperation rev  = new Reverse();
		//a1 = rev.op(a1);
		for (String s : rev.op(a1).getFiniteStrings()) {
			System.out.println(s);
			}
		
		System.out.println("\nSubstrings:");
		UnaryOperation sub  = new Substring();
		//a1 = sub.op(a1);
		for (String s : sub.op(a1).getFiniteStrings()) {
			System.out.println(s);
			}
		
		System.out.println("\nInsert:");
		b1 = Automaton.makeString("Z");
		BinaryOperation ins  = new Insert();
		//a1 = sub.op(a1);
		for (String s : ins.op(a1,b1).getFiniteStrings()) {
			System.out.println(s);
			}
		
		System.out.println("\nPrefix:");
		UnaryOperation pre  = new Prefix();
		//a1 = tlc.op(a1);
		for (String s : pre.op(a1).getFiniteStrings()) {
			System.out.println(s);
			}
		
		System.out.println("\nPostfix:");
		UnaryOperation post  = new Postfix();
		//a1 = tlc.op(a1);
		for (String s : post.op(a1).getFiniteStrings()) {
			System.out.println(s);
			}
		
		//wReplace1(a1,'A','Z');
		
	}

	
	static Automaton wCharAt1(Automaton a, int index) {
		//System.out.println("\nCharAt1:");
		UnaryOperation operation = new CharAt1(index);

		return operation.op(a);
	}

	static Automaton wCharAt2(Automaton a) {
		//System.out.println("\nCharAt2:");
		UnaryOperation operation = new CharAt2();

		return operation.op(a);
	}
	
	static Automaton wContains(Automaton a1, Automaton a2) {
		//System.out.println("\nContains:");
		BinaryOperation operation = new Contains();

		return operation.op(a1,a2);
	}
	
	static Automaton wDelete(Automaton a) {
		//System.out.println("\nDelete:");
		UnaryOperation operation = new Delete();

		return operation.op(a);
	}
	
	static Automaton wDeleteCharAt(Automaton a) {
		//System.out.println("\nDeleteCharAt:");
		UnaryOperation del = new DeleteCharAt();

		return del.op(a);
	}
	
	static Automaton wInsert(Automaton a1, Automaton a2) {
		//System.out.println("\nInsert:");
		BinaryOperation operation = new Insert();

		return operation.op(a1,a2);
	}
	
	static Automaton wPostfix(Automaton a) {
		//System.out.println("\nPostfix:");
		UnaryOperation operation = new Postfix();

		return operation.op(a);
	}
	
	static Automaton wPrefix(Automaton a) {
		//System.out.println("\nPrefix:");
		UnaryOperation operation = new Prefix();

		return operation.op(a);
	}
	
	static Automaton wReplace1(Automaton a, char t, char r) {
		//System.out.println("\nReplace(A,Z):");
		UnaryOperation operation  = new Replace1(t,r);
		
		return operation.op(a);
	}
	
	static Automaton wReplace2(Automaton a, char t) {
		//System.out.println("\nReplace(A,?):");
		UnaryOperation operation  = new Replace2(t);

		return operation.op(a);
	}
	
	static Automaton wReplace3(Automaton a, char t) {
		//System.out.println("\nReplace(?,A):");
		UnaryOperation operation  = new Replace3(t);

		return operation.op(a);
	}
	
	static Automaton wReplace4(Automaton a) {
		//System.out.println("\nReplace(?,?):");
		UnaryOperation operation  = new Replace4();

		return operation.op(a);
	}
	
	static Automaton wReplace5(Automaton a1, Automaton a2) {
		//System.out.println("\nReplace(int,int,String):");
		BinaryOperation operation  = new Replace5();

		return operation.op(a1,a2);
	}
	
	static Automaton wReplace6(Automaton a, String s1, String s2) {
		//System.out.println("\nReplace(String,String):");
		UnaryOperation operation  = new Replace6(s1,s2);

		return operation.op(a);
	}
	
//	static Automaton wPreciseDelete(Automaton a, int start, int end) {
//		//System.out.println("\nPreciseDelete(start,end):");
//		UnaryOperation operation  = new PreciseDelete(start,end);
//
//		return operation.op(a);
//	}
//	
//	static Automaton wPrecisePrefix(Automaton a, int end) {
//		//System.out.println("\nPrecisePrefix(end):");
//		UnaryOperation operation  = new PrecisePrefix(end);
//
//		return operation.op(a);
//	}
//	
//	static Automaton wPreciseSetCharAt(Automaton a1, Automaton a2, int offset) {
//		//System.out.println("\nPreciseSetCharAt(offset):");
//		BinaryOperation operation  = new PreciseSetCharAt(offset);
//
//		return operation.op(a1,a2);
//	}
//	
//	static Automaton wPreciseSetLength(Automaton a, int length) {
//		//System.out.println("\nPreciseSetLength(length):");
//		UnaryOperation operation  = new PreciseSetLength(length);
//
//		return operation.op(a);
//	}
//	
//	static Automaton wPreciseInsert(Automaton a1, Automaton a2, int offset) {
//		//System.out.println("\nPreciseInsert(offset,string):");
//		BinaryOperation operation  = new PreciseInsert(offset);
//
//		return operation.op(a1,a2);
//	}
//	
//	static Automaton wPreciseSubstring(Automaton a, int start, int end) {
//		
//		c1 = a.clone();
//		
//		//System.out.println("\nSubstring(start,end):");
//		UnaryOperation operation  = new PreciseSubstring(start,end);
//		b1 = operation.op(a);
//		
//		assertTrue("ASSERT FAIL: -automatonMutated", a.equals(c1));	
//		assertTrue("ASSERT FAIL: -returned non-deterministic", b1.isDeterministic());	
//		
//		return b1;
//	}
//	
//	static Automaton wPreciseSuffix(Automaton a, int start) {
//		//System.out.println("\nPrecisePrefix(start):");
//		UnaryOperation operation  = new PreciseSuffix(start);
//
//		return operation.op(a);
//	}
//	
//	static Automaton wPreciseTrim(Automaton a) {
//		//System.out.println("\nPreciseTrim:");
//		UnaryOperation operation  = new PreciseTrim();
//
//		return operation.op(a);
//	}
	
	static Automaton wReverse(Automaton a) {
		//System.out.println("\nReverse:");
		UnaryOperation operation  = new Reverse();

		return operation.op(a);
	}
	
	static Automaton wToLowerCase(Automaton a) {
		//System.out.println("\nToLowerCase:");
		UnaryOperation operation  = new ToLowerCase();

		return operation.op(a);
	}
	
	static Automaton wToUpperCase(Automaton a) {
		//System.out.println("\nToUpperCase:");
		UnaryOperation operation  = new ToUpperCase();

		return operation.op(a);
	}
	
	static Automaton wSubstring(Automaton a) {
		//System.out.println("\nSubstring:");
		UnaryOperation operation  = new Substring();

		return operation.op(a);
	}

	static Automaton wTrim(Automaton a) {
		//System.out.println("\nTrim:");
		UnaryOperation operation  = new Trim();

		return operation.op(a);
	}
	
}
