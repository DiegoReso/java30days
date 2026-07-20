package Day3;

public class LeetCodeTimeToBuySellStock {
    public static void main(String[] args) {

        //ideia para o desenvolvimento
        //Se eu comprar no dia 1 (preço 1) e vender no dia 4 (preço 6), meu lucro é 6 - 1 = 5.
        //Esse é o melhor negócio possível aqui. A resposta é 5.
        //Eu não posso comprar no dia 4 (preço 6) e vender no dia 1 (preço 1) — o dia 1 vem antes
        //e não vendo antes de comprar.
        int precos[] = {7, 1, 5, 3, 6, 4};
        int menorPreco = precos[0];
        int maiorLucro = 0;
        for (int i = 0; i < precos.length; i++) {
            if (precos[i] < menorPreco) {
                menorPreco = precos[i];
            }
            if ((precos[i] - menorPreco) > maiorLucro) {
                maiorLucro = precos[i] - menorPreco;
            }
        }
        System.out.println(maiorLucro);
    }
}
