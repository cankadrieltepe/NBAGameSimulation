package team;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class PlayOff {
	JLabel team1;
	JLabel team2;
	JLabel team3;
	JLabel team4;
	JLabel team5;
	JLabel team6;
	JLabel team7;
	JLabel team8;
	JLabel winner1;
	JLabel winner2;
	JLabel winner3;
	JLabel winner4;
	JLabel winner5;
	JLabel winner6;
	JLabel winner7;
	
	JFrame frame=new JFrame();
	public PlayOff(ArrayList<Team> winners1,ArrayList<Team> topTeams) {
		team1=new JLabel(topTeams.get(0).getTeamname());
		team2=new JLabel(topTeams.get(1).getTeamname());
		team3=new JLabel(topTeams.get(2).getTeamname());
		team4=new JLabel(topTeams.get(3).getTeamname());
		team5=new JLabel(topTeams.get(4).getTeamname());
		team6=new JLabel(topTeams.get(5).getTeamname());
		team7=new JLabel(topTeams.get(6).getTeamname());
		team8=new JLabel(topTeams.get(7).getTeamname());
		team1.setBounds(50, 50, 75, 25);
		team2.setBounds(50, 100, 75, 25);
		team3.setBounds(50, 150, 75, 25);
		team4.setBounds(50, 200, 75, 25);
		team5.setBounds(50, 250, 75, 25);
		team6.setBounds(50, 300, 75, 25);
		team7.setBounds(50, 350, 75, 25);
		team8.setBounds(50, 400, 75, 25);
		
		winner1=new JLabel(winners1.get(0).getTeamname());
		winner2=new JLabel(winners1.get(1).getTeamname());
		winner3=new JLabel(winners1.get(2).getTeamname());
		winner4=new JLabel(winners1.get(3).getTeamname());
		winner5=new JLabel(winners1.get(4).getTeamname());
		winner6=new JLabel(winners1.get(5).getTeamname());
		winner7=new JLabel(winners1.get(6).getTeamname());
		winner1.setBounds(150,75,100,25);
		winner2.setBounds(150,175,100,25);
		winner3.setBounds(150,275,100,25);
		winner4.setBounds(150,375,100,25);
		winner5.setBounds(270,125,100,25);
		winner6.setBounds(270,325,100,25);
		winner7.setBounds(390,225,100,25);
		frame.add(winner1);
		frame.add(winner2);
		frame.add(winner3);
		frame.add(winner4);
		frame.add(winner5);
		frame.add(winner6);
		frame.add(winner7);
		
		frame.add(team1);
		frame.add(team2);
		frame.add(team3);
		frame.add(team4);
		frame.add(team5);
		frame.add(team6);
		frame.add(team7);
		frame.add(team8);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(800,800);
		frame.setLayout(null);
		frame.setVisible(true);
	}
}
