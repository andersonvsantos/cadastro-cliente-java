package br.com.anderson.domain;

import java.util.Objects;

/**
 * @author Estudante
 */

public class Cliente {

    private String nome;
    private static Long cpf;
    private Long tel;
    private String endereco;
    private Integer numero;
    private String cidade;
    private String estado;

    public Cliente (String nome, String cpf, String tel, String endereco, String numero, String cidade, String estado) {

        this.nome = nome;
        this.cpf = Long.valueOf(cpf.trim());
        this.tel = Long.valueOf(tel.trim());
        this.endereco = endereco;
        this.numero = Integer.valueOf(numero);
        this.cidade = cidade;
        this.estado = estado;
    }

    public static Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(cpf, cliente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cpf);
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\n" +
                "Cpf: " + cpf + "\n" +
                "Contato: " + tel + "\n" +
                "Endereço: " + endereco + ", n° " + numero + " - " + cidade + "/" + estado;
    }
}
