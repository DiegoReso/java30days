package Day2;

public class LeetCodeValidPalindrome {
    public static void main(String[] args) {
        String palindrome = "race a car";

        System.out.println(isPalindrome(palindrome));
    }

    static boolean isPalindrome(String palindrome) {

            int esquerda = 0;
            int direita = palindrome.length() - 1;
            while (esquerda < direita) {

                while(esquerda < direita && !Character.isLetterOrDigit(palindrome.charAt(esquerda))) {
                    esquerda++;
                }
                while(esquerda < direita && !Character.isLetterOrDigit(palindrome.charAt(direita))) {
                    direita--;
                }
                if(Character.toLowerCase(palindrome.charAt(esquerda)) != Character.toLowerCase(palindrome.charAt(direita))) {
                    return false;
                }
                esquerda++;
                direita--;
            }
        return true;
    }
}
