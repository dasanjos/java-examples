java-examples
=============

Java implementation of several puzzles and programming test tasks (with JUnit tests). 

List of Examples:
-----------------

* Company - Who's Our Boss (Amazon 2013)
* Decathlon - 
* Look Ahead Iterator - 
* Fibbonaci (Numbers, Sum, Average) - http://en.wikipedia.org/wiki/Fibonacci_number 
* FizzBuzz - http://en.wikipedia.org/wiki/Bizz_buzz
* Zebra Puzzle - http://en.wikipedia.org/wiki/Zebra_Puzzle
* RestClient - Uploads CV via POST to REST Service - http://cvu.zanox.com/

TODO: 
-----
* Fix Average Fibbonaci (test not passing)
* Merge Generic and Optimized Zebra Puzzle Algorithms
* move input.csv and template.xsl from resources/ to resources/zebraPuzzle
* Read: http://java.sun.com/developer/technicalArticles/Interviews/community/pepperdine_qa.html


### Amazon 2013 Test Task - Who's Our Boss

Initech is a company which has CEO Bill and a hierarchy of employees. 
Employees can have a list of other employees reporting to them, which can themselves have reports, and so on. 
An employee with at least one report is called a manager.  

Please implement the closestCommonManager method to find the closest manager (i.e. farthest from the CEO) to two employees. 
You may assume that all employees eventually report up to the CEO.   

**Sample Data:**
* CEO Bill has 3 employees reporting to him: {Dom, Samir, Michael}  
* Dom has three reports { Peter, Bob, Porter}  
* Samir has no reports {}  
* Michael has no reports {}  
* Peter has 2 reports {Milton, Nina}  
* Bob has no reports {}  
* Porter has no reports {}  
* Milton has no reports {}  
* Nina has no reports {}  

**Sample calls:**
* closestCommonManager(Milton, Nina) = Peter  
* closestCommonManager(Nina, Porter) = Dom  
* closestCommonManager(Nina, Samir) = Bill  
* closestCommonManager(Peter, Nina) = Peter