package com.dasanjos.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Company {

	// IMPORTANT: DO NOT MODIFY THIS CLASS
	public static class Employee {

		private final int id;
		private final String name;
		private List<Employee> reports;

		public Employee(int id, String name) {
			this.id = id;
			this.name = name;
			this.reports = new ArrayList<Employee>();
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public List<Employee> getReports() {
			return reports;
		}

		public void addReport(Employee employee) {
			reports.add(employee);
		}
	}

	// Auxiliary class to create a tree with links to parent node
	public static class EmployeeTreeNode {

		private Employee self;
		private EmployeeTreeNode parent;
		private int level;

		public EmployeeTreeNode(Employee self, EmployeeTreeNode parent,
				int level) {
			this.self = self;
			this.parent = parent;
			this.level = level;
		}

		public Employee getSelf() {
			return self;
		}

		public EmployeeTreeNode getParent() {
			return parent;
		}

		public int getLevel() {
			return level;
		}
	}

	/**
	 * closestCommonManager - method to find the closest manager (i.e. farthest from the CEO) to two employees
	 * 
	 * Solution complexity analysis: Runtime complexity O(N+H) and Spatial complexity O(2N)
	 * 
	 * Read the attached PDF for more explanation about the problem Note: Don't modify the signature of this function
	 * 
	 * @param ceo
	 * @param firstEmployee
	 * @param secondEmployee
	 * @return common manager for both employees that is closest to them.
	 */
	public static Employee closestCommonManager(Employee ceo, Employee firstEmployee, Employee secondEmployee) {
		EmployeeTreeNode firstNode = null;
		EmployeeTreeNode secondNode = null;

		int level = 0;
		Stack<EmployeeTreeNode> stack = new Stack<EmployeeTreeNode>();
		stack.add(new EmployeeTreeNode(ceo, null, level));
		do {
			EmployeeTreeNode parent = stack.pop();
			level++;
			for (Employee employee : parent.getSelf().getReports()) {
				EmployeeTreeNode etn = new EmployeeTreeNode(employee, parent, level);
				stack.add(etn);

				if (etn.getSelf().getId() == firstEmployee.getId()) {
					firstNode = etn;
				} else if (etn.getSelf().getId() == secondEmployee.getId()) {
					secondNode = etn;
				}
			}
		} while (!stack.isEmpty());

		return searchCommonParent(firstNode, secondNode);
	}

	/**
	 * Search for common parent between two tree nodes (assuming it exists)
	 * 
	 * @param firstNode
	 * @param secondNode
	 * @return Employee the common parent between firstNode and secondNode
	 */
	private static Employee searchCommonParent(EmployeeTreeNode firstNode, EmployeeTreeNode secondNode) {
		if (firstNode == null || secondNode == null) {
			throw new RuntimeException("Could not find common parent");
		}

		//Corner case - if one employee is the boss of the other
		if (firstNode.getSelf().getId() == secondNode.getParent().getSelf().getId()) {
			return firstNode.getSelf();
		} else if (firstNode.getParent().getSelf().getId() == secondNode.getSelf().getId()) {
			return secondNode.getSelf();
		}

		//Travel upwards the tree by level searching for common parent
		EmployeeTreeNode firstParent = firstNode.getParent();
		EmployeeTreeNode secondParent = secondNode.getParent();
		while (firstParent.getSelf().getId() != secondParent.getSelf().getId()) {
			if (firstParent.getLevel() > secondParent.getLevel()) {
				firstParent = firstParent.getParent();
			} else {
				secondParent = secondParent.getParent();
			}
		}

		return firstParent.getSelf();
	}
};
