package com.gtmd.productoscrudgt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gtmd.productoscrudgt.model.ModelProduct;

public class gestionm extends AppCompatActivity {

    private EditText etcod, etnombre, etprcecio;
    private Button btncrear, btneditar, btnbuscar, btnelimina, btnListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionm);
        assing_references();
        event_button();

    }
    private void assing_references(){
        etcod = (EditText) findViewById(R.id.etcodigoMS);
        etnombre = (EditText) findViewById(R.id.etnombreMS);
        etprcecio = (EditText) findViewById(R.id.precioMS);

        btncrear = (Button) findViewById(R.id.btnCrear);
        btneditar = (Button) findViewById(R.id.btnModificar);
        btnbuscar = (Button) findViewById(R.id.btnBuscar);
        btnelimina = (Button) findViewById(R.id.btnEliminar);
        btnListar = (Button) findViewById(R.id.btnListarMS);

    }

    private void event_button() {
        btncrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
    }


    private void createUser(){
        try{
            ModelProduct product = new ModelProduct();
            product.setCodigo(etcod.getText().toString());
            product.setNombre(etnombre.getText().toString());
            product.setPrecio(Double.parseDouble(etprcecio.getText().toString()));
            if(product.saveProduct(getApplicationContext())){
                Toast.makeText(getApplicationContext(), "Product ssucesful crated..", Toast.LENGTH_SHORT).show();
                Intent inte = new Intent(getApplicationContext(), listproduct.class);
                startActivity(inte);

            }else{
                Toast.makeText(getApplicationContext(), "Err create Product..", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "->"+e, Toast.LENGTH_SHORT).show();

        }
    }
}