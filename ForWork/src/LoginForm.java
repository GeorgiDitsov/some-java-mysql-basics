import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

/**
 * @author Georgi Ditsov
 *Copyright (c) 7.01.2018 �. Georgi Ditsov to Present.
 *All rights reserved.
 */

public class LoginForm {

	private JFrame frame;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm window = new LoginForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Log In");
		frame.setResizable(false);
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(104, 184, 200, 20);
		frame.getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(104, 227, 200, 20);
		frame.getContentPane().add(passwordField);
		
		JLabel lblNewLabel = new JLabel("<html>Enter a valid username and password<br>to gain access to the database<html>");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Rockwell", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 70, 500, 75);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setLabelFor(textFieldUsername);
		lblUsername.setFont(new Font("Rockwell", Font.PLAIN, 12));
		lblUsername.setBounds(30, 184, 64, 20);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setLabelFor(passwordField);
		lblPassword.setFont(new Font("Rockwell", Font.PLAIN, 12));
		lblPassword.setBounds(30, 227, 64, 20);
		frame.getContentPane().add(lblPassword);
		
		JButton btnLogin = new JButton("Log In");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldUsername.getText().isEmpty() || passwordField.equals(null)){
					JOptionPane.showMessageDialog(null, "Enter username and password", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else{
					try {
						String username = textFieldUsername.getText();
						String password = passwordField.getText();
						Socket socketClient = new Socket("localhost",1211);;
						BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
						BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
						writer.write(36);
						writer.flush();
						writer.write(username+"\n");
						writer.flush();
						writer.write(password+"\n");
						writer.flush();
						int correct = reader.read();
						reader.close();
						writer.close();
						socketClient.close();
						if(correct == 1){
							JOptionPane.showMessageDialog(null, "You have connection with the database.");
							frame.dispose();
							ClientForm.openClientForm();
						}
						else if(correct == 0){
							JOptionPane.showMessageDialog(null, "Wrong username or password", "Error!", JOptionPane.ERROR_MESSAGE);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
						
					}
					textFieldUsername.setText(null);
					passwordField.setText(null);
				}
			}
		});
		btnLogin.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnLogin.setBounds(150, 270, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		JLabel lblCopyright = new JLabel("This program is made by Georgi Ditsov");
		lblCopyright.setFont(new Font("Rockwell", Font.PLAIN, 11));
		lblCopyright.setForeground(Color.BLUE);
		lblCopyright.setBounds(354, 336, 220, 14);
		frame.getContentPane().add(lblCopyright);
	}
}
