package com.weffle;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("employee")
public class EmployeeResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Employee[] getEmployees() {
        return Employee.getEmployees();
    }

    @GET
    @Path("response")
    public Response getEmployeesResponse() {
        return Response.ok().status(200).entity(Employee.getEmployees()).header("Access-Control-Allow-Origin", "*").build();
    }
}
