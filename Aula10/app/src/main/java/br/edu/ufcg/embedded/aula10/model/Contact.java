package br.edu.ufcg.embedded.aula10.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by rogerio on 05/10/16.
 */
public class Contact implements Serializable {
    @SerializedName("_id")
    private String id;
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String userId;

    public Contact() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
