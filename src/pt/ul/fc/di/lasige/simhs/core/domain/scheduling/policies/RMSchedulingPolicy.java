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
package pt.ul.fc.di.lasige.simhs.core.domain.scheduling.policies;

import pt.ul.fc.di.lasige.simhs.core.domain.scheduling.Job;
import pt.ul.fc.di.lasige.simhs.core.domain.scheduling.SchedulingPolicy;
import pt.ul.fc.di.lasige.simhs.core.domain.workload.IPeriodic;

public class RMSchedulingPolicy implements SchedulingPolicy {
	@Override
	public int compare(Job o1, Job o2) {
		
		IPeriodic pTask1 = (IPeriodic) o1.getParentTask();
		IPeriodic pTask2 = (IPeriodic) o2.getParentTask();
		// if the schedulables are not periodic, a ClassCastException will be thrown
		
		int r1 = new Integer(pTask1.getPeriod()).compareTo(pTask2.getPeriod());

		if (r1 != 0) {
			return r1;
		} else {
			int r2 = new Integer(o1.getReleaseTime()).compareTo(o2.getReleaseTime());

			if (r2 != 0) {
				return r2;
			} else {
				return o1.getParentTask().toStringId()
						.compareTo(o2.getParentTask().toStringId());
			}
		}
	}
}