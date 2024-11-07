public class Venda {
    private Produto produto;
    private int quantidade;
    private double total;

    public Venda(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.total = produto.getPreco() * quantidade;
        produto.removerEstoque(quantidade); // Reduzir o estoque
    }

    public double getTotal() {
        return total;
    }

    public Produto getProduto() {
        return produto;
    }

    @Override
    public String toString() {
        return "Venda: " + produto.getNome() + ", Quantidade: " + quantidade + ", Total: R$ " + total;
    }
}