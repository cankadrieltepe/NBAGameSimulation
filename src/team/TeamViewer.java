package team;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

import Player.player;

public class TeamViewer implements ActionListener{

	private Team userTeam;
	private JButton backButton;
	private JFrame frame=new JFrame();
	JButton playerButton1;
	JButton playerButton2;
	JButton playerButton3;
	JButton playerButton4;
	JButton playerButton5;
	JButton playerButton6;
	JButton playerButton7;
	JButton playerButton8;
	JButton playerButton9;
	JButton playerButton10;
	ArrayList<player> players;

	public TeamViewer(Team userTeam) {
		this.userTeam=userTeam;
		frame.setTitle("View Team");
		frame.setSize(400,400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		
		 this.players = userTeam.getPlayers();
		playerButton1 = new JButton(players.get(0).getPlayer_name());
        playerButton1.addActionListener(this);
         frame.add(playerButton1);
     	playerButton2 = new JButton(players.get(1).getPlayer_name());
        playerButton2.addActionListener(this);
         frame.add(playerButton2);
     	playerButton3 = new JButton(players.get(2).getPlayer_name());
        playerButton3.addActionListener(this);
         frame.add(playerButton3);
     	playerButton4 = new JButton(players.get(3).getPlayer_name());
        playerButton4.addActionListener(this);
         frame.add(playerButton4);
     	playerButton5 = new JButton(players.get(4).getPlayer_name());
        playerButton5.addActionListener(this);
         frame.add(playerButton5);
     	 playerButton6 = new JButton(players.get(5).getPlayer_name());
        playerButton6.addActionListener(this);
         frame.add(playerButton6);
     	 playerButton7 = new JButton(players.get(6).getPlayer_name());
        playerButton7.addActionListener(this);
         frame.add(playerButton7);
     	 playerButton8 = new JButton(players.get(7).getPlayer_name());
        playerButton8.addActionListener(this);
         frame.add(playerButton8);
     	 playerButton9 = new JButton(players.get(8).getPlayer_name());
        playerButton9.addActionListener(this);
         frame.add(playerButton9);
     	 playerButton10 = new JButton(players.get(9).getPlayer_name());
        playerButton10.addActionListener(this);
         frame.add(playerButton10);
	        
	        backButton = new JButton("Back");
	        backButton.addActionListener(this);
	        frame.add(backButton);

	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==backButton) {
			frame.dispose();
		}
		if(e.getSource()==playerButton1)	{
			PlayerView playerGUI=new PlayerView(players.get(0));
		} 
		if(e.getSource()==playerButton2)	{
			PlayerView playerGUI=new PlayerView(players.get(1));
		} 
		if(e.getSource()==playerButton3)	{
			PlayerView playerGUI=new PlayerView(players.get(2));
		} 
		if(e.getSource()==playerButton4)	{
			PlayerView playerGUI=new PlayerView(players.get(3));
		} 
		if(e.getSource()==playerButton5)	{
			PlayerView playerGUI=new PlayerView(players.get(4));
		} 
		if(e.getSource()==playerButton6)	{
			PlayerView playerGUI=new PlayerView(players.get(5));
		} 
		if(e.getSource()==playerButton7)	{
			PlayerView playerGUI=new PlayerView(players.get(6));
		} 
		if(e.getSource()==playerButton8)	{
			PlayerView playerGUI=new PlayerView(players.get(7));
		} 
		if(e.getSource()==playerButton9)	{
			PlayerView playerGUI=new PlayerView(players.get(8));
		} 
		if(e.getSource()==playerButton10)	{
			PlayerView playerGUI=new PlayerView(players.get(9));
		} 
		
	}

}
