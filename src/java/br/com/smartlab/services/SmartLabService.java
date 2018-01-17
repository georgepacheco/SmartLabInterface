/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smartlab.services;

import br.com.smartlab.dao.SensorDataDAO;
import br.com.smartlab.model.SensorData;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author george
 */
@Path("/")
public class SmartLabService {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActualSensorValue(@PathParam("id") String id) {
        SensorDataDAO dao = new SensorDataDAO();
        SensorData data = dao.getActualSensorValue(id);
        Response resp = Response.status(Response.Status.OK).entity(data).
                header("Access-Control-Allow-Origin", "*").build();
        return resp;
    }

    @GET
    @Path("/{id}/values")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSensorValues(@PathParam("id") String id) {
        SensorDataDAO dao = new SensorDataDAO();
        ArrayList<SensorData> data = dao.getAllSensorValues(id);
        Response resp = Response.status(Response.Status.OK).entity(data).
                header("Access-Control-Allow-Origin", "*").build();
        resp.getHeaders().add("result-count", Integer.toString(data.size()));
        return resp;
    }

    @GET
    @Path("/{id}/values/date")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSensorValuesByDate(@PathParam("id") String id, @QueryParam("startTime") String startTime, @QueryParam("endTime") String endTime) {
        SensorDataDAO dao = new SensorDataDAO();
        ArrayList<SensorData> data = dao.getSensorValuesByDate(id, startTime, endTime);
        Response resp = Response.status(Response.Status.OK).entity(data).
                header("Access-Control-Allow-Origin", "*").build();
        resp.getHeaders().add("result-count", Integer.toString(data.size()));
        return resp;
    }
}
