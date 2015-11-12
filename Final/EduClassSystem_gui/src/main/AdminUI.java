package main;

import instruction.Instruction;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

import course.Course;
import user.Admin;

public class AdminUI extends JFrame {


	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	private JPanel headPanel;
	private JLabel welcome;
	
	private JPanel myCoursePanel;
	private JLabel adminLabel;
	
	private JButton btnChangePassword;
	private JButton btnTeacher;
	private JButton btnStudent;
	private JButton btnCourse;
	private JButton btnChoose;
	
	private JPanel mainPanel;
	private JLabel lblNewPassword;
	private JLabel lblRepeatPassword;
	private JPasswordField passwordField;
	private JPasswordField repeatPasswordField;
	private JButton btnPasswordOk;
	private JButton btnPasswordReset;
	
	
	private JLabel lblDetailInformation;
	
	JTextField txtTime;
	JTextField txtTeacher;
	JTextField txtClassroom;
	JTextField txtCName;
	JTextField txtTA;
	JTextField txtCredit;
	JTextField txtCNO;
	
	private JLabel lblCno;		
	private JLabel lblCName;
	private JLabel lblTeacher;
	private JLabel lblCredit;		
	private JLabel lblClassroom;		
	private JLabel lblTime;		
	private JLabel lblTa;
		
	private JLabel lblUserID;
	private JLabel lblUserName;
	private JTextField txtUserName;
	
	private JButton btnDelete;
	private JComboBox<String> comboBox;
	
	private MainSystem eduSystem;
	private Instruction instruction;
	private Admin admin;
	
	ArrayList<Course> courseList = new ArrayList<Course>();
	boolean isOnThePanel = true;
	
	private int status = 1;//course 1; teacher 2; student 3; choose 4

	public AdminUI(Admin user, MainSystem mainSystem, Instruction instr) {
		
		this.eduSystem = mainSystem;
		this.instruction = instr;
		this.admin = user;
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setTitle("System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//draw headPanel
		headPanel = new JPanel();
		headPanel.setBounds(5, 5, 774, 35);
		headPanel.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(0, 0, 0)));
		contentPane.add(headPanel);
		headPanel.setLayout(null);
		
		welcome = new JLabel("Welcome");
		welcome.setBounds(0, 0, 140, 33);
		welcome.setHorizontalAlignment(SwingConstants.CENTER);
		welcome.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		headPanel.add(welcome);
		
		//draw myCoursePanel
		myCoursePanel = new JPanel();
		myCoursePanel.setBounds(5, 41, 162, 515);
		myCoursePanel.setBorder(new MatteBorder(0, 0, 0, 3, (Color) new Color(0, 0, 0)));
		contentPane.add(myCoursePanel);
		myCoursePanel.setLayout(null);
		
		adminLabel = new JLabel("Administrator");
		adminLabel.setBounds(30, 0, 155, 22);
		adminLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		myCoursePanel.add(adminLabel);
	
		btnTeacher = new JButton("Show/Delete Teacher");		
		btnTeacher.setBounds(0, 69, 155, 23);
		myCoursePanel.add(btnTeacher);
		
		btnStudent = new JButton("Show/Delete Student");		
		btnStudent.setBounds(0, 45, 155, 23);
		myCoursePanel.add(btnStudent);
		
		btnCourse = new JButton("Show/Delete Course");		
		btnCourse.setBounds(0, 21, 155, 23);
		myCoursePanel.add(btnCourse);
		
		btnChoose = new JButton("Show/Delete Choose");		
		btnChoose.setBounds(0, 93, 155, 23);
		myCoursePanel.add(btnChoose);
		
		btnChangePassword = new JButton("Change Password");
		btnChangePassword.setBounds(0, 117, 155, 23);
		myCoursePanel.add(btnChangePassword);
		
		//draw MainPanel	
		
		mainPanel = new JPanel();
		mainPanel.setBounds(168, 41, 611, 515);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		//changePassword
		lblNewPassword = new JLabel("New Password:");
		lblNewPassword.setBounds(238, 67, 100, 15);
		
		lblRepeatPassword = new JLabel("Repeat Password:");
		lblRepeatPassword.setBounds(220, 92, 100, 15);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(323, 67, 100, 23);
		
		repeatPasswordField = new JPasswordField();
		repeatPasswordField.setBounds(323, 92, 100, 23);
		
