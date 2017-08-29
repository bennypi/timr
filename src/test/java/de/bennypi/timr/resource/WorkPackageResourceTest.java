package de.bennypi.timr.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

public class WorkPackageResourceTest extends JerseyTest {

	@Override
	public Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(WorkPackageResource.class);
	}

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
}
