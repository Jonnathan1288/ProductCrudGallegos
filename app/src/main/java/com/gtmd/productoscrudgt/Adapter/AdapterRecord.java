package com.gtmd.productoscrudgt.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gtmd.productoscrudgt.Menusl;
import com.gtmd.productoscrudgt.R;
import com.gtmd.productoscrudgt.agregarproducto;
import com.gtmd.productoscrudgt.model.Product;


import java.util.ArrayList;

public class AdapterRecord extends RecyclerView.Adapter<AdapterRecord.HolderRecord>{

    //Variables
    private Context context;
    private ArrayList<Product> recordsList;
    //Constructor

    public static String cod = null;

    public AdapterRecord(Context context, ArrayList<Product> recordsList){
        this.context = context;
        this.recordsList = recordsList;
    }

    @NonNull
    @Override
    public HolderRecord onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout
        View view = LayoutInflater.from(context).inflate(R.layout.row_record, parent, false);

        return new HolderRecord(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderRecord holder, int position) {

        Product model = recordsList.get(position);
        final int id = model.getId_product();

        holder.codigo.setText("Código: "+model.getCodigo());
        holder.nombre.setText("Nombre: "+model.getNombre());

        holder.precio.setText("Precio: "+model.getPrecio());

        holder.profileIv.setImageResource(R.drawable.product);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionsEditOrDetele( model.getCodigo(), model.getNombre(), model.getPrecio());
            }
        });
    }

    public void OptionsEditOrDetele(String codigo, String nombre, double precio){
        String [] optionC_D = {"Modificar", "Eliminar"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setItems(optionC_D, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    Intent intent = new Intent(context, agregarproducto.class);
                    intent.putExtra("CODIGO", codigo);
                    intent.putExtra("NOMBRE", nombre);
                    String pre = String.valueOf(precio);
                    intent.putExtra("PRECIO", pre);

                    intent.putExtra("EDIT_USER", true);
                    context.startActivity(intent);
                }else if( which == 1){
                    cod = codigo;
                    System.out.println("Damos en regreso del adapter -> "+cod);
                    ((Menusl)context).onResume();
                }
            }
        });

        builder.create().show();
    }

    @Override
    public int getItemCount() {
        return recordsList.size();
    }

    class HolderRecord extends RecyclerView.ViewHolder{
        //vistas
        ImageView profileIv;
        TextView codigo, nombre, precio;
        ImageButton moreBtn;
        public HolderRecord(@NonNull View itemView){
            super(itemView);

            //Inicializamos la vistas
            profileIv = itemView.findViewById(R.id.profileIv);
            codigo = itemView.findViewById(R.id.codigoView);
            nombre = itemView.findViewById(R.id.nombreView);
            precio = itemView.findViewById(R.id.precioView);
            moreBtn = itemView.findViewById(R.id.moreBtn);

        }
    }
}
