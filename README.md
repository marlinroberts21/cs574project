# CS-574 Project - Spring 2020
### Team:
Kenny Ballou, Marlin Roberts


### Project Code:

**BRICS Java String Analyzer String Operations**  
  * github: 	https://github.com/rodneyxr/brics-jsa  
  * web: 		http://www.brics.dk/JSA/  
	
**BRICS Automata Code**  
  * github:		https://github.com/cs-au-dk/dk.brics.automaton  
  * web:		http://www.brics.dk/automaton/  
	
### Summary:

We will be examining the properties of string operations provided in the JSA package. The string operations model common Java String and StringBuilder operations. These operations work on strings modeled as automata, and rely upon the dk.brics.automata code. Operations manipulate the automata using set operations such as union and intersection, as well as directly altering states and transitions when set operations alone are insufficient.  

We will examine these aspects of the string operations and supporting automata code:  

***Operations accurately model the Java String or StringBuilder operations:***  

  * Operations that work with concrete strings can be checked by comparing results with those of the Java String or StringBuilder operations they model.  

  * Operations that work with symbolic strings or a combination of concrete and symbolic strings return a set of possible values. These can be checked against a set from an oracle created for the purpose of regression testing.  

  * Checking these properties will occur in Junit tests.  

***Operations return correct automata:***  

  * All operations are expected to return automata that have certain properties, such as being deterministic. These properties will be checked with assertions placed within the operation classes where possible.  
  
***Automata operation sequence***  

  * Various sequence properties have been identified in the automata code that will be examined with AspectJ.  
  
