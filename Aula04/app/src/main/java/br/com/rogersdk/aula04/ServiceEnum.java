package br.com.rogersdk.aula04;

/**
 * Created by rogerio on 10/09/16.
 */
public enum ServiceEnum {
    LIFECYCLE, BOUND, FOREGROUND;

    public static ServiceEnum fromOrdinal(int n) {return values()[n];}
}
