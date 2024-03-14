package team;
import Player.*;
import User.change_information;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DraftingProcess  implements ActionListener{
	public  String[] listofplayernames;
	public  ArrayList<player> ListOfPlayers1;
	JComboBox<String> comboBox;
	private ArrayList<player> availablePlayers;
	player userSelectedPlayer;
	private  boolean userSelectionMade=false;
	 String selectedPlayerName;
	JFrame frame =new JFrame();
	ArrayList<Team> teams;
	Team userTeam;
	JButton changeInfo=new JButton("Change User Info");

	JButton openSim=new JButton("Open Simulation");
	JLabel label=new JLabel("PRESS IT AFTER THE 10TH \nPLAYER IS CHOSEN!");
	public DraftingProcess(String[] listofplayernames,ArrayList<player> availablePlayers,ArrayList<Team> teams,Team userTeam){
		this.teams=teams;
		this.userTeam=userTeam;
		this.availablePlayers=availablePlayers;
		frame.setSize(400,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		comboBox=new JComboBox<>(listofplayernames);
		comboBox.setBounds(50,50,150,40);
		frame.add(comboBox);
		comboBox.addActionListener(this);
		openSim.setBounds(100, 250, 175, 25);
		openSim.addActionListener(this);
		changeInfo.setBounds(100,150,150,25);
		changeInfo.addActionListener(this);
		frame.add(changeInfo);
		label.setBounds(50, 200, 300, 25);
		frame.add(openSim);
		frame.add(label);

		frame.setVisible(true);
		 JButton selectButton = new JButton("Select Player");
		 selectButton.setBounds(250,50,100,40);
        selectButton.addActionListener(e -> selectPlayer());
        frame.add(selectButton);
	}
	  private void selectPlayer() {
	         selectedPlayerName = (String) comboBox.getSelectedItem();
	        userSelectionMade = true;
	        

	    }
	 public  String waitForUserInput(String teamName) {
	        // Simulate waiting for user input
	        while (!userSelectionMade) {
	            try {
	                Thread.sleep(100);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	        userSelectionMade = false;
	        return selectedPlayerName;
	    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==openSim) {
			SeasonGUI open=new SeasonGUI(teams,userTeam);
		}
		if(e.getSource()==changeInfo) {
			change_information change= new  change_information();
			frame.dispose();
		}
	}
	 public void updateAvailablePlayers(ArrayList<player> updatedAvailablePlayers) {
	        // Update the available players in the combo box
	        availablePlayers = updatedAvailablePlayers;
	        updateComboBox();
	    }

	    private void updateComboBox() {
	        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(getPlayerNames(availablePlayers));
	        comboBox.setModel(model);
	    }

	    private String[] getPlayerNames(ArrayList<player> players) {
	        String[] playerNames = new String[players.size()];
	        for (int i = 0; i < players.size(); i++) {
	            playerNames[i] = players.get(i).getPlayer_name();
	        }
	        return playerNames;
	    }
	}
