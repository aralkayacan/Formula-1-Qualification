package graph_package;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.*;

import data_package.Heap;
import data_package.Racer;
import data_package.Team;
import data_package.Utils;
import main_package.teamGenerator;

public class tournamentBoard extends JFrame {
	
	// STEP 1 DEFINITION
	Team[] teams;
	Racer[] racers;
	Heap maxHeap;
	boolean keepRacing;
	int numbersOfTeams;
	
	JLabel lblHeader;
	 
	DefaultListModel<Team> dlm = new DefaultListModel<Team>();
	JList<Team> lstTeam = new JList<Team>(dlm);
	JScrollPane scrList = new JScrollPane(lstTeam);
	 
	JButton btnFillItUp, btnAction;
	 
	JPanel pnlButton;
	
	public tournamentBoard() throws HeadlessException {
		super();
		// STEP 2 INITIALIZATION
		this.setTitle("Season Management Board");
		this.setSize(500,350);
		this.setLocation(700, 360);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		lblHeader = new JLabel("Formula 1 22-23 Season");
		
		btnFillItUp = new JButton("Fill Up Drivers");
		btnAction = new JButton("Start");btnAction.setEnabled(false);
		
		pnlButton = new JPanel(new GridLayout(1, 2));
		pnlButton.add(btnFillItUp);pnlButton.add(btnAction);
		
		this.add(lblHeader, BorderLayout.NORTH);
		this.add(scrList, BorderLayout.CENTER);
		this.add(pnlButton, BorderLayout.SOUTH);
		
		keepRacing=true;
		numbersOfTeams=10;
		maxHeap = new Heap(numbersOfTeams);
		btnFillItUp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				teamGenerator tg = new teamGenerator();
				dlm.removeAllElements();	
				
				teams = new Team[numbersOfTeams];
				for(var i=0;i<teams.length;i++) {
					teams[i] = new Team(Integer.toString(i+1), tg.teamNames[tg.indexes[i]] );
					teams[i].addRacer(new Racer(Integer.toString((i+1)*11), tg.firstRacerNames[tg.indexes[i]]));
					teams[i].addRacer(new Racer(Integer.toString((i+2)*11), tg.secondRacerNames[tg.indexes[i]]));
				}
				
				/*teamA = new Team("1", tg.teamNames[tg.indexes[0]] );
				teamA.addRacer(new Racer("44", tg.firstRacerNames[tg.indexes[0]]));
				teamA.addRacer(new Racer("55", tg.secondRacerNames[tg.indexes[0]]));	
				
				teamB = new Team("2", tg.teamNames[tg.indexes[1]] );
				teamB.addRacer(new Racer("66", tg.firstRacerNames[tg.indexes[1]]));
				teamB.addRacer(new Racer("77", tg.secondRacerNames[tg.indexes[1]]));
				
				teamC = new Team("3", tg.teamNames[tg.indexes[2]] );
				teamC.addRacer(new Racer("88", tg.firstRacerNames[tg.indexes[2]]));
				teamC.addRacer(new Racer("99", tg.secondRacerNames[tg.indexes[2]]));
				
				dlm.addElement(teamA);dlm.addElement(teamB);dlm.addElement(teamC);*/
				
				for(var i : teams)
					dlm.addElement(i);
				
				btnAction.setEnabled(true);
			}
		});
		
		

		btnAction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnFillItUp.setText("Reset");
				
				Random rnd = new Random();
				racers = new Racer[numbersOfTeams]; 

				if(btnAction.getText().equals("Show Race Results")) {

					teamGenerator tgg = new teamGenerator();
					int randomNumber = Utils.generateRandomNumber();
					String fastestDriver = tgg.firstRacerNames[randomNumber];
					String fastestDriverLapTime = Utils.generateRandomLapTime();
					
					for(var i : teams) {
						maxHeap.push(i);
					}
					
					Team winnerTeam = maxHeap.peek();
					teamGenerator tg = new teamGenerator();
					int indexOfWinnesTeam = tg.returnTeamIndex(winnerTeam.teamName);
					String firstWinnerRacer = tg.firstRacerNames[indexOfWinnesTeam];
					String secondWinnerRacer = tg.secondRacerNames[indexOfWinnesTeam];
					
					String congrats = " | "+winnerTeam+" | \n#1 - "+firstWinnerRacer+" \n#2 - "+secondWinnerRacer + "\nFastest Lap: " + fastestDriver + " " + fastestDriverLapTime;
					JOptionPane.showMessageDialog(null,	congrats);
					tournamentBoard tb = new tournamentBoard();
					setVisible(false);
					tb.setVisible(true);
				}
				
				try {
					for(var i=0; i<teams.length;i++)
						racers[i] = teams[i].removeLastRacer();
				} catch(Exception exception) {
					keepRacing=false;
					btnFillItUp.setEnabled(false);
					btnAction.setText("Show Race Results");
				}
				
				if(keepRacing) {
					btnAction.setText("Continue");
					Racer[] racersOnRunway = Arrays.copyOf(racers, racers.length);
					/*Racer racerA = teamA.removeLastRacer();
					Racer racerB = teamB.removeLastRacer();
					Racer racerC = teamC.removeLastRacer();
					
					String[] racerIDs = new String[] {racerA.racerID, racerB.racerID, racerC.racerID};*/
					
					int arrayLenght = racersOnRunway.length;
			        for (int i = arrayLenght-1; i > 0; i--) {
			             
			        	int j = rnd.nextInt(i+1);
			            var temp = racersOnRunway[i];
			            racersOnRunway[i] = racersOnRunway[j];
			            racersOnRunway[j] = temp;
						//shuffle the copy of racers array(racersOnRunway) for changing the order randomly
			        }
			        double addScore=10;
			        for(var i=0; i<racersOnRunway.length;i++) {
			        	
			        	for(var j=0; j<racers.length;j++) {
			        		
			        		if(racersOnRunway[i].equals(racers[j])) {
			        			racers[j].racerScore += (addScore - i);
								//Gives the scores according to the possession that racers finished
			        		}

			        	}
			        }
				
			        for(var i=0; i<teams.length;i++)
			        	teams[i].score += racers[i].racerScore;
			        
			        for(var i=0; i<teams.length;i++)
			        	dlm.setElementAt(teams[i], i);
				}        

				
			}
		});
	
		
	}
}
