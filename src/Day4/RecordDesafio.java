package Day4;

//criacao de um record com varios parametros, poucas linha, util para DTO, numa classe normal seriam varias linhas
//de getters e setters....
public record RecordDesafio(
        String nome,
        String email,
        int idade,
        String sexo,
        String password
) {
    @Override
    public String toString() {
        return "Usuario[nome=" + nome + ", senha=***]";   //podemos sobreescrever tbm -> esconde a senha
    }
}
