package com.capacitacao.embedded.aula03;

import java.io.Serializable;

/**
 * Created by rogerio on 20/08/16.
 *
 * Um Simples POJO - Plain Old Java Object
 *
 * Implementa a interface Serializable para poder ser passado como argumento no Bundle do fragment.
 */
public class UserModel implements Serializable {

    private String name;
    private String gender;
    private String birthdate;
    private String email;
    private String password;

    public UserModel(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UserModel(String name, String email, String gender, String birthDate, String password) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.birthdate = birthDate;
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
