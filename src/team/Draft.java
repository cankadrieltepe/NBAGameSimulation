package team;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import Player.player;

public class Draft {
	
	public static void drafting(ArrayList<Team> teams, ArrayList<player> availablePlayers ,	Team userTeam ) {
	
		Collections.shuffle(teams);

		for (int round=1;round<=10;round++) {
			 for (Team team : teams) {
				 if(team.getTeamName()=="UserTeam") {
					 DraftingProcess draftingProcess = new DraftingProcess(getPlayerNames(availablePlayers),availablePlayers,teams,userTeam);

	                    player selectedPlayer = findPlayerByName(draftingProcess.waitForUserInput(team.getTeamName()),availablePlayers);
	                    team.draftPlayer(selectedPlayer);
	                    availablePlayers.remove(selectedPlayer);
				 }else {
	                player selectedPlayer = selectPlayer(team, availablePlayers);
	                team.draftPlayer(selectedPlayer);
	                availablePlayers.remove(selectedPlayer);
				 }
	            }
			 Collections.reverse(teams);
		}
		for(Team team:teams) {
			double score=team.calculateTeamScore();
			team.setTeamScore(score);
		}
		 logPlayerInformation(teams);
	}//logging the player info
	private static void logPlayerInformation(ArrayList<Team> teams) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("CuratedTeamsInfo.txt"))) {
            for (Team team : teams) {
                writer.write("Team: " + team.getTeamName() + "\n");
                for (player player : team.getPlayers()) {
                    writer.write(player.getPlayer_name().toString() + "\n");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//find the given player from the list
	 private static player findPlayerByName(String playerName, ArrayList<player> availablePlayers) {
	        for (player availablePlayer : availablePlayers) {
	            if (availablePlayer.getPlayer_name().equals(playerName)) {
	                return availablePlayer;
	            }
	        }
	        return null; 
	    }

//select a player according to its position
private static player selectPlayer(Team team, ArrayList<player> availablePlayers) {
	String[] positions= {"PG","PF","SG","SF","C"};
        for (String position : positions) {
            if (!teamHasPosition(team, position)) {
                ArrayList<player> playersInPosition = getPlayersInPosition(availablePlayers, position);
                if (!playersInPosition.isEmpty()) {
                    return getRandomPlayer(playersInPosition);
                }
            }
        }
        // If the team already has players in all positions, select any available player
        return getRandomPlayer(availablePlayers);
    }

    private static boolean teamHasPosition(Team team, String position) {
        for (player player : team.getPlayers()) {
            if (player.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    private static ArrayList<player> getPlayersInPosition(ArrayList<player> players, String position) {
        ArrayList<player> playersInPosition = new ArrayList<>();
        for (player player : players) {
            if (player.getPosition().equals(position)) {
                playersInPosition.add(player);
            }
        }
        return playersInPosition;
    }

    private static player getRandomPlayer(ArrayList<player> players) {
        if (!players.isEmpty()) {
            int index = new Random().nextInt(players.size());
            return players.get(index);
        }
        return null;
    }
	  private static String[] getPlayerNames(ArrayList<player> players) {
	        String[] playerNames = new String[players.size()];
	        for (int i = 0; i < players.size(); i++) {
	            playerNames[i] = players.get(i).getPlayer_name();
	        }
	        return playerNames;
	    }
}
