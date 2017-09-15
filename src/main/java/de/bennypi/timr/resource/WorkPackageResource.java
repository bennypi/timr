package de.bennypi.timr.resource;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.bennypi.timr.workpackage.WorkPackage;
import de.bennypi.timr.workpackage.WorkPackageHandler;

/**
 * Root resource (exposed at "workpackage" path)
 */
@RestController
@RequestMapping("/workpackage")
public class WorkPackageResource {

	private WorkPackageHandler handler = WorkPackageHandler.getInstance();

	/**
	 * Return all workpackges
	 *
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public List<UUID> getAll() {
		return handler.getUUIDs();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/current")
	public WorkPackage getCurrent() {
		return handler.getCurrent().orElseThrow(() -> new NotFoundException());
	}

	/**
	 * Get a workpackage by its id
	 *
	 * @param Id
	 *            The package's UUID as a string
	 *
	 * @return All available information for this workpackage
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public WorkPackage getWorkPackage(@PathVariable("id") String id) {
		return handler.getWorkPackage(UUID.fromString(id)).orElseThrow(() -> new NotFoundException(id));
	}

	/**
	 * Create a new workpackage with the current time as startingtime
	 *
	 * @return The UUID of the workpackage
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/start")
	public UUID startPackage() {
		return handler.startWorkPackage();
	}

	/**
	 * Stop the current workpackage with the current time as endingtime.
	 *
	 * @return The finished workpackage or 404 if there is no open workpackage.
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/stop")
	public WorkPackage finishPackage() {
		return handler.stopWorkPackage().orElseThrow(() -> new NotFoundException());
	}

	/*
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
	*/
}
