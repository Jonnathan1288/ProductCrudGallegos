package com.gtmd.productoscrudgt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.gtmd.productoscrudgt.model.ModelProduct;
import com.gtmd.productoscrudgt.model.Product;

import java.util.ArrayList;
import java.util.List;

public class listproduct extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listproduct);
        asignacion_variables();
        Listarpersonas();
    }

    private void asignacion_variables(){
        listView = (ListView) findViewById(R.id.listViewsalida);
    }

    private void Listarpersonas(){
        ModelProduct persona = new ModelProduct();
        List<Product> listap = persona.Listar(getApplicationContext());
        ArrayList<Product> lis = new ArrayList<>();

        //ArrayList<Persona> listapersona = new ArrayList<Persona>();
        for (int i = 0; i < listap.size(); i++){
            Product p = new Product();
            p.setCodigo(listap.get(i).getCodigo());
            p.setNombre(listap.get(i).getNombre());
            p.setPrecio(listap.get(i).getPrecio());
            lis.add(p);
            Toast.makeText(this, listap.get(i).getCodigo()+" "+listap.get(i).getNombre()+" "+listap.get(i).getPrecio(), Toast.LENGTH_SHORT).show();
        }
        ArrayAdapter adapter =new ArrayAdapter(this, R.layout.list_item_gt, lis);
        listView.setAdapter(adapter);


        for (int i = 0; i < listap.size(); i++){
            Toast.makeText(this, listap.get(i).getCodigo()+" "+listap.get(i).getNombre()+" "+listap.get(i).getPrecio(), Toast.LENGTH_SHORT).show();

        }
    }

}