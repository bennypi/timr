package de.bennypi.timr.WorkPackage;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class WorkPackageHandler {

	private ConcurrentHashMap<UUID, WorkPackage> map;
	private static final WorkPackageHandler wpl = new WorkPackageHandler();

	private WorkPackageHandler() {
		map = new ConcurrentHashMap<>();
	}

	public static WorkPackageHandler getInstance() {
		return wpl;
	}

	public void addWorkPackage(WorkPackage wp) {
		map.put(wp.getId(), wp);
	}

	public WorkPackage getWorkPackage(UUID id) {
		return map.get(id);
	}

	public int size() {
		return map.size();
	}
}
