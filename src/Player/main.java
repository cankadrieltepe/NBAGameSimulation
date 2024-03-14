package Player;
import team.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import Player.player.PlayerStats;
import User.new_user;
/************** Pledge of Honor ******************************************
I hereby certify that I have completed this programming project on my own
without any help from anyone else. The effort in the project thus belongs
completely to me. I did not search for a solution, or I did not consult any
program written by others or did not copy any program from other sources. I
read and followed the guidelines provided in the project description.
READ AND SIGN BY WRITING YOUR NAME SURNAME AND STUDENT ID
SIGNATURE: <Can Kadri Eltepe, 83855>
*************************************************************************/
public class main {
	public static String[] listofplayernames;
	public static ArrayList<player> ListOfPlayers1;
	public static Team userTeam;
	public static ArrayList<Team> teams;
	public static void main(String[] args) {
		
		BufferedReader r=null;
		 ArrayList<player> ListOfPlayers= new ArrayList<player>();
		try {
			File csvFile =new File("2022-2023 NBA Player Stats - Regular - 2022-2023 NBA Player Stats - Regular.csv");
			//when I turned it into a csv it became comma seperated not";" seperated.
			r=new BufferedReader(new FileReader(csvFile));
			String line="";
			
			//we must skip the first line............
			String row1=r.readLine();
			while((line=r.readLine())!=null) {
				String [] row= line.split(",");
				if(row[2].equals("C")) {
				player Player=new C(row[1],row[2],Double.valueOf(row[29]),Double.valueOf(row[23]),Double.valueOf(row[24]),Double.valueOf(row[25]),Double.valueOf(row[26]),0);
				ListOfPlayers.add(Player);
				int score= Player.Scoring(Player);
				Player.setScore(score);
				}else if(row[2].equals("SG")){
					player Player=new SG(row[1],row[2],Double.valueOf(row[29]),Double.valueOf(row[23]),Double.valueOf(row[24]),Double.valueOf(row[25]),Double.valueOf(row[26]),0);
					ListOfPlayers.add(Player);
					int score= Player.Scoring(Player);
					Player.setScore(score);
				}else if(row[2].equals("PF")){
					player Player=new PF(row[1],row[2],Double.valueOf(row[29]),Double.valueOf(row[23]),Double.valueOf(row[24]),Double.valueOf(row[25]),Double.valueOf(row[26]),0);
					ListOfPlayers.add(Player);
					int score= Player.Scoring(Player);
					Player.setScore(score);
				}else if(row[2].equals("SF")){
					player Player=new SF(row[1],row[2],Double.valueOf(row[29]),Double.valueOf(row[23]),Double.valueOf(row[24]),Double.valueOf(row[25]),Double.valueOf(row[26]),0);
					ListOfPlayers.add(Player);
					int score= Player.Scoring(Player);
					Player.setScore(score);
				}else if(row[2].equals("PG")){
					player Player=new PG(row[1],row[2],Double.valueOf(row[29]),Double.valueOf(row[23]),Double.valueOf(row[24]),Double.valueOf(row[25]),Double.valueOf(row[26]),0);
					ListOfPlayers.add(Player);
					int score= Player.Scoring(Player);
					Player.setScore(score);
				}
			}
			//removing the duplicates
			
		
			 ListOfPlayers1=  mergeDuplicates(ListOfPlayers);
			//changing the listofplayers to string to make them suitable for the combobox
				ArrayList<String> ListOfPlayersNames= new ArrayList<String>();
				for(player Player:ListOfPlayers1) {
					ListOfPlayersNames.add(Player.getPlayer_name());
				}
				listofplayernames=new String[ListOfPlayersNames.size()];
				int count=0;
				for(String names:ListOfPlayersNames) {
					listofplayernames[count]=names;
					count++;
				}
				
				userTeam=new Team("UserTeam","LOGO1.jpg");
				Team Team1=new Team("Team1","LOGO10.png");
				Team Team2=new Team("Team2","LOGO11.png");
				Team Team3=new Team("Team3","LOGO12.png");
				Team Team4=new Team("Team4","LOGO13.png");
				Team Team5=new Team("Team5","LOGO14.png");
				Team Team6=new Team("Team6","LOGO15.jpg");
				Team Team7=new Team("Team7","LOGO16.png");
				Team Team8=new Team("Team8","LOGO2.jpg");
				Team Team9=new Team("Team9","LOGO3.jpg");
				Team Team10=new Team("Team10","LOGO4.jpg");
				Team Team11=new Team("Team11","LOGO5.jpeg");
				Team Team12=new Team("Team12","LOGO6.png");
				Team Team13=new Team("Team13","LOGO7.png");
				Team Team14=new Team("Team14","LOGO8.png");
				Team Team15=new Team("Team15","LOGO9.png");
				teams = new ArrayList<Team>();
				teams.add(Team1);
				teams.add(Team2);
				teams.add(Team3);
				teams.add(Team4);
				teams.add(Team5);
				teams.add(Team6);
				teams.add(Team7);
				teams.add(Team8);
				teams.add(Team9);
				teams.add(Team10);
				teams.add(Team11);
				teams.add(Team12);
				teams.add(Team13);
				teams.add(Team14);
				teams.add(Team15);
				teams.add(userTeam);
				
				DraftingProcess fraft=new DraftingProcess(listofplayernames,ListOfPlayers1,teams,userTeam);
				Draft.drafting(teams,ListOfPlayers1,userTeam);
			}catch(IOException e) {
				e.printStackTrace();
				System.err.println(e);		
		}finally {
			try {
			r.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
	}
	
		
	static ArrayList<player> mergeDuplicates(ArrayList<player> players) {
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
		 ArrayList<player> resulting=new ArrayList<>();

		 resulting.addAll(nonDuplicates);
		   for (PlayerStats stats : playerStatsMap.values()) {
	            player mergedPlayer = stats.calculateAveragePlayer();
	            resulting.add(mergedPlayer);
	        }
		return resulting;
		
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
			 this.initialPlayer=initialPlayer;
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
