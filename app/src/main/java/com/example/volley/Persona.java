package com.example.volley;

public class Persona {
    private String nombre;
    private int edad;
    private String Sexo;
    private String descripcion;


    public Persona(String nombre, int edad, String sexo, String descripcion) {
        this.nombre = nombre;
        this.edad = edad;
        Sexo = sexo;
        this.descripcion = descripcion;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void Correr (){

    }
}
