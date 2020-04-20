package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import dk.brics.automaton.Automaton;
import dk.brics.string.stringoperations.BinaryOperation;
import dk.brics.string.stringoperations.Contains;
import dk.brics.string.stringoperations.Insert;

public class Test_Contains {
	
	static Automaton a1,b1,c1,d1,e1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		a1 = Automaton.makeString("ABC");
		b1 = Automaton.makeString("B");

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void Test_Contains_True() {
		c1 = a1.clone();
		d1 = b1.clone();
		BinaryOperation containsOp  = new Contains();
		e1 = containsOp.op(a1,b1);
		assertTrue("ASSERT FAIL: -substringFail", (e1.getShortestExample(true).equals(""+(char)1)));		// Java String Operation Property
		assertTrue("ASSERT FAIL: -automatonMutated", a1.equals(c1));								// String Lib - Automaton Property
		//fail("Not yet implemented"); // TODO
	}
	
	@Test
	public final void Test_Contains_False() {
		c1 = a1.clone();
		d1 = b1.clone();
		BinaryOperation containsOp  = new Contains();
		e1 = containsOp.op(b1,a1);
		assertTrue("ASSERT FAIL: -substringFail", (e1.getShortestExample(true).equals(""+(char)0)));		// Java String Operation Property
		assertTrue("ASSERT FAIL: -automatonMutated", a1.equals(c1));								// String Lib - Automaton Property
		//fail("Not yet implemented"); // TODO
	}

}
