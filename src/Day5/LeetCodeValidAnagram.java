package Day5;

public class LeetCodeValidAnagram {
    public static void main(String[] args) {
        String string = "anagram";
        String string2 = "nagaram";

        System.out.println(isAnagram(string, string2));

    }

    public static boolean isAnagram(String string, String string2) {
        int c[] = new int[26];

        if (string.length() != string2.length()) {
            return false;
        }
        for (int i = 0; i < string.length(); i++) {
            c[string.charAt(i) - 'a']++;
        }

        for (int i = 0; i < string2.length(); i++) {
            c[string2.charAt(i) - 'a']--;
        }

        for (int contagem : c) {
            if (contagem != 0) {
                return false;
            }
        }

        return true;
    }
}
