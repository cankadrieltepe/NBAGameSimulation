package team;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Player.*;
public class Team {
	protected String Teamname;
	protected ArrayList<player> players;
	protected double TeamScore;
	protected int numberOfGamesWon;
	protected int numberOfGamesLost;
	protected int numberOfGamesTied;
	protected ImageIcon teamLogo;

	public ImageIcon getTeamLogo() {
		return teamLogo;
	}
	public void setTeamLogo(ImageIcon teamLogo) {
		this.teamLogo = teamLogo;
	}
	public int getNumberOfGamesLost() {
		return numberOfGamesLost;
	}
	public int getNumberOfGamesTied() {
		return numberOfGamesTied;
	}
	public void setNumberOfGamesLost(int numberOfGamesLost) {
		this.numberOfGamesLost = numberOfGamesLost;
	}
	public void setNumberOfGamesTied(int numberOfGamesTied) {
		this.numberOfGamesTied = numberOfGamesTied;
	}
	public int getNumberOfGamesWon() {
		return numberOfGamesWon;
	}
	public void setNumberOfGamesWon(int numberOfGamesWon) {
		this.numberOfGamesWon = numberOfGamesWon;
	}
	public double calculateTeamScore() {
		double totalScore=0.0;
		for(player player: players) {
			totalScore+=player.getScore();
		}
		return totalScore;
	}
	public double getTeamScore() {
		return TeamScore;
	}
	public void setTeamScore(double teamScore) {
		this.TeamScore = teamScore;
	}
	public String getTeamName() {
		return Teamname;
	}
	
	public void setTeamName(String Teamname) {
		this.Teamname = Teamname;
	}
	public String getTeamname() {
		return Teamname;
	}
	public void setTeamname(String teamname) {
		Teamname = teamname;
	}
	public ArrayList<player> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<player> players) {
		this.players = players;
	}
	public Team(String Teamname,String path){
		this.Teamname = Teamname;
		this.players=new ArrayList<>();
		this.teamLogo=new ImageIcon(path);
	}
	
	public void draftPlayer(player player) {
		players.add(player);
	}
	
	
}
