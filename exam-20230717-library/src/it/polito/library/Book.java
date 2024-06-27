package it.polito.library;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class Book {

    private String title;
    private String id;
    private boolean rent;
    private Map<String, String> rentings;
    private int numberRent;

    public Book(String id, String title){
        this.title = title;
        this.id = id;
        this.rent = false;
        this.numberRent = 0;

        rentings = new TreeMap<>();

    }

    public String getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public Map<String, String> getRentings(){
        return rentings;
    }

    public void startRental(String readerId, String date){
        String start = date + " ONGOING";
        this.rentings.put(readerId, start);
        this.rent = true;

        this.numberRent++;
    }

    public void endRental(String readerId, String date){
        String end = rentings.get(readerId).split(" ")[0] + " " + date;
        this.rentings.put(readerId, end);
        this.rent = false;
    }

    public boolean isRent(){
        return rent;
    }

    public int getNumberOfRents(){
        return this.numberRent;
    }

}
