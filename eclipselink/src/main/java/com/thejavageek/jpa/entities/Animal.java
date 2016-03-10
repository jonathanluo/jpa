package com.thejavageek.jpa.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * http://www.thejavageek.com/2014/09/25/jpa-caching-example 
 *
 */
@Entity
@Cacheable
public class Animal implements Serializable {

    @Id
    @TableGenerator(name = "animal_gen", table = "id_gen", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 1)
    @GeneratedValue(generator = "animal_gen", strategy = GenerationType.TABLE)
    private int idAnimal;

    public int getIdAnimal() {
        return this.idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

}