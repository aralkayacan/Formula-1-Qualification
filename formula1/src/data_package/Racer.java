package data_package;

import java.util.Random;

public class Racer {
    
    //**VARIABLES**
    public String racerID;
    public String racerName;
    public double racerScore;
    
        //Holds next class value like node classes.
    public Racer nextRacer;
    
    //**CONSTRUCTOR** 
    public Racer(String _racerID,String _racerName) {
        this.racerID = _racerID;
        this.racerName = _racerName;
        this.racerScore = 0;
        this.nextRacer = null;
    }
    
    //**GETTERS AND SETTERS**
    public String getRacerID() { return this.racerID; }//get the racerID
    
    public void setRacerID(String _racerID) { this.racerID = _racerID; }//set the racerID
    
    public String getRacerName() { return this.racerName; }//get the racerName
    
    public void setRacerName(String _racerName) { this.racerName = _racerName; }//set the racerName
    
    public double getRacerScore() { return this.racerScore; }//get the raceTime
    
    public void setRacerScore(double _raceTime) { this.racerScore = _raceTime; }//set the raceTime
    
    public Racer getNextRacer() { return this.nextRacer; }//get the nextRacer
    
    public void setNextRacer(Racer _nextRacer) { this.nextRacer = _nextRacer; }//set the nextRacer

    
    
    
}