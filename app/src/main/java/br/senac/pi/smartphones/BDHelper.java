package br.senac.pi.smartphones;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aluno on 20/11/2015.
 */
public class BDHelper extends SQLiteOpenHelper {
    private static final String TAG = "sql";
    //Nome do banco
    private static final String NOME_BANCO = "loja.sqlite";
    private static final int VERSAO_BANCO = 2;

    public BDHelper(Context context){
        //context, nome do banco, factory, versão
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS celular(id integer primary key autoincrement, modelo text, fabricante text, preco decimal(7,2));");
            }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE celular;");
        onCreate(db);
    }
    public long insert(Celular celular){
        long id = celular.getId();
        SQLiteDatabase db = getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put("modelo", celular.getModelo());
            values.put("fabricante", celular.getFabricante());
            values.put("preco",celular.getPreco());
            if (id != 0){
                String Id = String.valueOf(celular.getId());
                String[] whereargs = new String[]{Id};

                int count = db.update("celular",values, "id=?", whereargs);
                return count;
            }else{
                id = db.insert("celular","",values);
                return id;
            }
        }finally {
            db.close();
        }
    }

    public int delete(Celular celular){
        SQLiteDatabase db = getWritableDatabase();
        try{
            int count = db.delete("celular","id=?",new String[]{String.valueOf(celular.getId())});
            return count;
        }finally {
            db.close();
        }
    }

     public List<Celular> seleciona(){
        SQLiteDatabase db = getWritableDatabase();
        try{
            Cursor cursor = db.query("celular", null, null, null, null, null, null, null);
            return Lista(cursor);
        }finally {
            db.close();
        }
    }

    public List<Celular> Lista(Cursor cursor){
        List<Celular> listCelular = new ArrayList<Celular>();
        if (cursor.moveToFirst()){
            do {
                Celular celular = new Celular();
                listCelular.add(celular);
                celular.setId( cursor.getInt(cursor.getColumnIndex("id")));
                celular.setModelo(cursor.getString(cursor.getColumnIndex("modelo")));
                celular.setFabricante(cursor.getString(cursor.getColumnIndex("fabricante")));
                celular.setPreco(cursor.getDouble(cursor.getColumnIndex("fabricante")));

            }while (cursor.moveToNext());
        }
        return listCelular;
    }
}
