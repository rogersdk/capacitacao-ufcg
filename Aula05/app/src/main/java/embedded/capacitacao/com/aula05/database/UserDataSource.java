package embedded.capacitacao.com.aula05.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import embedded.capacitacao.com.aula05.User;

/**
 * UserDataSource.java
 *
 * Classe responsavel por interagir com o banco de dados de User
 *
 * Created by rogerio on 16/09/16.
 */
public class UserDataSource {

    private Aula05DbHelper dbHelper;

    /**
     * Declaração de todas as colunas do objeto
     * */
    private String[] allColumns = {
            UserContract.UserEntry.COLUMN_NAME,
            UserContract.UserEntry.COLUMN_EMAIL,
            UserContract.UserEntry.COLUMN_PASSWORD,
    };

    public UserDataSource(Context context) {
        dbHelper = new Aula05DbHelper(context);
    }

    /**
     * Insere um novo User no banco de dados e retorna um long (ID) do User no banco de dados.
     *
     * Instancia-se um ContentValues objeto, que é um mapa de chave-valor da tabela de User no
     * banco de dados.
     * */
    public long addNewUser(String name, String email, String password) {

        ContentValues values = new ContentValues();
        values.put(UserContract.UserEntry.COLUMN_NAME, name);
        values.put(UserContract.UserEntry.COLUMN_EMAIL, email);
        values.put(UserContract.UserEntry.COLUMN_PASSWORD, password);


        return dbHelper.getWritableDatabase().insert(UserContract.TABLE_NAME, null, values);
    }

    /**
     * Retorna uma lista de todos os usuários.
     *
     * 1 - Cria uma lista vazia;
     * 2 - Instancia o cursor com seus parametros;
     * 3 - Itera o cursor ate o fim adicionando os itens a lista;
     * 4 - retorna a lista.
     * */
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        /**
         * Cursor que tem por finalidade interagir na busca de User no banco de dados.
         *
         * @param1 -    Nome da coluna
         * @param2 -    As colunas a serem retornadas (Ex: name, email, password...)
         * @param3 -    Colunas para clausula Where (Ex: where name = 'Exemplo')
         *              tudo que tem 'name' Exemplo é buscado no database.
         * @param4 -    Função de agrupamento de dados (Ex: Count)
         * @param5 -    Filtrar retorno por grupo de linhas (Ex: Busca tudo entre os registros
         *              10 e 50).
         * @param6 -    Ordem dos resultados ascendente = ASC e descendente = DESC
         * */
        Cursor cursor = dbHelper.getReadableDatabase().query(
                UserContract.TABLE_NAME,                     // The table to query
                allColumns,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        cursor.moveToFirst();

        /**
         * Retorna true se o cursor estiver apontando para a posicao depois do ultimo
         * */
        while(!cursor.isAfterLast()) {
            User user = parseCursor(cursor);
            users.add(user);
            cursor.moveToNext();
        }

        cursor.close();
        return users;
    }

    /**
     * Função responsável por transformar o Cursor em um POJO
     * */
    private User parseCursor(Cursor cursor) {
        User u = new User();

        u.setName(cursor.getString(0));
        u.setEmail(cursor.getString(1));
        u.setPassword(cursor.getString(2));

        return u;
    }

}