		btnPasswordOk = new JButton("Change");
		btnPasswordOk.setBounds(183, 139, 80, 23);
		
		btnPasswordReset = new JButton("Reset");
		btnPasswordReset.setBounds(392, 139, 80, 23);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(410, 139, 80, 23);
		mainPanel.add(btnDelete);
		
		//show and delete
		//JCombobox & JTable
		comboBox = new JComboBox<String>();
		comboBox.setBounds(59, 29, 130, 21);
		mainPanel.add(comboBox);
		drawCombobox();
		
		//JtextField

		txtCName = new JTextField();
		txtCName.setEditable(false);
		txtCName.setBounds(270, 29, 130, 21);
		txtCName.setColumns(10);
		
		txtTime = new JTextField();
		txtTime.setEditable(false);
		txtTime.setBounds(470, 57, 130, 21);
		txtTime.setColumns(10);
		
		txtTeacher = new JTextField();
		txtTeacher.setEditable(false);
		txtTeacher.setBounds(470, 29, 130, 21);
		txtTeacher.setColumns(10);
		
		txtCredit = new JTextField();
		txtCredit.setEditable(false);
		txtCredit.setBounds(59, 57, 130, 21);
		txtCredit.setColumns(10);
		
		txtClassroom = new JTextField();
		txtClassroom.setEditable(false);
		txtClassroom.setBounds(270, 57, 130, 21);
		txtClassroom.setColumns(10);
		
		txtTA = new JTextField();
		txtTA.setEditable(false);
		txtTA.setBounds(59, 85, 130, 21);
		txtTA.setColumns(10);
		
		txtUserName = new JTextField();
		txtUserName.setEditable(false);
		txtUserName.setBounds(270, 29, 130, 21);
		txtUserName.setColumns(10);
		
		//JLabels
		lblDetailInformation = new JLabel("Detail Infomation");
		lblDetailInformation.setBounds(6, 7, 189, 15);
		mainPanel.add(lblDetailInformation);
		
		lblCno = new JLabel("CNO");
		lblCno.setBounds(16, 32, 29, 15);
				
		lblCName = new JLabel("Name");
		lblCName.setBounds(207, 32, 42, 15);
		
		lblTeacher = new JLabel("Teacher");
		lblTeacher.setBounds(410, 32, 54, 15);
		
		lblCredit = new JLabel("Credit");
		lblCredit.setBounds(7, 60, 42, 15);
		
		lblClassroom = new JLabel("Classroom");
		lblClassroom.setBounds(197, 60, 80, 15);
		
		lblTime = new JLabel("Time");
		lblTime.setBounds(422, 60, 42, 15);

		lblTa = new JLabel("TA");
		lblTa.setBounds(17, 88, 42, 15);
		
		lblUserID = new JLabel("ID");
		lblUserID.setBounds(16, 32, 29, 15);
		
		lblUserName = new JLabel("Name");
		lblUserName.setBounds(207, 32, 42, 15);
		
		addCourse();
		showText();		
		
		status = 1;
		setVisible(true);
		
