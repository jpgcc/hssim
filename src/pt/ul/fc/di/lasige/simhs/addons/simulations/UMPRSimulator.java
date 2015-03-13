/*
 * Copyright (c) 2012, LaSIGE, FCUL, Lisbon, Portugal.
 * All rights reserved.
 *
 * This file is distributed under the terms in the attached LICENSE file.
 * If you do not find this file, copies can be obtained by writing to:
 * LaSIGE, FCUL, Campo Grande, Ed. C6, Piso 3, 1749-016 LISBOA, Portugal
 * (c/o João Craveiro)
 * 
 * If you consider using this tool for your research, please be kind
 * as to cite the paper describing it:
 * 
 * J. Craveiro, R. Silveira and J. Rufino, "hsSim: an Extensible 
 * Interoperable Object-Oriented n-Level Hierarchical Scheduling 
 * Simulator," in WATERS 2012, Pisa, Italy, Jul. 2012.
 */
package pt.ul.fc.di.lasige.simhs.addons.simulations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;

import pt.ul.fc.di.lasige.simhs.addons.loggers.GraspLogger;
import pt.ul.fc.di.lasige.simhs.addons.loggers.SuperBasicLogger;
import pt.ul.fc.di.lasige.simhs.addons.models.UMPRComponent;
import pt.ul.fc.di.lasige.simhs.addons.models.PeriodicInterfaceTask;
import pt.ul.fc.di.lasige.simhs.core.domain.RTSystem;
import pt.ul.fc.di.lasige.simhs.core.domain.scheduling.schedulers.GEDFScheduler;
import pt.ul.fc.di.lasige.simhs.core.domain.workload.PeriodicTask;
import pt.ul.fc.di.lasige.simhs.core.platform.IPlatform;
import pt.ul.fc.di.lasige.simhs.core.platform.PhysicalPlatform;
import pt.ul.fc.di.lasige.simhs.core.platform.PhysicalProcessor;
import pt.ul.fc.di.lasige.simhs.core.simulation.AbstractBasicSimulator;
import pt.ul.fc.di.lasige.simhs.core.simulation.ILogger;

/**
 * @author jcraveiro
 */
