package com.utm.pad.d2c.dslservices.procesing;

import com.utm.pad.d2c.model.Employee;

import java.util.List;

/**
 * Created by imacovei on 07.11.2016.
 */
public class All implements Request {

    private String name;

    public All() {
    }

    public All(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Employee> getData(List<Employee> employees) {
        return employees;
    }


}
