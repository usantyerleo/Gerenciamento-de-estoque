import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private List<Venda> vendas;
    private double lucro;

    public Sistema() {
        vendas = new ArrayList<>();
        lucro = 0.0;
    }

    public void registrarVenda(Produto produto, int quantidade) {
        Venda venda = new Venda(produto, quantidade);
        vendas.add(venda);
        lucro += venda.getTotal();
        System.out.println("Venda registrada com sucesso: " + venda);
    }

    public void gerarRelatorioVendas() {
        System.out.println("Relatório de Vendas:");
        for (Venda venda : vendas) {
            System.out.println(venda);
        }
        System.out.println("Lucro total: R$ " + lucro);
    }
}
