import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.UIManager;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

/**
 * @author Georgi Ditsov
 *Copyright (c) 7.01.2018 ã. Georgi Ditsov to Present.
 *All rights reserved.
 */

public class ClientForm extends Client {

	private JFrame frmSystemManagement;
	private static JTextField textSerialNumber;
	private static JComboBox<String> comboBoxSerialsForSearch;
	private static JComboBox<String> comboBoxSerials;
	private static JComboBox<String> comboBoxUnusedComputers;
	private static JComboBox<String> comboBoxUsedComputers;
	private static JComboBox<String> comboBoxModel;
	private static JComboBox<String> comboBoxCpu;
	private static JComboBox<String> comboBoxRam;
	private static JComboBox<String> comboBoxRam2;
 	private static JComboBox<String> comboBoxDisk;
 	private static JComboBox<String> comboBoxDisk2;
	private static JComboBox<String> comboBoxOS;
	private static JComboBox<String> comboBoxOS2;
	private static JComboBox<String> comboBoxMSOffice;
	private static JComboBox<String> comboBoxMSOffice2;
	private static JComboBox<String> comboBoxOthers;
	private static JComboBox<String> cmbEmployees;
	private static JComboBox<String> cmbEmployees2;
	private static JComboBox<String> cmbEmployees3;
	private static JComboBox<String> cmbEmployeesForSearch;
	private static JComboBox<String> comboBoxNewOwners;
	private static JComboBox<String> comboBoxRooms;
	private static JComboBox<String> comboBoxRooms2;
	private static JComboBox<String> comboBoxDepartments;
	private static JComboBox<String> comboBoxDepartments2;
	private static JComboBox<String> comboBoxMonitorSerials;
	private static JComboBox<String> comboBoxMonitorModels;
	private static JTextArea textAreaForPrint;
	private static JTextArea textAreaForPrint2;
	private static String selectedType;
	private JTextField textName;
	private static JRadioButton btnPc, btnLaptop;
	private static JDatePickerImpl datePicker;
	private static JCheckBox checkOtherPrograms;
	private static JCheckBox checkOwner;
	private static JLabel lblMonitorSerialNumber, lblMonitorModel;
	private static String[] records;
	private static JButton btnAdd, btnClear_1, btnSearchByName;
	

	/**
	 * Launch the application.
	 */
	public static void openClientForm() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientForm window = new ClientForm();
					window.frmSystemManagement.setVisible(true);
					loadEmployees();
					loadPcModels();
					loadPcCPUs();
					loadPcRAMs();
					loadPcDiskDrives();
					loadPcOsVersions();
					loadPcMsOffice();
					loadPcOtherPrograms();
					loadDepartments();
					loadRooms();
					loadSerials();
					loadUsedPCs();
					loadUnusedPCs();
					loadMonitorSerials();
					loadMonitorModels();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application. 
	 */
	public ClientForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSystemManagement = new JFrame();
		frmSystemManagement.setTitle("System Management");
		frmSystemManagement.setBounds(100, 100, 800, 480);
		frmSystemManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(new Color(0, 0, 0));
		frmSystemManagement.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("\u0414\u043E\u0431\u0430\u0432\u0438", null, panel_1, null);
		panel_1.setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(0, 0, 799, 413);
		panel_1.add(tabbedPane_1);
		
		JPanel panel_1_1 = new JPanel();
		tabbedPane_1.addTab("\u041A\u043E\u043C\u043F\u044E\u0442\u044A\u0440", null, panel_1_1, null);
		panel_1_1.setLayout(null);
		
		JLabel lblSerialNum = new JLabel("\u0421\u0435\u0440\u0438\u0435\u043D \u041D\u043E\u043C\u0435\u0440:");
		lblSerialNum.setBounds(32, 20, 97, 14);
		panel_1_1.add(lblSerialNum);
		
		textSerialNumber = new JTextField();
		lblSerialNum.setLabelFor(textSerialNumber);
		textSerialNumber.setBounds(229, 17, 200, 20);
		panel_1_1.add(textSerialNumber);
		textSerialNumber.setColumns(10);
		
		JLabel lblModel = new JLabel("\u041C\u043E\u0434\u0435\u043B:");
		lblModel.setBounds(32, 50, 97, 14);
		panel_1_1.add(lblModel);
		
		comboBoxModel = new JComboBox<String>();
		comboBoxModel.setEditable(true);
		AutoCompleteDecorator.decorate(ClientForm.comboBoxModel);
		lblModel.setLabelFor(comboBoxModel);
		comboBoxModel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxModel.setBounds(229, 47, 200, 20);
		panel_1_1.add(comboBoxModel);
		
		JLabel lblCpu = new JLabel("\u041F\u0440\u043E\u0446\u0435\u0441\u043E\u0440:");
		lblCpu.setBounds(32, 80, 97, 14);
		panel_1_1.add(lblCpu);
		
		comboBoxCpu = new JComboBox<String>();
		comboBoxCpu.setEditable(true);
		AutoCompleteDecorator.decorate(ClientForm.comboBoxCpu);
		lblCpu.setLabelFor(comboBoxCpu);
		comboBoxCpu.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxCpu.setBounds(229, 77, 200, 20);
		panel_1_1.add(comboBoxCpu);
		
		JLabel lblRam = new JLabel("RAM \u043F\u0430\u043C\u0435\u0442:");
		lblRam.setBounds(32, 110, 97, 14);
		panel_1_1.add(lblRam);
		
		comboBoxRam = new JComboBox<String>();
		comboBoxRam.setEditable(true);
		AutoCompleteDecorator.decorate(ClientForm.comboBoxRam);
		lblRam.setLabelFor(comboBoxRam);
		comboBoxRam.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxRam.setBounds(229, 107, 200, 20);
		panel_1_1.add(comboBoxRam);
		
		JLabel lblDisk = new JLabel("\u0414\u0438\u0441\u043A:");
		lblDisk.setBounds(32, 140, 97, 14);
		panel_1_1.add(lblDisk);
		
		comboBoxDisk = new JComboBox<String>();
		comboBoxDisk.setEditable(true);
		AutoCompleteDecorator.decorate(ClientForm.comboBoxDisk);
		lblDisk.setLabelFor(comboBoxDisk);
		comboBoxDisk.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxDisk.setBounds(229, 137, 200, 20);
		panel_1_1.add(comboBoxDisk);
		
		JLabel lblOS = new JLabel("\u041E\u043F\u0435\u0440\u0430\u0446\u0438\u043E\u043D\u043D\u0430 \u0441\u0438\u0441\u0442\u0435\u043C\u0430:");
		lblOS.setBounds(32, 170, 155, 14);
		panel_1_1.add(lblOS);
		
		comboBoxOS = new JComboBox<String>();
		comboBoxOS.setEditable(true);
		AutoCompleteDecorator.decorate(ClientForm.comboBoxOS);
		lblOS.setLabelFor(comboBoxOS);
		comboBoxOS.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxOS.setBounds(229, 167, 200, 20);
		panel_1_1.add(comboBoxOS);
		
		JLabel lblOffice = new JLabel("\u041E\u0444\u0438\u0441 \u041F\u0430\u043A\u0435\u0442:");
		lblOffice.setBounds(32, 200, 97, 14);
		panel_1_1.add(lblOffice);
		
