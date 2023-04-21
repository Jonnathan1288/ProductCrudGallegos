package com.gtmd.productoscrudgt;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gtmd.productoscrudgt.model.ModelProduct;

import de.hdodenhof.circleimageview.CircleImageView;

public class agregarproducto extends AppCompatActivity {

    //Implement references view
    private EditText etcod, etnombre, etprcecio;

    private FloatingActionButton saveBtn, cancelBtn;

    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;

    //Actionbar
    private ActionBar actionBar;
    private boolean editUser = false;

    private String mensajeTextoEdit = "Seguro en cancelar ";

    private CircleImageView profileIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregarproducto);
        assing_references();
        event_button();

        actionBar = getSupportActionBar();
        //Titulo
        actionBar.setTitle("Agregar Producto");
        //Boton Negro
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        editUser = intent.getBooleanExtra("EDIT_USER", false);

        if(editUser){
            actionBar.setTitle("Modificar Registro");
            intent();
        }
    }
    private void intent(){
        try {
            etcod.setEnabled(false);
            Intent intent = getIntent();
            etcod.setText(intent.getStringExtra("CODIGO"));
            etnombre.setText(intent.getStringExtra("NOMBRE"));
            etprcecio.setText(intent.getStringExtra("PRECIO"));
            Toast.makeText(this, "cod-> "+intent.getStringExtra("PRECIO"), Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, "Err int-> "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    private void assing_references(){
        etcod = (EditText) findViewById(R.id.codigoProd_AG);
        etnombre = (EditText) findViewById(R.id.nombreProd_AG);
        etprcecio = (EditText) findViewById(R.id.precioProd_AG);

        saveBtn = (FloatingActionButton) findViewById(R.id.saveBtn);
        cancelBtn = (FloatingActionButton) findViewById(R.id.btnCancelarSave);

    }

    private void event_button() {
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateField();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), Menusl.class);
                startActivity(intent);
            }
        });
    }

    private void validateField(){
        if(etcod.getText().toString().isEmpty() || etnombre.getText().toString().isEmpty() || etprcecio.getText().toString().isEmpty()){
            builder = new AlertDialog.Builder(agregarproducto.this);
            builder.setTitle("Datos incompletos"); // set Title
            builder.setMessage("Debe ingresar todos los campos.");  // set message
            builder.setCancelable(true); //  Sets whether the dialog is cancelable or not
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.cancel();
                }
            });
            alertDialog = builder.create();
            alertDialog.show();
        }else{
            if(editUser){
                builder = new AlertDialog.Builder(agregarproducto.this);
                builder.setTitle("Modificar"); // set Title
                builder.setMessage("Confirmar cambios");  // set message
                builder.setCancelable(true); //  Sets whether the dialog is cancelable or not

                builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialog.cancel();
                                CreateUpdateProduct(2);
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialog.cancel();
                            }
                        });
                alertDialog = builder.create();
                alertDialog.show();
            }else {
                CreateUpdateProduct(1);
            }
        }
    }


    private void CreateUpdateProduct(int codigo){
        ModelProduct product = new ModelProduct();
        product.setCodigo(etcod.getText().toString());
        product.setNombre(etnombre.getText().toString());
        product.setPrecio(Double.parseDouble(etprcecio.getText().toString()));

        try{
            if(codigo == 1){
                if(product.saveProduct(getApplicationContext())){
                    Toast.makeText(getApplicationContext(), "Product ssucesful crated..", Toast.LENGTH_SHORT).show();
                    Intent inte = new Intent(getApplicationContext(), Menusl.class);
                    startActivity(inte);

                }else{
                    Toast.makeText(getApplicationContext(), "Err create Product..", Toast.LENGTH_SHORT).show();
                }
            }else{
                if(product.updateProduct(getApplicationContext(), etcod.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Product ssucesful update..", Toast.LENGTH_SHORT).show();
                    Intent inte = new Intent(getApplicationContext(), Menusl.class);
                    startActivity(inte);

                }else{
                    Toast.makeText(getApplicationContext(), "Err update Product..", Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "->"+e, Toast.LENGTH_SHORT).show();

        }
    }



}