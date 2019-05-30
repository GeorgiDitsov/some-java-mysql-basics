import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

/**
 * @author Georgi Ditsov
 *Copyright (c) 7.01.2018 �. Georgi Ditsov to Present.
 *All rights reserved.
 */

public class Requests {
	
	public static int logIn(String username, String password){
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		Connection connect = null;
		int result = 0;
		try {
			Class.forName(driver);
		 	connect = DriverManager.getConnection(host, username, password);
			result = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = 0;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{ 
			      try{
			         if(connect!=null){
			            connect.close();
			         }   
			      }catch(SQLException se){
			         se.printStackTrace();
			      }
			   }
		return result;
	}

	public static String addPc(String serial, String machine, String model, String cpu, String ram, String diskDrive, 
			String os, String msOffice, String otherPrograms, String date, String employee,
			String room, String owner, String monitorSerialNumber, String monitorModel){
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		String result = null, command;
		Connection connect = null;
		PreparedStatement stm = null;
		System.out.println(machine);
		try {
			int idEmployee = 0, idOwner = 0, idRoom = 0, idMonitor = 0;
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			command = "select id from computers where serialNumber =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, serial);
			ResultSet rs = stm.executeQuery();
			if(rs.next()){
				result = "This computer is already in the database!";
			}
			else{
				command = "select id from employees where name =?";
				stm = connect.prepareStatement(command);
				stm.setString(1, employee);
				rs = stm.executeQuery();
				if(rs.next()){
					idEmployee = rs.getInt("id");
				}
				command = "select id from employees where name =?";
				stm = connect.prepareStatement(command);
				stm.setString(1, owner);
				rs = stm.executeQuery();
				if(rs.next()){
					idOwner = rs.getInt("id");
				}
				command = "select id from rooms where roomNumber =?";
				stm = connect.prepareStatement(command);
				stm.setString(1, room);
				rs = stm.executeQuery();
				if(rs.next()){
					idRoom = rs.getInt("id");
				}
				command = "select id from monitors where serialNumber =? and model =?";
				stm = connect.prepareStatement(command);
				stm.setString(1, monitorSerialNumber);
				stm.setString(2, monitorModel);
				rs = stm.executeQuery();
				if(rs.next()){
					idMonitor = rs.getInt("id");
				}
				if(otherPrograms.equals("null")){
					otherPrograms = null;
				}
					command = "insert into computers(serialNumber, machineType, model, CPU, RAM, diskDrive, monitorId, "
							+ "operatingSystem, msOffice, otherPrograms, dateOfInstallation, installedBy, roomId, ownerId) "
							+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				stm = connect.prepareStatement(command);
				stm.setString(1, serial);
				stm.setString(2, machine);
				stm.setString(3, model);
				stm.setString(4, cpu);
				stm.setString(5, ram);
				stm.setString(6, diskDrive);
				if(idMonitor != 0){
					stm.setInt(7, idMonitor);
				}
				else{
					stm.setNull(7, Types.INTEGER);
				}
				stm.setString(8, os);
				stm.setString(9, msOffice);
				if(otherPrograms.equals("null") || otherPrograms == null){
					stm.setNull(10, Types.VARCHAR);
				}
				else{
					stm.setString(10, otherPrograms);
				}
				stm.setString(11, date);
				stm.setInt(12, idEmployee);
				stm.setInt(13, idRoom);
				if(idOwner != 0){
					stm.setInt(14, idOwner);
				}
				else{
					stm.setNull(14, Types.INTEGER);
				}
				stm.executeUpdate();
				command = "insert into historyOfUsing(usedPC, employee, firstDay) values (?,?,?)";
				stm = connect.prepareStatement(command);
				stm.setString(1, serial);
				stm.setString(2, owner);
				stm.setString(3, date);
				stm.executeUpdate();
				result = "Data is inserted successfully!";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = "You tried to enter wrond data in the database!";
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		return result;
	}
	public static String allPc(){
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		Connection connect = null;
		Statement stm = null;
		String records = null;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select * from computers";
			stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(command);
			while(rs.next()){
				int serial = rs.getInt("serialNumber");
				String model = rs.getString("model");
				String cpu = rs.getString("CPU");
				String ram = rs.getString("RAM");
				String disk = rs.getString("diskDrive");
				String os = rs.getString("operatingSystem");
				String msOffice = rs.getString("msOffice");
				String date = rs.getString("dateOfInstallation");
				int employee = rs.getInt("installedBy");
				int roomId = rs.getInt("roomId");
				int ownerId = rs.getInt("ownerId");
				records =""+serial+"|"+model+"|"+cpu+"|"+ram+"|"+disk+"|"+os+"|"+msOffice+"|"+date+"|"+employee+"|"
						+roomId+"|"+ownerId;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		return records;
		
	}
	
	public static ArrayList<String> loadEmployees() {
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		Connection connect = null;
		Statement stm = null;
		ArrayList<String> employees = new ArrayList<String>();
		int count = 0;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select name from employees order by name";
			stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(command);
			while(rs.next()){
				employees.add(count, rs.getString("name"));
				count++;		
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		}
		return employees;
	}
	
	public static ArrayList<String> loadSerialNumbers() {
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		Connection connect = null;
		Statement stm = null;
		ArrayList<String> serials = new ArrayList<String>();
		int count = 0;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select serialNumber from computers";
			stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(command);
			while(rs.next()){
				serials.add(count, rs.getString("serialNumber"));
				count++;		
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		}
		return serials;
	}

	public static ArrayList<String> loadPcModels(){
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/computers";
		String username = "root";
		String password = "";
		Connection connect = null;
		Statement stm = null;
		ArrayList<String> models = new ArrayList<String>();
		int count = 0;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select names from models";
			stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(command);
			while(rs.next()){
				models.add(count, rs.getString("names"));
				count++;		
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		}
		return models;
	}
	public static ArrayList<String> loadPcCPUs() {
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/computers";
		String username = "root";
		String password = "";
		Connection connect = null;
		Statement stm = null;
		ArrayList<String> cpus = new ArrayList<String>();
		int count = 0;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select names from cpu";
			stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(command);
			while(rs.next()){
				cpus.add(count, rs.getString("names"));
				count++;		
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		}
		return cpus;
	}

	public static ArrayList<String> loadMemory() {
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/computers";
		String username = "root";
		String password = "";
		Connection connect = null;
		Statement stm = null;
		ArrayList<String> memory = new ArrayList<String>();
		int count = 0;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select names from memory";
			stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(command);
			while(rs.next()){
				memory.add(count, rs.getString("names"));
				count++;		
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		}
		return memory;
	}
	

	public static ArrayList<String> loadDiskDrives() {
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/computers";
		String username = "root";
		String password = "";
		Connection connect = null;
		Statement stm = null;
		ArrayList<String> diskDrives = new ArrayList<String>();
		int count = 0;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select names from diskdrive";
			stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(command);
			while(rs.next()){
				diskDrives.add(count, rs.getString("names"));
				count++;		
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		}
		return diskDrives;
	}

	public static ArrayList<String> loadOS() {
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/computers";
		String username = "root";
		String password = "";
		Connection connect = null;
		Statement stm = null;
		ArrayList<String> osVersions = new ArrayList<String>();
		int count = 0;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select names from osversion";
			stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(command);
			while(rs.next()){
				osVersions.add(count, rs.getString("names"));
				count++;		
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		}
		return osVersions;
	}

	public static ArrayList<String> loadOfficeVersions() {
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/computers";
		String username = "root";
		String password = "";
		Connection connect = null;
		Statement stm = null;
		ArrayList<String> offices = new ArrayList<String>();
		int count = 0;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select names from msofficeversions";
			stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(command);
			while(rs.next()){
				offices.add(count, rs.getString("names"));
				count++;		
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		}
		return offices;
	}
	public static ArrayList<String> loadOtherPrograms(){
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/computers";
		String username = "root";
		String password = "";
		Connection connect = null;
		Statement stm = null;
		ArrayList<String> otherProgs = new ArrayList<String>();
		int count = 0;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select names from otherprograms";
			stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(command);
			while(rs.next()){
				otherProgs.add(count, rs.getString("names"));
				count++;		
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		}
		return otherProgs;
	}

	public static ArrayList<String> loadRooms() {
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		Connection connect = null;
		Statement stm = null;
		ArrayList<String> rooms = new ArrayList<String>();
		int count = 0;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select roomNumber from rooms order by roomNumber";
			stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(command);
			String roomNumber;
			while(rs.next()){
				roomNumber = rs.getString("roomNumber");
				rooms.add(count, roomNumber);
				count++;		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		}
		return rooms;
	}

	public static ArrayList<String> loadDepartments() {
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		Connection connect = null;
		Statement stm = null;
		ArrayList<String> departments = new ArrayList<String>();
		int count = 0;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select name from departments";
			stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(command);
			while(rs.next()){
				departments.add(count, rs.getString("name"));
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		}
		return departments;
	}
	public static ArrayList<String> loadUsedPCs(){
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		Connection connect = null;
		Statement stm = null;
		ArrayList<String> usedPCs = new ArrayList<String>();
		int count = 0;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select serialNumber from computers where ownerId is not null";
			stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(command);
			while(rs.next()){
				usedPCs.add(count, rs.getString("serialNumber"));
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		}
		return usedPCs;
	}
	public static ArrayList<String> loadUnusedPCs() {
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		Connection connect = null;
		Statement stm = null;
		ArrayList<String> unusedPCs = new ArrayList<String>();
		int count = 0;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select serialNumber from computers where ownerId is null";
			stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(command);
			while(rs.next()){
				unusedPCs.add(count, rs.getString("serialNumber"));
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		}   
		return unusedPCs;
	}

	public static ArrayList<String> loadMonitorSerialNumbers() {
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		Connection connect = null;
		Statement stm = null;
		ArrayList<String> monitorSerials = new ArrayList<String>();
		int count = 0;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select serialNumber from monitors";
			stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(command);
			while(rs.next()){
				monitorSerials.add(count, rs.getString("serialNumber"));
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		}
		return monitorSerials;
	}

	public static ArrayList<String> loadMonitorModels() {
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		Connection connect = null;
		Statement stm = null;
		ArrayList<String> monitorModels = new ArrayList<String>();
		int count = 0;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select model from monitors";
			stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(command);
			while(rs.next()){
				monitorModels.add(count, rs.getString("model"));
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		}
		return monitorModels;
	}

	public static void addDepartment(String department){
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		Connection connect = null;
		Statement stm = null;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			stm = connect.createStatement();
			String command = "insert into departments(name) values('"+department+"');";
			stm.executeUpdate(command);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
	}

	public static void addEmployee(String employeeName, String departmentName) {
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		Connection connect = null;
		Statement stm = null;
		int id = 0;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			stm = connect.createStatement();
			String command = "select id from departments where name = '"+departmentName+"';";
			ResultSet rs = stm.executeQuery(command);
			rs.next();
			id = rs.getInt("id");
			command = "insert into employees(name, departmentID) values('"+employeeName+"', "+id+");";
			stm.executeUpdate(command);
			System.out.println("Records are inserted successfully!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
	}
	public static void addPcModel(String model) {
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/computers";
		String username = "root";
		String password = "";
		Connection connect = null;
		PreparedStatement stm = null;
		int id = 0;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select id from models where names =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, model);
			ResultSet rs =  stm.executeQuery();
			if(rs.next()){
				id = rs.getInt("id");
			}
			if(id == 0){
				command = "insert into models(names) values(?)";
				stm = connect.prepareStatement(command);
				stm.setString(1, model);
				stm.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
	}
	public static void addPcCPU(String cpu){
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/computers";
		String username = "root";
		String password = "";
		Connection connect = null;
		PreparedStatement stm = null;
		int id = 0;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select id from cpu where names =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, cpu);
			ResultSet rs =  stm.executeQuery();
			if(rs.next()){
				id = rs.getInt("id");
			}
			if(id == 0){
				command = "insert into cpu(names) values(?)";
				stm = connect.prepareStatement(command);
				stm.setString(1, cpu);
				stm.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
	}
	public static void addPcRAM(String ram){
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/computers";
		String username = "root";
		String password = "";
		Connection connect = null;
		PreparedStatement stm = null;
		int id = 0;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select id from memory where names =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, ram);
			ResultSet rs =  stm.executeQuery();
			if(rs.next()){
				id = rs.getInt("id");
			}
			if(id == 0){
				command = "insert into memory(names) values(?)";
				stm = connect.prepareStatement(command);
				stm.setString(1, ram);
				stm.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
	}
	public static void addPcDiskDrive(String diskDrive){
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/computers";
		String username = "root";
		String password = "";
		Connection connect = null;
		PreparedStatement stm = null;
		int id = 0;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select id from diskDrive where names =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, diskDrive);
			ResultSet rs =  stm.executeQuery();
			if(rs.next()){
				id = rs.getInt("id");
			}
			if(id == 0){
				command = "insert into diskDrive(names) values(?)";
				stm = connect.prepareStatement(command);
				stm.setString(1, diskDrive);
				stm.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
	}
	public static void addPcOSVersion(String os){
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/computers";
		String username = "root";
		String password = "";
		Connection connect = null;
		PreparedStatement stm = null;
		int id = 0;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select id from osVersion where names =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, os);
			ResultSet rs =  stm.executeQuery();
			if(rs.next()){
				id = rs.getInt("id");
			}
			if(id == 0){
				command = "insert into osVersion(names) values(?)";
				stm = connect.prepareStatement(command);
				stm.setString(1, os);
				stm.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
	}
	public static void addPcMSOffice(String msOffice){
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/computers";
		String username = "root";
		String password = "";
		Connection connect = null;
		PreparedStatement stm = null;
		int id = 0;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select id from msOfficeVersions where names =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, msOffice);
			ResultSet rs =  stm.executeQuery();
			if(rs.next()){
				id = rs.getInt("id");
			}
			if(id == 0){
				command = "insert into msOfficeVersions(names) values(?)";
				stm = connect.prepareStatement(command);
				stm.setString(1, msOffice);
				stm.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
	}
	public static void addPcOtherPrograms(String otherPrograms){
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/computers";
		String username = "root";
		String password = "";
		Connection connect = null;
		PreparedStatement stm = null;
		int id = 0;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select id from otherPrograms where names =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, otherPrograms);
			ResultSet rs =  stm.executeQuery();
			if(rs.next()){
				id = rs.getInt("id");
			}
			if(id == 0){
				command = "insert into otherPrograms(names) values(?)";
				stm = connect.prepareStatement(command);
				stm.setString(1, otherPrograms);
				stm.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
	}
	public static void addRoom(String room){
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		Connection connect = null;
		PreparedStatement stm = null;
		int id = 0;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select id from rooms where roomNumber =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, room);
			ResultSet rs =  stm.executeQuery();
			if(rs.next()){
				id = rs.getInt("id");
			}
			if(id == 0){
				command = "insert into rooms(roomNumber) values(?)";
				stm = connect.prepareStatement(command);
				stm.setString(1, room);
				stm.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
	}
	public static void addMonitor(String monitorSerial, String monitorModel) {
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		Connection connect = null;
		PreparedStatement stm = null;
		int id = 0;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select id from monitors where serialNumber =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, monitorSerial);
			ResultSet rs =  stm.executeQuery();
			if(rs.next()){
				id = rs.getInt("id");
			}
			if(id == 0){
				command = "insert into monitors(serialNumber, model) values(?,?)";
				stm = connect.prepareStatement(command);
				stm.setString(1, monitorSerial);
				stm.setString(2, monitorModel);
				stm.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
	}
	public static String selectBySerialNumber(String serialNumber){
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		Connection connect = null;
		PreparedStatement stm = null;
		String records = new String(), machineType = new String();
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select machineType from computers where serialNumber=?";
			stm = connect.prepareStatement(command);
			stm.setString(1, serialNumber);
			ResultSet rs = stm.executeQuery();
			if(rs.next()){
				machineType = rs.getString("machineType");
			}
			if(machineType.equals("PC")){
				records = selectPc(serialNumber);
			}
			else if(machineType.equals("Laptop")){
				records = selectLaptop(serialNumber);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		return records;	
	}
	public static String selectByEmployeeName(String employee){
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		Connection connect = null;
		PreparedStatement stm = null;
		String records = new String(), machineType = new String(), serialNumber = new String();
		int idEmployee = 0;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select id from employees where name =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, employee);
			ResultSet rs = stm.executeQuery();
			if(rs.next()){
				idEmployee = rs.getInt("id");
			}
			command = "select serialNumber, machineType from computers where ownerId =?";
			stm = connect.prepareStatement(command);
			stm.setInt(1, idEmployee);
			rs = stm.executeQuery();
			if(rs.next()){
				serialNumber = rs.getString("serialNumber");
				machineType = rs.getString("machineType");
			}
			if(machineType.equals("PC")){
				records = selectPc(serialNumber);
			}
			else if(machineType.equals("Laptop")){
				records = selectLaptop(serialNumber);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		return records;	
	}

	private static String selectPc(String serialNumber){
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		String records = new String();
		Connection connect = null;
		PreparedStatement stm = null;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select computers.serialNumber, computers.machineType, computers.model, computers.CPU, "
					+ "computers.RAM, computers.diskDrive, computers.operatingSystem, computers.msOffice, "
					+ "computers.otherPrograms, computers.dateOfInstallation, employees.name, rooms.roomNumber, "
					+ "monitors.serialNumber, monitors.model from computers join employees on "
					+ "computers.installedBy=employees.id join rooms on computers.roomId=rooms.id "
					+ "join monitors on computers.monitorId=monitors.id where computers.serialNumber =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, serialNumber);
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				records = rs.getString("serialNumber") +"/";
				records += rs.getString("machineType") +"/";
				records += rs.getString("model") +"/";
				records += rs.getString("CPU") + "/";
				records += rs.getString("RAM") + "/";
				records += rs.getString("diskDrive") + "/";
				records += rs.getString("operatingSystem") + "/";
				records += rs.getString("msOffice") + "/";
				records += rs.getString("otherPrograms") + "/";
				records += rs.getString("dateOfInstallation") + "/";
				records += rs.getString("name") + "/";
				records += rs.getString("roomNumber") + "/";
				records += rs.getString("monitors.serialNumber") + "/";
				records += rs.getString("monitors.model") + "/";
			}
			command = "select employees.name from computers join employees on employees.id=computers.ownerId "
					+ "where serialNumber =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, serialNumber);
			rs = stm.executeQuery();
			while(rs.next()){
				records += rs.getString("name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		}
		return records;
	}
	private static String selectLaptop(String serialNumber){
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		String records = new String();
		Connection connect = null;
		PreparedStatement stm = null;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select computers.serialNumber, computers.machineType, computers.model, computers.CPU, "
					+ "computers.RAM, computers.diskDrive, computers.operatingSystem, computers.msOffice, "
					+ "computers.otherPrograms, computers.dateOfInstallation, employees.name, rooms.roomNumber "
					+ "from computers join employees on computers.installedBy=employees.id join rooms on "
					+ "computers.roomId=rooms.id where computers.serialNumber =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, serialNumber);
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				records = rs.getString("serialNumber") +"/";
				records += rs.getString("machineType") +"/";
				records += rs.getString("model") +"/";
				records += rs.getString("CPU") + "/";
				records += rs.getString("RAM") + "/";
				records += rs.getString("diskDrive") + "/";
				records += rs.getString("operatingSystem") + "/";
				records += rs.getString("msOffice") + "/";
				records += rs.getString("otherPrograms") + "/";
				records += rs.getString("dateOfInstallation") + "/";
				records += rs.getString("name") + "/";
				records += rs.getString("roomNumber") + "/";
			}
			command = "select employees.name from computers join employees on employees.id=computers.ownerId "
					+ "where serialNumber =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, serialNumber);
			rs = stm.executeQuery();
			while(rs.next()){
				records += rs.getString("name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		}
		return records;
	}
	
	public static String showPCsInRoom(String roomNumber) {
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		int roomId = 0;
		Connection connect = null;
		PreparedStatement stm = null;
		String records = new String();
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select id from rooms where roomNumber =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, roomNumber);
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				roomId = rs.getInt("id"); 
			}
			command = "select computers.serialNumber from computers join rooms on rooms.id=computers.roomId "
					+ "where computers.roomId =?";
			stm = connect.prepareStatement(command);
			stm.setInt(1, roomId);
			rs = stm.executeQuery();
			while(rs.next()){
				String computer = rs.getString("serialNumber");
				records += computer+"/";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		return records;
	}
	
	public static ArrayList<String> showWhatsInRoom(String roomNumber){
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		int roomId = 0, count = 0;
		ArrayList<String> records = new ArrayList<String>();
		Connection connect = null;
		PreparedStatement stm = null;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select id from rooms where roomNumber =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, roomNumber);
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				roomId = rs.getInt("id");
			}
			command = "select computers.serialNumber, employees.name from computers join employees on "
					+ "employees.id=computers.ownerId where computers.roomId =?";
			stm = connect.prepareStatement(command);
			stm.setInt(1, roomId);
			rs = stm.executeQuery();
			while(rs.next()){
				String computer = rs.getString("serialNumber");
				String employeeName = rs.getString("name");
				records.add(count, computer+"/"+employeeName);
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		return records;
	}

	public static String usedBy(String receivedSerialNumber) {
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		Connection connect = null;
		PreparedStatement stm = null;
		String user = null;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select employees.name from employees join computers on employees.id=computers.ownerId"
					+ " where computers.serialNumber =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, receivedSerialNumber);
			ResultSet rs = stm.executeQuery();
			if(rs.next()){
				user = rs.getString("name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		return user;
	}
	public static String setUnused(String serialNumber, String date){
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		String result = null, employee = null;
		int ownerId = 0;
		Connection connect = null;
		PreparedStatement stm = null;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select ownerId from computers where serialNumber =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, serialNumber);
			ResultSet rs = stm.executeQuery();
			if(rs.next()){
				ownerId = rs.getInt("ownerId");
			}
			command = "select name from employees where id =?";
			stm = connect.prepareStatement(command);
			stm.setInt(1, ownerId);
			rs = stm.executeQuery();
			if(rs.next()){
				employee = rs.getString("name");
			}
			command = "update computers set ownerId = NULL where serialNumber =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, serialNumber);
			stm.executeUpdate();
			command = "update historyOfUsing set lastDay =? where usedPC =? and employee =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, date);
			stm.setString(2, serialNumber);
			stm.setString(3, employee);
			stm.executeUpdate();
			result = "PC with serial number: "+serialNumber+" is unused.";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = "Error in SQL command!";
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		return result;
	}

	public static String setUsed(String serial, String user, String date) {
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		String result = null, command;
		int userId = 0;
		Connection connect = null;
		PreparedStatement stm = null;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			command = "select id from employees where name =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, user);
			ResultSet rs = stm.executeQuery();
			if(rs.next()){
				userId = rs.getInt("id");
			}
			command = "update computers set ownerId =? where serialNumber =?";
			stm = connect.prepareStatement(command);
			stm.setInt(1, userId);
			stm.setString(2, serial);
			stm.executeUpdate();
			command = "insert into historyOfUsing(usedPC, employee, firstDay) values (?,?,?)";
			stm = connect.prepareStatement(command);
			stm.setString(1, serial);
			stm.setString(2, user);
			stm.setString(3, date);
			stm.executeUpdate();
			result = "PC with serial number: "+serial+" is set to: "+user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = "Error in SQL command!";
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		return result;
	}

	public static String reinstallPC(String serialNumber, String reinstallDate, String newOS, String newMSOffice, String newRam, String newDisk) {
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		String result = null, command;
		Connection connect = null;
		PreparedStatement stm = null;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			command = "update computers set operatingSystem =?, msOffice =?, dateOfInstallation =?, RAM =?,"
					+ " diskDrive =? where serialNumber =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, newOS);
			stm.setString(2, newMSOffice);
			stm.setString(3, reinstallDate);
			stm.setString(4, serialNumber);
			stm.setString(5, newRam);
			stm.setString(6, newDisk);
			stm.executeUpdate();
			result = "Records are inserted successfully!";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = "Error in SQL command!";
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		return result;
	}
	
	public static String updateDepartment(String departmentForChange, String newDepartmentName) {
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		String result = null, command;
		Connection connect = null;
		PreparedStatement stm = null;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			command = "update departments set name =? where name =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, newDepartmentName);
			stm.setString(2, departmentForChange);
			stm.executeUpdate();
			result = "Records are inserted successfully!";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = "Error in SQL command!";
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		return result;
	}

	public static String deleteEmployee(String employee) {
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		String result = null, command;
		Connection connect = null;
		PreparedStatement stm = null;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			command = "delete from employees where name =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, employee);
			stm.executeUpdate();
			result = "Records are deleted successfully!";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = "Error in SQL command!";
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		return result;
	}
	
	public static String showHistoryOfPC(String serialNumberForHistory) {
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		Connection connect = null;
		PreparedStatement stm = null;
		String records = new String();
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select usedPC, employee, firstDay, lastDay from historyofusing where usedPC =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, serialNumberForHistory);
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				records += rs.getString("usedPC")+" ";
				records += rs.getString("employee")+" ";
				records += rs.getString("firstDay")+" ";
				records += rs.getString("lastDay")+"|";
			}
			if(records.equals("")){
				records = "There is no history for this computer.";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		return records;
	}
	public static String deletePC(String forDeleting) {
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		String result = null, command;
		Connection connect = null;
		PreparedStatement stm = null;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			command = "delete from computers where serialNumber =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, forDeleting);
			stm.executeUpdate();
			result = "Records are deleted successfully!";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = "Error in SQL command!";
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		return result;
	}
	public static String showInfoForPC(String serialNumberForInfo){
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		Connection connect = null;
		PreparedStatement stm = null;
		String records = new String();
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			String command = "select computers.serialNumber, computers.model, rooms.roomNumber, employees.name, "
					+ "computers.dateOfInstallation, computers.operatingSystem from computers "
					+ "join employees on employees.id=computers.ownerId join rooms on rooms.id=computers.roomId "
					+ "where computers.serialNumber =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, serialNumberForInfo);
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				records += rs.getString("serialNumber")+"|";
				records += rs.getString("model")+"|";
				records += rs.getString("roomNumber")+"|";
				records += rs.getString("name")+"|";
				records += rs.getString("dateOfInstallation")+"|";
				records += rs.getString("operatingSystem");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		return records;
	}
	public static String changeEmployeeDepartment(String employeeName, String newDepartment){
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		String result = null, command;
		Connection connect = null;
		PreparedStatement stm = null;
		int idDepartment = 0;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			command = "select id from departments where name =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, newDepartment);
			ResultSet rs = stm.executeQuery();
			if(rs.next()){
				idDepartment = rs.getInt("id");
			}
			command = "update employees set departmentID =? where name =?";
			stm = connect.prepareStatement(command);
			stm.setInt(1, idDepartment);
			stm.setString(2, employeeName);
			stm.executeUpdate();
			result = "Records are inserted successfully!";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = "Error in SQL command!";
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		return result;
	}

	public static String getDepartment(String employee) {
		String driver = "com.mysql.jdbc.Driver";
		String host = "jdbc:mysql://localhost:3306/management";
		String username = "root";
		String password = "";
		String department = null, command;
		Connection connect = null;
		PreparedStatement stm = null;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(host, username, password);
			command = "select departments.name from departments join employees on "
					+ "departments.id=employees.departmentID where employees.name =?";
			stm = connect.prepareStatement(command);
			stm.setString(1, employee);
			ResultSet rs = stm.executeQuery();
			if(rs.next()){
				department = rs.getString("departments.name");
			}
			else{
				department = "���� �����!";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		      try{
		         if(stm!=null){
		        	 connect.close(); 
		         }
		      }catch(SQLException se){
		      }
		      try{
		         if(connect!=null){
		            connect.close();
		         }   
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		return department;
	}
}
