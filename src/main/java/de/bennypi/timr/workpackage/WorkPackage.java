package de.bennypi.timr.workpackage;

import java.util.Calendar;

/**
 * This class represents a single workpackage
 *
 * @author benny
 *
 */
public class WorkPackage {
	private Calendar startingTime;
	private Calendar endingTime;
	private String description;

	private WorkPackage(Calendar startingTime, Calendar endingTime, String description) {
		this.startingTime = startingTime;
		this.endingTime = endingTime;
		this.description = description;
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

	@Override
	public String toString() {
		String str = "Details for this workpackage:\n";
		str += "Starting Time: " + startingTime.getTime().toString() + "\n";
		str += "Ending Time: " + (endingTime == null ? "Still running \n" : endingTime.getTime().toString() + "\n");
		str += "Description: " + (description == null ? "" : description);
		return str;
	}

	public static class WorkPackageBuilder {
		private Calendar nestedStartingTime;
		private Calendar nestedEndingTime;
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
