package com.dasanjos.java;

/**
 * <p>Java implementation of <a href="http://en.wikipedia.org/wiki/Zebra_Puzzle"> Zebra Puzzle</a> (also called Einstein's Puzzle)</p> 
 * 
 * <p><b>Input file (input.csv)</b><br />
 * <pre>
 * 5
 * SAME,nationality,English,color,Red
 * SAME,nationality,Spaniard,pet,Dog
 * SAME,drink,Coffee,color,Green
 * SAME,drink,Tea,nationality,Ukrainian
 * TO_THE_LEFT_OF,color,Ivory,color,Green
 * SAME,smoke,Old gold,pet,Snails
 * SAME,smoke,Kools,color,Yellow
 * SAME,drink,Milk,position,3
 * SAME,nationality,Norwegian,position,1
 * NEXT_TO,smoke,Chesterfields,pet,Fox
 * NEXT_TO,smoke,Kools,pet,Horse
 * SAME,smoke,Lucky strike,drink,Orange juice
 * SAME,smoke,Parliaments,nationality,Japanese
 * NEXT_TO,color,Blue,nationality,Norwegian
 * SAME,drink,Water
 * SAME,pet,Zebra
 * </pre></p>
 * 
 * <p><b>Example output file (output.xml)</b><br />
 * <pre>
 * &lt;solutions>
 *   &lt;solution>
 *     &lt;house position="1" color="Yellow" nationality="Norwegian" drink="Water"        smoke="Kools"         pet="Fox"/>
 *     &lt;house position="2" color="Blue"   nationality="Ukrainian" drink="Tea"          smoke="Chesterfields" pet="Horse"/>
 *     &lt;house position="3" color="Red"    nationality="English"   drink="Milk"         smoke="Old gold"      pet="Snails"/>
 *     &lt;house position="4" color="Ivory"  nationality="Spaniard"  drink="Orange juice" smoke="Lucky strike"  pet="Dog"/>
 *     &lt;house position="5" color="Green"  nationality="Japanese"  drink="Coffee"       smoke="Parliaments"   pet="Zebra"/>
 *   &lt;/solution>
 * &lt;/solutions>
 * </pre></p>
 */
public class ZebraPuzzle 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