		comboBox.addItemListener(new ItemListener() {			
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					showText();		
				}				
			}
		});
		
		btnCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeUser();
				removePassword();
				addCourse();
				status = 1;
				drawCombobox();
				if(!isOnThePanel){
					mainPanel.add(comboBox);
					mainPanel.add(lblDetailInformation);
					isOnThePanel = true;
				}
				showText();
				repaint();
			}
		});
		
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeCourse();
				removePassword();
				addUser();
				status = 2;
				drawCombobox();
				if(!isOnThePanel){
					mainPanel.add(comboBox);
					mainPanel.add(lblDetailInformation);
					isOnThePanel = true;
				}
				showText();
				repaint();
			}
		});
		
		btnTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeCourse();
				removePassword();
				addUser();
				status = 3;
				drawCombobox();
				if(!isOnThePanel){
					mainPanel.add(comboBox);
					mainPanel.add(lblDetailInformation);
					isOnThePanel = true;
				}
				showText();
				repaint();
			}
		});
		
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeUser();
				removePassword();
				addCourse();
				status = 4;
				drawCombobox();
				if(!isOnThePanel){
					mainPanel.add(comboBox);
					mainPanel.add(lblDetailInformation);
					isOnThePanel = true;
				}
				showText();
				repaint();
			}
		});
		
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeCourse();
				removeUser();
				addPassword();
				if(isOnThePanel){
					mainPanel.remove(comboBox);
					mainPanel.remove(lblDetailInformation);
					isOnThePanel = false;
				}
				repaint();
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String stringList;
				int num = comboBox.getSelectedIndex();
				switch (status){
				case 1:
					stringList = "courselist";
					break;
				case 2:
					stringList = "studentlist";
					break;
				case 3:
					stringList = "teacherlist";
					break;
				default:
					stringList = "chooselist";
					break;
				}
				admin.delete(stringList, num, eduSystem.getUserList(), eduSystem.getCourseList());
				instruction.refresh(eduSystem.getCourseList().getCourseList(), eduSystem.getUserList());
				eduSystem.output.save(eduSystem.getUserList(), eduSystem.getCourseList(), eduSystem.getScoreList());
				drawCombobox();
			}			
		});
		
		btnPasswordOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password1 , password2;
				password1 = String.valueOf(passwordField.getPassword());
				password2 = String.valueOf(repeatPasswordField.getPassword());
				if (password1.equals("") || password2.equals("")){
					JOptionPane.showMessageDialog(null,"Please Input");
				}else{
					admin.changePassword(password1, password2, eduSystem);
					eduSystem.output.save(eduSystem.getUserList(), eduSystem.getCourseList(), eduSystem.getScoreList());
				}
			}
		});
		
		btnPasswordReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				passwordField.setText("");
				repeatPasswordField.setText("");
			}
		});
		
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					String password1 , password2;
					password1 = String.valueOf(passwordField.getPassword());
					password2 = String.valueOf(repeatPasswordField.getPassword());
					if (password1.equals("") || password2.equals("")){
						JOptionPane.showMessageDialog(null,"Please Input");
					}else{
						admin.changePassword(password1, password2, eduSystem);
						eduSystem.output.save(eduSystem.getUserList(), eduSystem.getCourseList(), eduSystem.getScoreList());
					}
				}				
			}
		});
		
		repeatPasswordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					String password1 , password2;
					password1 = String.valueOf(passwordField.getPassword());
					password2 = String.valueOf(repeatPasswordField.getPassword());
					if (password1.equals("") || password2.equals("")){
						JOptionPane.showMessageDialog(null,"Please Input");
					}else{
						admin.changePassword(password1, password2, eduSystem);
						eduSystem.output.save(eduSystem.getUserList(), eduSystem.getCourseList(), eduSystem.getScoreList());
					}
				}				
			}
		});
		
		
	}
	
	private void addCourse(){
		mainPanel.add(txtCName);
		mainPanel.add(txtTime);
		mainPanel.add(txtTeacher);
		mainPanel.add(txtCredit);
		mainPanel.add(txtClassroom);
		mainPanel.add(txtTA);
		mainPanel.add(lblCno);
		mainPanel.add(lblCName);
		mainPanel.add(lblTeacher);
		mainPanel.add(lblCredit);
		mainPanel.add(lblClassroom);
		mainPanel.add(lblTime);
		mainPanel.add(lblTa);
	}
	
	private void removeCourse(){
		mainPanel.remove(txtCName);
		mainPanel.remove(txtTime);
		mainPanel.remove(txtTeacher);
		mainPanel.remove(txtCredit);
		mainPanel.remove(txtClassroom);
		mainPanel.remove(txtTA);
		mainPanel.remove(lblCno);
		mainPanel.remove(lblCName);
		mainPanel.remove(lblTeacher);
		mainPanel.remove(lblCredit);
		mainPanel.remove(lblClassroom);
		mainPanel.remove(lblTime);
		mainPanel.remove(lblTa);
	}
	
	private void addUser(){
		mainPanel.add(lblUserID);
		mainPanel.add(lblUserName);
		mainPanel.add(txtUserName);
	}
	
	private void removeUser(){
		mainPanel.remove(lblUserID);
		mainPanel.remove(lblUserName);
		mainPanel.remove(txtUserName);
	}
	
	private void addPassword(){
		mainPanel.remove(btnDelete);
		mainPanel.add(lblNewPassword);
		mainPanel.add(lblRepeatPassword);
		mainPanel.add(passwordField);
		mainPanel.add(repeatPasswordField);
		mainPanel.add(btnPasswordOk);
		mainPanel.add(btnPasswordReset);		
	}
	
	private void removePassword(){
		mainPanel.add(btnDelete);
		mainPanel.remove(lblNewPassword);
		mainPanel.remove(lblRepeatPassword);
		mainPanel.remove(passwordField);
		mainPanel.remove(repeatPasswordField);
		mainPanel.remove(btnPasswordOk);
		mainPanel.remove(btnPasswordReset);
	}
	
	
	private void drawCombobox() {		
		comboBox.removeAllItems();
		int size,i;
		switch (status) {
		case 1:
			size = eduSystem.getCourseList().getCourseList().size();
			for (i = 0; i < size; i++){
				comboBox.addItem(eduSystem.getCourseList().getCourseList().get(i).getCourseId());
			}
			if (size != 0){
				comboBox.setSelectedIndex(0);	
			}
			break;	
		case 2:
			size = eduSystem.getUserList().getStudentList().size();
			for (i = 0; i < size; i++){
				comboBox.addItem(eduSystem.getUserList().getStudentList().get(i).getId());
			}
			if (size != 0){
				comboBox.setSelectedIndex(0);	
			}
			break;
		case 3:
			size = eduSystem.getUserList().getTeacherList().size();
			for (i = 0; i < size; i++){
				comboBox.addItem(eduSystem.getUserList().getTeacherList().get(i).getId());
			}
			if (size != 0){
				comboBox.setSelectedIndex(0);	
			}
			break;
		case 4:
			size = eduSystem.getCourseList().getCourseList().size();
			for (i = 0; i < size; i++){
				if(!(eduSystem.getCourseList().getCourseList().get(i).isCompulsory())) {
					courseList.add(eduSystem.getCourseList().getCourseList().get(i));
					comboBox.addItem((String)(eduSystem.getCourseList().getCourseList().get(i).getCourseId()));
				}
			}
			if (size != 0){
				comboBox.setSelectedIndex(0);	
			}
			break;
		default:
			break;
		}		
	}

	private void showText(){
		String string;
		switch (status) {
		case 1:
			txtCName.setText(eduSystem.getCourseList().getCourseList().get(comboBox.getSelectedIndex()).getCourseName());
			txtClassroom.setText(eduSystem.getCourseList().getCourseList().get(comboBox.getSelectedIndex()).getClassroom());
			txtCredit.setText(String.valueOf(eduSystem.getCourseList().getCourseList().get(comboBox.getSelectedIndex()).getScore()));
			txtTeacher.setText(eduSystem.getCourseList().getCourseList().get(comboBox.getSelectedIndex()).getTeacherUser().getName());
			txtTime.setText(eduSystem.getCourseList().getCourseList().get(comboBox.getSelectedIndex()).getTime());
			string = "";
			for(int i = 0 ; i < eduSystem.getCourseList().getCourseList().get(comboBox.getSelectedIndex()).getAssistStudentList().size(); i++){
				string = string + eduSystem.getCourseList().getCourseList().get(comboBox.getSelectedIndex()).getAssistStudentList().get(i).getName() + "/";
			}
			if (string.length() > 0){
				string = string.substring(0, string.length()-1);
			}else{
				string = "None";
			}		
			txtTA.setText(string);		
			break;
		case 2:
			txtUserName.setText(eduSystem.getUserList().getStudentList().get(comboBox.getSelectedIndex()).getName());
			break;
		case 3:
			txtUserName.setText(eduSystem.getUserList().getTeacherList().get(comboBox.getSelectedIndex()).getName());
			break;
		default:
			int num = comboBox.getSelectedIndex();			
			txtCName.setText(courseList.get(num).getCourseName());
			txtClassroom.setText(courseList.get(num).getClassroom());
			txtCredit.setText(String.valueOf(courseList.get(num).getScore()));
			txtTeacher.setText(courseList.get(num).getCourseName());
			txtTime.setText(courseList.get(num).getTime());
			string = "";
			for(int i = 0 ; i < courseList.get(num).getAssistStudentList().size(); i++){
				string = string + courseList.get(num).getAssistStudentList().get(i).getName() + "/";
			}
			if (string.length() > 0){
				string = string.substring(0, string.length()-1);
			}else{
				string = "None";
			}		
			txtTA.setText(string);	
			break;
		}
		
	}
}
