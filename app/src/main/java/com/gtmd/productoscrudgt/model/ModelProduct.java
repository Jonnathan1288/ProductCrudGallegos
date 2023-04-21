package com.gtmd.productoscrudgt.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.gtmd.productoscrudgt.conectionSQL.SitemaSQLiteHelper;

import java.util.ArrayList;

public class ModelProduct extends Product{

    public ModelProduct() {
    }

    public ModelProduct(int id_product, String codigo, String nombre, double precio) {
        super(id_product, codigo, nombre, precio);
    }

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

    public boolean updateProduct(Context product, String cod){
        try {
            SitemaSQLiteHelper cp = new SitemaSQLiteHelper(product);
            String sql = "UPDATE t_product set nombre='"+getNombre()+"', precio='"+getPrecio()+"' where codigo = '"+cod+"'";
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

    public ArrayList<Product> searchLike(Context product, String id){
        ArrayList<Product> productResult = new ArrayList<>();
        SitemaSQLiteHelper cp = new SitemaSQLiteHelper(product);
        Cursor cursobd = cp.getReadableDatabase().rawQuery("select * from t_product where codigo like '"+id+"%' or nombre like '"+id+"%'", null);

        if(cursobd.moveToFirst()){
            do {
                Product product1 = new Product();
                product1.setId_product(cursobd.getInt(0));
                product1.setCodigo(cursobd.getString(1));
                product1.setNombre(cursobd.getString(2));
                product1.setPrecio(Double.parseDouble(cursobd.getString(3)));

                productResult.add(product1);
            }while (cursobd.moveToNext());
        }
        return productResult;
    }

    public boolean deleteProdcut(Context context, String cod){
        try {
            SitemaSQLiteHelper cp = new SitemaSQLiteHelper(context);
            SQLiteDatabase db = cp.getWritableDatabase();
            String sql = "DELETE FROM t_product WHERE codigo = '" + cod + "'";
            System.out.println(sql);
            db.execSQL(sql);
            return true;
        }catch (Exception e){
            e.toString();
            return false;
        }
    }

    public int getRecordsCount(Context product){
        SitemaSQLiteHelper cp = new SitemaSQLiteHelper(product);
        Cursor cursobd = cp.getReadableDatabase().rawQuery("select * from t_product", null);
        int count = cursobd.getCount();
        cursobd.close();
        return count;
    }

}
