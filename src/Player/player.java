package Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;

public class player {
	protected String Player_name;
	protected String position;
	protected double PTS;
	protected double TRB;
	protected double AST;
	protected double BLK;
	protected double STL;
	protected double score;
	protected ImageIcon profilePic = new ImageIcon("PROFİLEPIC.png");

	protected double PTS_weight;
	protected double TRB_weight;
	protected double AST_weight;
	protected double BLK_weight;
	protected double STL_weight;
	public double getPTS_weight() {
		return PTS_weight;
	}
	public double getTRB_weight() {
		return TRB_weight;
	}
	public double getAST_weight() {
		return AST_weight;
	}
	public double getBLK_weight() {
		return BLK_weight;
	}
	public double getSTL_weight() {
		return STL_weight;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public int Scoring(player Player) {
		return 0;
	}
	public String getPlayer_name() {
		return Player_name;
	}
	public void setPlayer_name(String player_name) {
		Player_name = player_name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public double getPTS() {
		return PTS;
	}
	public void setPTS(double pTS) {
		PTS = pTS;
	}
	public double getTRB() {
		return TRB;
	}
	public void setTRB(double tRB) {
		TRB = tRB;
	}
	public double getAST() {
		return AST;
	}
	public void setAST(double aST) {
		AST = aST;
	}
	public double getBLK() {
		return BLK;
	}
	public void setBLK(double bLK) {
		BLK = bLK;
	}
	public double getSTL() {
		return STL;
	}
	public void setSTL(double sTL) {
		STL = sTL;
	}

	public player(String Player_name,String position,double PTS,double TRB,double AST,double STL, double BLK ,double score) {
		this.Player_name=Player_name;
		this.position=position;
		this.PTS=PTS;
		this.TRB=TRB;
		this.AST=AST;
		this.BLK=BLK;
		this.STL=STL;
		this.score=score;
	}
	static void mergeDuplicates(ArrayList<player> players) {
		Map<String, PlayerStats> playerStatsMap= new HashMap<>();
		ArrayList<player> nonDuplicates=new ArrayList<>();
		
		 for (player player : players) {
	            String playerName = player.getPlayer_name();
	            if (playerStatsMap.containsKey(playerName)) {
	                playerStatsMap.get(playerName).addPlayer(player);
	            } else {
	                nonDuplicates.add(player);
	                playerStatsMap.put(playerName, new PlayerStats(player));
	            }
	        }
		 players.clear();
		 
		 players.addAll(nonDuplicates);
		   for (PlayerStats stats : playerStatsMap.values()) {
	            player mergedPlayer = stats.calculateAveragePlayer();
	            players.add(mergedPlayer);
	        }
		
		
	}
static class PlayerStats{
	double totalPTS=0;
	double totalTRB=0;
	double totalAST=0;
	 double totalBLK=0;
	 double totalSTL=0;
	 double totalscore=0;
	 int playerCount=0;
	 
	 player initialPlayer;
	 PlayerStats(player initialPlayer){
		 addPlayer(initialPlayer);
	 }
	 
	 void addPlayer(player player) {
		 totalPTS += player.getPTS();
         totalTRB += player.getTRB();
         totalAST += player.getAST();
         totalSTL += player.getSTL();
         totalBLK += player.getBLK();
         totalscore+=player.getScore();
         playerCount++;
	 }
	 player calculateAveragePlayer() {   
		 double avgPTS = totalPTS / playerCount;         
		 double avgTRB = totalTRB / playerCount;    
		 double avgAST = totalAST / playerCount;     
		 double avgSTL = totalSTL / playerCount;    
		 double avgBLK = totalBLK / playerCount; 
		 double avgScore=totalscore/playerCount;
		 return new player( initialPlayer.getPlayer_name(), initialPlayer.getPosition(), avgPTS, avgTRB, avgAST, avgSTL, avgBLK,avgScore);       
		 }
	 
}
}
