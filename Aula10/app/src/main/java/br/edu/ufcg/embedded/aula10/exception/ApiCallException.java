/*******************************************************************
 * Copyright (C) 2014 DL.                                           *
 * All rights, including trade secret rights, reserved.             *
 *******************************************************************/

package br.edu.ufcg.embedded.aula10.exception;

import com.android.volley.VolleyError;

/**
 * ApiCallException.java
 *
 * Classe responsável por cuidar das mensagens enviadas pelo servidor como resposta às requisições.
 *
 */
public class ApiCallException extends VolleyError {

    public ApiCallException(String message) {
        super(message);
    }

}
