package com.anfesoft.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MUDRAS")
public class Mudras {
    
    @Id
    @Column(name = "ID")
    private int id;
    
    @Column(name = "NAME")
    private String name;

    public Mudras() {
        super();
    }

    public Mudras(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}