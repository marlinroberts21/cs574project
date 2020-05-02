package test;

import static org.junit.Assert.*;
import static test.FileOps.*;

import dk.brics.automaton.Automaton;
import dk.brics.string.stringoperations.Replace2;
import dk.brics.string.stringoperations.UnaryOperation;
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
public class Test_Replace2 {
	
	private final static int MULT_LENGTH = 4;
	private final static int MIN_LENGTH = 4;
	private final static int MED_LENGTH = 100;
	private final static int MAX_LENGTH = 200;

	// test specific objects
	static Automaton a1,a2,a3,a4,a5,a6,b1,c1,preMin,empty,emptyString,minAlpha,medAlpha,maxAlpha;
	
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
  public char char1;

  @Parameter(value = 1)
  public Automaton auto1;

  @Parameter(value = 2)
  public String testName;

  // common test settings
  static final boolean OUTPUT_AUTOMATA = true;
  static final boolean TEST_STRING_RESULTS = true;
  static final String testDir = "cmp" + File.separator;

  // parameter data source
  @Parameters(name = "replace2_{index}")
  public static Collection<Object[]> parms() throws Exception {
    setUpBeforeClass();
	return Arrays.asList(new Object[][]{	
		{'A',a_min_min_n,"replace2_0"},
		{'Z',a_min_med_y,"replace2_1"},
		{'A',a_min_max_y,"replace2_2"},
		{'Z',a_med_min_n,"replace2_3"},
		{'A',a_med_med_y,"replace2_4"},
		{'Z',a_med_max_n,"replace2_5"},
		{'Z',a_max_min_y,"replace2_6"},
		{'Z',a_max_med_n,"replace2_7"},
		{'Z',a_max_max_y,"replace2_8"}});
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
		
		a1 = Automaton.makeString("ABCD");
		a1.setInfo("a1");
		
		a3 = a1.repeat(MULT_LENGTH, MULT_LENGTH);
		a3.setInfo("a3");
		
		a5 = a3.repeat(MULT_LENGTH, MULT_LENGTH);
		a5.setInfo("a5");
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
  public final void test_Replace2() throws IOException, ClassCastException, ClassNotFoundException {

    System.out.println("BEG:  " + testName);

    c1 = auto1.clone();
    c1.setInfo("c1");

    UnaryOperation operation = new Replace2(char1);
    b1 = operation.op(auto1);
    b1.setInfo("b1");

    assertTrue("ASSERT FAIL: -argument modified", auto1.equals(c1));
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
