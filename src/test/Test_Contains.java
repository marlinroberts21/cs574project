package test;

import static org.junit.Assert.*;
import static test.FileOps.*;

import dk.brics.automaton.Automaton;
import dk.brics.string.stringoperations.BinaryOperation;
import dk.brics.string.stringoperations.Contains;
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
public class Test_Contains {
	static final String testDir = "cmp\\";

  private static final int MULT_LENGTH = 4;
  public static final char BINARY_TRUE = (char) 1;
  public static final char BINARY_FALSE = (char) 0;

  // test specific objects
  static Automaton a1, a1b, a2, a3, a4, a5, a6, b1, c1, c2, preMin;

  // assign parameters to public variables
  @Parameter(value = 0)
  public Automaton auto1;

  @Parameter(value = 1)
  public Automaton auto2;

  @Parameter(value = 2)
  public String testName;

  @Parameter(value = 3)
  public char RESULT;

  // common test settings
  static final boolean OUTPUT_AUTOMATA = true;
  static final boolean TEST_STRING_RESULTS = true;

  // parameter data source
  @Parameters(name = "contains_{index}")
  public static Collection<Object[]> parms() throws Exception {
    setUpBeforeClass();
    // test cases need work, symbolic containing concrete not working?
    return Arrays.asList(
        new Object[][] {
          {a1, a1b, "contains_0", BINARY_TRUE},
          {a1b, a1, "contains_1", BINARY_FALSE},
          {a2, a1b, "contains_2", BINARY_FALSE},
          {a3, a4, "contains_3", BINARY_FALSE},
          {a6, a5, "contains_4", BINARY_FALSE},
          {a5, a6, "contains_5", BINARY_FALSE}
        });
  }

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {

    // short, concrete
    a1 = Automaton.makeString("ABCD");
    a1.setInfo("a1");

    a1b = Automaton.makeString("AB");
    a1b.setInfo("a1b");

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
    a1 = null;
    // a2 = null;
    a3 = null;
    // a4 = null;
    a5 = null;
    // a6 = null;
    b1 = null;
    c1 = null;
    preMin = null;
  }

  @Test
  public final void test_Contains() throws IOException, ClassCastException, ClassNotFoundException {

    System.out.println("BEG:  " + testName);

    c1 = auto1.clone();
    c1.setInfo("c1");

    c2 = auto2.clone();
    c2.setInfo("c2");

    BinaryOperation operation = new Contains();
    b1 = operation.op(auto1, auto2);
    b1.setInfo("b1");

    assertTrue("ASSERT FAIL: -argument modified", auto1.equals(c1));
    assertTrue("ASSERT FAIL: -argument modified", auto2.equals(c2));
    assertTrue(
        "ASSERT FAIL: -contains operation failed", b1.getShortestExample(true).charAt(0) == RESULT);

    // System.out.println("string: " + b1.getShortestExample(true));

    if (OUTPUT_AUTOMATA) {
      storeAutomaton(b1, testDir + testName);
    }

    if (TEST_STRING_RESULTS) {
      assertTrue(
          "ASSERT FAIL: -string operation inconsistent with previous results",
          (b1.equals(loadAutomaton(testDir + testName))));
    }

    preMin = b1.clone();
    preMin.setInfo("pm");

    b1.minimize();
    assertTrue("ASSERT FAIL: -automaton not minimal", b1.equals(preMin));

    System.out.println("END:  " + testName);
  }
}
