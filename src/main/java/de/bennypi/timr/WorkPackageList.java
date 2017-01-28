package de.bennypi.timr;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class WorkPackageList {

	private ConcurrentHashMap<UUID, WorkPackage> map;
	private static final WorkPackageList wpl = new WorkPackageList();

	private WorkPackageList() {
		map = new ConcurrentHashMap<>();
	}

	public static WorkPackageList getInstance() {
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
