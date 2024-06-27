package it.polito.ski;

public class Slope {

    private String name;
    private String difficulty;
    private Lift startingLift;

    public Slope(String name, String difficulty, Lift lift){
        this.name = name;
        this.difficulty = difficulty;
        this.startingLift = lift;
    }

    public String getName(){
        return this.name;
    }

    public String getDifficulty(){
        return this.difficulty;
    }

    public Lift getLift(){
        return this.startingLift;
    }
}
