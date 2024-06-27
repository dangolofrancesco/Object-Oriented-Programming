package it.polito.ski;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Lift {

    private String name;
    private Type type;
    private List<String> slopes;

    public Lift(String name, Type type){
        this.name = name;
        this.type = type;

        slopes = new LinkedList<>();
    }

    public String getName(){
        return this.name;
    }

    public Type getType(){
        return this.type;
    }

    public void addSlope(String name){
        slopes.add(name);
    }

    public Collection<String> getSlopes(){
        return this.slopes;
    }
}
