package br.com.rogersdk.tipcalculator;

/**
 * Created by rogerio on 17/10/16.
 */
public class Conta {

    private double consumido;
    private int porcentagemGorjeta;
    private int numeroDePessoas;


    public Conta() {

    }

    public Conta(String consumido, int porcentagemGorjeta, String numeroDePessoas) throws IllegalArgumentException {
        if(consumido.trim().equals("")) {
            throw new IllegalArgumentException("Valor consumido nao pode ser vazio");
        }

        this.consumido = Double.valueOf(consumido);

        this.porcentagemGorjeta = porcentagemGorjeta;

        if(numeroDePessoas.trim().equals("")) {
            throw new IllegalArgumentException("Valor numeroDePessoas nao pode ser vazio");
        }
        this.numeroDePessoas = Integer.valueOf(numeroDePessoas);
    }


    public double calcularValorPorPessoa() {
        return calcularValorTotal() / numeroDePessoas;
    }

    public double calcularValorTotal() {
        return (consumido + (consumido * porcentagemGorjeta) );
    }


}
