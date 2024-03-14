package team;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.FileWriter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import javax.swing.SwingWorker;


public class SeasonGUI implements ActionListener{
	private ArrayList<Team> teams;
	private JButton startsimulation;
	private JButton viewTeamButton;
	private JButton pauseButton;
	private JButton resumeButton;
	private JButton PlayOff_Bracket;
	ArrayList<Team> topTeams;
	private JLabel teamsInfoLabel;
	
	private JFrame frame=new JFrame();
	private JTextArea resultsTextArea;
	private boolean simulationPaused;
    private SwingWorker<Void, Void> simulationWorker;
    private ArrayList<Team> winners1 ;
	Team userTeam;
	public SeasonGUI(ArrayList<Team> teams,Team userTeam) {
		this.teams=teams;
		this.simulationPaused=false;
		this.userTeam=userTeam;
		JPanel mainPanel =new JPanel();
		frame.setTitle("Match and Season");
		frame.setSize(600,400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainPanel.setLayout(new BorderLayout());
		
		startsimulation=new JButton("Start Simulation");
		startsimulation.addActionListener(this);
		viewTeamButton = new JButton("View Team");
		viewTeamButton.addActionListener(this);
        pauseButton = new JButton("Pause");
        pauseButton.addActionListener(this);
        resumeButton = new JButton("Resume");
        resumeButton.addActionListener(this);
        PlayOff_Bracket=new JButton("PlayOff Backet");
        PlayOff_Bracket.addActionListener(this);

        resultsTextArea = new JTextArea();
        resultsTextArea.setEditable(false);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startsimulation);
        buttonPanel.add(viewTeamButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(resumeButton);
        buttonPanel.add(PlayOff_Bracket);
        
        teamsInfoLabel = new JLabel();
        updateTeamsInfoLabel();  // Initial update of teams' information

        mainPanel.add(teamsInfoLabel, BorderLayout.EAST);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(resultsTextArea), BorderLayout.CENTER);
        //reference 3
        frame.add(mainPanel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //reference 4
        //I run the this stuff in the background so it gets updated.
        simulationWorker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
            	playNormalGames(teams);
            	topTeams= getTopTeams();
    			runPlayoffs(topTeams);
                return null;
            }

            @Override
            protected void done() {
                // Enable the  UI components after simulation is complete
                startsimulation.setEnabled(true);
                viewTeamButton.setEnabled(true);
                pauseButton.setEnabled(false);
                resumeButton.setEnabled(false);
            }
        };
	}

	 private void logGameResult(Team team1, Team team2, double score1, double score2) {
	        // Log the results of a normal game to a file
	        String log = team1.getTeamName() + " vs. " + team2.getTeamName() +
	                ": " + score1 + " - " + score2 + " \n";

	        appendToResultsTextArea(log);
	        writeToLogFile("normalGames_log.txt",log);
	      
	    }

	    private void logPlayoffResult(Team team1, Team team2, Team winner) {
	        // Log the results of a playoff match to a file
	        String log = "Playoff: " + team1.getTeamName() + " vs. " + team2.getTeamName() +
	                ", Winner: " + winner.getTeamName() + "\n";

	        appendToResultsTextArea(log);
	        writeToLogFile("playoff_log.txt",log);
	       }

	    private void logChampion(Team champion) {
	        // Log the champion to a file
	        String log = "Champion: " + champion.getTeamName() + "\n";

	        appendToResultsTextArea(log);
	        writeToLogFile("champion_log.txt",log);

	    }

