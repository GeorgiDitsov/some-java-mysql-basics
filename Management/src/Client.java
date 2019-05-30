import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

/**Started Mar 17, 2019 by Georgi Ditsov
*/

public class Client {
	private static Socket socketClient;
	private static DataInputStream reader;
	private static DataOutputStream writer;
	
	static void startConnection() throws UnknownHostException, IOException{
		socketClient = new Socket("localhost",1211);
		reader = new DataInputStream(socketClient.getInputStream());
		writer = new DataOutputStream(socketClient.getOutputStream());
	}
	
	static boolean logIn(String username, String password){
		boolean isCorrect = false;
		try {
			writer.writeInt(1);
			writer.flush();
			writer.writeUTF(username);
			writer.flush();
			writer.writeUTF(password);
			writer.flush();
			isCorrect = reader.readBoolean();
			socketClient.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
		return isCorrect;
	}
	
	static void endConnection(){
		try {
			socketClient = new Socket("localhost",1211);
			writer = new DataOutputStream(socketClient.getOutputStream());
			writer.writeInt(0);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}