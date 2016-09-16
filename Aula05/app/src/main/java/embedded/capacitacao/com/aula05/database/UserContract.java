package embedded.capacitacao.com.aula05.database;

import android.provider.BaseColumns;

/**
 * UserContract.java
 *
 * Classe responsavel por ter as informacoes essenciais da tabela de User.
 *
 * Created by rogerio on 16/09/16.
 */
public final class UserContract {

    // o nome da tabela dentro do schema
    public static final String TABLE_NAME = "user";

    // Script de criacao da tabela
    public static final String SQL_CREATE_USER = "CREATE TABLE " + TABLE_NAME +
            "(" +
            UserEntry._ID + " INTEGER PRIMARY KEY, " +
            UserEntry.COLUMN_NAME + " STRING, " +
            UserEntry.COLUMN_EMAIL + " STRING, " +
            UserEntry.COLUMN_PASSWORD + " STRING " +
            ")";

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private UserContract() {}

    // Inner class que define o conteudo da tabela User
    public static class UserEntry implements BaseColumns {
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASSWORD = "password";

    }


}
