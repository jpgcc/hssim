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
package pt.ul.fc.di.lasige.simhs.core.domain.workload;

import java.util.Observer;

import pt.ul.fc.di.lasige.simhs.core.domain.scheduling.IScheduler;
import pt.ul.fc.di.lasige.simhs.core.platform.IProcessor;

/**
 * @author jcraveiro
 *
 */
public interface IComponent extends IAbsSchedulable,Cloneable,Observer {
	

	public void addChild(IAbsSchedulable at);
	
	public void setScheduler(IScheduler s);
	
	public IScheduler getScheduler();
	
	public void tick();
	
	public void tickle();
	
	public void tickle(double exec);
	
	public double getBudget();
	
	public void addBudget(double plus);
	
	public void consumeBudget(double exec);

	public void bindProcessor(IProcessor proc);

	public void unbindProcessor(IProcessor proc);
	
	public IComponent clone();
	
	public Workload getWorkload();

}
