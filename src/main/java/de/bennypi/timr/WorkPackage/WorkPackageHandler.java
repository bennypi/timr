package de.bennypi.timr.WorkPackage;

import java.util.Calendar;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

// TODO: Remember if last workpackage is complete
public class WorkPackageHandler {

	private ConcurrentHashMap<UUID, WorkPackage> map;
	private static final WorkPackageHandler wpl = new WorkPackageHandler();
	private UUID currentPackage = null;

	private WorkPackageHandler() {
		map = new ConcurrentHashMap<>();
	}

	public static WorkPackageHandler getInstance() {
		return wpl;
	}

	public UUID startWorkPackage() {
		if (currentPackage != null) {
			stopWorkPackage();
		}
		WorkPackage wp = new WorkPackage.WorkPackageBuilder(Calendar.getInstance()).createWorkPackage();
		currentPackage = wp.getId();
		map.put(wp.getId(), wp);
		return wp.getId();
	}
	
	public WorkPackage stopWorkPackage(){
		map.get(currentPackage).setEndingTime(Calendar.getInstance());
		UUID id = currentPackage;
		currentPackage = null;
		return map.get(id);
	}

	public WorkPackage getWorkPackage(UUID id) {
		return map.get(id);
	}

	public int size() {
		return map.size();
	}
}
