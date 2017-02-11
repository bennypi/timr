package de.bennypi.timr;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import de.bennypi.timr.resource.WorkPackageResourceTest;
import de.bennypi.timr.workpackage.WorkPackageHandlerTest;
import de.bennypi.timr.workpackage.WorkPackageTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ WorkPackageHandlerTest.class, WorkPackageTest.class, WorkPackageResourceTest.class })

public class SuiteUnitTests {
	// the class remains empty,
	// used only as a holder for the above annotations
}
