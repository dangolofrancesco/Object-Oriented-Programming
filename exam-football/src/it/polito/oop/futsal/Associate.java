package it.polito.oop.futsal;

public class Associate {

    private final String first;
    private final String last;
    private final String telephone;
    private final int id;

    private int numOfBookings;

    public Associate(int id, String first, String last, String mobile){
        this.id = id;
        this.first = first;
        this.last = last;
        this.telephone = mobile;
        this.numOfBookings = 0;
    }

    public String getName(){
        return this.first;
    }

    public String getSurname(){
        return this.last;
    }

    public String getMobile(){
        return this.telephone;
    }

    public int getId(){
        return this.id;
    }

    public void addBooking(){
        this.numOfBookings++;
    }

    public int getNumOfBookings(){
        return this.numOfBookings;
    }

}