		comboBoxMSOffice = new JComboBox<String>();
		comboBoxMSOffice.setEditable(true);
		AutoCompleteDecorator.decorate(ClientForm.comboBoxMSOffice);
		lblOffice.setLabelFor(comboBoxMSOffice);
		comboBoxMSOffice.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxMSOffice.setBounds(229, 197, 200, 20);
		panel_1_1.add(comboBoxMSOffice);
		
		checkOtherPrograms = new JCheckBox("\u0414\u0440\u0443\u0433\u0438 \u041F\u0440\u043E\u0433\u0440\u0430\u043C\u0438:");
		checkOtherPrograms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				if(checkOtherPrograms.isSelected()){
					comboBoxOthers.setEnabled(true);
					comboBoxOthers.setSelectedIndex(-1);
				}
				else{
					comboBoxOthers.setEnabled(false);
					comboBoxOthers.setSelectedIndex(-1);
				}
			}
		});
		checkOtherPrograms.setBounds(10, 226, 130, 23);
		panel_1_1.add(checkOtherPrograms);
		
		comboBoxOthers = new JComboBox<String>();
		comboBoxOthers.setEnabled(false);
		comboBoxOthers.setEditable(true);
		AutoCompleteDecorator.decorate(ClientForm.comboBoxOthers);
		comboBoxOthers.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxOthers.setBounds(229, 227, 200, 20);
		panel_1_1.add(comboBoxOthers);
		
		JLabel lblDate = new JLabel("\u0414\u0430\u0442\u0430 \u043D\u0430 \u0438\u043D\u0441\u0442\u0430\u043B\u0430\u0446\u0438\u044F:");
		lblDate.setBounds(32, 260, 155, 14);
		panel_1_1.add(lblDate);
		
		UtilDateModel model = new UtilDateModel();
		model.setDate(Year.now().getValue(), YearMonth.now().getMonth().getValue()-1, MonthDay.now().getDayOfMonth());
		Properties prop = new Properties();
		prop.put("text.today", "Today");
		prop.put("text.month", "Month");
		prop.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, prop);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(229, 258, 120, 24);
		panel_1_1.add(datePicker);
		
		JLabel lblInstalled = new JLabel("\u0418\u043D\u0441\u0442\u0430\u043B\u0438\u0440\u0430\u043D \u043E\u0442:");
		lblInstalled.setBounds(32, 290, 97, 14);
		panel_1_1.add(lblInstalled);
		
		cmbEmployees = new JComboBox<String>();
		cmbEmployees.setEditable(true);
		AutoCompleteDecorator.decorate(ClientForm.cmbEmployees);
		cmbEmployees.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cmbEmployees.setBounds(229, 287, 200, 20);
		panel_1_1.add(cmbEmployees);
		
		JLabel lblRoom = new JLabel("\u0412 \u0441\u0442\u0430\u044F:");
		lblRoom.setBounds(32, 320, 97, 14);
		panel_1_1.add(lblRoom);
		
		comboBoxRooms = new JComboBox<String>();
		comboBoxRooms.setEditable(true);
		AutoCompleteDecorator.decorate(ClientForm.comboBoxRooms);
		lblRoom.setLabelFor(comboBoxRooms);
		comboBoxRooms.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxRooms.setBounds(229, 317, 200, 20);
		panel_1_1.add(comboBoxRooms);
		
		JButton btnSearchInRoom = new JButton("\u0422\u044A\u0440\u0441\u0438");
		btnSearchInRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxRooms.getSelectedItem() == null){
					JOptionPane.showMessageDialog(null, "Въведи стая!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else{
					showWhatsInRoom(((String)comboBoxRooms.getSelectedItem()));
					//String choice = (String) JOptionPane.showInputDialog(null, null, "Â ñòàÿ "+(String)comboBoxRooms.getSelectedItem(),
							//JOptionPane.PLAIN_MESSAGE, null, records, records[0]);
					comboBoxRooms.setSelectedIndex(0);
				}
			}
		});
		btnSearchInRoom.setBounds(439, 316, 89, 23);
		panel_1_1.add(btnSearchInRoom);
		
		btnSearchByName = new JButton("\u0422\u044A\u0440\u0441\u0438");
		btnSearchByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cmbEmployees2.getSelectedItem() == null){
					JOptionPane.showMessageDialog(null, "Èçáåðè èìå íà ñëóæèòåë!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else{
					findWithEmployeeName((String)cmbEmployees2.getSelectedItem());
					btnAdd.setVisible(false);
					btnClear_1.setVisible(true);
					cmbEmployeesForSearch.setSelectedIndex(0);
				}
			}
		});
		btnSearchByName.setEnabled(false);
		btnSearchByName.setBounds(439, 346, 89, 23);
		panel_1_1.add(btnSearchByName);
		
		checkOwner = new JCheckBox("\u0418\u0437\u043F\u043E\u043B\u0437\u0432\u0430\u043D \u043E\u0442:");
		checkOwner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				if(checkOwner.isSelected()){
					cmbEmployees2.setEnabled(true);
					btnSearchByName.setEnabled(true);
					cmbEmployees2.setSelectedIndex(-1);
				}
				else{
					cmbEmployees2.setEnabled(false);
					btnSearchByName.setEnabled(false);
					cmbEmployees2.setSelectedIndex(-1);
				}
			}
		});
		checkOwner.setBounds(10, 346, 130, 23);
		panel_1_1.add(checkOwner);
		
		cmbEmployees2 = new JComboBox<String>();
		cmbEmployees2.setEnabled(false);
		cmbEmployees2.setEditable(true);
		AutoCompleteDecorator.decorate(ClientForm.cmbEmployees2);
		cmbEmployees2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cmbEmployees2.setBounds(229, 347, 200, 20);
		panel_1_1.add(cmbEmployees2);
		
		lblMonitorSerialNumber = new JLabel("\u0421\u0435\u0440\u0438\u0435\u043D \u2116 \u041C\u043E\u043D\u0438\u0442\u043E\u0440:");
		lblMonitorSerialNumber.setBounds(455, 50, 130, 14);
		panel_1_1.add(lblMonitorSerialNumber);
		
		comboBoxMonitorSerials = new JComboBox<String>();
		comboBoxMonitorSerials.setEditable(true);
		AutoCompleteDecorator.decorate(comboBoxMonitorSerials);
		comboBoxMonitorSerials.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxMonitorSerials.getSelectedIndex() != 0){
					comboBoxMonitorModels.setSelectedIndex(comboBoxMonitorSerials.getSelectedIndex());
				}
			}
		});
		lblMonitorSerialNumber.setLabelFor(comboBoxMonitorSerials);
		comboBoxMonitorSerials.setBounds(595, 47, 169, 20);
		panel_1_1.add(comboBoxMonitorSerials);
		
		lblMonitorModel = new JLabel("\u041C\u043E\u0434\u0435\u043B \u041C\u043E\u043D\u0438\u0442\u043E\u0440:");
		lblMonitorModel.setBounds(455, 80, 120, 14);
		panel_1_1.add(lblMonitorModel);
		
		comboBoxMonitorModels = new JComboBox<String>();
		comboBoxMonitorModels.setEditable(true);
		AutoCompleteDecorator.decorate(comboBoxMonitorSerials);
		lblMonitorModel.setLabelFor(comboBoxMonitorModels);
		comboBoxMonitorModels.setBounds(595, 77, 169, 20);
		panel_1_1.add(comboBoxMonitorModels);
		
		
		ButtonGroup machineType = new ButtonGroup();
		
		btnPc = new JRadioButton("\u0421\u0442\u0430\u0446\u0438\u043E\u043D\u0430\u0440\u0435\u043D \u041A\u043E\u043C\u043F\u044E\u0442\u044A\u0440");
		btnPc.setSelected(true);
		btnPc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnPc.isSelected()){
					lblMonitorSerialNumber.setVisible(true);
					lblMonitorModel.setVisible(true);
					comboBoxMonitorSerials.setVisible(true);
					comboBoxMonitorModels.setVisible(true);
				}
			}
		});
		btnPc.setBounds(435, 16, 190, 23);
		panel_1_1.add(btnPc);
		machineType.add(btnPc);
		
		btnLaptop = new JRadioButton("\u041B\u0430\u043F\u0442\u043E\u043F");
		btnLaptop.setBounds(627, 16, 109, 23);
		btnLaptop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnLaptop.isSelected()){
					lblMonitorSerialNumber.setVisible(false);
					lblMonitorModel.setVisible(false);
					comboBoxMonitorSerials.setVisible(false);
					comboBoxMonitorModels.setVisible(false);
					comboBoxMonitorSerials.setSelectedIndex(0);
					comboBoxMonitorModels.setSelectedIndex(0);
				}
			}
		});
		panel_1_1.add(btnLaptop);
		machineType.add(btnLaptop);
		
		machineType.getSelection();
		
		
		btnAdd = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textSerialNumber.getText().isEmpty()
						|| comboBoxModel.getSelectedItem() == null
						|| comboBoxCpu.getSelectedItem() == null
						|| comboBoxRam.getSelectedItem() == null
						|| comboBoxDisk.getSelectedItem() == null
						|| comboBoxOS.getSelectedItem() == null
						|| comboBoxMSOffice.getSelectedItem() == null
						|| (checkOtherPrograms.isSelected() && comboBoxOthers.getSelectedItem() == null)
						|| datePicker.getJFormattedTextField().getText().isEmpty()
						|| cmbEmployees.getSelectedItem() == null
						|| comboBoxRooms.getSelectedItem() == null
						|| (checkOwner.isSelected() && cmbEmployees2.getSelectedItem() == null)
						|| (btnPc.isSelected() && (comboBoxMonitorSerials.getSelectedItem() == null || comboBoxMonitorModels.getSelectedItem() == null))){
					 JOptionPane.showMessageDialog(null, "Èìà ïðàçíè ïîëåòà!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else{
						if(btnPc.isSelected()){
							addMonitor(((String) comboBoxMonitorSerials.getSelectedItem()),
									((String) comboBoxMonitorModels.getSelectedItem()));
						}
						addPcModels(((String) comboBoxModel.getSelectedItem()));
						addPcCPUs((String) comboBoxCpu.getSelectedItem());
						addPcRAMs((String) comboBoxRam.getSelectedItem());
						addPcDiskDrives((String) comboBoxDisk.getSelectedItem());
						addPcOSVersions((String) comboBoxOS.getSelectedItem());
						addPcMSOffices((String) comboBoxMSOffice.getSelectedItem());
						addRooms(((String)comboBoxRooms.getSelectedItem()));
						if(checkOtherPrograms.isSelected()){
							addPcOthers((String) comboBoxOthers.getSelectedItem());
						}
						if(btnPc.isSelected()){
							selectedType = "PC";
						}
						if(btnLaptop.isSelected()){
							selectedType = "Laptop";
						}
						addFunction(textSerialNumber.getText(), selectedType, ((String) comboBoxModel.getSelectedItem()),
								((String) comboBoxCpu.getSelectedItem()), ((String) comboBoxRam.getSelectedItem()),
								((String) comboBoxDisk.getSelectedItem()), ((String) comboBoxOS.getSelectedItem()),
								((String) comboBoxMSOffice.getSelectedItem()), ((String) comboBoxOthers.getSelectedItem()),
								datePicker.getJFormattedTextField().getText(),((String) cmbEmployees.getSelectedItem()),
								((String)comboBoxRooms.getSelectedItem()), ((String) cmbEmployees2.getSelectedItem()),
								((String) comboBoxMonitorSerials.getSelectedItem()), ((String) comboBoxMonitorModels.getSelectedItem()));
						comboBoxSerialsForSearch.removeAllItems();
						comboBoxSerials.removeAllItems();
						cmbEmployeesForSearch.removeAllItems();
						comboBoxUsedComputers.removeAllItems();
						comboBoxMonitorSerials.removeAllItems();
						comboBoxMonitorModels.removeAllItems();
						comboBoxCpu.removeAllItems();
						comboBoxRam.removeAllItems();
						comboBoxRam2.removeAllItems();
						comboBoxDisk.removeAllItems();
						comboBoxDisk2.removeAllItems();
						comboBoxOS.removeAllItems();
						comboBoxOS2.removeAllItems();
						comboBoxMSOffice.removeAllItems();
						comboBoxMSOffice2.removeAllItems();
						comboBoxOthers.removeAllItems();
						comboBoxRooms.removeAllItems();
						comboBoxRooms2.removeAllItems();
						loadSerials();
						loadUsedPCs();
						loadMonitorSerials();
						loadMonitorModels();
						loadMonitorSerials();
						loadPcCPUs();
						loadPcRAMs();
						loadPcDiskDrives();
						loadPcModels();
						loadPcOsVersions();
						loadPcMsOffice();
						loadPcOtherPrograms();
						loadRooms();
						textSerialNumber.setText(null);
						comboBoxModel.setSelectedIndex(0);
						comboBoxCpu.setSelectedIndex(0);
						comboBoxRam.setSelectedIndex(0);
						comboBoxDisk.setSelectedIndex(0);
						comboBoxOS.setSelectedIndex(0);
						comboBoxMSOffice.setSelectedIndex(0);
						comboBoxOthers.setSelectedIndex(0);
						datePicker.getJFormattedTextField().setText(null);
						cmbEmployees.setSelectedIndex(0);
						comboBoxRooms.setSelectedIndex(0);
						cmbEmployees2.setSelectedIndex(0);
						comboBoxMonitorSerials.setSelectedIndex(0);
						comboBoxMonitorModels.setSelectedIndex(0);
				}
			}
		});
		btnAdd.setBounds(675, 351, 89, 23);
		panel_1_1.add(btnAdd);
		
		btnClear_1 = new JButton("Изчисти");
		btnClear_1.setVisible(false);
		btnClear_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textSerialNumber.setText(null);
				comboBoxModel.setSelectedIndex(0);
				comboBoxCpu.setSelectedIndex(0);
				comboBoxRam.setSelectedIndex(0);
				comboBoxDisk.setSelectedIndex(0);
				comboBoxOS.setSelectedIndex(0);
				comboBoxMSOffice.setSelectedIndex(0);
				comboBoxOthers.setSelectedIndex(0);
				datePicker.getJFormattedTextField().setText(null);
				cmbEmployees.setSelectedIndex(0);
				comboBoxRooms.setSelectedIndex(0);
				cmbEmployees2.setSelectedIndex(0);
				comboBoxMonitorSerials.setSelectedIndex(0);
				comboBoxMonitorModels.setSelectedIndex(0);
				checkOtherPrograms.setSelected(false);
				checkOwner.setSelected(false);
				btnAdd.setVisible(true);
				btnClear_1.setVisible(false);
				btnSearchByName.setEnabled(false);
				panel_1_1.repaint();
			}
		});
		btnClear_1.setBounds(675, 351, 89, 23);
		panel_1_1.add(btnClear_1);
		
		JPanel panel_1_2 = new JPanel();
		tabbedPane_1.addTab("\u0421\u044A\u0449\u0435\u0441\u0442\u0432\u0443\u0432\u0430\u0449 \u041A\u043E\u043C\u043F\u044E\u0442\u044A\u0440", null, panel_1_2, null);
		panel_1_2.setLayout(null);
		
		JLabel lblSerialNumber2 = new JLabel("\u0421\u0435\u0440\u0438\u0435\u043D \u041D\u043E\u043C\u0435\u0440:");
		lblSerialNumber2.setBounds(10, 20, 150, 14);
		panel_1_2.add(lblSerialNumber2);
		
		comboBoxUnusedComputers = new JComboBox<String>();
		comboBoxUnusedComputers.setEditable(true);
		AutoCompleteDecorator.decorate(comboBoxUnusedComputers);
		lblSerialNumber2.setLabelFor(comboBoxUnusedComputers);
		comboBoxUnusedComputers.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxUnusedComputers.setBounds(229, 17, 200, 20);
		panel_1_2.add(comboBoxUnusedComputers);
		
		JLabel lblNewOwner = new JLabel("\u041D\u043E\u0432 \u043F\u0440\u0438\u0442\u0435\u0436\u0430\u0442\u0435\u043B:");
		lblNewOwner.setBounds(10, 50, 150, 14);
		panel_1_2.add(lblNewOwner);
		
		comboBoxNewOwners = new JComboBox<String>();
		comboBoxNewOwners.setEditable(true);
		AutoCompleteDecorator.decorate(comboBoxNewOwners);
		lblNewOwner.setLabelFor(comboBoxNewOwners);
		comboBoxNewOwners.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxNewOwners.setBounds(229, 47, 200, 20);
		panel_1_2.add(comboBoxNewOwners);
		
		JLabel lblStartsUsing = new JLabel("\u0417\u0430\u043F\u043E\u0447\u0432\u0430 \u0434\u0430 \u0438\u0437\u043F\u043E\u043B\u0437\u0432\u0430 \u043E\u0442:");
		lblStartsUsing.setBounds(10, 80, 150, 14);
		panel_1_2.add(lblStartsUsing);
		
		UtilDateModel modelTwo = new UtilDateModel();
		model.setDate(Year.now().getValue(), YearMonth.now().getMonth().getValue()-1, MonthDay.now().getDayOfMonth());
		Properties propTwo = new Properties();
		propTwo.put("text.today", "Today");
		propTwo.put("text.month", "Month");
		propTwo.put("text.year", "Year");
		JDatePanelImpl datePanelTwo = new JDatePanelImpl(modelTwo, propTwo);
		JDatePickerImpl datePickerStartsUsing = new JDatePickerImpl(datePanelTwo, new DateLabelFormatter());
		lblStartsUsing.setLabelFor(datePickerStartsUsing);
		datePickerStartsUsing.setBounds(229, 78, 120, 24);
		panel_1_2.add(datePickerStartsUsing);
		
		JButton btnUpdate = new JButton("\u041F\u0440\u043E\u043C\u0435\u043D\u0438");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxUnusedComputers.getSelectedItem() == null
						|| comboBoxNewOwners.getSelectedItem() == null
						|| datePickerStartsUsing.getJFormattedTextField().getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Èìà íåïîïúëíåíè ïîëåòà!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else{
					setUsed(((String)comboBoxUnusedComputers.getSelectedItem()),
							((String)comboBoxNewOwners.getSelectedItem()), 
							datePickerStartsUsing.getJFormattedTextField().getText());
					comboBoxUnusedComputers.removeAllItems();
					loadUnusedPCs();
					comboBoxUsedComputers.removeAllItems();
					loadUsedPCs();
					comboBoxNewOwners.setSelectedIndex(0);
					datePickerStartsUsing.getJFormattedTextField().setText(null);
				}
			}
		});
		btnUpdate.setBounds(136, 136, 89, 23);
		panel_1_2.add(btnUpdate);
		
		JLabel labelMadeBy_1 = new JLabel("This program is made by Georgi Ditsov");
		labelMadeBy_1.setForeground(Color.BLUE);
		labelMadeBy_1.setFont(new Font("Rockwell", Font.PLAIN, 11));
		labelMadeBy_1.setBounds(384, 360, 220, 14);
		panel_1_2.add(labelMadeBy_1);
		
		JButton btnDeletePC = new JButton("\u0411\u0440\u0430\u043A\u0443\u0432\u0430\u043D\u0435");
		btnDeletePC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxUnusedComputers.getSelectedItem() == null){
					JOptionPane.showMessageDialog(null, "Èçáåðè ñåðèåí íîìåð", "Ãðåøêà!", JOptionPane.ERROR_MESSAGE);
				}
				else{
					deletePC(((String)comboBoxUnusedComputers.getSelectedItem()));
					comboBoxUnusedComputers.removeAllItems();
					loadUnusedPCs();
				}
			}
		});
		btnDeletePC.setBounds(439, 16, 110, 23);
		panel_1_2.add(btnDeletePC);
		
		JPanel panel_1_3 = new JPanel();
		tabbedPane_1.addTab("\u0421\u043B\u0443\u0436\u0438\u0442\u0435\u043B", null, panel_1_3, null);
		panel_1_3.setLayout(null);
		
		JLabel lblEmployeeName = new JLabel("\u0418\u043C\u0435\u043D\u0430:");
		lblEmployeeName.setBounds(10, 20, 80, 14);
		panel_1_3.add(lblEmployeeName);
		
		textName = new JTextField();
		lblEmployeeName.setLabelFor(textName);
		textName.setBounds(100, 17, 200, 20);
		panel_1_3.add(textName);
		textName.setColumns(10);
		
		JLabel lblDepartment = new JLabel("\u041E\u0442\u0434\u0435\u043B:");
		lblDepartment.setBounds(10, 50, 80, 14);
		panel_1_3.add(lblDepartment);
		
		comboBoxDepartments = new JComboBox<String>();
		comboBoxDepartments.setEditable(true);
		AutoCompleteDecorator.decorate(comboBoxDepartments);
		lblDepartment.setLabelFor(comboBoxDepartments);
		comboBoxDepartments.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxDepartments.setBounds(100, 47, 200, 20);
		panel_1_3.add(comboBoxDepartments);
		
		JButton btnAddDepartment = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438");
		btnAddDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String department = JOptionPane.showInputDialog("Âúâåäè èìåòî íà îòäåëà:", null);
				if(department == null || (department != null && department.equals(""))){
					JOptionPane.showMessageDialog(null, "Íèùî íå áåøå äîáàâåíî.");
				}
				else{
					Client.addDepartment(department);
					comboBoxDepartments.removeAllItems();
					comboBoxDepartments2.removeAllItems();
					loadDepartments();
				}
			}
		});
		btnAddDepartment.setBounds(310, 46, 90, 23);
		panel_1_3.add(btnAddDepartment);
		
		JButton btnAddEmployee = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438 \u0421\u043B\u0443\u0436\u0438\u0442\u0435\u043B");
		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textName.getText().isEmpty() || comboBoxDepartments.getSelectedItem() == null){
					JOptionPane.showMessageDialog(null, "Èìà ïðàçíè ïîëåòà!", "Ãðåøêà!", JOptionPane.ERROR_MESSAGE);
				}
				else{
					Client.addEmployee(textName.getText(), ((String)comboBoxDepartments.getSelectedItem()));
					textName.setText(null);
					cmbEmployees.removeAllItems();
					cmbEmployees2.removeAllItems();
					cmbEmployees3.removeAllItems();
					comboBoxDepartments.setSelectedIndex(0);
					Client.loadEmployees();
				}
			}
		});
		btnAddEmployee.setBounds(150, 145, 150, 25);
		panel_1_3.add(btnAddEmployee);
		
		JLabel labelMadeBy_2 = new JLabel("This program is made by Georgi Ditsov");
		labelMadeBy_2.setForeground(Color.BLUE);
		labelMadeBy_2.setFont(new Font("Rockwell", Font.PLAIN, 11));
		labelMadeBy_2.setBounds(544, 360, 220, 14);
		panel_1_3.add(labelMadeBy_2);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("\u041D\u0430\u043C\u0435\u0440\u0438", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblSearchBySerialNum = new JLabel("\u041F\u043E \u0421\u0435\u0440\u0438\u0435\u043D \u041D\u043E\u043C\u0435\u0440:");
		lblSearchBySerialNum.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSearchBySerialNum.setBounds(10, 30, 138, 14);
		panel_2.add(lblSearchBySerialNum);
		
		JButton btnSearch_1 = new JButton("\u0422\u044A\u0440\u0441\u0438");
		btnSearch_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBoxSerialsForSearch.getSelectedItem() == null){
					JOptionPane.showMessageDialog(null, "Enter serial number!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else{
					findWithSerialNumber((String)comboBoxSerialsForSearch.getSelectedItem());
					btnAdd.setVisible(false);
					btnClear_1.setVisible(true);
					comboBoxSerialsForSearch.setSelectedIndex(0);
				}
			}
		});
		btnSearch_1.setBounds(368, 26, 89, 23);
		panel_2.add(btnSearch_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 150, 599, 199);
		panel_2.add(scrollPane);
		
		textAreaForPrint = new JTextArea();
		textAreaForPrint.setEditable(false);
		textAreaForPrint.setFont(new Font("Monospaced", Font.PLAIN, 18));
		scrollPane.setViewportView(textAreaForPrint);
		
		JButton btnPrint = new JButton("\u041F\u0435\u0447\u0430\u0442");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textAreaForPrint.getText().isEmpty() == false){
					try {
						textAreaForPrint.print();
					} catch (PrinterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Ïîëåòî çà ïå÷àò å ïðàçíî!", "Ãðåøêà!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPrint.setBounds(581, 379, 89, 23);
		panel_2.add(btnPrint);
		
		comboBoxSerialsForSearch = new JComboBox<String>();
		comboBoxSerialsForSearch.setEditable(true);
		AutoCompleteDecorator.decorate(comboBoxSerialsForSearch);
		lblSearchBySerialNum.setLabelFor(comboBoxSerialsForSearch);
		comboBoxSerialsForSearch.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxSerialsForSearch.setBounds(158, 27, 200, 20);
		panel_2.add(comboBoxSerialsForSearch);
		
		JLabel lblRooms = new JLabel("\u0412 \u0441\u0442\u0430\u044F:");
		lblRooms.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblRooms.setBounds(10, 110, 110, 14);
		panel_2.add(lblRooms);
		
		comboBoxRooms2 = new JComboBox<String>();
		comboBoxRooms2.setEditable(true);
		AutoCompleteDecorator.decorate(comboBoxRooms2);
		lblRooms.setLabelFor(comboBoxRooms2);
		comboBoxRooms2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxRooms2.setBounds(158, 107, 200, 20);
		panel_2.add(comboBoxRooms2);
		
		JButton btnSearch_3 = new JButton("\u0422\u044A\u0440\u0441\u0438");
		btnSearch_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBoxRooms2.getSelectedItem() == null){
					JOptionPane.showMessageDialog(null, "Èçáåðè ñòàÿ!", "Ãðåøêà!", JOptionPane.ERROR_MESSAGE);
				}
				else{
					showPCsInRoom(((String)comboBoxRooms2.getSelectedItem()));
					comboBoxRooms2.setSelectedIndex(0);
				}
			}
		});
		btnSearch_3.setBounds(368, 106, 89, 23);
		panel_2.add(btnSearch_3);
		
		JButton btnClearTextArea = new JButton("\u0418\u0437\u0447\u0438\u0441\u0442\u0438");
		btnClearTextArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaForPrint.setText(null);
				comboBoxSerialsForSearch.setSelectedIndex(0);
				comboBoxRooms2.setSelectedIndex(0);
			}
		});
		btnClearTextArea.setBounds(680, 379, 89, 23);
		panel_2.add(btnClearTextArea);
		
		JLabel lblSearchByEmployeeName = new JLabel("\u041F\u043E \u0418\u043C\u0435:");
		lblSearchByEmployeeName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSearchByEmployeeName.setBounds(10, 70, 138, 14);
		panel_2.add(lblSearchByEmployeeName);
		
		cmbEmployeesForSearch = new JComboBox<String>();
		cmbEmployeesForSearch.setEditable(true);
		AutoCompleteDecorator.decorate(cmbEmployeesForSearch);
		cmbEmployeesForSearch.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cmbEmployeesForSearch.setBounds(158, 67, 200, 20);
		panel_2.add(cmbEmployeesForSearch);
		
		JButton btnSearch_2 = new JButton("\u0422\u044A\u0440\u0441\u0438");
		btnSearch_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cmbEmployeesForSearch.getSelectedItem() == null){
					JOptionPane.showMessageDialog(null, "Èçáåðè èìå íà ñëóæèòåë!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else{
					findWithEmployeeName((String)cmbEmployeesForSearch.getSelectedItem());
					btnAdd.setVisible(false);
					btnClear_1.setVisible(true);
					cmbEmployeesForSearch.setSelectedIndex(0);
				}
			}
		});
		btnSearch_2.setBounds(368, 66, 89, 23);
		panel_2.add(btnSearch_2);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("\u041F\u0440\u043E\u043C\u0435\u043D\u0438", null, panel_3, null);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		panel_3.add(tabbedPane_2, BorderLayout.CENTER);
		
		JPanel panel_3_1 = new JPanel();
		tabbedPane_2.addTab("\u041A\u043E\u043C\u043F\u044E\u0442\u044A\u0440", null, panel_3_1, null);
		panel_3_1.setLayout(null);
		
		JLabel lblComputers = new JLabel("\u0418\u0437\u0431\u0435\u0440\u0438 \u041A\u043E\u043C\u043F\u044E\u0442\u044A\u0440:");
		lblComputers.setBounds(10, 20, 200, 14);
		panel_3_1.add(lblComputers);
		
		comboBoxUsedComputers = new JComboBox<String>();
		comboBoxUsedComputers.setEditable(true);
		AutoCompleteDecorator.decorate(comboBoxUsedComputers);
		lblComputers.setLabelFor(comboBoxUsedComputers);
		comboBoxUsedComputers.setBounds(245, 17, 200, 20);
		panel_3_1.add(comboBoxUsedComputers);
		
		JLabel lblStopsUsing = new JLabel("\u0414\u0430\u0442\u0430 \u043D\u0430 \u043F\u043E\u0441\u043B\u0435\u0434\u043D\u043E \u0438\u0437\u043F\u043E\u043B\u0437\u0432\u0430\u043D\u0435:");
		lblStopsUsing.setBounds(10, 52, 200, 14);
		panel_3_1.add(lblStopsUsing);
		
		UtilDateModel modelThree = new UtilDateModel();
		model.setDate(Year.now().getValue(), YearMonth.now().getMonth().getValue()-1, MonthDay.now().getDayOfMonth());
		Properties propThree = new Properties();
		propThree.put("text.today", "Today");
		propThree.put("text.month", "Month");
		propThree.put("text.year", "Year");
		JDatePanelImpl datePanelThree = new JDatePanelImpl(modelThree, propThree);
		JDatePickerImpl datePickerStopsUsing = new JDatePickerImpl(datePanelThree, new DateLabelFormatter());
		lblStopsUsing.setLabelFor(datePickerStopsUsing);
		datePickerStopsUsing.setBounds(245, 48, 120, 24);
		panel_3_1.add(datePickerStopsUsing);
		
		JButton btnUsedBy = new JButton("\u0418\u0437\u043F\u043E\u043B\u0437\u0432\u0430\u043D \u043E\u0442");
		btnUsedBy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxUsedComputers.getSelectedItem() == null){
					JOptionPane.showMessageDialog(null, "Select Computer", "InfoBox: Error!", JOptionPane.ERROR_MESSAGE);
				}
				else{
					usedBy(((String)comboBoxUsedComputers.getSelectedItem()));
					comboBoxUsedComputers.setSelectedIndex(0);
					datePickerStopsUsing.getJFormattedTextField().setText(null);
				}
			}
		});
		btnUsedBy.setBounds(455, 16, 144, 23);
		panel_3_1.add(btnUsedBy);
		
		JButton btnSetUnused = new JButton("\u041D\u0430\u043F\u0440\u0430\u0432\u0438 \u041D\u0435\u0438\u0437\u043F\u043E\u043B\u0437\u0432\u0430\u043D");
		btnSetUnused.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxUsedComputers.getSelectedItem() == null 
						|| datePickerStopsUsing.getJFormattedTextField().getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Èìà ïðàçíè ïîëåòà", "Ãðåøêà!", JOptionPane.ERROR_MESSAGE);
				}
				else{					setUnused(((String)comboBoxUsedComputers.getSelectedItem()), 
							datePickerStopsUsing.getJFormattedTextField().getText());
					comboBoxUsedComputers.removeAllItems();
					loadUsedPCs();
					comboBoxUnusedComputers.removeAllItems();
					loadUnusedPCs();
					datePickerStopsUsing.getJFormattedTextField().setText(null);
				}
			}
		});
		btnSetUnused.setBounds(609, 16, 155, 23);
		panel_3_1.add(btnSetUnused);
		
		JLabel lblReinstall = new JLabel("\u0414\u0430\u0442\u0430 \u043D\u0430 \u043F\u0440\u0435\u0438\u043D\u0441\u0442\u0430\u043B\u0438\u0440\u0430\u043D\u0435:");
		lblReinstall.setBounds(10, 87, 200, 14);
		panel_3_1.add(lblReinstall);
		
		UtilDateModel modelFour = new UtilDateModel();
		model.setDate(Year.now().getValue(), YearMonth.now().getMonth().getValue()-1, MonthDay.now().getDayOfMonth());
		Properties propFour = new Properties();
		propFour.put("text.today", "Today");
		propFour.put("text.month", "Month");
		propFour.put("text.year", "Year");
		JDatePanelImpl datePanelFour = new JDatePanelImpl(modelFour, propFour);
		JDatePickerImpl datePickerReinstallDate = new JDatePickerImpl(datePanelFour, new DateLabelFormatter());
		lblReinstall.setLabelFor(datePickerReinstallDate);
		datePickerReinstallDate.setBounds(245, 83, 120, 24);
		panel_3_1.add(datePickerReinstallDate);
		
		comboBoxOS2 = new JComboBox<String>();
		comboBoxOS2.setEditable(true);
		AutoCompleteDecorator.decorate(comboBoxOS2);
		comboBoxOS2.setBounds(245, 118, 200, 20);
		panel_3_1.add(comboBoxOS2);
		
		comboBoxMSOffice2 = new JComboBox<String>();
		comboBoxMSOffice2.setEditable(true);
		AutoCompleteDecorator.decorate(comboBoxMSOffice2);
		comboBoxMSOffice2.setBounds(245, 152, 200, 20);
		panel_3_1.add(comboBoxMSOffice2);
		
		JButton btnChange = new JButton("\u041F\u0440\u043E\u043C\u0435\u043D\u0438");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(datePickerReinstallDate.getJFormattedTextField().getText().isEmpty()
						|| (String)comboBoxOS2.getSelectedItem() == null
						|| (String)comboBoxMSOffice2.getSelectedItem() == null
						|| (String)comboBoxUsedComputers.getSelectedItem() == null
						|| (String)comboBoxRam2.getSelectedItem() == null
						|| (String)comboBoxDisk2.getSelectedItem()== null){
					JOptionPane.showMessageDialog(null, "Select the serial number if the PC,reinstall date, operating system version, office package version, ram memory and hard disk", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else{
					reinstallPc(((String)comboBoxUsedComputers.getSelectedItem()),
							datePickerReinstallDate.getJFormattedTextField().getText(),
							((String)comboBoxOS2.getSelectedItem()), ((String)comboBoxMSOffice2.getSelectedItem()),
							((String)comboBoxRam2.getSelectedItem()), ((String)comboBoxDisk2.getSelectedItem()));
					comboBoxUsedComputers.setSelectedIndex(0);
					comboBoxOS2.setSelectedIndex(0);
					comboBoxMSOffice2.setSelectedIndex(0);
					comboBoxRam2.setSelectedIndex(0);
					comboBoxDisk2.setSelectedIndex(0);
					datePickerReinstallDate.getJFormattedTextField().setText(null);
				}
			}
		});
		btnChange.setBounds(310, 256, 89, 23);
		panel_3_1.add(btnChange);
		
		JLabel labelMadeBy_3 = new JLabel("This program is made by Georgi Ditsov");
		labelMadeBy_3.setForeground(Color.BLUE);
		labelMadeBy_3.setFont(new Font("Rockwell", Font.PLAIN, 11));
		labelMadeBy_3.setBounds(544, 360, 220, 14);
		panel_3_1.add(labelMadeBy_3);
		
		comboBoxRam2 = new JComboBox<String>();
		comboBoxRam2.setEditable(true);
		AutoCompleteDecorator.decorate(comboBoxRam2);
		comboBoxRam2.setBounds(245, 183, 200, 20);
		panel_3_1.add(comboBoxRam2);
		
		comboBoxDisk2 = new JComboBox<String>();
		comboBoxDisk2.setEditable(true);
		AutoCompleteDecorator.decorate(comboBoxDisk2);
		comboBoxDisk2.setBounds(245, 213, 200, 20);
		panel_3_1.add(comboBoxDisk2);
		
		JLabel lblNewOS = new JLabel("\u041D\u043E\u0432\u0430 \u041E\u043F\u0435\u0440\u0430\u0446\u0438\u043E\u043D\u043D\u0430 \u0441\u0438\u0441\u0442\u0435\u043C\u0430:");
		lblNewOS.setBounds(10, 121, 200, 14);
		panel_3_1.add(lblNewOS);
		
		JLabel lblNewOffice = new JLabel("\u041D\u043E\u0432 \u041E\u0444\u0438\u0441 \u041F\u0430\u043A\u0435\u0442:");
		lblNewOffice.setBounds(10, 155, 144, 14);
		panel_3_1.add(lblNewOffice);
		
		JLabel lblNewRAM = new JLabel("\u041D\u043E\u0432\u0430 RAM \u043F\u0430\u043C\u0435\u0442:");
		lblNewRAM.setBounds(10, 186, 144, 14);
		panel_3_1.add(lblNewRAM);
		
		JLabel lblNewDisk = new JLabel("\u041D\u043E\u0432 \u0414\u0438\u0441\u043A:");
		lblNewDisk.setBounds(10, 216, 144, 14);
		panel_3_1.add(lblNewDisk);
		
		JPanel panel_3_2 = new JPanel();
		tabbedPane_2.addTab("\u0421\u043B\u0443\u0436\u0438\u0442\u0435\u043B, \u043E\u0442\u0434\u0435\u043B\u0438", null, panel_3_2, null);
		panel_3_2.setLayout(null);
		
		JLabel lblName = new JLabel("\u0421\u043B\u0443\u0436\u0438\u0442\u0435\u043B:");
		lblName.setBounds(10, 20, 97, 14);
		panel_3_2.add(lblName);
		
		cmbEmployees3 = new JComboBox<String>();
		cmbEmployees3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cmbEmployees3.getSelectedIndex() != 0){
					comboBoxDepartments2.setSelectedItem(getEmployeeDepartment((String) cmbEmployees3.getSelectedItem()));
				}
			}
		});
		cmbEmployees3.setEditable(true);
		AutoCompleteDecorator.decorate(cmbEmployees3);
		lblName.setLabelFor(cmbEmployees3);
		cmbEmployees3.setBounds(117, 17, 200, 20);
		panel_3_2.add(cmbEmployees3);
		
		JButton btnDelete = new JButton("\u0418\u0437\u0442\u0440\u0438\u0439");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cmbEmployees3.getSelectedItem() == null){
					JOptionPane.showMessageDialog(null, "Èçáåðè ñëóæèòåë!", "Ãðåøêà!", JOptionPane.ERROR_MESSAGE);
				}
				else{
					deleteEmployee(((String)cmbEmployees3.getSelectedItem()));
					cmbEmployees.removeAllItems();
					cmbEmployees2.removeAllItems();
					cmbEmployees3.removeAllItems();
					cmbEmployeesForSearch.removeAllItems();
					comboBoxNewOwners.removeAllItems();
					loadEmployees();
					comboBoxDepartments2.setSelectedIndex(0);
				}
			}
		});
		btnDelete.setBounds(327, 16, 89, 23);
		panel_3_2.add(btnDelete);
		
		JLabel lblDepartments = new JLabel("\u041E\u0442\u0434\u0435\u043B:");
		lblDepartments.setBounds(10, 54, 97, 14);
		panel_3_2.add(lblDepartments);
		
		comboBoxDepartments2 = new JComboBox<String>();
		comboBoxDepartments2.setEditable(true);
		comboBoxDepartments2.setBounds(117, 51, 200, 20);
		panel_3_2.add(comboBoxDepartments2);
		
		JButton btnChangeDepartment = new JButton("\u041F\u0440\u0435\u0438\u043C\u0435\u043D\u0443\u0432\u0430\u0439 \u043E\u0442\u0434\u0435\u043B");
		btnChangeDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxDepartments2.getSelectedItem() == null){
					JOptionPane.showMessageDialog(null, "Èçáåðè îòäåë!", "Ãðåøêà!", JOptionPane.ERROR_MESSAGE);
				}
				else{
					String newDepartmentName = JOptionPane.showInputDialog("Âúâåäè èìåòî íà îòäåëà:", null);
					if(newDepartmentName == null || (newDepartmentName != null && newDepartmentName.equals(""))){
						JOptionPane.showMessageDialog(null, "Íèùî íå áåøå äîáàâåíî.");
					}
					else{
						updateDepartment(((String) comboBoxDepartments2.getSelectedItem()), newDepartmentName);
						comboBoxDepartments.removeAllItems();
						comboBoxDepartments2.removeAllItems();
						loadDepartments();
					}
				}
			}
		});
		btnChangeDepartment.setBounds(327, 50, 180, 23);
		panel_3_2.add(btnChangeDepartment);
		
		JButton btnChangeEmployeeDepartment = new JButton("\u041F\u0440\u043E\u043C\u0435\u043D\u0438 \u043E\u0442\u0434\u0435\u043B");
		btnChangeEmployeeDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cmbEmployees3.getSelectedItem() == null || comboBoxDepartments2.getSelectedItem() == null){
					JOptionPane.showMessageDialog(null, "Èçáåðè ñëóæèòåë è íîâ îòäåë!", "Ãðåøêà!", JOptionPane.ERROR_MESSAGE);
				}
				else{
					changeEmployeeDepartment(((String) cmbEmployees3.getSelectedItem()),
							((String) comboBoxDepartments2.getSelectedItem()));
					cmbEmployees3.setSelectedIndex(0);
					comboBoxDepartments2.setSelectedIndex(0);
				}
			}
		});
		btnChangeEmployeeDepartment.setBounds(426, 16, 150, 23);
		panel_3_2.add(btnChangeEmployeeDepartment);
		
		JLabel labelMadeBy_4 = new JLabel("This program is made by Georgi Ditsov");
		labelMadeBy_4.setForeground(Color.BLUE);
		labelMadeBy_4.setFont(new Font("Rockwell", Font.PLAIN, 11));
		labelMadeBy_4.setBounds(544, 360, 220, 14);
		panel_3_2.add(labelMadeBy_4);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(UIManager.getColor("Button.background"));
		tabbedPane.addTab("\u0418\u0441\u0442\u043E\u0440\u0438\u044F", null, panel_4, null);
		panel_4.setLayout(null);
		
		JLabel lblSelectPC = new JLabel("\u0418\u0437\u0431\u0435\u0440\u0438 \u041A\u043E\u043C\u043F\u044E\u0442\u044A\u0440:");
		lblSelectPC.setBounds(10, 50, 200, 14);
		panel_4.add(lblSelectPC);
		
		comboBoxSerials = new JComboBox<String>();
		comboBoxSerials.setEditable(true);
		AutoCompleteDecorator.decorate(comboBoxSerials);
		lblSelectPC.setLabelFor(comboBoxSerials);
		comboBoxSerials.setBounds(250, 47, 200, 20);
		panel_4.add(comboBoxSerials);
		
		JButton btnShowHistory = new JButton("\u041F\u043E\u043A\u0430\u0436\u0438 \u0418\u0441\u0442\u043E\u0440\u0438\u044F");
		btnShowHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBoxSerials.getSelectedItem() == null){
					JOptionPane.showMessageDialog(null, "!", "Ãðåøêà!", JOptionPane.ERROR_MESSAGE);
				}
				else{
					showHistory(((String)comboBoxSerials.getSelectedItem()));
					comboBoxSerials.setSelectedIndex(0);
				}
			}
		});
		btnShowHistory.setBounds(487, 46, 170, 23);
		panel_4.add(btnShowHistory);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 128, 597, 237);
		panel_4.add(scrollPane_1);
		
		textAreaForPrint2 = new JTextArea();
		scrollPane_1.setViewportView(textAreaForPrint2);
		textAreaForPrint2.setEditable(false);
		textAreaForPrint2.setFont(new Font("Monospaced", Font.PLAIN, 18));
		
		JButton btnSecondPrint = new JButton("\u041F\u0435\u0447\u0430\u0442");
		btnSecondPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textAreaForPrint2.getText().isEmpty() == false){
					try {
						textAreaForPrint.print();
					} catch (PrinterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Ïîëåòî çà ïå÷àò å ïðàçíî!", "Ãðåøêà!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSecondPrint.setBounds(420, 379, 89, 23);
		panel_4.add(btnSecondPrint);
		
		JButton btnClear = new JButton("\u0418\u0437\u0447\u0438\u0441\u0442\u0438");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textAreaForPrint2.setText(null);
			}
		});
		btnClear.setBounds(518, 379, 89, 23);
		panel_4.add(btnClear);
	}
	static void fillWithPcSerialNumbers(String item){
		comboBoxSerialsForSearch.addItem(item);
		comboBoxSerials.addItem(item);
	}
	static void fillWithPcModels(String item){
		comboBoxModel.addItem(item);
	}
	static void fillWithPcCPUs(String item){
		comboBoxCpu.addItem(item);
	}
	static void fillWithPcRAMs(String item){
		comboBoxRam.addItem(item);
		comboBoxRam2.addItem(item);
	}
	static void fillWithPcDisks(String item){
		comboBoxDisk.addItem(item);
		comboBoxDisk2.addItem(item);
	}
	static void fillWithPcOS(String item){
		comboBoxOS.addItem(item);
		comboBoxOS2.addItem(item);
	}
	static void fillWithPcMSOffice(String item){
		comboBoxMSOffice.addItem(item);
		comboBoxMSOffice2.addItem(item);
	}
	static void fillWithPcOtherProgs(String item){
		comboBoxOthers.addItem(item);
	}
	static void fillWithRooms(String item){
		comboBoxRooms.addItem(item);
		comboBoxRooms2.addItem(item);
	}
	static void fillWithEmployees(String item){
		cmbEmployees.addItem(item);
		cmbEmployees2.addItem(item);
		cmbEmployees3.addItem(item);
		cmbEmployeesForSearch.addItem(item);
		comboBoxNewOwners.addItem(item);
	}
	static void fillWithDepartments(String item){
		comboBoxDepartments.addItem(item);
		comboBoxDepartments2.addItem(item);
	}
	static void fillWithMonitorSerials(String item){
		comboBoxMonitorSerials.addItem(item);
	}
	static void fillWithMonitorModels(String item){
		comboBoxMonitorModels.addItem(item);
	}
	static void setRecords(String serialNumber, String machineType,String machineModel, String cpu, String ram,
			String disk, String osVersion, String msOfficeVersion, String otherPrograms, String dateOfInstallation, 
			String employeeWhoInstalled, String roomNumber, String employeeWhoUse, String monitorSerialNumber,
			String monitorModel){
		textSerialNumber.setText(serialNumber);
		if(machineType.equals("PC")){
			btnPc.setSelected(true);
			lblMonitorSerialNumber.setVisible(true);
			lblMonitorModel.setVisible(true);
			comboBoxMonitorSerials.setVisible(true);
			comboBoxMonitorModels.setVisible(true);
		}
		else{
			btnLaptop.setSelected(true);
			lblMonitorSerialNumber.setVisible(false);
			lblMonitorModel.setVisible(false);
			comboBoxMonitorSerials.setVisible(false);
			comboBoxMonitorModels.setVisible(false);
		}
		comboBoxModel.setSelectedItem(machineModel);
		comboBoxCpu.setSelectedItem(cpu);
		comboBoxRam.setSelectedItem(ram);
		comboBoxDisk.setSelectedItem(disk);
		comboBoxOS.setSelectedItem(osVersion);
		comboBoxMSOffice.setSelectedItem(msOfficeVersion);
		if(otherPrograms == null || otherPrograms.equals("null") || otherPrograms.equals("")){
			checkOtherPrograms.setSelected(false);
			comboBoxOthers.setEnabled(false);
			comboBoxOthers.setSelectedItem(null);
		}
		else{
			checkOtherPrograms.setSelected(true);
			comboBoxOthers.setEnabled(true);
			comboBoxOthers.setSelectedItem(otherPrograms);
		}
		if(dateOfInstallation == null || dateOfInstallation.equals("null")){
			datePicker.getJFormattedTextField().setText(null);
		}
		else{
			datePicker.getJFormattedTextField().setText(dateOfInstallation);
		}
		cmbEmployees.setSelectedItem(employeeWhoInstalled);
		comboBoxRooms.setSelectedItem(roomNumber);
		if(employeeWhoUse == null || employeeWhoUse.equals("null")){
			checkOwner.setSelected(false);
			cmbEmployees2.setEnabled(false);
			cmbEmployees2.setSelectedItem(null);
		}
		else{
			checkOwner.setSelected(true);
			cmbEmployees2.setEnabled(true);
			cmbEmployees2.setSelectedItem(employeeWhoUse);
		}
		comboBoxMonitorSerials.setSelectedItem(monitorSerialNumber);
		comboBoxMonitorModels.setSelectedItem(monitorModel);
	}
	static void setTextAreaForPrint(String item) {
		textAreaForPrint.setText(item);
	}
	static void setTextAreaForPrint2(String item) {
		textAreaForPrint2.setText(item);
	}
	static void fillWithUsedComputers(String item){
		comboBoxUsedComputers.addItem(item);
	}
	static void fillWithUnusedComputers(String item) {
		comboBoxUnusedComputers.addItem(item);
	}
	static void fillRecords(String item, int count){
		records[count] = item;
	}
}