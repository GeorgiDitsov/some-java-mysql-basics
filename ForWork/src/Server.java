import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * @author Georgi Ditsov
 *Copyright (c) 7.01.2018 ã. Georgi Ditsov to Present.
 *All rights reserved.
 */

public class Server {
	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(1211);
		JOptionPane.showMessageDialog(null, "Server is running on port: "+server.getLocalPort());
		while(true){
			Socket socket = server.accept();
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	        int option = reader.read();
	        System.out.println(option);
	        switch(option){
	        case 1: int count = 0;
	        	ArrayList<String> employees = new ArrayList<String>(Requests.loadEmployees());
				while(count < employees.size()){
					writer.write(employees.get(count)+"\n");
					writer.flush();
					count++;
				}
	        	break;
	        case 2: String serialNum = reader.readLine();
	        	System.out.println("Received Data");
	        	String machineType = reader.readLine();
	        	System.out.println("Received Data");
	        	String modelOfMachine = reader.readLine();
	        	System.out.println("Received Data");
	        	String cpuOfMachine = reader.readLine();
	        	System.out.println("Received Data");
	        	String ramOfMachine = reader.readLine();
	        	System.out.println("Received Data");
	        	String diskDriveOfMachine = reader.readLine();
	        	System.out.println("Received Data");
	        	String osOfMachine = reader.readLine();
	        	System.out.println("Received Data");
	        	String msOfficeOfMachine = reader.readLine();
	        	System.out.println("Received Data");
	        	String otherProgramsOfMachine = reader.readLine();
	        	System.out.println("Received Data");
	        	String date = reader.readLine();
	        	System.out.println("Received Data");
	        	String employeeWhoInstalled = reader.readLine();
	        	System.out.println("Received Data");
	        	String roomWhereIsMachine = reader.readLine();
	        	System.out.println("Received Data");
	        	String employeeWhoUseIt = reader.readLine();
	        	System.out.println("Received Data");
	        	String monitorSerialNum = reader.readLine();
	        	System.out.println("Received Data");
	        	String modelOfMonitor = reader.readLine();
	        	System.out.println("Received Data");
	        	writer.write(Requests.addPc(serialNum, machineType, modelOfMachine, cpuOfMachine, ramOfMachine,
	        			diskDriveOfMachine, osOfMachine, msOfficeOfMachine, otherProgramsOfMachine, date,
	        			employeeWhoInstalled, roomWhereIsMachine, employeeWhoUseIt, monitorSerialNum, modelOfMonitor)+"\n");
	        	writer.flush();
	        	break;
	        case 3: String serialNumberForHistory = reader.readLine();
	        	writer.write(Requests.showHistoryOfPC(serialNumberForHistory)+"\n");
	        	writer.flush();
	        	break;
	        case 4: count = 0;
	        	ArrayList<String> models = new ArrayList<String>(Requests.loadPcModels());
	        	while(count < models.size()){
	        		writer.write(models.get(count)+"\n");
	        		writer.flush();
	        		count++;
	        	}
	        	break;
	        case 5: count = 0;
	        	ArrayList<String> processors = new ArrayList<String>(Requests.loadPcCPUs());
	        	while(count < processors.size()){
	        		writer.write(processors.get(count)+"\n");
	        		writer.flush();
	        		count++;
	        	}
	        	break;
	        case 6: count = 0;
	        	ArrayList<String> memory = new ArrayList<String>(Requests.loadMemory());
	        	while(count < memory.size()){
	        		writer.write(memory.get(count)+"\n");
	        		writer.flush();
	        		count++;
	        	}
	        	break;
	        case 7: count = 0;
	        	ArrayList<String> diskDrives = new ArrayList<String>(Requests.loadDiskDrives());
	        	while(count < diskDrives.size()){
	        		writer.write(diskDrives.get(count)+"\n");
	        		writer.flush();
	        		count++;
	        	}
	        	break;
	        case 8: count = 0;
	        	ArrayList<String> os = new ArrayList<String>(Requests.loadOS());
	        	while(count < os.size()){
	        		writer.write(os.get(count)+"\n");
	        		writer.flush();
	        		count++;
	        	}
	        	break;
	        case 9: count = 0;
	        	ArrayList<String> officeVersions = new ArrayList<String>(Requests.loadOfficeVersions());
	        	while(count < officeVersions.size()){
	        		writer.write(officeVersions.get(count)+"\n");
	        		writer.flush();
	        		count++;
	        	}
        	break;
	        case 10: count = 0;
	        	ArrayList<String> otherPrograms = new ArrayList<String>(Requests.loadOtherPrograms());
	        	while(count < otherPrograms.size()){
	        		writer.write(otherPrograms.get(count)+"\n");
	        		writer.flush();
	        		count++;
	        	}
	        	break;
	        case 11: count = 0;
	        	ArrayList<String> departments = new ArrayList<String>(Requests.loadDepartments());
	        	while(count < departments.size()){
	        		writer.write(departments.get(count)+"\n");
	        		writer.flush();
	        		//System.out.println(count+" "+departments.get(count));
	        		count++;
	        	}
	        	break;
	        case 12: String department = reader.readLine();
	        	System.out.println("Received data");
	        	Requests.addDepartment(department);
	        	writer.write("Records are saved successfully!\n");
	        	writer.flush();
	        	break;
	        case 13: String employeeName = reader.readLine();
	        	System.out.println("Received data");
	        	String departmentName = reader.readLine();
	        	System.out.println("Received data");
	        	Requests.addEmployee(employeeName, departmentName);
	        	writer.write("Records are inserted successfully!\n");
	        	writer.flush();
	        	break;
	        case 14: count = 0;
	        	ArrayList<String> rooms = new ArrayList<String>(Requests.loadRooms());
	        	while(count < rooms.size()){
	        		writer.write(rooms.get(count)+"\n");
	        		writer.flush();
	        		count++;
	        	}
	        	break;
	        case 15: count = 0;
	        	ArrayList<String> serials = new ArrayList<String>(Requests.loadSerialNumbers());
	        	while(count < serials.size()){
	        		writer.write(serials.get(count)+"\n");
	        		writer.flush();
	        		count++;
	        	}
	        	break;
	        case 16: String serialNumber= reader.readLine();
	        	writer.write(Requests.selectBySerialNumber(serialNumber)+"\n");
	        	writer.flush();
	        	break;
	        case 17: String model = reader.readLine();
        		System.out.println("Received data");
        		Requests.addPcModel(model);
        		//writer.write("Records are saved successfully!\n");
        		//writer.flush();
        	break;
	        case 18: String cpu = reader.readLine();
    			System.out.println("Received data");
    			Requests.addPcCPU(cpu);
    			//writer.write("Records are saved successfully!\n");
    			//writer.flush();
    		break;
	        case 19: String ram = reader.readLine();
    			System.out.println("Received data");
    			Requests.addPcRAM(ram);
    			//writer.write("Records are saved successfully!\n");
    			//writer.flush();
    		break;
	        case 20: String diskDrive  = reader.readLine();
    			System.out.println("Received data");
    			Requests.addPcDiskDrive(diskDrive);
    			//writer.write("Records are saved successfully!\n");
    			//writer.flush();
    		break;
	        case 21: String osVersion = reader.readLine();
    			System.out.println("Received data");
    			Requests.addPcOSVersion(osVersion);
    			//writer.write("Records are saved successfully!\n");
    			//writer.flush();
    		break;
	        case 22: String msOffice = reader.readLine();
    			System.out.println("Received data");
    			Requests.addPcMSOffice(msOffice);
    			//writer.write("Records are saved successfully!\n");
    			//writer.flush();
    		break;
	        case 23: String otherProgram = reader.readLine();
    			System.out.println("Received data");
    			Requests.addPcOtherPrograms(otherProgram);
    			//writer.write("Records are saved successfully!\n");
    			//writer.flush();
    		break;
	        case 24: String room = reader.readLine();
    			System.out.println("Received data");
    			Requests.addRoom(room);
    			//writer.write("Records are saved successfully!\n");
    			//writer.flush();
    		break;
	        case 25: String monitorSerial = reader.readLine();
	         	System.out.println("Received data");
	         	String monitorModel = reader.readLine();
	         	System.out.println("Received data");
	         	Requests.addMonitor(monitorSerial, monitorModel);
	         	//writer.write("Records are saved successfully!\n");
    			//writer.flush();
        		break;
	        case 26: count = 0;
	        	ArrayList<String> usedPCs = new ArrayList<String>(Requests.loadUsedPCs());
	        	while(count < usedPCs.size()){
	        		writer.write(usedPCs.get(count)+"\n");
	        		writer.flush();
	        		count++;
	        	}
	        	break;
	        case 27: count = 0;
	        	ArrayList<String> unusedPCs = new ArrayList<String>(Requests.loadUnusedPCs());
	        	while(count < unusedPCs.size()){
	        		writer.write(unusedPCs.get(count)+"\n");
	        		writer.flush();
	        		count++;
	        	}
	        	break;
	        case 28: count = 0;
	        	ArrayList<String> monitorSerials = new ArrayList<String>(Requests.loadMonitorSerialNumbers());
	        	while(count < monitorSerials.size()){
	        		writer.write(monitorSerials.get(count)+"\n");
	        		writer.flush();
	        		count++;
	        	}
	        	break;
	        case 29: count = 0;
	        	ArrayList<String> monitorModels = new ArrayList<String>(Requests.loadMonitorModels());
	        	while(count < monitorModels.size()){
	        		writer.write(monitorModels.get(count)+"\n");
	        		writer.flush();
	        		count++;
	        	}
	        	break;
	        case 30: String receivedSerialNumber = reader.readLine();
	        	writer.write(Requests.usedBy(receivedSerialNumber)+"\n");
	        	writer.flush();
	        	break;
	        case 31: String secondReceivedSerialNumber = reader.readLine();
	        	String dateEnd = reader.readLine();
	        	writer.write(Requests.setUnused(secondReceivedSerialNumber, dateEnd)+"\n");
	        	writer.flush();
	        	break;
	        case 32: String thirdReceivedSerialNumber = reader.readLine();
	        	String user = reader.readLine();
	        	String dateStart = reader.readLine();
	        	writer.write(Requests.setUsed(thirdReceivedSerialNumber, user, dateStart)+"\n");
	        	writer.flush();
	        	break;
	        case 33: String employee = reader.readLine();
	        	writer.write(Requests.deleteEmployee(employee)+"\n");
	        	writer.flush();
	        	break;
	        case 34: String forthReceivedSerialNumber = reader.readLine();
	        	String reinstallDate = reader.readLine();
	        	String newOS = reader.readLine();
	        	String newMSOffice = reader.readLine();
	        	String newRam = reader.readLine();
	        	String newDisk = reader.readLine();
	        	writer.write(Requests.reinstallPC(forthReceivedSerialNumber, reinstallDate, newOS, newMSOffice, newRam, newDisk)+"\n");
	        	writer.flush();
	        	break;
	        case 35: String roomNumber = reader.readLine();
	        	writer.write(Requests.showPCsInRoom(roomNumber)+"\n");
	        	writer.flush();
	        	break;
	        case 36: String username = reader.readLine();
	        	String password = reader.readLine();
	        	writer.write(Requests.logIn(username, password));
	        	writer.flush();
	        	break;
	        case 37: String serialNumberForInfo = reader.readLine();
	        		writer.write(Requests.showInfoForPC(serialNumberForInfo)+"\n");
	        		writer.flush();
	        	break;
	        case 38:  String forDeleting = reader.readLine();
        		writer.write(Requests.deletePC(forDeleting)+"\n");
        		writer.flush();
        		break;
	        case 39: String employeeNameForSearching = reader.readLine();
	        	writer.write(Requests.selectByEmployeeName(employeeNameForSearching)+"\n");
	        	writer.flush();
	        	break;
	        case 40: String departmentForChange = reader.readLine();
	        	String newDepartmentName = reader.readLine();
	        	writer.write(Requests.updateDepartment(departmentForChange, newDepartmentName)+"\n");
	        	writer.flush();
	        	break;
	        case 41: String employeeName2 = reader.readLine();
	        	String newDepartment = reader.readLine();
	        	writer.write(Requests.changeEmployeeDepartment(employeeName2, newDepartment)+"\n");
	        	writer.flush();
	        	break;
	        case 42: String employeeName3 = reader.readLine();
	        	writer.write(Requests.getDepartment(employeeName3)+"\n");
	        	writer.flush();
	        	break;
	        case 43: String roomNumber2 = reader.readLine();
	        	count = 0;
        		ArrayList<String> records = new ArrayList<String>(Requests.showWhatsInRoom(roomNumber2));
        		while(count < records.size()){
        			System.out.println(records.get(count)+"\n");
        			writer.write(records.get(count)+"\n");
        			writer.flush();
        			count++;
        		}
        		break;
	        default: System.out.println("No one called");
	        	break;
	        }
	        socket.close();
		}
	}
}
