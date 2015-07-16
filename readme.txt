SCSI 561 Program2
-------- Report -------
Hien_To_Program2.pdf

-------- Source code -------
csci561_plot.R is an R file to plot graphs in batch

Netbean Project: PL
	PL: main class include methods for all experiments
	SentenceGenerator: to generate random sentences
	PLResolution: the complete algorithm
	WalkSat: the incomplete algorithm
	CombinationGenerator: generate combination subsets of a larger set, it is used to generate random sentences 
	BinaryCombinationGenerator: generate combination of a set of binary numbers, it is used to generate all clauses
    	FileReader: read data from input files

-------- Output convensions ---------
exp{3}_{satisfied}_{ws}_{time}.xls
	3 is the order of experiment, we have 1,2,3
	ws is the algorithm, we have ws and pl
	satisfied means satisfied sentences
	time means runtime is measured in microsecond, otherwise runtime is measured in iteration 


-------- Test ---------
Just run the PL.java file, you will receive all the output in the folder /src/pl/

Thanks you!