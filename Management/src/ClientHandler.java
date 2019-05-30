import java.io.*;
import java.net.*;

/**Started Mar 17, 2019 by Georgi Ditsov
*/

public class ClientHandler extends Thread {

	private Socket socketClient;
	private DataInputStream reader;
	private DataOutputStream writer;
	
	ClientHandler(Socket socketClient, DataInputStream reader, DataOutputStream writer){
		this.socketClient = socketClient;
		this.reader = reader;
		this.writer = writer;
	}
	
	@Override
	public void run() {
		synchronized (this) {
			int option;
			Requests request;
			while(true){
				try{
					option = reader.readInt();
					if(option == 0){
						System.out.println("Client "+this.socketClient+" sends exit...");
						this.socketClient.close();
						break;
					}
					switch(option){
					case 1: request = new Requests(reader.readUTF(), reader.readUTF());
						writer.writeBoolean(request.isLogged);
						writer.flush();
						if(!request.isLogged){
							this.socketClient.close();
						}
						break;
					case 2: 
						break;
					default: System.out.println("No one called!");
						break;
					}
				}catch(IOException e){
					e.printStackTrace();
				}	
			}
		}
	}
}
