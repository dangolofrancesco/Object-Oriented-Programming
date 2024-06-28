package it.polito.oop.futsal;

import java.util.LinkedList;
import java.util.List;

import it.polito.oop.futsal.Fields.Features;

public class Field implements FieldOption{

    private final int id;
    private final Features features;

    private List<Booking> bookings;
    

    public Field(Features f, int id){
        this.id = id;
        this.features = f;

        this.bookings = new LinkedList<>();
    }

    public boolean match(Features f){
        if ( f.indoor == true && this.features.indoor == false) return false;
        if ( f.ac == true && this.features.ac == false) return false;
        if ( f.heating == true && this.features.heating == false) return false;
        return true;
    }

    public boolean isIndoor(){
        return this.features.indoor;
    }

    public boolean hasHeating(){
        return this.features.heating;
    }

    public boolean hasAC(){
        return this.features.ac;
    }

    public int getField(){
        return this.id;
    }

    public void addBooking(Booking b){
        this.bookings.add(b);
    }

    public boolean isBooked(String time){
        for ( Booking b : bookings){
            if (b.getTime().equals(time)){
                return true;
            }
        }
        return false;
    }

    public int getOccupation(){
        return bookings.size();
    }
}
