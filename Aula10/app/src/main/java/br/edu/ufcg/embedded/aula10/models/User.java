package br.edu.ufcg.embedded.aula10.models;

/**
 * Created by rogerio on 04/10/16.
 */
public class User {

    public static final String getUserUrl = "http://ec2-52-67-176-213.sa-east-1.compute.amazonaws.com:9000/api/v1/users/";

    private String id;
    private String email;

    public User() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
