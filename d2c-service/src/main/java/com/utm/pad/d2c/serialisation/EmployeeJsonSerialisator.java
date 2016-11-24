package com.utm.pad.d2c.serialisation;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utm.pad.d2c.model.Employee;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by imacovei on 23.11.2016.
 */
public class EmployeeJsonSerialisator implements EmployeeSerialisator {
    ObjectMapper mapper;
    final private String type = "json";
    private String employeeListInString;

    public EmployeeJsonSerialisator() {
    }

    public EmployeeJsonSerialisator(String employeeList) {
        this.employeeListInString = employeeList;
    }

    public String getEmployeeList() {
        return employeeListInString;
    }

    public void setEmployeeList(String employeeList) {
        this.employeeListInString = employeeList;
    }

    public String serializeObjects(Object objToString) {
        mapper = new ObjectMapper();
        try {
            String jsonInString = mapper.writeValueAsString(objToString);

            return jsonInString;
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Employee> deserializeObjects() {
        mapper = new ObjectMapper();
        try {
            TypeReference<List<Employee>> mapType = new TypeReference<List<Employee>>() {
            };
            ArrayList<Employee> jsonToEmployeeList = mapper.readValue(employeeListInString, mapType);
            return jsonToEmployeeList;
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
