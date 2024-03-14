package User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.*;

import Player.player;
import team.Team;

public class new_user implements ActionListener{
	
	JFrame frame =new JFrame();
	JButton createAccount=new JButton("Sign up");
	JButton loginOpen=new JButton("Login");
	JLabel nick=new JLabel("NickName:");
	JLabel Password=new JLabel("Password:");
	JLabel name=new JLabel("Name:");
	JLabel surname=new JLabel("Surname:");
	JLabel age=new JLabel("Age:");
	JLabel Email=new JLabel("E-mail:");
	JLabel message=new JLabel();
	JTextField nickname= new JTextField();
	JTextField password= new JTextField();
	JTextField Name= new JTextField();
	JTextField Surname= new JTextField();
	JTextField Age= new JTextField();
	JTextField email= new JTextField();
	public new_user(){
		
		nick.setBounds(50, 50, 75, 25);
		Password.setBounds(50, 100, 75, 25);
		name.setBounds(50, 150, 75, 25);
		surname.setBounds(50, 200, 75, 25);
		age.setBounds(50, 250, 75, 25);
		Email.setBounds(50, 300, 75, 25);
		frame.add(nick);
		frame.add(Password);
		frame.add(name);
		frame.add(surname);
		frame.add(age);
		frame.add(Email);
		
		nickname.setBounds(125, 50, 200, 25);
		password.setBounds(125, 100, 200, 25);
		Name.setBounds(125, 150, 200, 25);
		Surname.setBounds(125, 200, 200, 25);
		Age.setBounds(125, 250, 200, 25);
		email.setBounds(125, 300, 200, 25);
		frame.add(nickname);
		frame.add(password);
		frame.add(Name);
		frame.add(Surname);
		frame.add(Age);
		frame.add(email);
		
		createAccount.setBounds(50, 350, 100, 25);
		createAccount.setFocusable(false);
		createAccount.addActionListener(this);
		frame.add(createAccount);
		loginOpen.setBounds(250, 350, 75, 25);
		loginOpen.setFocusable(false);
		loginOpen.addActionListener(this);
		frame.add(loginOpen);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,600);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==loginOpen) {
			LoginPage login=new LoginPage();
			frame.dispose();
		}
		if(e.getSource()==createAccount) {
			try {
			String ni=nickname.getText();
			String pa=password.getText();
			String Na=Name.getText();
			String Su=Surname.getText();
			String Ag=Age.getText();
			int A=Integer.parseInt(Age.getText());
			String em=email.getText();
			BufferedReader reader=new BufferedReader(new FileReader("users.txt"));
			String line="";
			while((line=reader.readLine())!=null) {
				String [] row= line.split(",");
				if(ni.equals(row[0])) {
					JOptionPane.showInternalMessageDialog(null,"You must pick a different nickname!\nThis username is taken.","Warning",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
			}
			//reference 7
			reader.close();
			if(!ni.matches("[a-zA-Z0-9]+")) {
				JOptionPane.showInternalMessageDialog(null,"You must pick a suitable nickname!\nUsername can only include letter and number characters.","Warning",JOptionPane.WARNING_MESSAGE);
				return;
			}if (pa.length() < 8 || !pa.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&+=]).*")) {
				JOptionPane.showInternalMessageDialog(null,"You must pick a suitable password!\nPassword should be at least eight characters, including letters, numbers, and\r\n"
						+ "special characters.","Warning",JOptionPane.WARNING_MESSAGE);
				return;
			}if (Na.length() < 3 || !Na.matches("[a-zA-Z]+")) {
				JOptionPane.showInternalMessageDialog(null,"You must pick a suitable Name!\nName and surname, each should have at least three characters (only letters).","Warning",JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (Su.length() < 3 || !Su.matches("[a-zA-Z]+")) {
				JOptionPane.showInternalMessageDialog(null,"You must pick a suitable Surname!\nName and surname, each should have at least three characters (only letters).","Warning",JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(A<12) {
				JOptionPane.showInternalMessageDialog(null,"You must pick a suitable Age!\nAge must be at least 12","Warning",JOptionPane.WARNING_MESSAGE);
				return;
			}
			BufferedReader reader1=new BufferedReader(new FileReader("users.txt"));
			String line1="";
			while((line1=reader1.readLine())!=null) {
				String [] row1= line1.split(",");
				if(em.equals(row1[5])) {
					JOptionPane.showInternalMessageDialog(null,"You must pick a different email!\nThis email is in use.","Warning",JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
			reader1.close();
			if (!em.matches("^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,4}$")) {
				JOptionPane.showInternalMessageDialog(null,"You must pick a suitable Email!\nThe email address should be in the correct format. (e.g., name@domain.com)","Warning",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			
			File f=new File("users.txt");
			if(!f.exists()) {
				f.createNewFile();
			}
			FileWriter pw=new FileWriter(f,true);
			pw.write(ni+","+pa+","+Na+","+Su+","+Ag+","+em+"\n");
			pw.close();
			LoginPage login=new LoginPage();
			frame.dispose();
			}catch(IOException f) {
				f.printStackTrace();
			}catch(NumberFormatException t) {
				System.out.println("age should be a number!");
				t.printStackTrace();
			}catch(Exception o) {
				o.printStackTrace();
			}
		}
		
	}

	
}
