import java.util.*;
import java.io.*;
public class PageRank {
  
public int[][] path ;
public double[] pagerank;
private int totalNodes;

public PageRank(int totalNodes,int[][] adjacentMatrix) {
	this.totalNodes =totalNodes;
	path =adjacentMatrix;
	pagerank = new double[totalNodes+1];
}
  
public void calc(){
     
	double InitialPageRank =1 / (double)totalNodes;
	double OutgoingLinks=0; 
	double DampingFactor = 0.85; 
	double TempPageRank[] = new double[10];
	 
	int ExternalNodeNumber;
	int InternalNodeNumber; 
	int k=1; // For Traversing
	int ITERATION_STEP=1;
	
	System.out.printf(" Total Number of Nodes :"+totalNodes+"\t Initial PageRank  of All Nodes :"+InitialPageRank+"\n");
	 
	// 0th ITERATION (初始PageRank)
	for(k=0;k<=totalNodes;k++)
	{
	  this.pagerank[k]=InitialPageRank;
	}   
	   
	System.out.printf("\n Initial PageRank Values , 0th Step \n");
	for(k=1;k<=totalNodes;k++)
	{
	  System.out.printf(" Page Rank of "+k+" is :\t"+this.pagerank[k]+"\n");
	}  
		
	 
	 while(ITERATION_STEP<=2) // Iterations
	 {
	   // Store the PageRank for All Nodes in Temporary Array 
	  for(k=1;k<=totalNodes;k++)
	 {  
	   TempPageRank[k]=this.pagerank[k];
	   this.pagerank[k]=0;
	  }
	     
	 for(InternalNodeNumber=1;InternalNodeNumber<=totalNodes;InternalNodeNumber++)
	 {
	  for(ExternalNodeNumber=1;ExternalNodeNumber<=totalNodes;ExternalNodeNumber++)
	   {
	    if(this.path[ExternalNodeNumber][InternalNodeNumber] == 1)
	    { 
	      k=1;
	      OutgoingLinks=0;  // Count the Number of Outgoing Links for each ExternalNodeNumber
	      while(k<=totalNodes)
	      {
	        if(this.path[ExternalNodeNumber][k] == 1 )
	        {
	          OutgoingLinks=OutgoingLinks+1; // Counter for Outgoing Links
	        }  
	        k=k+1;  
	      } 
	         // Calculate PageRank     
	         this.pagerank[InternalNodeNumber]+=TempPageRank[ExternalNodeNumber]*(1/OutgoingLinks);    
	     }
	   }  
	 }    
	      
	     System.out.printf("\n After "+ITERATION_STEP+"th Step \n");
	   
	     for(k=1;k<=totalNodes;k++) 
	      System.out.printf(" Page Rank of "+k+" is :\t"+this.pagerank[k]+"\n"); 
	   
	     ITERATION_STEP = ITERATION_STEP+1;
	}
	 
	// Add the Damping Factor to PageRank
	for(k=1;k<=totalNodes;k++)
	{
	  this.pagerank[k]=(1-DampingFactor)+ DampingFactor*this.pagerank[k]; 
	  } 
	   
	// Display PageRank
	System.out.printf("\n Final Page Rank : \n"); 
	for(k=1;k<=totalNodes;k++)
	{
	 System.out.printf(" Page Rank of "+k+" is :\t"+this.pagerank[k]+"\n"); 
	  }
   
 } 
 
 public static void main(String args[])
    {
        int nodes= 6;
        String dataPath = "./dataset/graph_1.txt";
        
        FileParser fp = new FileParser(dataPath,nodes);
        try {
        	fp.readData();
            PageRank p = new PageRank(nodes,fp.getadjacentMatrix());
            p.calc();
        }catch (Exception e) {
        	System.out.println(e.getMessage());
        }
           
           
    }   
 
}