import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * @author Georgi Ditsov
 *Copyright (c) 7.01.2018 �. Georgi Ditsov to Present.
 *All rights reserved.
 */
public class Client {

	public static void addFunction(String serial, String machineType, String model, String cpu, String ram,
			String diskDrive, String os, String msOffice, String otherPrograms, String date, String employee,
			String room, String owner, String monitorSerialNumber, String monitorModel){
		try{
			Socket socketClient= new Socket("localhost",1211);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(2);
			writer.flush();
			writer.write(serial+"\n");
			writer.flush();
			writer.write(machineType+"\n");
			writer.flush();
			writer.write(model+"\n");
			writer.flush();
			writer.write(cpu+"\n");
			writer.flush();
			writer.write(ram+"\n");
			writer.flush();
			writer.write(diskDrive+"\n");
			writer.flush();
			writer.write(os+"\n");
			writer.flush();
			writer.write(msOffice+"\n");
			writer.flush();
			writer.write(otherPrograms+"\n");
			writer.flush();
			writer.write(date+"\n");
			writer.flush();
			writer.write(employee+"\n");
			writer.flush();
			writer.write(room+"\n");
			writer.flush();
			writer.write(owner+"\n");
			writer.flush();
			writer.write(monitorSerialNumber+"\n");
			writer.flush();
			writer.write(monitorModel+"\n");
			writer.flush();
			JOptionPane.showMessageDialog(null, reader.readLine(), "���������:", JOptionPane.INFORMATION_MESSAGE);
			socketClient.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}		
	}

	public static void loadEmployees() {
		try {
			ClientForm.fillWithEmployees(null);
			Socket socketClient= new Socket("localhost",1211);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			writer.write(1);
			writer.flush();
			String name;
			while(true){
				name = reader.readLine();
				if(name == null){
					break;
				}
				else{
					ClientForm.fillWithEmployees(name);
				}
			}
			socketClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}	
	}
	
	public static void addEmployee(String name, String department) {
		try {
			Socket socketClient= new Socket("localhost",1211);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(13);
			writer.flush();
			writer.write(name +"\n");
			writer.flush();
			writer.write(department +"\n");
			writer.flush();
			JOptionPane.showMessageDialog(null, reader.readLine(), "", JOptionPane.INFORMATION_MESSAGE);
			socketClient.close();
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}		
	}

