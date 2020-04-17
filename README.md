# CS-574 Project - Spring 2020

### Team:

Kenny Ballou, Marlin Roberts


### Project Code:

**BRICS Java String Analyzer String Operations**

  * [JSA GitHub][jsa-github]
  * [JSA Project Homepage][jsa]

**BRICS Automata Code**

  * [DK Brics Automata GitHub][brics-automata-github]
  * [DK Brics Automata Project Homepage][brics-automata]

### Summary:

We will be examining the properties of string operations provided in the
[`JSA`][jsa] package.  The string operations model common Java `String` and
`StringBuilder` operations.  These operations work on strings modeled as
automata, and rely upon the `dk.brics.automata` code.  Operations manipulate
the automata using set operations such as _union_ and _intersection_, as well
as directly altering states and transitions when set operations alone are
insufficient.

We will examine these aspects of the string operations and supporting automata
code:

***Operations accurately model the Java String or StringBuilder operations:***

  * Operations that work with concrete strings can be checked by comparing
    results with those of the Java `String` or `StringBuilder` operations they
    model.

  * Operations that work with symbolic strings or a combination of concrete and
    symbolic strings return a set of possible values.  These can be checked
    against a set from an oracle created for the purpose of regression testing.

  * Checking these properties will occur in [`Junit`][junit] tests.

***Operations return correct automata:***

  * All operations are expected to return automata that have certain
  properties, such as being deterministic.  These properties will be checked
  with assertions placed within the operation classes where possible.

***Automata operation sequence***

  * Various sequence properties have been identified in the automata code that
    will be examined with [AspectJ][aspectj] and [JavaMOP][javamop].



[jsa]: http://www.brics.dk/JSA/
[jsa-github]: https://github.com/rodneyxr/brics-jsa
[brics-automata]: http://www.brics.dk/automaton/
[brics-automata-github]: https://github.com/cs-au-dk/dk.brics.automaton
[junit]: https://junit.org/
[aspectj]: https://www.eclipse.org/aspectj/
[javamop]: http://fsl.cs.illinois.edu/index.php/JavaMOP4
