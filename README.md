# hsSim: an Extensible Interoperable Object-Oriented n-Level Hierarchical Scheduling Simulator

Hierarchical scheduling is a recent real-time scheduling topic. It is used to obtain temporal interference isolation in various scenarios, such as scheduling soft real-time aperiodic tasks along with hard real-time periodic tasks, and in mixed-criticality scenarios. Most theory and practice focuses on two-level hierarchies, with a root (global) scheduler managing resource contention by partitions (or scheduling servers), and a local scheduler in each partition/server to schedule the respective tasks. hsSim is an object-oriented hierarchical scheduling simulator supporting an arbitrary number of levels. With the goal of openness, extensibility and interoperability in mind, due care was put into the design, applying known design patterns where deemed advantageous.

## News

5 Dec 2013: hsSim 0.1 alpha is online. Comments are welcome!

## Documentations

Currently we have the following documentation available (more to come):

* The paper describing the goals and principles and early development stages of hsSim, “**hsSim: an Extensible Interoperable Object-Oriented n-Level Hierarchical Scheduling Simulator**”, presented at the [3rd International Workshop on Analysis Tools and Methodologies for Embedded and Real-time Systems (WATERS 2012)](http://retis.sssup.it/waters2012) is available [here](http://lasige.di.fc.ul.pt/~jcraveiro/?n=Publications.Craveiro12hssim), along with the respective presentation slides.

More documentation will be available in the future.

## References
A list of publications describing models, algorithms and analysis methods supported by hsSim can be found [here](REFERENCES.md).

## Interoperability
A list of third party tools and formats with which hsSim is compatible can be found [here](INTEROPERABILITY.md).

## Powered by hsSim
A list of academic projects and papers that use hsSim or one of its early versions can be found [here](USES.md).