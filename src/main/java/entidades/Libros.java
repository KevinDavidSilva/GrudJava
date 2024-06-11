/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.Date;

/**
 *
 * @author elmer
 */
public class Libros {
     private int libroID;
    private String titulo;
    private String autor;
    private String genero;
    private java.util.Date publicacion;
    private boolean disponible;

    public Libros() {
    }

    public Libros(int libroID, String titulo, String autor, String genero, Date publicacion, boolean disponible) {
        this.libroID = libroID;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.publicacion = publicacion;
        this.disponible = disponible;
    }

    public int getLibroID() {
        return libroID;
    }

    public void setLibroID(int libroID) {
        this.libroID = libroID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Date publicacion) {
        this.publicacion = publicacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
   @Override
    public String toString() {
        return titulo;
    }
    
    
}
