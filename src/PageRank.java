import java.util.*;
import java.io.*;
public class PageRank {
  
public int[][] path ;
public double[] pagerank;
private int totalNodes=0;
private double threshold=0;
private double dampingFactor=0;

public PageRank(int totalNodes,int[][] adjacentMatrix,double threshold,double dampingFactor) {
	this.totalNodes =totalNodes;
	path =adjacentMatrix;
	pagerank = new double[totalNodes+1];
	this.threshold = threshold;
	this.dampingFactor = dampingFactor;
}
  
public void calc(){
     
	double initialPageRank =1 / (double)totalNodes;
	double outdegree=0; 
	double TempPageRank[] = new double[totalNodes+1];	
	int ExternalNodeNumber;
	int InternalNodeNumber; 
	int k=1; 
	int ITERATION_STEP=1;
	
	System.out.printf(" Total Number of Nodes :"+totalNodes+"\t Initial PageRank  of All Nodes :"+initialPageRank+"\n");
	System.out.printf(" DampingFactor :"+dampingFactor+"\t ConvergeThreshold : %f \n",threshold);
	
	// 0th ITERATION (PageRank)
	Arrays.fill(this.pagerank, initialPageRank); 
	   
	System.out.printf("\n Initial PageRank Values , 0th Step \n");
	for(k=1;k<=totalNodes;k++)
	{
	  System.out.printf(" Page Rank of "+k+" is :\t"+this.pagerank[k]+"\n");
	}  
		
	 while(!isConvergent(TempPageRank, pagerank)) // Iterations
	 {
		 // Store the PageRank in Temporary Array
		 TempPageRank=Arrays.copyOf(this.pagerank, this.pagerank.length);
		 Arrays.fill(this.pagerank, 0);	 
	     
		 //Count PageRank of each node
		 for(InternalNodeNumber=1;InternalNodeNumber<=totalNodes;InternalNodeNumber++){
			 for(ExternalNodeNumber=1;ExternalNodeNumber<=totalNodes;ExternalNodeNumber++){
				 if(this.path[ExternalNodeNumber][InternalNodeNumber] == 1){ 
					 k=1;
					 outdegree=0;  // Count the Number of Outgoing Links for each ExternalNodeNumber
					 while(k<=totalNodes) {
						 if(this.path[ExternalNodeNumber][k] == 1 ) {
							 outdegree=outdegree+1; // Counter for Outgoing Links
						 }  
						 k=k+1;  
					 } 
					 // Calculate PageRank     
					 this.pagerank[InternalNodeNumber]+=TempPageRank[ExternalNodeNumber]*(1/outdegree);    
				 }
			 }
			 this.pagerank[InternalNodeNumber] = dampingFactor+ (1-dampingFactor)*this.pagerank[InternalNodeNumber];
		 }    
	     
		 //Output the page rank of this step
	     System.out.printf("\n After "+ITERATION_STEP+"th Step \n");	   
	     for(k=1;k<=totalNodes;k++) 
	    	 System.out.printf(" Page Rank of "+k+" is :\t"+this.pagerank[k]+"\n"); 
	   
	     ITERATION_STEP = ITERATION_STEP+1;
	}
	 
	// Display final PageRank
	System.out.printf("\n Final Page Rank : \n"); 
	for(k=1;k<=totalNodes;k++)
	{
	 System.out.printf(" Page Rank of "+k+" is :\t"+this.pagerank[k]+"\n"); 
	 }
   
 } 

private boolean isConvergent(double[] oldPageRank,double[] newPageRank){
	int length = oldPageRank.length;
	for(int pageNumber=0; pageNumber < length ;pageNumber++){
		double diff = Math.abs(newPageRank[pageNumber] - oldPageRank[pageNumber]);
		if(diff > threshold)
			return false;
	}
	return true;
}
 
}