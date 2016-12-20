import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileParser {
	
	
	public void readData(String dataPath) throws Exception{
		int nodeNumber = 6;
		FileReader fr = new FileReader(dataPath);
		BufferedReader br = new BufferedReader(fr);
		int[][] matrix = new int[nodeNumber][nodeNumber];
		
        while (br.ready()) {
        	String line = br.readLine();
        	System.out.println(line);
        	String edge[] =line.split(",");
        	int origin = Integer.parseInt(edge[0])-1;
        	int terminus = Integer.parseInt(edge[1])-1;
        	matrix[origin][terminus] =1;
        	System.out.println(origin + "->" + terminus);
        	
        }
        
        
        for(int row=0; row < nodeNumber ;row++) {
        	for(int col=0; col< nodeNumber ;col++){
        		System.out.print(matrix[row][col] +" ");
        	}
        	System.out.println();
        }
        
        
        fr.close();
	}
	
	 public static void main(String args[]){
		 FileParser fp = new FileParser() ;
		 try{
			 fp.readData("./dataset/graph_1.txt");
		 }catch(Exception e){
			 System.out.println(e.getMessage());
			 
		 }
		 
	 }
}
