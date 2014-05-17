package com.dasanjos.java.util;

import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public abstract class TimeTracker {
	@Rule
	public TestRule watchman = new TestWatcher() {
		long startTime = 0;
		long endTime = 0;

		@Override
		protected void starting(Description description) {
			super.starting(description);
			startTime = System.currentTimeMillis();
		}

		@Override
		protected void finished(Description description) {
			super.finished(description);
			endTime = System.currentTimeMillis();
			System.out.printf("%s time: %dns%n", description.getMethodName(),
					endTime - startTime);

		}
	};
}
