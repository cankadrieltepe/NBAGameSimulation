package team;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Player.player;

public class PlayerView implements ActionListener {
	JFrame frame=new JFrame();
	private JTextArea playerDetails;
	private JButton backButton;
	public PlayerView(player player) {
		playerDetails=new JTextArea(10,30);
		playerDetails.setEditable(false);
		displayPlayerInfo(player);
		backButton=new JButton("Back");
		backButton.addActionListener(this);
		frame.add(backButton);
		frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
		frame.add(new JScrollPane(playerDetails));
		frame.setTitle("Player");
		frame.setSize(600,400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

	}
	  private void displayPlayerInfo(player player) {
		  playerDetails.append("Player Name: "+player.getPlayer_name()+"\n");
		  playerDetails.append("Position: "+player.getPosition()+"\n");
		  playerDetails.append("AST: "+player.getAST()+"\n");
		  playerDetails.append("STL: "+player.getSTL()+"\n");
		  playerDetails.append("BLK: "+player.getBLK()+"\n");
		  playerDetails.append("PTS: "+player.getPTS()+"\n");
		  playerDetails.append("TRB: "+player.getTRB()+"\n");
		  
		  playerDetails.append("AST_weight: "+player.getAST_weight()+"\n");
		  playerDetails.append("STL_weight: "+player.getSTL_weight()+"\n");
		  playerDetails.append("BLK_weight: "+player.getBLK_weight()+"\n");
		  playerDetails.append("PTS_weight: "+player.getPTS_weight()+"\n");
		  playerDetails.append("TRB_weight: "+player.getTRB_weight()+"\n");
		  
		  playerDetails.append("Score: "+player.getScore()+"\n");


	    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==backButton) {
			frame.dispose();
		}
	}
}
