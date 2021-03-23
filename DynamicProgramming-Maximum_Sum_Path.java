package matrix;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class MaximumPath {
	public class Pair<K, V> {

	    public final K key;
	    public final V value;

	    public Pair(K key, V value) {
	        this.key = key;
	        this.value = value;
	    }

	    public boolean equals(Object o) {
	        return o instanceof Pair && Objects.equals(key, ((Pair<?,?>)o).key) && Objects.equals(value, ((Pair<?,?>)o).value);
	    }

	    public int hashCode() {
	        return 31 * Objects.hashCode(key) + Objects.hashCode(value);
	    }

	    public String toString() {
	        return "(" +key + "," + value + ")";
	    }
	}
	public void maxPath(int [][]cost, int n, int m){
		int i, j;
        int temp[][]=new int[n+1][m+1];
 
        temp[0][0] = cost[0][0];
 
        /* Initialize first column of total cost(temp) array */
        for (i = 1; i <= n; i++)
            temp[i][0] = temp[i-1][0] + cost[i][0];
 
        /* Initialize first row of temp array */
        for (j = 1; j <= m; j++)
            temp[0][j] = temp[0][j-1] + cost[0][j];
 
        /* Construct rest of the temp array */
        for (i = 1; i <= n; i++)
            for (j = 1; j <= m; j++)
                temp[i][j] = max(temp[i-1][j-1], 
                               temp[i-1][j],
                               temp[i][j-1]) + cost[i][j];
        System.out.println("Maximum value :"+ temp[n][m]);
        
        // path calculation
        
        i = n;j = m;
        ArrayList<Pair> path = new ArrayList<Pair>();
        Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, j);
        path.add(0, p);
        while(i!=0 || j!=0){
        	if(i>0 && j>0){
        		if(temp[i-1][j-1] == max(temp[i-1][j-1], temp[i-1][j], temp[i][j-1])){
        			i = i-1;
        			j=j-1;
        		}else if (temp[i-1][j] == max(temp[i-1][j-1], temp[i-1][j], temp[i][j-1])){
        			i = i-1;
        		}else{
        			j = j-1;
        		}
        	}else if (i> 0 & j==0){
        		i = i-1;
        	}else if (i==0 & j>0){
        		j = j-1;
        	}
        	p = new Pair<Integer, Integer>(i, j);
        	path.add(0, p);
        }
        for(Pair pat : path){
        	System.out.print(pat.toString());
        }
	}
	
	private  int max(int a,int b, int c){
        int l = Math.max(a, b);
        return Math.max(l, c);
    }
	
	public static void main(String args[]){
		// initializing an array on declaration
		int matrixsizes[][] = {{5,5},{14,10},{11,15},{6,7},{8,4}};

		for (int a = 0; a < matrixsizes.length; a++)  { 
			Random r=new Random();
			int n = matrixsizes[a][0]; // matric size n
			int m = matrixsizes[a][1]; // matrix size m
			System.out.print("------------------------------------");
			System.out.print("\n n: "+String.valueOf(n)+" and m: "+String.valueOf(m)+"\n");
			int[][] matrix=new int[n][m];
			for(int i=0;i<n;i++)
				{
				  for(int j=0;j<m;j++)
				  {
					  matrix[i][j]=r.nextInt(20); // Value
					 System.out.print(matrix[i][j]+"\t");
				  }
			  
				 System.out.print("\n");
			  }
			MaximumPath ma = new MaximumPath();
			ma.maxPath(matrix, n-1, m-1);	
			System.out.print("\n");	 
		}
            
		

	}
}
