public class Produto {
    private String nome;
    private int quantidade;
    private double preco;
    private String tipo;

    public Produto(String nome, int quantidade, double preco, String tipo) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    // Método para remover uma quantidade do estoque
    public void removerEstoque(int quantidadeARemover) throws IllegalArgumentException {
        if (quantidadeARemover <= 0) {
            throw new IllegalArgumentException("Quantidade a remover deve ser maior que zero.");
        }
        if (quantidadeARemover > quantidade) {
            throw new IllegalArgumentException("Quantidade em estoque insuficiente para a venda.");
        }
        this.quantidade -= quantidadeARemover;
    }

    @Override
    public String toString() {
        return "Produto: " + nome + ", Quantidade: " + quantidade + ", Preço: R$ " + String.format("%.2f", preco) + ", Tipo: " + tipo;
    }

}
