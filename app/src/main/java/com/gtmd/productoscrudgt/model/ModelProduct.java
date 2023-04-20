package com.gtmd.productoscrudgt.model;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.gtmd.productoscrudgt.conectionSQL.SitemaSQLiteHelper;

import java.util.ArrayList;

public class ModelProduct extends Product{

    public boolean saveProduct(Context prodict){
        try {
            SitemaSQLiteHelper cp = new SitemaSQLiteHelper(prodict);
            String sql = "INSERT INTO t_product(codigo,nombre,precio) VALUES ('"+getCodigo()+"','"+getNombre()+"','"+getPrecio()+"')";
            Toast.makeText(prodict, ""+sql, Toast.LENGTH_SHORT).show();
            cp.getWritableDatabase().execSQL(sql);
            return true;
        }catch (Exception e){
            e.toString();
            return false;
        }
    }

    public ArrayList<Product> Listar(Context person){
        ArrayList<Product> listarUsuario = new ArrayList<>();
        SitemaSQLiteHelper cp = new SitemaSQLiteHelper(person);
        Cursor cursobd = cp.getReadableDatabase().rawQuery("select * from t_product", null);

        if(cursobd.moveToFirst()){
            do {
                Product user = new Product();
                user.setId_product(cursobd.getInt(0));
                user.setCodigo(cursobd.getString(1));
                user.setNombre(cursobd.getString(2));
                user.setPrecio(Double.parseDouble(cursobd.getString(3)));
                listarUsuario.add(user);
            }while (cursobd.moveToNext());
        }
        return listarUsuario;
    }

}
