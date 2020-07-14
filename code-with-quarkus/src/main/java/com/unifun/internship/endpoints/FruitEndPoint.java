package com.unifun.internship.endpoints;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.unifun.internship.orm.Fruit;

@Path("/fruit")
public class FruitEndPoint {

    @GET
    @Path("list")
    public String getList() {
        return Fruit.listAll().toString();
    }

    @GET
    @Path("deleteById")
    public String deleteById(@QueryParam("abc") long id) {
        return Fruit.deleteByID(id);
    }

    @GET
    @Path("add")
    @Transactional
    public String add(@QueryParam("name") String name, @QueryParam("color") String color) {
        Fruit fruit = new Fruit(name, color);
        fruit.persist();
        return fruit.isPersistent() ? "persisted" : "not persisted";
    }

    @GET
    @Path("changeNameById")
    @Transactional
    public String changeNameById(@QueryParam("id") long id, @QueryParam("name") String name) {
        Fruit fruit = Fruit.findById(id);
        if (fruit != null) {
            fruit.name = name;
            return "success";
        } else {
            return "no such fruit";
        }
    }

    @GET
    @Path("showById")
    @Transactional
    public String showById(@QueryParam("id") long id) {
        Fruit fruit = Fruit.findById(id);
        if (fruit != null) {
            return fruit.toString();
        } else {
            return "no such fruit";
        }
    }
}
