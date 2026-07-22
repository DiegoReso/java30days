package Day6;

public class DesafioArray {
    public static void main(String[] args) {
        //Existe um par que soma o alvo?
        System.out.println(existePar(new int[]{1, 2, 4, 7, 11}, 9));   // true
        System.out.println(existePar(new int[]{1, 2, 4, 7, 11}, 100)); // false
        System.out.println(existePar(new int[]{1, 3, 5, 7}, 8));          // true
        System.out.println(existePar(new int[]{1, 3, 5}, 2));          // false
    }

    static boolean existePar(int[] nums, int alvo) {

        int esquerda = 0;
        int direita = nums.length - 1;

        while (esquerda < direita) {
            int soma = nums[esquerda] + nums[direita];
            if(soma == alvo) {
                return true;
            }
            if(soma > alvo) {
                direita--;
            }else {
                esquerda++;
            }
        }
        return false;
    }
}
