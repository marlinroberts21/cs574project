package test;

import static org.junit.Assert.*;
//import static test.FileOps.*;

import dk.brics.automaton.Automaton;
//import dk.brics.string.stringoperations.Replace1;
//import dk.brics.string.stringoperations.UnaryOperation;
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
public class Test_Minimize {
	
//	private final static int MULT_LENGTH = 4;
	private final static int MIN_LENGTH = 4;
	private final static int MED_LENGTH = 100;
	private final static int MAX_LENGTH = 200;

	// test specific objects
	static Automaton 	a1,a2,a3,a4,a5,a6,
						b1,c1,d1,a1m,b1m,c1m,d1m,
						preMin,empty,emptyString,
						minAlpha,medAlpha,maxAlpha;
	
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
  public String testName;

  // common test settings
  static final boolean OUTPUT_AUTOMATA = true;
  static final boolean TEST_STRING_RESULTS = true;
  static final String testDir = "cmp" + File.separator;

  // parameter data source
  @Parameters(name = "minimize_{index}")
  public static Collection<Object[]> parms() throws Exception {
    setUpBeforeClass();
	return Arrays.asList(new Object[][]{	
		{a_min_min_n,"minimize_0"},
		{a_min_med_y,"minimize_1"},
		{a_min_max_y,"minimize_2"},
		{a_med_min_n,"minimize_3"},
		{a_med_med_y,"minimize_4"},
		{a_med_max_n,"minimize_5"},
		{a_max_min_y,"minimize_6"},
		{a_max_med_n,"minimize_7"},
		{a_max_max_y,"minimize_8"}});
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

  @SuppressWarnings("static-access")
@Test
  public final void test_Minimize() throws IOException, ClassCastException, ClassNotFoundException {

    System.out.println("BEG:  " + testName);

    a1 = auto1.clone();
    a1.setInfo("a1");
    
    b1 = auto1.clone();
    b1.setInfo("b1");
    
    c1 = auto1.clone();
    c1.setInfo("c1");
    
    d1 = auto1.clone();
    d1.setInfo("d1");
    
    d1.minimize();
    d1m = d1.clone();
    d1.minimize();
    assertTrue("ASSERT FAIL: - Hopcroft minimization fail...", d1.equals(d1m));
    
    a1.setMinimization(0);
    a1.minimize();
    a1m = a1.clone();
    a1.minimize();
    assertTrue("ASSERT FAIL: - Huffman minimization fail...", a1.equals(a1m));
        
    b1.setMinimization(1);
    b1.minimize();
    b1m = b1.clone();
    b1.minimize();
    assertTrue("ASSERT FAIL: - BRZOZOWSKI minimization fail...", b1.equals(b1m));
    
    c1.setMinimization(3);
    c1.minimize();
    c1m = c1.clone();
    c1.minimize();
    assertTrue("ASSERT FAIL: - VALMARI minimization fail...", c1.equals(c1m));
    
    assertTrue("ASSERT FAIL: - d1 != a1", d1.equals(a1));
    assertTrue("ASSERT FAIL: - d1 != b1", d1.equals(b1));
    assertTrue("ASSERT FAIL: - d1 != c1", d1.equals(c1));
    

    System.out.println("END:  " + testName);
  }
}
