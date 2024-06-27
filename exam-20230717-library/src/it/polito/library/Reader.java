package it.polito.library;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Reader {

    private String first;
    private String last;
    private String readerId;
    private Map<String, String> booksRent;
    private boolean renting;
    private int numberRents; 

    public Reader( int id, String first, String last){
        this.first = first;
        this.last = last;
        this.readerId = String.valueOf(id);
        this.renting = false;
        this.numberRents = 0;

        booksRent = new HashMap<>();

    }

    public String getId(){
        return readerId;
    }

    @Override
    public String toString(){
        return first + " " + last;
    }

    public void startRental(String bookId, String date){
        String start = date + " ONGOING";
        booksRent.put(bookId, start);
        this.renting = true;

        this.numberRents++;
    }

    public void endRental(String bookId, String date){
        String end = booksRent.get(bookId).split(" ")[0] + " " + date;
        booksRent.put(bookId, end);
        this.renting = false;
    }

    public boolean isRenting(){
        return this.renting;
    }

    public Map<String, String> getRentals(){
        return booksRent;
    }

    public int getNumberOfRents(){
        return this.numberRents;
    }


}
