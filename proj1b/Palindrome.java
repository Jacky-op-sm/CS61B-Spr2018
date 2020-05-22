public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> result = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            Character t = word.charAt(i);
            result.addLast(t);
        }
        return result;
    }

    private boolean isPalindromeHelper(Deque<Character> n) {
        if (n.size() <= 1) {
            return true;
        } else {
            Character fir = n.removeFirst();
            Character las = n.removeLast();
            boolean rest = isPalindromeHelper(n);
            return (fir == las) && rest;
        }
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindromeHelper(wordDeque);
    }

    private boolean isPalindromeHelper(Deque<Character> n, CharacterComparator cc) {
        if (n.size() <= 1) {
            return true;
        } else {
            Character fir = n.removeFirst();
            Character las = n.removeLast();
            boolean rest = isPalindromeHelper(n, cc);
            return (cc.equalChars(fir, las)) && rest;
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindromeHelper(wordDeque, cc);
    }
}

