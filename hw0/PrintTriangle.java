public class PrintTriangle {
	   public static void main(String[] args) {
	      DrawTriangle(10);
  }

     public static void DrawTriangle(int N) {
	      int row = N, num, i;
        for(i = 1; i <= row; i += 1){
          for(num = 1; num <= i; num += 1){
	          System.out.print("*");
	          }
	          System.out.println();
	        }
	   }
	}
