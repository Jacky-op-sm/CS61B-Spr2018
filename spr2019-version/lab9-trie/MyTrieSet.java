import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class MyTrieSet implements TrieSet61B {

    private Node root;    // root of trie

    public MyTrieSet() {
        root = new Node(null, false);
    }

    public static void main(String[] args) {
        MyTrieSet t = new MyTrieSet();
        t.add("hello");
        t.add("hek");
        t.add("hem");
        System.out.println(t.contains("hello"));
        System.out.println(t.contains("hek"));
        System.out.println(t.keysWithPrefix("he"));
        System.out.println(t.contains("hek"));
    }

    @Override
    public void clear() {
        root = new Node(null, false);
    }

    @Override
    public boolean contains(String key) {
        if (key == null || key.length() < 1) {
            return false;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                return false;
            }
            curr = curr.map.get(c);
        }
        return curr.isKey;
    }

    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> x = new ArrayList<>();
        Node curr = root;
        for (int i = 0, n = prefix.length(); i < n; i++) {
            char c = prefix.charAt(i);
            curr = curr.map.get(c);
        }
        for (Character c : curr.map.keySet()) {
            keysWithPrefix(prefix + c, x, curr.map.get(c));
        }
        return x;
    }

    private void keysWithPrefix(String s, List<String> x, Node n) {
        if (n.isKey) {
            x.add(s);
        }
        for (Character c : n.map.keySet()) {
            keysWithPrefix(s + c, x, n.map.get(c));
        }
    }

    @Override
    public String longestPrefixOf(String key) {
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                return key.substring(0, i);
            }
            curr = curr.map.get(c);
        }
        return key;
    }

    private static class Node {
        private boolean isKey;
        private Hashtable<Character, Node> map;

        private Node(Character c, boolean b) {
            isKey = b;
            map = new Hashtable<>(4);
        }
    }
}
