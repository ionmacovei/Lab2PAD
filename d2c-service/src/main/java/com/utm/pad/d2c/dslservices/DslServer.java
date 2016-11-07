package com.utm.pad.d2c.dslservices;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utm.pad.d2c.dslservices.procesing.Request;

import java.io.IOException;

/**
 * Created by imacovei on 03.11.2016.
 */
public class DslServer {

    public static String requestTrensfer(String req, String name) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enableDefaultTyping(); // default to using DefaultTyping.OBJECT_AND_NON_CONCRETE
            mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
            Request r = mapper.readValue(req, Request.class);
            r.setName(name);
            return mapper.writeValueAsString(r);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Request getRequestfromString(String req) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Request r = mapper.readValue(req, Request.class);
            return r;
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
