package dk.brics.string.tests;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.BasicAutomata;
import dk.brics.string.stringoperations.Reverse;
import org.junit.Assert;
import org.junit.Test;

public class ReverseOperationTests {

  @Test
  public void test_reverse_string_operation_behaves() {
    Automaton a = BasicAutomata.makeString("reverse");
    Reverse r = new Reverse();
    Automaton b = r.op(a);
    Assert.assertNotEquals("Automaton a == b", a, b);
    Assert.assertEquals(
        b.getShortestExample(true), new StringBuilder("reverse").reverse().toString());
  }
}
