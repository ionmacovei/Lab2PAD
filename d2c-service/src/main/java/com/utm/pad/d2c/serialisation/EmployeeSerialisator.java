package com.utm.pad.d2c.serialisation;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.utm.pad.d2c.model.Employee;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by imacovei on 23.11.2016.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = EmployeeJsonSerialisator.class, name = "json"),
        @JsonSubTypes.Type(value = EmployeeXmlSerialisator.class, name = "xml"),})
public interface EmployeeSerialisator extends Serializable {
    String serializeObjects(Object objToString);

    ArrayList<Employee> deserializeObjects();

    String getEmployeeList();

    Boolean validate();


}
