package com.capacitacao.embedded.aula03;

/**
 * Created by rogerio on 20/08/16.
 *
 * Um Simples POJO - Plain Old Java Object
 */
public class UserModel {

    private String name;
    private String email;

    public UserModel(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

}
