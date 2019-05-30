import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.GroupLayout.*;
import javax.swing.LayoutStyle.*;


/**Started Mar 17, 2019 by Georgi Ditsov
*/

public class LoginForm {
	
	private JFrame frmLogIn;
	private JPasswordField txtPassword;
	private JTextField txtUsername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm window = new LoginForm();
					window.frmLogIn.setVisible(true);
					Client.startConnection();
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
		frmLogIn = new JFrame();
		frmLogIn.setTitle("Log In");
		frmLogIn.setBounds(100, 100, 450, 300);
		frmLogIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtUsername.getText().isEmpty() || txtPassword.getPassword().length == 0){
					JOptionPane.showMessageDialog(frmLogIn, "Enter username and password", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else{
					if(Client.logIn(txtUsername.getText(), String.valueOf(txtPassword.getPassword()))){
						frmLogIn.dispose();
						ClientForm client = new ClientForm();
						client.frmClient.setVisible(true);
					}
					else{
						txtPassword.setText(null);
						JOptionPane.showMessageDialog(frmLogIn, "Wrong username or password", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnLogIn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout groupLayout = new GroupLayout(frmLogIn.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(162, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUsername)
								.addComponent(lblPassword)
								.addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
							.addGap(162))))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(331, Short.MAX_VALUE)
					.addComponent(btnLogIn)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(60)
					.addComponent(lblUsername)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(22)
					.addComponent(lblPassword)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnLogIn)
					.addContainerGap())
		);
		frmLogIn.getContentPane().setLayout(groupLayout);
	}
}
