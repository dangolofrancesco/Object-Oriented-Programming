package it.polito.ski;

public class Type {

    private String code;
    private String category;
    private int capacity;

    public Type(String code, String category, int capacity){
        this.code = code;
        this.category = category;
        this.capacity = capacity;
    }

    public String getCode(){
        return this.code;
    }

    public String getCategory(){
        return this.category;
    }

    public int getCapacity(){
        return this.capacity;
    }
}
