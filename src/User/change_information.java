package User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Player.player;
import team.Team;

public class change_information implements ActionListener{

	
	JFrame frame =new JFrame();
	JButton changeInfo=new JButton("Change Information");
	JLabel nick=new JLabel("Old NickName:");
	JLabel Password=new JLabel("New Password:");
	JLabel age=new JLabel("New Age:");
	JLabel Email=new JLabel("New E-mail:");
	JTextField nickname= new JTextField();
	JTextField password= new JTextField();
	JTextField Age= new JTextField();
	JTextField email= new JTextField();
	
	public change_information(){
		
		nick.setBounds(50, 50, 100, 25);
		Password.setBounds(50, 100, 100, 25);
		age.setBounds(50, 150, 100, 25);
		Email.setBounds(50, 200, 100, 25);
		frame.add(nick);
		frame.add(age);
		frame.add(Password);
		frame.add(Email);
		nickname.setBounds(150, 50, 200, 25);
		password.setBounds(150, 100, 200, 25);
		Age.setBounds(150, 150, 200, 25);
		email.setBounds(150, 200, 200, 25);
		frame.add(nickname);
		frame.add(Age);
		frame.add(password);
		frame.add(email);
		
		changeInfo.setBounds(50, 250, 150, 25);
		changeInfo.setFocusable(false);
		changeInfo.addActionListener(this);

		frame.add(changeInfo);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,400);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==changeInfo) {
			try{
				String tempfile="temp.txt";
				File oldFile=new File("users.txt");
				File newFile=new File(tempfile);
				String ni=nickname.getText();
				String pa=password.getText();
				String Ag=Age.getText();
				String em=email.getText();
			FileWriter fw=new FileWriter(tempfile,true);
			BufferedWriter bw=new BufferedWriter(fw);
			PrintWriter pw= new PrintWriter(bw);
			BufferedReader reader=new BufferedReader(new FileReader("users.txt"));
			String line="";
			while((line=reader.readLine())!=null) {
				String [] row= line.split(",");
				if(!ni.equals(row[0])) {
					pw.println(line);
				}else if(ni.equals(row[0])){
					row[1]=pa;
					row[4]=Ag;
					row[5]=em;
					String line1=String.join(",",row);
					pw.println(line1);
				}
				
			}
			//reference 1.
			pw.flush();
			pw.close();
			bw.close();
			fw.close();
			reader.close();
			
			oldFile.delete();
			File temp2=new File("users.txt");
			newFile.renameTo(temp2);
			LoginPage login=new LoginPage();
			frame.dispose();
			}catch(IOException t) {
				t.printStackTrace();
			}
		}
		
	}

}
