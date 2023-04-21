package com.gtmd.productoscrudgt;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gtmd.productoscrudgt.Adapter.AdapterRecord;
import com.gtmd.productoscrudgt.model.ModelProduct;

public class Menusl extends AppCompatActivity {

    private FloatingActionButton addRecordBtn;

    //RecyclerView
    private RecyclerView recordsRv;

    private ActionBar actionBar;

    //Boolean que permite el eliminado del user.
    private boolean deleteUser = false;

    private AlertDialog alertDialog;

    private AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menusl);

        try {
            addRecordBtn = findViewById(R.id.addRecordBtn);
            recordsRv = findViewById(R.id.recordsRv);
            actionBar = getSupportActionBar();
            actionBar.setTitle("Administración de productos");
        }catch (Exception e){
            Toast.makeText(this, "Err-> "+e.getMessage()+ " ", Toast.LENGTH_SHORT).show();
        }
        // Cargando Registros
        loadRecords();

        addRecordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Iniciar la Activity
                Intent intent = new Intent(getApplicationContext(), agregarproducto.class);
                intent.putExtra("EDIT_USER", false);
                startActivity(intent);
            }
        });
    }

    public void loadRecords(){
        try {
            ModelProduct modeloUsuario = new ModelProduct();
            AdapterRecord adapterRecord = new AdapterRecord(Menusl.this,
                    modeloUsuario.Listar(getApplicationContext()));
            recordsRv.setAdapter(adapterRecord);
            Toast.makeText(this, ""+modeloUsuario.getRecordsCount(getApplicationContext()), Toast.LENGTH_SHORT).show();
            //Establecer el numero de Registros
            actionBar.setSubtitle("Total: "+modeloUsuario.getRecordsCount(getApplicationContext()));
        }catch (Exception e){
            Toast.makeText(this, "Err Load-> "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private void searchRecords(String query){
        try {
            ModelProduct modelProduct = new ModelProduct();
            AdapterRecord adapterRecord = new AdapterRecord(Menusl.this,
                    modelProduct.searchLike(getApplicationContext(), query));
            recordsRv.setAdapter(adapterRecord);
        }catch (Exception e){
            System.out.println("Problemas con la busqueda-> "+e.getMessage());
        }

    }
    @Override
    public void onResume(){ // protected
        super.onResume();
        loadRecords();
        deleteProduct();
    }

    private void deleteProduct(){
        String codIsPresent =  AdapterRecord.cod;
        if(!(codIsPresent == null)){
            builder = new AlertDialog.Builder(Menusl.this);
            builder.setTitle("Delete Porduct"); // set Title
            builder.setMessage("Sure in delete product?");  // set message
            builder.setCancelable(true); //  Sets whether the dialog is cancelable or not

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.cancel();
                            deleteProduct(codIsPresent);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.cancel();
                            AdapterRecord.cod = null;
                        }
                    });
            alertDialog = builder.create();
            alertDialog.show();

        }else{
            //Si el valir me llega null entonces no se mandara ninguna warning..
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchRecords(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchRecords(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void deleteProduct(String codigo){
        ModelProduct usuario = new ModelProduct();
        if(usuario.deleteProdcut(getApplicationContext(), codigo)){
            AdapterRecord.cod = null;
            Toast.makeText(getApplicationContext(), "Succesful of delete product", Toast.LENGTH_SHORT).show();
            loadRecords();
        }else{
            Toast.makeText(getApplicationContext(), "Err delete product", Toast.LENGTH_SHORT).show();
        }
    }
}