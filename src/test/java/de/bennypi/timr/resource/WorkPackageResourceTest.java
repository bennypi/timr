package de.bennypi.timr.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import de.bennypi.timr.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class WorkPackageResourceTest {
	
	@Test
	public void dummy() {
		assert(true);
	}

	/*
	@Test
	public void testGetAll() {
		Response output = target("/workpackage/all").request().get();
		assertEquals("should return status 200", 200, output.getStatus());
		assertNotNull("Should return list", output.getEntity());
		// todo: Check output 
	}

	@Test
	public void testGetEntry() {
		Response output = target("/workpackage").request().get();
		// todo: Test the output
	}
	*/
}
