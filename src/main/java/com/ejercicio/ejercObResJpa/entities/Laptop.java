package com.ejercicio.ejercObResJpa.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Laptop {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private static Long id;
    private String marca;
    private String modelo;
    private Double price;
    private LocalDate releaseDate;

    //Constructores

    public Laptop() {
    }

    public Laptop(Long id, String marca, String modelo, Double price, LocalDate releaseDate) {
        Laptop.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.price = price;
        this.releaseDate = releaseDate;
    }
    //Getter y Setter


    public static Long getId() {
        return id;
    }

    public void setId(Long id) {
        Laptop.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