	public static void loadSerials(){
		try {
			ClientForm.fillWithPcSerialNumbers(null);
			Socket socketClient= new Socket("localhost",1211);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			writer.write(15);
			writer.flush();
			String name;
			while(true){
				name = reader.readLine();
				if(name == null){
					break;
				}
				else{
					ClientForm.fillWithPcSerialNumbers(name);
				}
			}
			socketClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);;
		}
	}

	public static void loadPcModels(){
		try {
			ClientForm.fillWithPcModels(null);
			Socket socketClient= new Socket("localhost",1211);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			writer.write(4);
			writer.flush();
			String name;
			while(true){
				name = reader.readLine();
				if(name == null){
					break;
				}
				else{
					ClientForm.fillWithPcModels(name);
				}
			}
			socketClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void addPcModels(String model){
		try {
			Socket socketClient= new Socket("localhost",1211);
			//BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(17);
			writer.flush();
			writer.write(model +"\n");
			writer.flush();
			//JOptionPane.showMessageDialog(null, reader.readLine(), "", JOptionPane.INFORMATION_MESSAGE);
			socketClient.close();
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void loadPcCPUs(){
		try {
			ClientForm.fillWithPcCPUs(null);
			Socket socketClient= new Socket("localhost",1211);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			writer.write(5);
			writer.flush();
			String name;
			while(true){
				name = reader.readLine();
				if(name == null){
					break;
				}
				else{
					ClientForm.fillWithPcCPUs(name);
				}
			}
			socketClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void addPcCPUs(String cpu){
		try {
			Socket socketClient= new Socket("localhost",1211);
			//BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(18);
			writer.flush();
			writer.write(cpu +"\n");
			writer.flush();
			//JOptionPane.showMessageDialog(null, reader.readLine(), "", JOptionPane.INFORMATION_MESSAGE);
			socketClient.close();
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void loadPcRAMs(){
		try {
			ClientForm.fillWithPcRAMs(null);
			Socket socketClient= new Socket("localhost",1211);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			writer.write(6);
			writer.flush();
			String name;
			while(true){
				name = reader.readLine();
				if(name == null){
					break;
				}
				else{
					ClientForm.fillWithPcRAMs(name);
				}
			}
			socketClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void addPcRAMs(String ram){
		try {
			Socket socketClient= new Socket("localhost",1211);
			//BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(19);
			writer.flush();
			writer.write(ram +"\n");
			writer.flush();
			//JOptionPane.showMessageDialog(null, reader.readLine(), "", JOptionPane.INFORMATION_MESSAGE);
			socketClient.close();
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void loadPcDiskDrives(){
		try {
			ClientForm.fillWithPcDisks(null);
			Socket socketClient= new Socket("localhost",1211);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			writer.write(7);
			writer.flush();
			String name;
			while(true){
				name = reader.readLine();
				if(name == null){
					break;
				}
				else{
					ClientForm.fillWithPcDisks(name);
				}
			}
			socketClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void addPcDiskDrives(String diskDrive){
		try {
			Socket socketClient= new Socket("localhost",1211);
			//BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(20);
			writer.flush();
			writer.write(diskDrive +"\n");
			writer.flush();
			//JOptionPane.showMessageDialog(null, reader.readLine(), "", JOptionPane.INFORMATION_MESSAGE);
			socketClient.close();
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void loadPcOsVersions(){
		try {
			ClientForm.fillWithPcOS(null);
			Socket socketClient= new Socket("localhost",1211);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			writer.write(8);
			writer.flush();
			String name;
			while(true){
				name = reader.readLine();
				if(name == null){
					break;
				}
				else{
					ClientForm.fillWithPcOS(name);
				}
			}
			socketClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void addPcOSVersions(String os){
		try {
			Socket socketClient= new Socket("localhost",1211);
			//BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(21);
			writer.flush();
			writer.write(os +"\n");
			writer.flush();
			//JOptionPane.showMessageDialog(null, reader.readLine(), "", JOptionPane.INFORMATION_MESSAGE);
			socketClient.close();
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void loadPcMsOffice(){
		try {
			ClientForm.fillWithPcMSOffice(null);
			Socket socketClient= new Socket("localhost",1211);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			writer.write(9);
			writer.flush();
			String name;
			while(true){
				name = reader.readLine();
				if(name == null){
					break;
				}
				else{
					ClientForm.fillWithPcMSOffice(name);
				}
			}
			socketClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void addPcMSOffices(String msOffice){
		try {
			Socket socketClient= new Socket("localhost",1211);
			//BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(22);
			writer.flush();
			writer.write(msOffice +"\n");
			writer.flush();
			//JOptionPane.showMessageDialog(null, reader.readLine(), "", JOptionPane.INFORMATION_MESSAGE);
			socketClient.close();
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void loadPcOtherPrograms(){
		try {
			ClientForm.fillWithPcOtherProgs(null);
			ClientForm.fillWithPcOtherProgs("none");
			Socket socketClient= new Socket("localhost",1211);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			writer.write(10);
			writer.flush();
			String name;
			while(true){
				name = reader.readLine();
				if(name == null){
					break;
				}
				else{
					ClientForm.fillWithPcOtherProgs(name);
				}
			}
			socketClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void addPcOthers(String otherPrograms){
		try {
			Socket socketClient= new Socket("localhost",1211);
			//BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(23);
			writer.flush();
			writer.write(otherPrograms +"\n");
			writer.flush();
			//JOptionPane.showMessageDialog(null, reader.readLine(), "", JOptionPane.INFORMATION_MESSAGE);
			socketClient.close();
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void loadRooms(){
		try {
			ClientForm.fillWithRooms(null);
			Socket socketClient= new Socket("localhost",1211);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			writer.write(14);
			writer.flush();
			String records;
			while(true){
				records = reader.readLine();
				if(records == null){
					break;
				}
				else{
					ClientForm.fillWithRooms(records);
				}
			}
			socketClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void addRooms(String room){
		try {
			Socket socketClient= new Socket("localhost",1211);
			//BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(24);
			writer.flush();
			writer.write(room +"\n");
			writer.flush();
			//JOptionPane.showMessageDialog(null, reader.readLine(), "", JOptionPane.INFORMATION_MESSAGE);
			socketClient.close();
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void addDepartment(String department){
		try {
			Socket socketClient= new Socket("localhost",1211);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(12);
			writer.flush();
			writer.write(department +"\n");
			writer.flush();
			JOptionPane.showMessageDialog(null, reader.readLine(), "", JOptionPane.INFORMATION_MESSAGE);
			socketClient.close();
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}		
	}

	public static void loadDepartments() {
		try {
			ClientForm.fillWithDepartments(null);
			Socket socketClient= new Socket("localhost",1211);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			writer.write(11);
			writer.flush();
			String name;
			while(true){
				name = reader.readLine();
				if(name == null){
					break;
				}
				else{
					ClientForm.fillWithDepartments(name);
				}
			}
			socketClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void updateDepartment(String department, String newDepartentName){
		try {
			ClientForm.fillWithDepartments(null);
			Socket socketClient= new Socket("localhost",1211);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			writer.write(40);
			writer.flush();
			writer.write(department+"\n");
			writer.flush();
			writer.write(newDepartentName+"\n");
			writer.flush();
			JOptionPane.showMessageDialog(null, reader.readLine(), "", JOptionPane.INFORMATION_MESSAGE);
			socketClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void loadUsedPCs(){
		try {
			ClientForm.fillWithUsedComputers(null);
			Socket socketClient= new Socket("localhost",1211);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			writer.write(26);
			writer.flush();
			String name;
			while(true){
				name = reader.readLine();
				if(name == null){
					break;
				}
				else{
					ClientForm.fillWithUsedComputers(name);
				}
			}
			socketClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void loadUnusedPCs(){
		try {
			ClientForm.fillWithUnusedComputers(null);
			Socket socketClient= new Socket("localhost",1211);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			writer.write(27);
			writer.flush();
			String name;
			while(true){
				name = reader.readLine();
				if(name == null){
					break;
				}
				else{
					ClientForm.fillWithUnusedComputers(name);
				}
			}
			socketClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void loadMonitorSerials(){
		try {
			ClientForm.fillWithMonitorSerials(null);
			Socket socketClient= new Socket("localhost",1211);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			writer.write(28);
			writer.flush();
			String name;
			while(true){
				name = reader.readLine();
				if(name == null){
					break;
				}
				else{
					ClientForm.fillWithMonitorSerials(name);
				}
			}
			socketClient.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void loadMonitorModels(){
		try {
			ClientForm.fillWithMonitorModels(null);
			Socket socketClient= new Socket("localhost",1211);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			writer.write(29);
			writer.flush();
			String name;
			while(true){
				name = reader.readLine();
				if(name == null){
					break;
				}
				else{
					ClientForm.fillWithMonitorModels(name);
				}
			}
			socketClient.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void addMonitor(String monitorSerialNumber, String monitorModel){
		try {
			Socket socketClient= new Socket("localhost",1211);
			//BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(25);
			writer.flush();
			writer.write(monitorSerialNumber +"\n");
			writer.flush();
			writer.write(monitorModel+"\n");
			writer.flush();
			//JOptionPane.showMessageDialog(null, reader.readLine(), "", JOptionPane.INFORMATION_MESSAGE);
			socketClient.close();
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void setUnused(String serialNumber, String date){
		try {
			Socket socketClient= new Socket("localhost",1211);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(31);
			writer.flush();
			writer.write(serialNumber+"\n");
			writer.flush();
			writer.write(date+"\n");
			writer.flush();
			JOptionPane.showMessageDialog(null, reader.readLine(), "", JOptionPane.INFORMATION_MESSAGE);
			socketClient.close();
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void setUsed(String serialNumber, String user, String date){
		try {
			Socket socketClient= new Socket("localhost",1211);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(32);
			writer.flush();
			writer.write(serialNumber+"\n");
			writer.flush();
			writer.write(user+"\n");
			writer.flush();
			writer.write(date+"\n");
			writer.flush();
			JOptionPane.showMessageDialog(null, reader.readLine(), "", JOptionPane.INFORMATION_MESSAGE);
			socketClient.close();
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void findWithSerialNumber(String serialNumber){
		try{
			Socket socketClient = new Socket("localhost",1211);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(16);
			writer.flush();
			writer.write(serialNumber+"\n");
			writer.flush();
			String records = new String();
			records = reader.readLine();
			String[] recordsInArray = records.split("/");
			for(int i = 0; i < recordsInArray.length; i++){
				System.out.println(recordsInArray[i]);
			}
			if(recordsInArray[1].equals("PC")){
				ClientForm.setRecords(recordsInArray[0], recordsInArray[1], recordsInArray[2], recordsInArray[3],
						recordsInArray[4], recordsInArray[5], recordsInArray[6], recordsInArray[7], recordsInArray[8],
						recordsInArray[9], recordsInArray[10], recordsInArray[11], recordsInArray[14],
						recordsInArray[12], recordsInArray[13]);
			}
			else if(recordsInArray[1].equals("Laptop")){
				if(recordsInArray.length == 13){
					ClientForm.setRecords(recordsInArray[0], recordsInArray[1], recordsInArray[2], recordsInArray[3],
							recordsInArray[4], recordsInArray[5], recordsInArray[6], recordsInArray[7], recordsInArray[8],
							recordsInArray[9], recordsInArray[10], recordsInArray[11], recordsInArray[12],
							null, null);
				}
				else{
					ClientForm.setRecords(recordsInArray[0], recordsInArray[1], recordsInArray[2], recordsInArray[3],
							recordsInArray[4], recordsInArray[5], recordsInArray[6], recordsInArray[7], recordsInArray[8],
							recordsInArray[9], recordsInArray[10], recordsInArray[11], null,
							null, null);
				}					
			}
			socketClient.close();
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void findWithEmployeeName(String employee){
		try{
			Socket socketClient = new Socket("localhost",1211);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(39);
			writer.flush();
			writer.write(employee+"\n");
			writer.flush();
			String records = new String();
			records = reader.readLine();
			String[] recordsInArray = records.split("/");
			for(int i = 0; i < recordsInArray.length; i++){
				System.out.println(recordsInArray[i]);
			}
			if(recordsInArray[1].equals("PC")){
				ClientForm.setRecords(recordsInArray[0], recordsInArray[1], recordsInArray[2], recordsInArray[3],
						recordsInArray[4], recordsInArray[5], recordsInArray[6], recordsInArray[7], recordsInArray[8],
						recordsInArray[9], recordsInArray[10], recordsInArray[11], recordsInArray[14],
						recordsInArray[12], recordsInArray[13]);
			}
			else if(recordsInArray[1].equals("Laptop")){
				if(recordsInArray.length == 13){
					ClientForm.setRecords(recordsInArray[0], recordsInArray[1], recordsInArray[2], recordsInArray[3],
							recordsInArray[4], recordsInArray[5], recordsInArray[6], recordsInArray[7], recordsInArray[8],
							recordsInArray[9], recordsInArray[10], recordsInArray[11], recordsInArray[12],
							null, null);
				}
				else{
					ClientForm.setRecords(recordsInArray[0], recordsInArray[1], recordsInArray[2], recordsInArray[3],
							recordsInArray[4], recordsInArray[5], recordsInArray[6], recordsInArray[7], recordsInArray[8],
							recordsInArray[9], recordsInArray[10], recordsInArray[11], null,
							null, null);
				}					
			}
			socketClient.close();
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, "���������� ���� ��������!", "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void showPCsInRoom(String room){
		String result = "� ����: "+room+"/";
		try{
			Socket socketClient = new Socket("localhost",1211);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(35);
			writer.flush();
			writer.write(room+"\n");
			writer.flush();
			result += reader.readLine();
			result = result.replace("/", "\n");
			ClientForm.setTextAreaForPrint(result);
			socketClient.close();
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void showWhatsInRoom(String room){
		try {
			ClientForm.fillWithRooms(null);
			Socket socketClient= new Socket("localhost",1211);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			writer.write(43);
			writer.flush();
			writer.write(room+"\n");
			writer.flush();
			ArrayList<String> records = new ArrayList<String>();
			int count = 0;
			while(true){
				records.add(count, reader.readLine());
				if(records.get(count) == null){
					System.out.println(records.get(count));
					break;
				}
				else{
					System.out.println(records.get(count));
					//ClientForm.fillRecords(record, count);
					count++;
				}
			}
			socketClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void usedBy(String serialNumber) {
		String user = null;
		try{
			Socket socketClient = new Socket("localhost",1211);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(30);
			writer.flush();
			writer.write(serialNumber+"\n");
			writer.flush();
			user = reader.readLine();
			JOptionPane.showMessageDialog(null, "�������� ��� ������ ����� "+serialNumber+" �� �������� ��: "+user);
			socketClient.close();
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void deleteEmployee(String employee){
		try{
			Socket socketClient = new Socket("localhost",1211);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(33);
			writer.flush();
			writer.write(employee+"\n");
			writer.flush();
			JOptionPane.showMessageDialog(null, reader.readLine());
			socketClient.close();
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void reinstallPc(String serial, String date, String os, String msOfffice, String ram, String disk){
		try {
			Socket socketClient= new Socket("localhost",1211);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(34);
			writer.flush();
			writer.write(serial+"\n");
			writer.flush();
			writer.write(date+"\n");
			writer.flush();
			writer.write(os+"\n");
			writer.flush();
			writer.write(msOfffice+"\n");
			writer.flush();
			writer.write(ram+"\n");
			writer.flush();
			writer.write(disk+"\n");
			writer.flush();
			JOptionPane.showMessageDialog(null, reader.readLine(), "", JOptionPane.INFORMATION_MESSAGE);
			socketClient.close();
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void showHistory(String serial){
		String result = "History of PC: "+serial+"|";
		try{
			Socket socketClient = new Socket("localhost",1211);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(3);
			writer.flush();
			writer.write(serial+"\n");
			writer.flush();
			result += reader.readLine();
			result = result.replace("|", "\n");
			ClientForm.setTextAreaForPrint2(result);
			socketClient.close();
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void deletePC(String serial){
		try{
			Socket socketClient = new Socket("localhost",1211);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(38);
			writer.flush();
			writer.write(serial+"\n");
			writer.flush();
			JOptionPane.showMessageDialog(null, reader.readLine());
			socketClient.close();
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void showInfoForPC(String serial){
		try{
			Socket socketClient = new Socket("localhost",1211);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(37);
			writer.flush();
			writer.write(serial+"\n");
			writer.flush();
			String result = reader.readLine();
			result = result.replace("|", "\n");
			ClientForm.setTextAreaForPrint(result);
			socketClient.close();
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void changeEmployeeDepartment(String employeeName, String newDepartment){
		try{
			Socket socketClient = new Socket("localhost",1211);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(41);
			writer.flush();
			writer.write(employeeName+"\n");
			writer.flush();
			writer.write(newDepartment+"\n");
			writer.flush();
			JOptionPane.showMessageDialog(null, reader.readLine(), "", JOptionPane.INFORMATION_MESSAGE);
			socketClient.close();
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static String getEmployeeDepartment(String employee){
		String department = new String();
		try{
			Socket socketClient = new Socket("localhost",1211);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(42);
			writer.flush();
			writer.write(employee+"\n");
			writer.flush();
			department = reader.readLine();
			socketClient.close();
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
		return department;
	}
}
