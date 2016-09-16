package embedded.capacitacao.com.aula05.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Aula05DbHelper.java
 *
 * Classe responsavel por criar o schema da base de dados.
 *
 * Created by rogerio on 16/09/16.
 */
public class Aula05DbHelper extends SQLiteOpenHelper {

    public final static String DATABASE_NAME = "aula05.db";

    // se o schema do seu banco de dados mudar, voce deve incrementar a versao.
    public final static int DATABASE_VERSION = 1;


    public Aula05DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Chamado quando a base de dados é criada pela primeira vez. Deve-se colocar aqui
     * a criação das tabelas como também alguma população inicial.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(UserContract.SQL_CREATE_USER);
    }

    /**
     * Chamado quando a base de dados precisa ser atualizada. Sua implementacao deve utilizar esse
     * metodo para dropar tabelas, adicionar tabelas, ou fazer qualquer coisa que precise para
     * atualizar o schema para a nova versão.
     *
     * */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
