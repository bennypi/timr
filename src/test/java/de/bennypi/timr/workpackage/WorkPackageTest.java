package de.bennypi.timr.workpackage;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Calendar;

import org.junit.Test;

public class WorkPackageTest {

	@Test
	public void test() {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		String des = "description";
		WorkPackage wp = new WorkPackage.WorkPackageBuilder(cal1).endingTime(cal2).description(des).createWorkPackage();
		assertThat(wp.getStartingTime(), is(cal1));
		assertThat(wp.getEndingTime(), is(cal2));
		assertThat(wp.getDescription(), is(des));
	}

}
