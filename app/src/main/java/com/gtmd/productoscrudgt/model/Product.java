package com.gtmd.productoscrudgt.model;

public class Product {
    private int id_product;
    private String codigo;
    private String nombre;
    private double precio;

    public Product() {
    }

    public Product(int id_product, String codigo, String nombre, double precio) {
        this.id_product = id_product;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id_product=" + id_product +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }
}
