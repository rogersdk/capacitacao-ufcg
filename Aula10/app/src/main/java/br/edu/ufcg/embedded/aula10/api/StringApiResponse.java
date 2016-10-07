package br.edu.ufcg.embedded.aula10.api;

/**
 * Created by rogerio on 06/10/16.
 */
public class StringApiResponse {
    private String message;

    public StringApiResponse() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
