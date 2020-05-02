package test;

import static org.junit.Assert.*;
import static test.FileOps.*;

import dk.brics.automaton.Automaton;
//import dk.brics.string.stringoperations.BinaryOperation;
//import dk.brics.string.stringoperations.Replace5;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class Test_Minus {

	private final static int MULT_LENGTH = 4;
	private final static int MIN_LENGTH = 4;
	private final static int MED_LENGTH = 100;
	private final static int MAX_LENGTH = 200;

	// test specific objects
	static Automaton a1,a2,a3,a4,a5,a6,b1,c1,c2,preMin,empty,emptyString,minAlpha,medAlpha,maxAlpha;
	
	static Automaton 	a_min_min_n,
						a_min_med_y,
						a_min_max_y,
						a_med_min_n,
						a_med_med_y,
						a_med_max_n,
						a_max_min_y,
						a_max_med_n,
						a_max_max_y;

  // assign parameters to public variables
  @Parameter(value = 0)
  public Automaton auto1;

  @Parameter(value = 1)
  public Automaton auto2;

  @Parameter(value = 2)
  public String testName;

  // common test settings
  static final boolean OUTPUT_AUTOMATA = true;
  static final boolean TEST_STRING_RESULTS = true;
  static final String testDir = "cmp" + File.separator;

  // parameter data source
  @Parameters(name = "minus_{index}")
  public static Collection<Object[]> parms() throws Exception {
    setUpBeforeClass();
    return Arrays.asList(
        new Object[][] {
          {a_min_min_n, a_min_med_y, "minus_0"},
          {a_min_max_y, a_med_min_n, "minus_1"},
          {a_med_med_y, a_med_max_n, "minus_2"}
        });
  }

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
		emptyString = Automaton.makeEmptyString();
		empty = Automaton.makeEmpty();
		
		minAlpha = Automaton.makeCharRange('A', 'C');
		minAlpha = minAlpha.union(Automaton.makeCharRange('a', 'c'));
		
		medAlpha = Automaton.makeCharRange('A', 'Z');
		medAlpha = medAlpha.union(Automaton.makeCharRange('a', 'Z'));
		
		maxAlpha = Automaton.makeAnyChar();
		
		a_min_min_n = minAlpha.repeat(MIN_LENGTH);
		a_min_med_y = medAlpha.repeat(MIN_LENGTH).union(emptyString);
		a_min_max_y = maxAlpha.repeat(MIN_LENGTH).union(emptyString);		
		a_med_min_n = minAlpha.repeat(MED_LENGTH);
		a_med_med_y = medAlpha.repeat(MED_LENGTH).union(emptyString);
		a_med_max_n = maxAlpha.repeat(MED_LENGTH);
		a_max_min_y = minAlpha.repeat(MAX_LENGTH).union(emptyString);
		a_max_med_n = medAlpha.repeat(MAX_LENGTH);
		a_max_max_y = maxAlpha.repeat(MAX_LENGTH).union(emptyString);
    // short, concrete
    a1 = Automaton.makeString("ABCD");
    a1.setInfo("a1");

    // short, symbolic
    a2 = Automaton.makeAnyChar();
    a2.setInfo("a2");
    a2 = a2.repeat(1, MULT_LENGTH);

    // medium, concrete
    a3 = a1.repeat(MULT_LENGTH, MULT_LENGTH);
    a3.setInfo("a3");

    // medium, symbolic
    a4 = Automaton.makeAnyChar();
    a4.setInfo("a4");
    a4 = a4.repeat(1, MULT_LENGTH ^ 2);

    // long, concrete
    a5 = a3.repeat(MULT_LENGTH, MULT_LENGTH);
    a5.setInfo("a5");

    // long, symbolic
    a6 = Automaton.makeAnyChar();
    a6.setInfo("a6");
    a6 = a6.repeat(1, MULT_LENGTH ^ 3);
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {}

  @Before
  public void setUp() throws Exception {
    setUpBeforeClass();
  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public final void test_Minus() throws IOException, ClassCastException, ClassNotFoundException {

    System.out.println("BEG:  " + testName);

    c1 = auto1.clone();
    c1.setInfo("c1");

    c2 = auto2.clone();
    c2.setInfo("c2");


    b1 = c1.minus(c2);
    b1.setInfo("b1");
    
    b1.minimize();

    assertTrue("ASSERT FAIL: -argument modified", auto1.equals(c1));
    assertTrue("ASSERT FAIL: -argument modified", auto2.equals(c2));
    assertTrue("ASSERT FAIL: -automaton not deterministic", b1.isDeterministic());

    if (OUTPUT_AUTOMATA) {
      storeAutomaton(b1, testDir + testName);
    }

    if (TEST_STRING_RESULTS) {
      assertTrue(
          "ASSERT FAIL: -string operation results incorrect",
          (b1.equals(loadAutomaton(testDir + testName))));
    }

    preMin = b1.clone();
    preMin.setInfo("pm");

    b1.minimize();
    assertTrue("ASSERT FAIL: -automaton not minimal", b1.equals(preMin));

    System.out.println("END:  " + testName);
  }
}