public class UMPRSimulator extends AbstractBasicSimulator {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		(new UMPRSimulator()).run();
	}
	
	private static final long SIMULATION_TIME = 200;


	public UMPRSimulator() throws Exception {
		super();
	}

	@Override
	protected RTSystem obtainSystem() throws InstantiationException, IllegalAccessException {
		
		/*
		 * This uses the UMPR to simulate MPR (which is a special case
		 * of the latter)
		 * 
		 * NOTE: Due to limitations in the Grasp tool itself, the GraspLogger
		 * does not deal well with processors of speed != 1.0
		 */
		IPlatform base = new PhysicalPlatform();
		base.bindProcessor(new PhysicalProcessor("proc1", 1.0));
		base.bindProcessor(new PhysicalProcessor("proc2", 1.0));
		base.bindProcessor(new PhysicalProcessor("proc3", 1.0));
		base.bindProcessor(new PhysicalProcessor("proc4", 1.0));
		
		RTSystem system = new RTSystem(base);
		
		system.setScheduler(new GEDFScheduler());
		
		/*
		 * The 3 example components are taken from (Easwaran et al., 2009) -
		 * Springer Real Time Systems
		 */
		
		//Component C1
		UMPRComponent c1 = new UMPRComponent("C1", system.getRootComponent(), 8.22, 6);
		c1.setScheduler(new GEDFScheduler());
		//add tasks to c1
		c1.addChild(new PeriodicTask("task1_01", c1, 5, 60));
		c1.addChild(new PeriodicTask("task1_02", c1, 5, 60));
		c1.addChild(new PeriodicTask("task1_03", c1, 5, 60));
		c1.addChild(new PeriodicTask("task1_04", c1, 5, 60));
		c1.addChild(new PeriodicTask("task1_05", c1, 5, 70));
		c1.addChild(new PeriodicTask("task1_06", c1, 5, 70));
		c1.addChild(new PeriodicTask("task1_07", c1, 5, 80));
		c1.addChild(new PeriodicTask("task1_08", c1, 5, 80));
		c1.addChild(new PeriodicTask("task1_09", c1, 10, 80));
		c1.addChild(new PeriodicTask("task1_10", c1, 5, 90));
		c1.addChild(new PeriodicTask("task1_11", c1, 10, 90));
		c1.addChild(new PeriodicTask("task1_12", c1, 10, 90));
		c1.addChild(new PeriodicTask("task1_13", c1, 10, 100));
		c1.addChild(new PeriodicTask("task1_14", c1, 10, 100));
		c1.addChild(new PeriodicTask("task1_15", c1, 10, 100));
		//add interface tasks to c1
		c1.addInterfaceTask(new PeriodicInterfaceTask("C1_1", c1, 5.0, 6, 6));
		c1.addInterfaceTask(new PeriodicInterfaceTask("C1_2", c1, 4.0, 6, 6));
		system.addChild(c1);
		
		//Component C2
		UMPRComponent c2 = new UMPRComponent("C2", system.getRootComponent(), 2.34, 8);
		c2.setScheduler(new GEDFScheduler());
		//add tasks to c2
		c2.addChild(new PeriodicTask("task2_01", c2, 5.0, 60));
		c2.addChild(new PeriodicTask("task2_02", c2, 5.0, 100));
		c2.addInterfaceTask(new PeriodicInterfaceTask("C2_1", c2, 3.0, 8, 8));
		//add interface tasks to c2
		system.addChild(c2);
		
		//Component C3
		UMPRComponent c3 = new UMPRComponent("C3", system.getRootComponent(), 5.83, 5);
		c3.setScheduler(new GEDFScheduler());
		//add tasks to c3
		c3.addChild(new PeriodicTask("task3_01", c3, 2.0, 45, 40));
		c3.addChild(new PeriodicTask("task3_02", c3, 2.0, 45, 45));
		c3.addChild(new PeriodicTask("task3_03", c3, 3.0, 45, 40));
		c3.addChild(new PeriodicTask("task3_04", c3, 3.0, 45, 45));
		c3.addChild(new PeriodicTask("task3_05", c3, 5.0, 50, 45));
		c3.addChild(new PeriodicTask("task3_06", c3, 5.0, 50, 50));
		c3.addChild(new PeriodicTask("task3_07", c3, 5.0, 50, 50));
		c3.addChild(new PeriodicTask("task3_08", c3, 5.0, 50, 50));
		c3.addChild(new PeriodicTask("task3_09", c3, 5.0, 70, 60));
		c3.addChild(new PeriodicTask("task3_10", c3, 5.0, 70, 60));
		c3.addChild(new PeriodicTask("task3_11", c3, 5.0, 70, 65));
		c3.addChild(new PeriodicTask("task3_12", c3, 5.0, 70, 65));
		c3.addChild(new PeriodicTask("task3_13", c3, 5.0, 70, 65));
		c3.addChild(new PeriodicTask("task3_14", c3, 5.0, 70, 65));
		c3.addChild(new PeriodicTask("task3_15", c3, 5.0, 70, 70));
		//add interface tasks to c3
		c3.addInterfaceTask(new PeriodicInterfaceTask("C3_1", c3, 3.0, 5, 5));
		c3.addInterfaceTask(new PeriodicInterfaceTask("C3_2", c3, 3.0, 5, 5));
		system.addChild(c3);
		
		return system;
	}

	@Override
	protected Collection<ILogger<?>> obtainLoggers() {

		Collection<ILogger<?>> loggers = new ArrayList<ILogger<?>>();

		ILogger<?> gLog;

		// Log to screen
		gLog = new SuperBasicLogger(getSystem());
		loggers.add(gLog);
		
		// Log to .grasp file
		try {
			gLog = new GraspLogger(getSystem(), new PrintStream(new File(
					"HMPR1.grasp")));
			loggers.add(gLog);
		} catch (FileNotFoundException e) {
			gLog = null;
			e.printStackTrace();
		}

		return loggers;

	}

	@Override
	protected long getSimulationTime() {
		return SIMULATION_TIME;
	}

}
