/*******************************************************************
 * Copyright (C) 2014 DL.                                           *
 * All rights, including trade secret rights, reserved.             *
 *******************************************************************/

package br.edu.ufcg.embedded.aula10.exception;

import com.android.volley.VolleyError;

/**
 * Created by rogerio on 05/10/16.
 */
public class ApiCallException extends VolleyError {

    public ApiCallException(String message) {
        super(message);
    }

}
