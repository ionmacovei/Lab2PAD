package com.utm.pad.d2c.dslservices.procesing;

import com.utm.pad.d2c.model.Employee;
import com.utm.pad.d2c.serialisation.EmployeeSerialisator;

import java.util.List;

/**
 * Created by imacovei on 07.11.2016.
 */
public class All implements Request {

    private String name;
    private EmployeeSerialisator serialisatorType;


    public All() {
    }

    public All(String name, EmployeeSerialisator serialisatorType) {
        this.name = name;
        this.serialisatorType = serialisatorType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeSerialisator getSerialisatorType() {
        return serialisatorType;
    }

    public void setSerialisatorType(EmployeeSerialisator serialisatorType) {
        this.serialisatorType = serialisatorType;
    }

    @Override
    public List<Employee> getData(List<Employee> employees) {
        return employees;
    }


}
