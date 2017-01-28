package de.bennypi.timr.Resource;

import java.util.Calendar;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.bennypi.timr.WorkPackage.WorkPackage;
import de.bennypi.timr.WorkPackage.WorkPackageHandler;

/**
 * Root resource (exposed at "workpackage" path)
 */
@Path("workpackage")
public class WorkPackageResource {

	private WorkPackageHandler list = WorkPackageHandler.getInstance();

	/**
	 * Return all workpackges
	 *
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAll() {
		return "Number of Entries: " + list.size();
	}

	/**
	 * Get a workpackage by its id
	 * 
	 * @param Id The package's UUID as a string
	 * 
	 * @return All available information for this workpackage
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getEntry(@PathParam("id") String id) {
		WorkPackage wp = list.getWorkPackage(UUID.fromString(id));
		return wp == null ? "UUID is unknown" : wp.toString();
	}

	/**
	 * Create a new workpackage with the current time as startingtime
	 * 
	 * @return The UUID of the workpackage
	 */
	//TODO: Change path to "start", rename function
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String addEntry() {
		WorkPackage wp = new WorkPackage.WorkPackageBuilder(Calendar.getInstance()).createWorkPackage();
		list.addWorkPackage(wp);
		return "UUID for Entry: " + wp.getId();
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String finishPackage() {
		//TODO: implement, add optional description
		return null;
	}
}
