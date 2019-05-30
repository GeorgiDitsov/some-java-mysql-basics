import java.io.*;
import java.net.*;

/**Started Mar 17, 2019 by Georgi Ditsov
*/

public class ManagementServer {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(1211);
		System.out.println("Server is running on port "+server.getLocalPort());
		Socket socket = null;
		DataInputStream reader = null;
        DataOutputStream writer = null;
		while(true){
			try{
				socket = server.accept();
				System.out.println("New client connected");
				reader = new DataInputStream(socket.getInputStream());
				writer = new DataOutputStream(socket.getOutputStream());
				new Thread(new ClientHandler(socket, reader, writer)).start();
			}catch(Exception e){
				socket.close();
				server.close();
				e.printStackTrace();
			}
		}
	}
}
