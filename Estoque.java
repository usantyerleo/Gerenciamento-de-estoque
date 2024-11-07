import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private List<Produto> produtos;

    public Estoque() {
        produtos = new ArrayList<>();
    }

    public void registrarProduto(Produto produto) {
        produtos.add(produto);
        System.out.println("Produto registrado com sucesso.");
    }

    public Produto pesquisarProduto(String nome) throws ProdutoNaoEncontradoException {
        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nome)) {
                return produto;
            }
        }
        throw new ProdutoNaoEncontradoException("Produto " + nome + " não encontrado.");
    }

    //Método para pesquisar produto pelo nome e tipo (ignora maiúsculas/minúsculas)
    public Produto pesquisarProdutoPorNomeETipo(String nome, String tipo) throws ProdutoNaoEncontradoException {
        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nome) && produto.getTipo().equalsIgnoreCase(tipo)) {
                return produto;
            }
        }
        throw new ProdutoNaoEncontradoException("Produto com nome '" + nome + "' e tipo '" + tipo + "' não encontrado.");
    }

    public void atualizarProduto(String nome, int novaQuantidade, double novoPreco) throws ProdutoNaoEncontradoException {
        Produto produto = pesquisarProduto(nome);
        produto.setQuantidade(novaQuantidade);
        produto.setPreco(novoPreco);
        System.out.println("Produto atualizado com sucesso.");
    }

    public void excluirProduto(String nome, String tipo) throws ProdutoNaoEncontradoException {
        // Chama o método de pesquisa para encontrar o produto pelo nome e tipo
        Produto produto = pesquisarProdutoPorNomeETipo(nome, tipo);

        // Remove o produto encontrado da lista
        produtos.remove(produto);
        System.out.println("Produto excluído com sucesso.");
    }

    public void gerarRelatorio() {
        System.out.println("**********************");
        System.out.println("Relatório de Estoque:");
        for (Produto produto : produtos) {
            System.out.println(produto);
            System.out.println("**********************");
        }
    }
}
