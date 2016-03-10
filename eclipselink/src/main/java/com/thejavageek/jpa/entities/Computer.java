package com.thejavageek.jpa.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * http://www.thejavageek.com/2014/09/25/jpa-caching-example 
 *
 */

@Entity
public class Computer implements Serializable {

    @Id
    @TableGenerator(name = "computer_gen", table = "id_gen", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 1)
    @GeneratedValue(generator = "computer_gen", strategy = GenerationType.TABLE)
    private int idComputer;
    private static final long serialVersionUID = 1L;

    public int getIdComputer() {
        return this.idComputer;
    }

    public void setIdComputer(int idComputer) {
        this.idComputer = idComputer;
    }

}
