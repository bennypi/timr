package de.bennypi.timr.workpackage;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

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
		UUID id = UUID.randomUUID();
		currentPackage = id;
		map.put(id, wp);
		return id;
	}

	public WorkPackage stopWorkPackage() {
		if (currentPackage == null) {
			return null;
		}
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

	public List<UUID> getUUIDs() {
		return Collections.list(map.keys());
	}

	public WorkPackage getCurrent() {
		return currentPackage == null ? null : map.get(currentPackage);
	}

	public WorkPackage updateWorkPackage(UUID id, WorkPackage wp) {
		if (map.contains(id)) {
			map.put(id, wp);
			return wp;
		} else {
			return null;
		}
	}
}
