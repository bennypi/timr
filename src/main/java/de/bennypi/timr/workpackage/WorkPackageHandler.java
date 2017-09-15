package de.bennypi.timr.workpackage;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class WorkPackageHandler {

	private ConcurrentHashMap<UUID, WorkPackage> workPackages;
	private static final WorkPackageHandler handler = new WorkPackageHandler();
	private UUID currentPackage = null;

	private WorkPackageHandler() {
		workPackages = new ConcurrentHashMap<>();
	}

	public static WorkPackageHandler getInstance() {
		return handler;
	}

	public UUID startWorkPackage() {
		if (currentPackage != null) {
			stopWorkPackage();
		}
		WorkPackage wp = new WorkPackage.WorkPackageBuilder(Calendar.getInstance()).createWorkPackage();
		UUID id = UUID.randomUUID();
		currentPackage = id;
		workPackages.put(id, wp);
		return id;
	}

	public Optional<WorkPackage> stopWorkPackage() {
		if (currentPackage == null) {
			return null;
		}
		workPackages.get(currentPackage).setEndingTime(Calendar.getInstance());
		UUID id = currentPackage;
		currentPackage = null;
		return Optional.of(workPackages.get(id));
	}

	public Optional<WorkPackage> getWorkPackage(UUID id) {
		return Optional.of(workPackages.get(id));
	}

	public int size() {
		return workPackages.size();
	}

	public List<UUID> getUUIDs() {
		return Collections.list(workPackages.keys());
	}

	public Optional<WorkPackage> getCurrent() {
		return currentPackage == null ? null : Optional.of(workPackages.get(currentPackage));
	}

	public WorkPackage updateWorkPackage(UUID id, WorkPackage wp) {
		if (workPackages.contains(id)) {
			workPackages.put(id, wp);
			return wp;
		} else {
			return null;
		}
	}
}
