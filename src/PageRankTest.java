
public class PageRankTest {

	public static void main(String[] args) {
        int nodes= 19;
        String dataPath = "./dataset/association_result.txt";
        double threshold = 0.0001;
        double d= 0.15;
        
        FileParser fp = new FileParser(dataPath,nodes);
        try {
        	fp.readData();
        	int[][] adjMatrix =fp.getAdjacentMatrix();
            PageRank p = new PageRank(nodes,adjMatrix,threshold,d);
            p.calc();
        }catch (Exception e) {
        	System.out.println(e.getMessage());
        }

	}

}
