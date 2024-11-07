public class ProdutoLimpeza extends Produto {
    private String tipo;

    public ProdutoLimpeza(String nome, int quantidade, double preco, String tipo) {
        super(nome, quantidade, preco,tipo);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return super.toString(); // Aproveita o toString da classe Produto
    }

}