package test;

import static org.junit.Assert.*;
import static test.FileOps.*;

import dk.brics.automaton.Automaton;
import dk.brics.string.stringoperations.CharAt1;
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
public class Test_CharAt1 {

  private static final int MULT_LENGTH = 4;

  // test specific objects
  static Automaton a1, a2, a3, a4, a5, a6, b1, c1, preMin;

  // assign parameters to public variables
  @Parameter(value = 0)
  public int index1;

  @Parameter(value = 1)
  public Automaton auto1;

  @Parameter(value = 2)
  public String testName;

  // common test settings
  static final boolean OUTPUT_AUTOMATA = true;
  static final boolean TEST_STRING_RESULTS = true;
  static final String testDir = "cmp" + File.separator;

  // parameter data source
  @Parameters(name = "charat1_{index}")
  public static Collection<Object[]> parms() throws Exception {
    setUpBeforeClass();
    return Arrays.asList(
        new Object[][] {
          {2, a1, "charat1_0"},
          {2, a2, "charat1_1"},
          {8, a3, "charat1_2"},
          {8, a4, "charat1_3"},
          {16, a5, "charat1_4"},
          {16, a6, "charat1_5"}
        });
  }

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {

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
    // MODIFIED TO AVOID STACK OVERFLOW...
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
  public final void test_CharAt1() throws IOException, ClassCastException, ClassNotFoundException {

    System.out.println("BEG:  " + testName);

    c1 = auto1.clone();
    c1.setInfo("c1");

    UnaryOperation operation = new CharAt1(index1);
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
