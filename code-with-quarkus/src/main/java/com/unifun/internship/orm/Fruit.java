package com.unifun.internship.orm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "fruit")
public class Fruit extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    public String color;

    public Fruit(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Fruit() {
    }

    @Override
    public String toString() {
        return "Fruit [id= " + id + ", name= " + name + ", color= " + color + "]";
    }

    @Transactional
    public static String deleteByID(Long id) {
        return Fruit.deleteById(id) ? "deleted" : "not deleted";
    }

}
