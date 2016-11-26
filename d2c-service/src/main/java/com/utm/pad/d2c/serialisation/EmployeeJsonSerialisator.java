package com.utm.pad.d2c.serialisation;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.report.ProcessingMessage;
import com.github.fge.jsonschema.report.ProcessingReport;
import com.github.fge.jsonschema.util.JsonLoader;
import com.utm.pad.d2c.model.Employee;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by imacovei on 23.11.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
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
            this.setEmployeeList(jsonInString);

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

    @Override
    public Boolean validate() {

        String jsonSchema = getJsonSchema();
        String jsonData = getEmployeeList();
        ProcessingReport report = null;
        boolean result = false;
        try {
            // System.out.println("Applying schema: @<@<"+jsonSchema+">@>@ to data: #<#<"+jsonData+">#>#");
            JsonNode schemaNode = JsonLoader.fromString(jsonSchema);
            JsonNode data = JsonLoader.fromString(jsonData);
            JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
            JsonSchema schema = factory.getJsonSchema(schemaNode);
            report = schema.validate(data);
        } catch (JsonParseException jpex) {
            System.out.println("Error. Something went wrong trying to parse json data: #<#<" + jsonData +
                    ">#># or json schema: @<@<" + jsonSchema + ">@>@. Are the double quotes included? " + jpex.getMessage());
            //jpex.printStackTrace();
        } catch (ProcessingException pex) {
            System.out.println("Error. Something went wrong trying to process json data: #<#<" + jsonData +
                    ">#># with json schema: @<@<" + jsonSchema + ">@>@ " + pex.getMessage());
            //pex.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error. Something went wrong trying to read json data: #<#<" + jsonData +
                    ">#># or json schema: @<@<" + jsonSchema + ">@>@");
            //e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error. Something went wrong trying to read json data: #<#<" + jsonData +
                    ">#># or json schema: @<@<" + jsonSchema + ">@>@");
            //e.printStackTrace();
        }
        if (report != null) {
            Iterator<ProcessingMessage> iter = report.iterator();
            while (iter.hasNext()) {
                ProcessingMessage pm = iter.next();
                System.out.println("Processing Message: " + pm.getMessage());
            }
            result = report.isSuccess();
        }
        System.out.println(" Result=" + result);
        return result;


    }

    public String getJsonSchema() {
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(new FileReader("schema.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject.toJSONString();
    }


}
