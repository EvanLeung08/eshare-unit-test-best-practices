package com.eshare.resttemplate.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Customer {

    private int id;
    private String name;

    public Customer(){

    }

    public Customer(int id,String name){
            this.id=id;
            this.name =name;
    }

}
