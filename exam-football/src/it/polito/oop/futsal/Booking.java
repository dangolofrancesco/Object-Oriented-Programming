package it.polito.oop.futsal;

public class Booking {

    private final int field;
    private final int associate;
    private final String start;

    public Booking(int field, int associate, String time){
        this.field = field;
        this.associate = associate;
        this.start = time;
    }

    public int getField(){
        return this.field;
    }

    public int getAssociate(){
        return this.associate;
    }

    public String getTime(){
        return this.start;
    }

}
