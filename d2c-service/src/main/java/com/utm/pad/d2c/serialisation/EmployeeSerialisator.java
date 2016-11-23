package com.utm.pad.d2c.serialisation;

import com.utm.pad.d2c.model.Employee;

import java.util.List;

/**
 * Created by imacovei on 23.11.2016.
 */
public interface EmployeeSerialisator {
    public String serializeObjects(Object objToString);

    public List<Employee> deserializeObjects(String listOfEmploees);
}
