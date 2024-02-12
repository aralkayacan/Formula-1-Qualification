package data_package;

public class Team {

    //**VARIABLES**
    public String teamID;
    public String teamName;
    public double score;
    
    /*
    * In this part, a structure similar to linked lists is used.
    * Team class => LinkedList class (save the first racers link)
    * Racer class => Node class (create a linked node list)
    */
    public Racer headRacer;
    
    
    //**CONSTRUCTOR** 
    public Team(String _teamID, String _teamName) {
        this.teamID = _teamID;
        this.teamName = _teamName;
        this.headRacer = null;
        this.score = 0;
    }
    
    /*public double calculateOverallScore() {  
        if(this.headRacer == null)
            return 0;
        else {
            Racer curr = headRacer;
            double sumOfRaceTime = 0;
            
            while(curr.nextRacer.nextRacer != null) {
                sumOfRaceTime += curr.racerScore;
                curr = curr.nextRacer;
            }        
            return sumOfRaceTime;
        }
    }*/
    
    public void addRacer(Racer _racer) {
        if(headRacer == null) {
            headRacer = _racer;
            //System.out.println(teamID+" | "+teamName+" takımına yarıscı "+headRacer.racerID+" | "+headRacer.racerName+" katılmıstır.");
        }
        else{
            Racer curr=headRacer;
            
            while(curr.nextRacer!=null){
                curr=curr.nextRacer;
            }
            curr.nextRacer =_racer;
            //System.out.println(curr.racerID+" | "+curr.racerName+" yarıscısından sonra, "+teamID+" | "+teamName+" takımına, yarıscı "+curr.nextRacer.racerID+" | "+curr.nextRacer.racerName+" katılmıstır.");
        }
    }
    
    public Racer removeLastRacer() {
    	Racer removedRacer=null;
        if(headRacer.nextRacer==null) {
        	removedRacer = headRacer;
            headRacer = null;
        }
        else {
            Racer curr=headRacer;
            
            while(curr.nextRacer.nextRacer!=null){
                curr=curr.nextRacer;
            }
            //System.out.println("liste içerisinde ki sonuncu eleman olan {"+curr.nextRacer.racerID+" | "+curr.nextRacer.racerName+"} silinmiştir");
            removedRacer = curr.nextRacer;
            curr.nextRacer = null;    
        }
        return removedRacer;
    }
    
    public String printTheTeamMembers(){
        Racer curr=headRacer;
    	if(curr==null)
    		return "";
        String teamMembers = "";
        teamMembers += "[ ";
        while (true){
        	if(curr.nextRacer==null){
            	teamMembers += curr.racerID+" | "+curr.racerName+" ]";
                break;
            }
            teamMembers += curr.racerID+" | "+curr.racerName+", ";
            curr=curr.nextRacer;
        } 
        return teamMembers;
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub 
    	return String.format("%s | %s :: %f    %s",teamID,teamName,score,printTheTeamMembers());
    }
}
