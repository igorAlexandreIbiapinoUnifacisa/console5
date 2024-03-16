package br.com.igor.console5;

import br.com.igor.console5.entidades.Cliente;
import br.com.igor.console5.entidades.Produto;
import br.com.igor.console5.repositorios.ClienteRepository;
import br.com.igor.console5.repositorios.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class CafeteriaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CafeteriaApplication.class, args);
    }

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=================================================");
        System.out.println("****  BEM VINDO AO SISTEMA DA SUA CAFETERIA  ****");
        System.out.println("=================================================");
        Scanner teclado = new Scanner(System.in);
        while (true) {
            System.out.println("\n MENU DE OPÇÕES");
            System.out.println("1. ADICIONAR UM PRODUTO");
            System.out.println("2. LISTAR OS PRODUTOS");
            System.out.println("3. DELETAR UM PRODUTO");
            System.out.println("4. ALTERAR UM PRODUTO");
            System.out.println("================================================");
            System.out.println("\n MENU DO BANCO DE DADOS ");
            System.out.println("5. ADICIONAR UM CLIENTE");
            System.out.println("6. LISTAR OS CLIENTES");
            System.out.println("7. DELETAR UM CLIENTE");
            System.out.println("\n================================================");
            System.out.println("8. SAIR DO SISTEMA");
            System.out.println("================================================");
            System.out.print("DIGITE A SUA OPÇÃO DESEJADA: ");
            int opcao = Integer.parseInt(teclado.nextLine());

            if (opcao == 1) { // Adicionar um produto
                System.out.println("================================================");
                System.out.println("ADICIONAR UM PRODUTO");
                System.out.println("DIGITE O NOME PARA O PRODUTO");
                String nome = teclado.nextLine();
                System.out.println("DIGITE A DESCRIÇÃO PARA O PRODUTO");
                String descricao = teclado.nextLine();
                System.out.println("DIGITE O PREÇO DO PRODUTO");
                float preco = Float.parseFloat(teclado.nextLine());

                Produto novo = new Produto();
                novo.setNome(nome);
                novo.setDescricao(descricao);
                novo.setPreco(preco);

                produtoRepository.save(novo);

            } else if (opcao == 2) { // Listar os produtos
                System.out.println("================================================");
                System.out.println("LISTAR OS PRODUTOS");
                for (Produto p : produtoRepository.findAll()) {
                    System.out.println(p.getCodigo() + "," + p.getNome() + ", " + p.getDescricao() + ", " + p.getPreco());
                }

            } else if (opcao == 3) { // Deletar os produtos
                System.out.println("================================================");
                System.out.println("DELETAR UM PRODUTO");
                System.out.println("DIGITE O CÓDIGO DO PRODUTO QUE DESEJA DELETAR");
                Long id = Long.parseLong(teclado.nextLine());

                Optional<Produto> produtoOptional = produtoRepository.findById(id);
                if (produtoOptional.isPresent()) {
                    produtoRepository.delete(produtoOptional.get());
                    System.out.println(" O PRODUTO INFORMADO FOI DELETADO COM SUCESSO");

                } else {
                    System.out.println(" O PRODUTO INFORMADO NÃO FOI ENCONTRADO");
                }


            } else if (opcao == 4) { // Alterar um produto
                System.out.println("================================================");
                System.out.println("DIGITE O CÓDIGO DO PRODUTO QUE VOCÊ DESEJA ALTERAR");
                Long id = Long.parseLong(teclado.nextLine());

                Optional<Produto> produtoOptional = produtoRepository.findById(id);
                if (produtoOptional.isPresent()) {
                    Produto produto = produtoOptional.get();

                    boolean alterando = true;
                    while (alterando) {
                        System.out.println("SELECIONE O QUE DESEJA ALTERAR:");
                        System.out.println("1. NOME (ATUAL: " + produto.getNome() + ")");
                        System.out.println("2. DESCRIÇÃO (ATUAL: " + produto.getDescricao() + ")");
                        System.out.println("3. PREÇO (ATUAL: " + produto.getPreco() + ")");
                        System.out.println("4. VOLTAR AO MENU PRINCIPAL");

                        int escolha = Integer.parseInt(teclado.nextLine());
                        if (escolha == 1) {
                            System.out.println("DIGITE O NOME DO NOVO PRODUTO");
                            String novoNome = teclado.nextLine();
                            produto.setNome(novoNome);
                        } else if (escolha == 2) {
                            System.out.println("DIGITE A NOVA DESCRIÇÃO PARA O PRODUTO");
                            String novaDescricao = teclado.nextLine();
                            produto.setDescricao(novaDescricao);
                        } else if (escolha == 3) {
                            System.out.println("DIGITE O NOVO PREÇO PARA O PRODUTO");
                            float novoPreco = Float.parseFloat(teclado.nextLine());
                            produto.setPreco(novoPreco);
                        } else if (escolha == 4) {
                            alterando = false;

                        } else {
                            System.out.println(" OPÇÃO INVALIDA, POR FAVOR, ESCOLHA UMA OPÇÃO VÁLIDA");

                        }


                    }
                    produtoRepository.save(produto);
                    System.out.println(" PRODUTO FOI ALTERADO COM SUCESSO");

                } else {
                    System.out.println(" O PRODUTO INFORMADO NÃO FOI ENCONTRADO!");
                }


            } else if (opcao == 5) {//ADICIONAR NO BANCO DE DADOS
                System.out.println("================================================");
                System.out.println("ADICIONAR UM CLINE");
                System.out.println("DIGITE O NOME DO CLIENTE");
                String nomeCliente = teclado.nextLine();
                System.out.println("DIGITE O ENDEREÇO DO CLIENTE");
                String endereco = teclado.nextLine();
                System.out.println("DIGITE O TELEFONE DO CLIENTE");
                String telefone = teclado.nextLine();
                System.out.println("DIGITE O INSTAGRAM DO CLIENTE");
                String instagram = teclado.nextLine();

                Cliente novo = new Cliente();
                novo.setEndereco(endereco);
                novo.setNomeCliente(nomeCliente);
                novo.setTelefone(telefone);
                novo.setInstragram(instagram);

                clienteRepository.save(novo);


            } else if (opcao == 6) {
                System.out.println("================================================");
                System.out.println("LISTAR OS PRODUTOS");
                for (Cliente c : clienteRepository.findAll()) {
                    System.out.println(c.getId() + "," + c.getNomeCliente() + ", " + c.getEndereco() + ", " + c.getTelefone() + ", " + c.getInstragram());
                }
            } else if (opcao == 7) {
                System.out.println("================================================");
                System.out.println("DELETAR UM CLIENTE");
                System.out.println("DIGITE O ID DO CLIENTE QUE DESEJA DELETAR");
                Long id = Long.parseLong(teclado.nextLine());

                Optional<Cliente> clienteOptional = clienteRepository.findById(id);
                if (clienteOptional.isPresent()) {
                    clienteRepository.delete(clienteOptional.get());
                    System.out.println(" O CLIENTE INFORMADO FOI DELETADO COM SUCESSO");

                } else {
                    System.out.println(" O CLIENTE INFORMADO NÃO FOI ENCONTRADO");

                }
            } else if (opcao == 8) { // Sair
                System.out.println("================================================");
                System.out.println("SAINDO DO SISTEMA");
                break; // Sai do while

            } else {
                System.out.println("================================================");
                System.out.println("OPÇÃO INVALIDA!! \nPOR FAVOR SELECIONE UMA OPÇÃO VÁLIDA!!");
            }

        }
    }
}