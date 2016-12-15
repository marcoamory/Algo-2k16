import java.util.Arrays;

public class Cmb {
	
	static void print(int k, int n) {
		print(k, 0, n, new int[k], 0);
	}
	
	static void print(int k, int idx, int n, int[] cmb, int jdx) {
		if (k == 0) {
			System.out.println(Arrays.toString(cmb));
			
			return;
		}
		
		for(int i = idx; i < n; i++) {
			cmb[jdx] = i;
			print(k - 1, i, n, cmb, jdx + 1);
		}
		
	}
	
	public static void main(String[] args) {
	  print(22, 50);	
	}

}
