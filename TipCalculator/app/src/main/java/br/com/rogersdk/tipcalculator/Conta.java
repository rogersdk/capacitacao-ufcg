package br.com.rogersdk.tipcalculator;

/**
 * Conta.java
 *
 * Classe responsável por representar uma Conta e fazer todos os cálculos necessários para a mesma.
 *
 */
public class Conta {

    private double consumido;
    private int porcentagemGorjeta;
    private int numeroDePessoas;

    public Conta() { }

    public Conta(String consumido, int porcentagemGorjeta, String numeroDePessoas) throws IllegalArgumentException {

        /**
         * Validação para a verificação do valor da entrada do atributo "consumido"
         * */
        if(consumido.trim().equals("")) {
            throw new IllegalArgumentException("Valor consumido nao pode ser vazio");
        }

        /**
         * Validação para a verificação do valor da entrada do atributo "numeroDePessoas"
         * */
        if(numeroDePessoas.trim().equals("")) {
            throw new IllegalArgumentException("Valor numeroDePessoas nao pode ser vazio");
        }

        this.consumido = Double.valueOf(consumido);

        this.porcentagemGorjeta = porcentagemGorjeta;


        this.numeroDePessoas = Integer.valueOf(numeroDePessoas);
    }


    /**
     * Método responsável por calcular o valor total da conta individualmente.
     * */
    public double calcularValorPorPessoa() {
        return calcularValorTotal() / numeroDePessoas;
    }

    /**
     * Método responsável por calcular o valor total da conta.
     * */
    public double calcularValorTotal() {
        return (consumido + (consumido * porcentagemGorjeta * 0.01) );
    }


}
