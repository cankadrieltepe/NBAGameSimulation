package User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Player.main;
import Player.player;
import team.Draft;
import team.DraftingProcess;
import team.Team;

public class LoginPage implements ActionListener {
	
	JFrame frame =new JFrame();
	JButton loginAccount=new JButton("Login");
	JLabel nick=new JLabel("NickName:");
	JLabel Password=new JLabel("Password:");
	JTextField nickname= new JTextField();
	JTextField password= new JTextField();
	
	LoginPage(){
		
		nick.setBounds(50, 50, 75, 25);
		Password.setBounds(50, 100, 75, 25);
		frame.add(nick);
		frame.add(Password);
		nickname.setBounds(125, 50, 200, 25);
		password.setBounds(125, 100, 200, 25);
		frame.add(nickname);
		frame.add(password);
		
		loginAccount.setBounds(50, 150, 75, 25);
		loginAccount.setFocusable(false);
		loginAccount.addActionListener(this);
		frame.add(loginAccount);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,400);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==loginAccount ) {
		try{
			String ni=nickname.getText();
			String pa=password.getText();
			BufferedReader reader=new BufferedReader(new FileReader("users.txt"));
			String line="";
			boolean tester=false;
			while((line=reader.readLine())!=null) {
				String [] row= line.split(",");
				if(ni.equals(row[0]) &&pa.equals(row[1])) {
					tester=true;
					
					break;
				}
			}
			reader.close();
			if(tester==false) {
				JOptionPane.showInternalMessageDialog(null,"Your username or password is wrong!","Warning",JOptionPane.WARNING_MESSAGE);
				return;
			}else {
				System.out.println("Succesfully logged in!");				
			}
		
			}catch(IOException t) {
				t.printStackTrace();
			}
			}
	}
}
