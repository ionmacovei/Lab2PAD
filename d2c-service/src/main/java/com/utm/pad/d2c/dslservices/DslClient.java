package com.utm.pad.d2c.dslservices;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utm.pad.d2c.dslservices.procesing.All;
import com.utm.pad.d2c.dslservices.procesing.Filter;
import com.utm.pad.d2c.dslservices.procesing.Request;
import com.utm.pad.d2c.dslservices.procesing.Sort;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by imacovei on 27.10.2016.
 */
public class DslClient {


    public static String getRequestForClient(Object objToString) {

        try {

           /* Request req = new All("client");//
            Request r = new Filter("client", 500, "<");
            Request request = new Sort("client", "firstName", "desc");*/
            ObjectMapper mapper = new ObjectMapper();
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

}
