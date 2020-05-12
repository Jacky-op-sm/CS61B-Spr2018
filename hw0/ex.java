public class ex {
  public static void main(String[] args) {
    String[] a = {"cat", "house"};
    for (String s : a) {
      for (int j = 0; j < 3; j += 1) {
        System.out.println(s);
        if (s.contains("house")) {
          break;
        }
      }
    }
  }
}
