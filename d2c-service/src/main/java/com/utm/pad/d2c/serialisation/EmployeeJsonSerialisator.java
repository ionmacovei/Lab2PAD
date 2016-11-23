package com.utm.pad.d2c.serialisation;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utm.pad.d2c.model.Employee;

import java.io.IOException;
import java.util.List;

/**
 * Created by imacovei on 23.11.2016.
 */
public class EmployeeJsonSerialisator implements EmployeeSerialisator {
    ObjectMapper mapper;


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
    public List<Employee> deserializeObjects(String listOfEmploees) {
        mapper = new ObjectMapper();
        try {
            TypeReference<List<Employee>> mapType = new TypeReference<List<Employee>>() {
            };
            List<Employee> jsonToEmployeeList = mapper.readValue(listOfEmploees, mapType);
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
