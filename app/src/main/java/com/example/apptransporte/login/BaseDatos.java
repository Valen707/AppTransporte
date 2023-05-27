package com.example.apptransporte.login;

public class BaseDatos {
    private String email;
    private String contraseña;

    private String uid;



    public BaseDatos(){

    }

    public BaseDatos(String email, String contraseña){
        this.email = email;
        this.contraseña = contraseña;


    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
