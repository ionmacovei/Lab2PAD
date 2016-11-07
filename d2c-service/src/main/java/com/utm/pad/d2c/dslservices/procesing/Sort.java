package com.utm.pad.d2c.dslservices.procesing;

import com.utm.pad.d2c.model.Employee;

import java.util.List;

/**
 * Created by imacovei on 07.11.2016.
 */
public class Sort implements Request {
    private String name;
    private String nameAtribut;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAtribut() {
        return nameAtribut;
    }

    public void setNameAtribut(String nameAtribut) {
        this.nameAtribut = nameAtribut;
    }

    @Override
    public List<Employee> getData(List<Employee> employees) {
        return null;
    }
}
