import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Estoque estoque = new Estoque();
        Sistema sistema = new Sistema();
        Scanner scanner = new Scanner(System.in);
        int opcao = -1; // Inicializa a variável opcao com um valor inválido

        do {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Pesquisar Produto");
            System.out.println("3. Atualizar Produto");
            System.out.println("4. Excluir Produto");
            System.out.println("5. Gerar Relatório de Estoque");
            System.out.println("6. Registrar Venda");
            System.out.println("7. Gerar Relatório de Vendas");
            System.out.println("8. Sair");
            System.out.print("Opção: ");

            // Solicita a opção até que seja válida
            while (true) {
                try {
                    opcao = scanner.nextInt(); // Tenta ler a opção
                    break; // Sai do loop se a leitura for bem-sucedida
                } catch (InputMismatchException e) {
                    System.err.println("Erro: A opção deve ser um número inteiro. Tente novamente.");
                    scanner.nextLine(); // Limpa o buffer do scanner
                }
            }

            switch (opcao) {
                case 1: // Adicionar Produto
                    System.out.print("Digite o nome do produto: ");
                    String nome = scanner.next();
                    System.out.print("Digite a quantidade: ");
                    int quantidade = 0;
                    while (true) {
                        try {
                            quantidade = scanner.nextInt();
                            break; // Sai do loop se a quantidade for válida
                        } catch (InputMismatchException e) {
                            System.err.println("Erro: A quantidade deve ser um número inteiro. Tente novamente.");
                            scanner.nextLine(); // Limpa o buffer do scanner
                        }
                    }
                    System.out.print("Digite o preço: ");
                    double preco = 0;
                    while (true) {
                        try {
                            preco = scanner.nextDouble();
                            break; // Sai do loop se o preço for válido
                        } catch (InputMismatchException e) {
                            System.err.println("Erro: O preço deve ser um número decimal. Tente novamente.");
                            scanner.nextLine(); // Limpa o buffer do scanner
                        }
                    }
                    System.out.print("Digite o tipo do produto (ex: Detergente Líquido): ");
                    String tipo = scanner.next();

                    // Normaliza nome e tipo para comparar independentemente de maiúsculas ou minúsculas
                    String nomeNormalizado = nome.toLowerCase();
                    String tipoNormalizado = tipo.toLowerCase();

                    try {
                        Produto produtoExistente = estoque.pesquisarProdutoPorNomeETipo(nomeNormalizado, tipoNormalizado);

                        if (produtoExistente != null) {
                            System.err.println("Erro: Já existe um produto com o nome '" + nome + "' e tipo '" + tipo + "' no estoque.");
                        } else {
                            Produto produto = new ProdutoLimpeza(nome, quantidade, preco, tipo);
                            estoque.registrarProduto(produto);
                            System.out.println("Produto registrado com sucesso.");
                        }
                    } catch (ProdutoNaoEncontradoException e) {
                        Produto produto = new ProdutoLimpeza(nome, quantidade, preco, tipo);
                        estoque.registrarProduto(produto);
                        System.out.println("Produto registrado com sucesso.");
                    }
                    break;

                case 2: // Pesquisar Produto
                    System.out.print("Digite o nome do produto a pesquisar: ");
                    String nomePesquisado = scanner.next();
                    System.out.print("Digite o tipo do produto a pesquisar: ");
                    String tipoPesquisado = scanner.next();

                    try {
                        Produto produtoPesquisado = estoque.pesquisarProdutoPorNomeETipo(nomePesquisado, tipoPesquisado);
                        System.out.println("Produto encontrado: " + produtoPesquisado);
                    } catch (ProdutoNaoEncontradoException e) {
                        System.err.println("Erro: Produto não encontrado.");
                    }
                    break;

                case 3: // Atualizar Produto
                    System.out.print("Digite o nome do produto a atualizar: ");
                    String nomeAtualizar = scanner.next();
                    System.out.print("Digite o tipo do produto a atualizar: ");
                    String tipoAtualizar = scanner.next();

                    try {
                        Produto produtoExistente = estoque.pesquisarProdutoPorNomeETipo(nomeAtualizar, tipoAtualizar);
                        System.out.print("Digite a nova quantidade: ");
                        int novaQuantidade = 0;
                        while (true) {
                            try {
                                novaQuantidade = scanner.nextInt();
                                break; // Sai do loop se a quantidade for válida
                            } catch (InputMismatchException e) {
                                System.err.println("Erro: A quantidade deve ser um número inteiro. Tente novamente.");
                                scanner.nextLine(); // Limpa o buffer do scanner
                            }
                        }
                        System.out.print("Digite o novo preço: ");
                        double novoPreco = 0;
                        while (true) {
                            try {
                                novoPreco = scanner.nextDouble();
                                break; // Sai do loop se o preço for válido
                            } catch (InputMismatchException e) {
                                System.err.println("Erro: O preço deve ser um número decimal. Tente novamente.");
                                scanner.nextLine(); // Limpa o buffer do scanner
                            }
                        }

                        estoque.atualizarProduto(produtoExistente.getNome(), novaQuantidade, novoPreco);
                        System.out.println("Produto atualizado com sucesso.");
                    } catch (ProdutoNaoEncontradoException e) {
                        System.err.println("Erro: Produto não encontrado.");
                    }
                    break;

                case 4: // Excluir Produto
                    System.out.print("Digite o nome do produto a excluir: ");
                    String nomeExcluir = scanner.next();
                    System.out.print("Digite o tipo do produto a excluir: ");
                    String tipoExcluir = scanner.next();

                    try {
                        Produto produtoExcluir = estoque.pesquisarProdutoPorNomeETipo(nomeExcluir, tipoExcluir);
                        estoque.excluirProduto(produtoExcluir.getNome(), produtoExcluir.getTipo());
                        System.out.println("Produto excluído com sucesso.");
                    } catch (ProdutoNaoEncontradoException e) {
                        System.err.println("Erro: Produto não encontrado.");
                    }
                    break;

                case 5: // Gerar Relatório de Estoque
                    estoque.gerarRelatorio();
                    break;

                case 6: // Registrar Venda
                    System.out.print("Digite o nome do produto para venda: ");
                    String nomeVenda = scanner.next();
                    System.out.print("Digite o tipo do produto para venda: ");
                    String tipoVenda = scanner.next();

                    try {
                        Produto produtoVenda = estoque.pesquisarProdutoPorNomeETipo(nomeVenda, tipoVenda);
                        System.out.print("Digite a quantidade a vender: ");
                        int quantidadeVenda = 0;
                        while (true) {
                            try {
                                quantidadeVenda = scanner.nextInt();
                                break; // Sai do loop se a quantidade for válida
                            } catch (InputMismatchException e) {
                                System.err.println("Erro: A quantidade deve ser um número inteiro. Tente novamente.");
                                scanner.nextLine(); // Limpa o buffer do scanner
                            }
                        }

                        if (quantidadeVenda > produtoVenda.getQuantidade()) {
                            System.err.println("Erro: Não há quantidade suficiente em estoque.");
                        } else {
                            sistema.registrarVenda(produtoVenda, quantidadeVenda);
                            System.out.println("Venda registrada com sucesso.");
                        }
                    } catch (ProdutoNaoEncontradoException e) {
                        System.err.println("Erro: Produto não encontrado no estoque.");
                    }
                    break;

                case 7: // Gerar Relatório de Vendas
                    sistema.gerarRelatorioVendas();
                    break;

                case 8: // Sair
                    System.out.println("Saindo do sistema.");
                    break;

                default:
                    System.err.println("Erro: Opção inválida. Tente novamente.");
            }
        } while (opcao != 8);

        scanner.close();
    }
}
