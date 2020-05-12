public class Max {
    /** Returns the maximum value from m. */
    public static int max(int[] m) {
        int n = m.length, i, biggest;
        biggest = m[0];
        for(i = 1; i < n; i += 1) {
          if (m[i] > biggest) {
            biggest = m[i];
          }
        }
        return biggest;
    }
    public static void main(String[] args) {
       int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
       int biggest = max(numbers);
       System.out.println(biggest);
    }
}
