/**
 * @author Estudante
 */
package br.com.anderson;

import br.com.anderson.dao.ClienteMapDAO;
import br.com.anderson.dao.IClienteDAO;
import br.com.anderson.domain.Cliente;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class App {

    public static IClienteDAO iClienteDAO;

    public static void main(String args[]) {
        iClienteDAO = new ClienteMapDAO();

        String opcao = selecionarOpcao();

        while (!isOpcaoValida(opcao)) {
            opcao = selecionarOpcao();
        }

        while (isOpcaoValida(opcao)) {
            if (isOpcaoSair(opcao)) {
                sair();
            } else if (isOpcaoCadastrar(opcao)) {
                String dados = JOptionPane.showInputDialog(null, "Informe os dados do cliente separados por virgulas, siga o exemplo: Nome,CPF,Telefone,Endereço,Número,Cidade,Estado", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                cadastrar(dados);
            } else if (isOpcaoConsultar(opcao)) {
                String numeroCpf = JOptionPane.showInputDialog(null, "Por favor informar o cpf a ser consultado: ", "Buscar cliente", JOptionPane.INFORMATION_MESSAGE);
                buscar(numeroCpf);
            } else if (isOpcaoExcluir(opcao)) {
                String numeroCpf = JOptionPane.showInputDialog(null, "Por favor informar o cpf a ser excluído: ", "Excluir cliente", JOptionPane.INFORMATION_MESSAGE);
                excluir(numeroCpf);
            } else if (isOpcaoAlterar(opcao)) {
                String numeroCpf = JOptionPane.showInputDialog(null, "Por favor informar o cpf a ser alterado: ", "Alterar dados cliente", JOptionPane.INFORMATION_MESSAGE);
                alterar(numeroCpf);
            }
            opcao = selecionarOpcao();

            while (!isOpcaoValida(opcao)) {
                opcao = selecionarOpcao();
            }
        }
    }

    private static void cadastrar(String dados) {
        String[] dadosSeparados = dados.split(",");

        Cliente cliente = new Cliente(dadosSeparados[0], dadosSeparados[1], dadosSeparados[2], dadosSeparados[3], dadosSeparados[4], dadosSeparados[5], dadosSeparados[6]);
        Boolean isCadastrado = iClienteDAO.cadastrar(cliente);

        if(isCadastrado) {
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente já se encontra cadastrado!", "Erro!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void buscar(String numeroCpf) {
        if (numeroCpf.equals("")) {
            JOptionPane.showMessageDialog(null, iClienteDAO.buscarTodos(), "Dados dos clientes!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            Long cpfLong = Long.parseLong(numeroCpf);
            Cliente clienteCadastrado = iClienteDAO.buscar(cpfLong);

            if (clienteCadastrado == null) {
                JOptionPane.showMessageDialog(null, "CPF não encontrado na base de dados!", "Erro!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, clienteCadastrado, "Dados do cliente!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private static void excluir(String numeroCpf) {
        Long cpfLong = Long.parseLong(numeroCpf);
        iClienteDAO.excluir(cpfLong);
        JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void alterar(String numeroCpf) {
        Cliente clienteCadastrado = iClienteDAO.buscar(Long.parseLong(numeroCpf));

        if (clienteCadastrado != null) {
            String dadosAtualizados = JOptionPane.showInputDialog(null, "Informe os dados do cliente separados por virgulas, siga o exemplo: Nome,Telefone,Endereço,Número,Cidade,Estado", "Alterar dados", JOptionPane.INFORMATION_MESSAGE);
            String[] dadosSeparados = dadosAtualizados.split(",");

            Cliente clienteAtualizado = new Cliente(dadosSeparados[0], numeroCpf, dadosSeparados[1], dadosSeparados[2], dadosSeparados[3], dadosSeparados[4], dadosSeparados[5]);
            iClienteDAO.alterar(clienteAtualizado);
            JOptionPane.showMessageDialog(null, "Dados altualizados com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "CPF   não encontrado na base de dados!", "Erro!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void sair() {
        JOptionPane.showMessageDialog(null, "Saindo...");
        System.exit(0);
    }

    private static boolean isOpcaoCadastrar(String opcao) {
        if ("1".equals(opcao)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isOpcaoConsultar(String opcao) {
        if ("2".equals(opcao)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isOpcaoExcluir(String opcao) {
        if ("3".equals(opcao)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isOpcaoAlterar(String opcao) {
        if ("4".equals(opcao)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isOpcaoSair(String opcao) {
        if ("5".equals(opcao)) {
            return true;
        } else  {
            return false;
        }
    }

    private static boolean isOpcaoValida(String opcao) {
        if ("1".equals(opcao) || "2".equals(opcao) || "3".equals(opcao) || "4".equals(opcao) || "5".equals(opcao)) {
            return true;
        } else {
            return false;
        }
    }

    private static String selecionarOpcao() {
        return JOptionPane.showInputDialog(null, "Selecione a opção desejada: \n " + "[1] Cadastrar, [2] Consultar, [3] Excluir, [4] Alterar, [5] Sair", "Sistema de cadastro", JOptionPane.INFORMATION_MESSAGE);
    }
}
