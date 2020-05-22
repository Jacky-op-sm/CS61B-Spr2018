public class OffByN implements CharacterComparator {
    int off;

    public OffByN(int N) {
        off = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int dis = x - y;
        if (dis == off || dis == -off) {
            return true;
        }
        return false;
    }

}
