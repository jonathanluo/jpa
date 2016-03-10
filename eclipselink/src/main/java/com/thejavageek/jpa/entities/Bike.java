package com.thejavageek.jpa.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * http://www.thejavageek.com/2014/09/25/jpa-caching-example 
 *
 */

@Entity
@Cacheable(false)
public class Bike implements Serializable {

    @Id
    @TableGenerator(name = "bike_gen", table = "id_gen", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 1)
    @GeneratedValue(generator = "bike_gen", strategy = GenerationType.TABLE)
    private int idBike;

    public int getIdBike() {
        return this.idBike;
    }

    public void setIdBike(int idBike) {
        this.idBike = idBike;
    }

}