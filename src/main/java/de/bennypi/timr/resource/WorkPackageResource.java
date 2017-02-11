package de.bennypi.timr.resource;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.bennypi.timr.workpackage.WorkPackage;
import de.bennypi.timr.workpackage.WorkPackageHandler;

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
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		return Response.status(Response.Status.OK).entity(handler.getUUIDs()).build();
	}

	@GET
	@Path("current")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCurrent() {
		WorkPackage wp = handler.getCurrent();
		if (wp == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("No open workpackage").build();
		} else {
			return Response.status(Response.Status.OK).entity(wp).build();
		}
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
	@Produces(MediaType.APPLICATION_JSON)
	public Response getWorkPackage(@PathParam("id") String id) {
		WorkPackage wp = handler.getWorkPackage(UUID.fromString(id));
		if (wp == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Entry not found for UUID: " + id).build();
		} else {
			return Response.status(Response.Status.OK).entity(wp.toString()).build();
		}
	}

	/**
	 * Create a new workpackage with the current time as startingtime
	 *
	 * @return The UUID of the workpackage
	 */
	@GET
	@Path("start")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response startPackage() {
		return Response.status(Response.Status.OK).entity(handler.startWorkPackage()).build();
	}

	/**
	 * Stop the current workpackage with the current time as endingtime.
	 *
	 * @return The finished workpackage or 404 if there is no open workpackage.
	 */
	@POST
	@Path("stop")
	@Produces(MediaType.APPLICATION_JSON)
	public Response finishPackage() {
		WorkPackage wp = handler.stopWorkPackage();
		if (wp == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("No open workpackage").build();
		}
		return Response.status(Response.Status.OK).entity(wp).build();
	}

	@PUT
	@Path("{id}/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePackage(@PathParam("id") String id, WorkPackage wp) {
		if (handler.updateWorkPackage(UUID.fromString(id), wp) == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Entry not found for UUID: " + id).build();
		} else {
			return Response.status(Response.Status.OK).entity(wp).build();
		}
	}
}
