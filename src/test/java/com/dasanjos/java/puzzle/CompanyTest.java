package com.dasanjos.java.puzzle;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dasanjos.java.puzzle.Company.Employee;

public class CompanyTest {

	Employee bill = new Employee(1, "Bill");
	Employee dom = new Employee(2, "Dom");
	Employee samir = new Employee(3, "Samir");
	Employee michael = new Employee(4, "Michael");
	Employee bob = new Employee(5, "Bob");
	Employee peter = new Employee(6, "Peter");
	Employee porter = new Employee(7, "Porter");
	Employee milton = new Employee(8, "Milton");
	Employee nina = new Employee(9, "Nina");

	
	@Before
	public void setup(){
		bill.addReport(dom);
		bill.addReport(samir);
		bill.addReport(michael);

		dom.addReport(bob);
		dom.addReport(peter);
		dom.addReport(porter);
				
		peter.addReport(milton);
		peter.addReport(nina);
	}
	
	@Test
	public void testFirstLevel() {
		Assert.assertEquals(bill.getId(), Company.closestCommonManager(bill, dom, samir).getId());
	}

	@Test
	public void testSecondLevel() {
		Assert.assertEquals(dom.getId(), Company.closestCommonManager(bill, peter, porter).getId());
	}

	@Test
	public void testThirdLevel() {
		Assert.assertEquals(peter.getId(), Company.closestCommonManager(bill, milton, nina).getId());
	}

	@Test
	public void testCrossOneLevel() {
		Assert.assertEquals(dom.getId(), Company.closestCommonManager(bill, nina, porter).getId());
	}

	@Test
	public void testCrossTwoLevels() {
		Assert.assertEquals(bill.getId(), Company.closestCommonManager(bill, nina, samir).getId());
	}

	@Test
	public void testSameHierarchy() {
		Assert.assertEquals(peter.getId(), Company.closestCommonManager(bill, peter, nina).getId());
	}
}
