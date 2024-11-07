import java.util.ArrayList;
import java.util.List;

public class RegistrosDeVendas { //Essa classe registra as vendas
    private List<Produto> vendas;
    private double totalVendas;

    public RegistrosDeVendas(){
        vendas = new ArrayList<>();
        totalVendas = 0.0;

    }

    public void registrarVenda(Produto produto, int quantidadeVendida) {
        if (produto.getQuantidade() >= quantidadeVendida) {
            produto.removerEstoque(quantidadeVendida);
            vendas.add(produto);
            totalVendas += produto.getPreco() * quantidadeVendida;
        } else {
            System.out.println("Estoque insuficiente para venda.");
        }
    }

    public double getTotalVendas(){
        return totalVendas;
    }
}
