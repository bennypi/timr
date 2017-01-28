package de.bennypi.timr;

import java.util.Calendar;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("workpackage")
public class WorkPackageResource {
	
	private WorkPackageList list = WorkPackageList.getInstance();
	
	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Number of Entries: " + list.size();
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String addEntry() {
		WorkPackage wp = new WorkPackage.WorkPackageBuilder(Calendar.getInstance()).createWorkPackage();
		list.addWorkPackage(wp);
		return "UUID for Entry: " + wp.getId();
	}
}