private void updateTeamsInfoLabel() {
    // Build a string with teams' information
    StringBuilder teamsInfo = new StringBuilder("<html><b>Teams Information</b><br>");
// reference 5
    for (Team team : teams) {
        teamsInfo.append(team.getTeamName())
                .append(": Wins - ").append(team.getNumberOfGamesWon())
                .append(", Losses - ").append(team.getNumberOfGamesLost())
                .append(", Ties - ").append(team.getNumberOfGamesTied())
                .append("<br>");
    }

    // Update the label with the new information
    teamsInfoLabel.setText(teamsInfo.toString());
    teamsInfoLabel.revalidate();
    teamsInfoLabel.repaint();
}
	    private void writeToLogFile(String fileName, String log) {
	        // Write the log to the specified file
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
	            writer.write(log);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	private Team selectRandomOpponent(Team currentTeam) {
        // Exclude the current team from the list of potential opponents
        ArrayList<Team> potentialOpponents = new ArrayList<>(teams);
        potentialOpponents.remove(currentTeam);

        // Shuffle the list and select the first team as the opponent
        Collections.shuffle(potentialOpponents);
        return potentialOpponents.get(0);
    } 
	private double simulateGame(Team team1, Team team2) {
		   double score1 = team1.getTeamScore();
		   double score2 = team2.getTeamScore();
		   
        // Display the simulated scores (replace with your logic)
        appendToResultsTextArea("Simulation: " + team1.getTeamName() + " " + score1 +
                " - " + score2 + " " + team2.getTeamName() + "\n");
      
        for (int i = 0; i < 10; i++) {  
            if (simulationPaused) {
                while (simulationPaused) {
                    simulateWithDelay(100);  // Check every 0.1 seconds if the simulation is still paused
                }
            }
            simulateWithDelay(10);  // Introduce a short delay between checks
        }
        return score1;  
    }
   
    private void playNormalGames(ArrayList<Team> teams) {
        // Simulate playing normal games
        for (Team team : teams) {
        	for(int i=0;i<2*teams.size();i++) {
            Team opponent = selectRandomOpponent(team);
            double teamScore = simulateGame(team, opponent);
            teamScore=(double) (teamScore*1.05);
            double opponentScore = simulateGame(opponent, team);
            
            if(teamScore==opponentScore) {
            	team.setNumberOfGamesTied(team.getNumberOfGamesTied()+1);
            	opponent.setNumberOfGamesTied(opponent.getNumberOfGamesTied()+1);
            }else if(teamScore>opponentScore) {
            	team.setNumberOfGamesWon(team.getNumberOfGamesWon()+1);
            	opponent.setNumberOfGamesLost(opponent.getNumberOfGamesLost()+1);
            }else {
            	team.setNumberOfGamesLost(team.getNumberOfGamesLost()+1);
            	opponent.setNumberOfGamesWon(opponent.getNumberOfGamesWon()+1);
            }
            // Log the results
            logGameResult(team, opponent, teamScore, opponentScore);
            simulateWithDelay(100);
            updateTeamsInfoLabel();
        	}

        }
    }
    private void simulateWithDelay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

private Team simulatePlayoffMatch(Team team1, Team team2) {
    double team1Score = team1.getTeamScore();
    double team2Score = team2.getTeamScore();

    team1Score = (double) (team1Score * 1.05);

    // Determine the winner based on the scores
    return (team1Score > team2Score) ? team1 : team2;
}
    private void runPlayoffs(ArrayList<Team> playoffTeams) {
        winners1 = new ArrayList<>();

        while (playoffTeams.size() > 1) {
            ArrayList<Team> winners = new ArrayList<>();

            for (int i = 0; i < playoffTeams.size(); i += 2) {
                Team team1 = playoffTeams.get(i);
                Team team2 = playoffTeams.get(i + 1);

                // Simulate a playoff match and determine the winner
                Team winner = simulatePlayoffMatch(team1, team2);

                // Log the playoff results
                logPlayoffResult(team1, team2, winner);
                simulateWithDelay(100);

                winners.add(winner);
                winners1.add(winner);
            }

            playoffTeams = winners;
        }
        

        // Log the champion
        Team champion = playoffTeams.get(0);
        logChampion(champion);
    }

    
    private void appendToResultsTextArea(String log) {
        resultsTextArea.append(log);
        resultsTextArea.setCaretPosition(resultsTextArea.getDocument().getLength());
    }
    private ArrayList<Team> getTopTeams() {
        // Sort teams based on the number of wins in descending order
        ArrayList<Team> sortedTeams = new ArrayList<>(teams);
        Collections.sort(sortedTeams, Comparator.comparingInt(Team::getNumberOfGamesWon).reversed());

        // Return the top 8 teams
        return new ArrayList<>(sortedTeams.subList(0, Math.min(8, sortedTeams.size())));
    }
 

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==startsimulation) {
		
			startsimulation.setEnabled(false);
            viewTeamButton.setEnabled(true);
            
            pauseButton.setEnabled(true);
            resumeButton.setEnabled(false);

            simulationWorker.execute(); 

		}
if(e.getSource()==viewTeamButton) {
	 simulationPaused = true;
     resumeButton.setEnabled(true);
     pauseButton.setEnabled(false); 
     TeamViewer teamViewer = new TeamViewer(userTeam);
		}
if(e.getSource()==pauseButton) {
	 simulationPaused = true;
     resumeButton.setEnabled(true);
     pauseButton.setEnabled(false);
}
if(e.getSource()==resumeButton) {

	 simulationPaused = false;
	 resumeButton.setEnabled(false);
	 pauseButton.setEnabled(true);
}
if(e.getSource()==PlayOff_Bracket) {
	PlayOff nea=new PlayOff(winners1,topTeams);
}
	}
}
