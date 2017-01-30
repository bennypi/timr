package de.bennypi.timr.Resource;

import java.util.Calendar;
import java.util.UUID;

import javax.ws.rs.Consumes;
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

	private WorkPackageHandler handler = WorkPackageHandler.getInstance();

	/**
	 * Return all workpackges
	 *
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAll() {
		return "Number of Entries: " + handler.size();
	}

	/**
	 * Get a workpackage by its id
	 * 
	 * @param Id
	 *            The package's UUID as a string
	 * 
	 * @return All available information for this workpackage
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getEntry(@PathParam("id") String id) {
		WorkPackage wp = handler.getWorkPackage(UUID.fromString(id));
		return wp == null ? "UUID is unknown" : wp.toString();
	}

	/**
	 * Get a workpackage by its id
	 * 
	 * @param Id
	 *            The package's UUID as a string
	 * 
	 * @return All available information for this workpackage
	 */
	@GET
	@Path("{id}/json")
	@Produces(MediaType.APPLICATION_JSON)
	public WorkPackage getWorkPackage(@PathParam("id") String id) {
		WorkPackage wp = handler.getWorkPackage(UUID.fromString(id));
		if (wp == null) {
			throw new NotFoundException(new JsonError("error", "id unkown: " + id));
		} else {
			return wp;
		}
	}

	/**
	 * Create a new workpackage with the current time as startingtime
	 * 
	 * @return The UUID of the workpackage
	 */
	@POST
	@Path("/start")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UUID startPackage() {
		return handler.startWorkPackage();
	}

	@POST
	@Path("/stop")
	@Produces(MediaType.TEXT_PLAIN)
	public String finishPackage() {
		WorkPackage wp = handler.stopWorkPackage();
		return wp.toString();
	}
}
