package it.polito.ski;


import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Parking {

    private String name;
    private int slots;
    private List<String> liftsServed;
    private int totalCapacity;

    public Parking(String name, int slots){
        this.name = name;
        this.slots = slots;
        this.totalCapacity = 0;
        this.liftsServed = new LinkedList<>();
    }

    public String getName(){
        return this.name;
    }

    public int getSlots(){
        return this.slots;
    }

    public void addLift(Lift l){
        liftsServed.add(l.getName());
        totalCapacity = totalCapacity + l.getType().getCapacity();
    }

    public Collection<String> getLifts(){
        return this.liftsServed;
    }

    public boolean isProportionate(){
        if ((slots/totalCapacity) < 30) return true;
        return false;
    }

    
}
