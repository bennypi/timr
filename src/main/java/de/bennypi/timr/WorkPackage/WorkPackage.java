package de.bennypi.timr.WorkPackage;

import java.util.Calendar;
import java.util.UUID;

/**
 * This class represents a single workpackage
 * 
 * @author benny
 *
 */
public class WorkPackage {
	private Calendar startingTime, endingTime;
	private String description;
	private UUID id;

	private WorkPackage(Calendar startingTime, Calendar endingTime, String description) {
		this.startingTime = startingTime;
		this.id = UUID.randomUUID();
	}

	public Calendar getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(Calendar startingTime) {
		this.startingTime = startingTime;
	}

	public Calendar getEndingTime() {
		return endingTime;
	}

	public void setEndingTime(Calendar endingTime) {
		this.endingTime = endingTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UUID getId() {
		return this.id;
	}

	@Override
	public String toString() {
		// TODO: Extend to full details, check for null
		return "Details for workpackage " + id + "\n" + "Starting Time: " + startingTime.getTime().toString();
	}

	public static class WorkPackageBuilder {
		private Calendar nestedStartingTime, nestedEndingTime;
		private String nestedDescription = "";

		public WorkPackageBuilder(Calendar startingTime) {
			nestedStartingTime = startingTime;
		}

		public WorkPackageBuilder endingTime(Calendar endingTime) {
			nestedEndingTime = endingTime;
			return this;
		}

		public WorkPackageBuilder description(String description) {
			nestedDescription = description;
			return this;
		}

		public WorkPackage createWorkPackage() {
			return new WorkPackage(nestedStartingTime, nestedEndingTime, nestedDescription);
		}
	}

}
