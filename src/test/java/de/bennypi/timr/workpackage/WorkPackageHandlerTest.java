package de.bennypi.timr.workpackage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.UUID;

import org.junit.Test;

public class WorkPackageHandlerTest {

	WorkPackageHandler handler = WorkPackageHandler.getInstance();

	@Test
	public void testWorkPackage() {
		try {
			handler.stopWorkPackage();
			fail("There is no package to stop");
		} catch (RuntimeException e) {
			assertThat(e.getMessage(), is("No open workpackage"));
		}
		UUID id = handler.startWorkPackage();
		assertThat(id, is(notNullValue()));

		WorkPackage wp = handler.getWorkPackage(id);
		assertThat(wp, is(notNullValue()));
		assertThat(wp.getStartingTime(), is(notNullValue()));

		wp = handler.stopWorkPackage();
		assertThat(wp.getStartingTime(), is(notNullValue()));
		assertThat(wp.getEndingTime(), is(notNullValue()));

		UUID secondId = handler.startWorkPackage();
		assertThat(id, is(not(secondId)));

		UUID thirdId = handler.startWorkPackage();
		assertThat(thirdId, is(not(secondId)));

		assertThat(handler.size(), is(3));
		assertThat(handler.getUUIDs().size(), is(3));
	}

}
