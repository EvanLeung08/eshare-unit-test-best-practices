package com.eshare.resttemplate.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Customer {
    @NonNull
    private int id;
    @NonNull
    private String name;

    public Customer(){

    }

    public Customer(int id,String name){
            this.id=id;
            this.name =name;
    }

}
