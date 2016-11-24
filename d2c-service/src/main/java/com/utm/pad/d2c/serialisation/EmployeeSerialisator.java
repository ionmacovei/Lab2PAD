package com.utm.pad.d2c.serialisation;

import com.utm.pad.d2c.model.Employee;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Created by imacovei on 23.11.2016.
 */
public interface EmployeeSerialisator extends Serializable {
    public String serializeObjects(Object objToString);

    public ArrayList<Employee> deserializeObjects();

    public String getEmployeeList();
}
