package main_package;

import java.util.Random;

public class teamGenerator {

	public String[] teamNames = new String[] {"Red Bull Racing","Ferrari","Mercedes AMG Petronas","McLaren","Alfa Romeo","Alpine","AlphaTauri","Haas F1 Team","Aston Martin","Williams"};
	public String[] firstRacerNames = new String[] {"Max Verstappen","Charles Leclerc","Lewis Hamilton","Lando Norris","Valtteri Bottas","Esteban Ocon","Yuki Tsunoda","Kevin Magnussen","Fernando Alonso","Alexander Albon"};
	public String[] secondRacerNames = new String[] {"Sergio Perez","Carlos Sainz","George Russell","Oscar Piastri","Zhou Guanyu","Pierre Gasly","Nyck de Vries","Nico Hulkenberg","Lance Stroll","Logan Sargeant"};
	public int[] indexes = new int[] {0,1,2,3,4,5,6,7,8,9};
	Random rnd = new Random();
	
	public teamGenerator() {
		shuffleArray();
	}
	
	public void shuffleArray() {
		int arrayLenght = indexes.length;
        for (int i = arrayLenght-1; i > 0; i--) {
             
            // Pick a random index from 0 to i
            int j = rnd.nextInt(i+1);
             
            // Swap array[i] with the element at random index
            int temp = indexes[i];
            indexes[i] = indexes[j];
            indexes[j] = temp;
        }
	}
	
	public int returnTeamIndex(String _teamName) {
		int index=-1;
		for(var i=0;i<teamNames.length;i++) {
			if(teamNames[i].equals(_teamName))
				index = i;
		}
		return index;
	}
}
