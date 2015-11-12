package main;

import instruction.Instruction;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import course.Course;
import user.Student;

public class StudentUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	private JPanel headPanel;
	private JLabel welcome;
	
	private JPanel myCoursePanel;
	private JLabel myCourseLable;
	
	
	private JButton btnCourseInfo;
	private JButton btnSelectCourse;
	private JButton btnQuitCourse;

	private JPanel mainPanel;
	
	private JComboBox<String> comboBox;
	private JTable table;
	private JScrollPane scrollPane;
	
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
	
	private JButton btnSelectOk;
	private JButton btnSelectReset;	
	private JButton btnQuitOk;
	private JButton btnQuitReset;
	
	private MainSystem eduSystem;
	private Instruction instruction;
	private Student student;

	private int state = 1;//my course;
	private int count = 0;

	/**
	 * Create the frame.
	 */
	public StudentUI(Student user, MainSystem mainSystem, Instruction instr) {
		
		this.eduSystem = mainSystem;
		this.instruction = instr;
		this.student = user;
		
		
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
		
		myCourseLable = new JLabel("My Course");
		myCourseLable.setBounds(40, 0, 82, 22);
		myCourseLable.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		myCoursePanel.add(myCourseLable);
		
		btnCourseInfo = new JButton("Course Info");
		btnCourseInfo.setBounds(0, 21, 155, 23);
		myCoursePanel.add(btnCourseInfo);
		
		btnSelectCourse = new JButton("Select Course");		
		btnSelectCourse.setBounds(0, 45, 155, 23);
		myCoursePanel.add(btnSelectCourse);
		
		btnQuitCourse = new JButton("Quit Course");
		btnQuitCourse.setBounds(0, 68, 155, 23);
		myCoursePanel.add(btnQuitCourse);
		
		//draw MainPanel	
		
		mainPanel = new JPanel();
		mainPanel.setBounds(168, 41, 611, 515);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		//JCombobox & JTable
		comboBox = new JComboBox<String>();
		comboBox.setBounds(59, 29, 130, 21);
		mainPanel.add(comboBox);	
		
		drawCombobox();
		drawTable();		
		
		//JtextField
		txtCNO = new JTextField();
		txtCNO.setEditable(false);
		txtCNO.setBounds(59, 29, 130, 21);
		txtCNO.setColumns(10);
		
		txtCName = new JTextField();
		txtCName.setEditable(false);
		txtCName.setBounds(270, 29, 130, 21);
		mainPanel.add(txtCName);
		txtCName.setColumns(10);
		
		txtTime = new JTextField();
		txtTime.setEditable(false);
		txtTime.setBounds(470, 57, 130, 21);
		mainPanel.add(txtTime);
		txtTime.setColumns(10);
		
		txtTeacher = new JTextField();
		txtTeacher.setEditable(false);
		txtTeacher.setBounds(470, 29, 130, 21);
		mainPanel.add(txtTeacher);
		txtTeacher.setColumns(10);
		
		txtCredit = new JTextField();
		txtCredit.setEditable(false);
		txtCredit.setBounds(59, 57, 130, 21);
		mainPanel.add(txtCredit);
		txtCredit.setColumns(10);
		
		txtClassroom = new JTextField();
		txtClassroom.setEditable(false);
		txtClassroom.setBounds(270, 57, 130, 21);
		mainPanel.add(txtClassroom);
		txtClassroom.setColumns(10);
		
		txtTA = new JTextField();
		txtTA.setEditable(false);
		txtTA.setBounds(59, 85, 130, 21);
		mainPanel.add(txtTA);
		txtTA.setColumns(10);
		
		//JLabels
		lblDetailInformation = new JLabel("Detail Infomation");
		lblDetailInformation.setBounds(6, 7, 189, 15);
		mainPanel.add(lblDetailInformation);
		
		lblCno = new JLabel("CNO");
		lblCno.setBounds(16, 32, 29, 15);
		mainPanel.add(lblCno);
		
		lblCName = new JLabel("Name");
		lblCName.setBounds(207, 32, 42, 15);
		mainPanel.add(lblCName);
		
		lblTeacher = new JLabel("Teacher");
		lblTeacher.setBounds(410, 32, 54, 15);
		mainPanel.add(lblTeacher);
		
		lblCredit = new JLabel("Credit");
		lblCredit.setBounds(7, 60, 42, 15);
		mainPanel.add(lblCredit);
		
		lblClassroom = new JLabel("Classroom");
		lblClassroom.setBounds(197, 60, 80, 15);
		mainPanel.add(lblClassroom);
		
		lblTime = new JLabel("Time");
		lblTime.setBounds(422, 60, 42, 15);
		mainPanel.add(lblTime);
		
		lblTa = new JLabel("TA");
		lblTa.setBounds(17, 88, 42, 15);
		mainPanel.add(lblTa);
		
		setEdit(false);
		showMyCourse();		
		
		mainPanel.repaint();
		
		setVisible(true);
		
		//JButtons
		
		btnSelectOk = new JButton("Select");
		btnSelectOk.setBounds(183, 139, 80, 23);
		
		btnSelectReset = new JButton("Reset");
		btnSelectReset.setBounds(392, 139, 80, 23);
		
		btnQuitOk = new JButton("Quit");
		btnQuitOk.setBounds(183, 139, 80, 23);
		
		btnQuitReset = new JButton("Reset");
		btnQuitReset.setBounds(392, 139, 80, 23);
		
		//Listeners
		btnCourseInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainPanel.remove(btnSelectOk);
				mainPanel.remove(btnSelectReset);
				mainPanel.remove(btnQuitOk);
				mainPanel.remove(btnQuitReset);
				state = 1;
				drawCombobox();
				showMyCourse();
				repaint();
			}
		});
		
		btnSelectCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.add(btnSelectOk);
				mainPanel.add(btnSelectReset);
				mainPanel.remove(btnQuitOk);
				mainPanel.remove(btnQuitReset);
				state = -1;
				drawAllCombobox();
				showAllCourse();
				repaint();
			}
		});
		
		btnQuitCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.remove(btnSelectOk);
				mainPanel.remove(btnSelectReset);
				mainPanel.add(btnQuitOk);
				mainPanel.add(btnQuitReset);
				state = 1;
				drawCombobox();
				showMyCourse();
				repaint();
			}
		});
		
		comboBox.addItemListener(new ItemListener() {			
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					if (state == 1)
						showMyCourse();
					else 
						showAllCourse();
				}				
			}
		});
		
		btnSelectOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String string = (String)(comboBox.getSelectedItem());
				student.selectCourse(string,eduSystem.getScoreList(), eduSystem.getCourseList());
				instruction.refresh(eduSystem.getCourseList().getCourseList(), eduSystem.getUserList());
				eduSystem.output.save(eduSystem.getUserList(), eduSystem.getCourseList(), eduSystem.getScoreList());
				drawCombobox();
				showAllCourse();
				drawTable();
				repaint();
			}
		});
		
		btnSelectReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setSelectedIndex(0);
			}
		});
		
		btnQuitOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String string = (String)(comboBox.getSelectedItem());
				student.quitCourse(string, eduSystem.getScoreList(), eduSystem.getCourseList());
				instruction.refresh(eduSystem.getCourseList().getCourseList(), eduSystem.getUserList());
				eduSystem.output.save(eduSystem.getUserList(), eduSystem.getCourseList(), eduSystem.getScoreList());
				drawCombobox();
				showMyCourse();
				drawTable();
				repaint();
			}
		});
		
		btnQuitReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setSelectedIndex(0);
			}
		});
		
	}
	
	private void setEdit(boolean bool){
		txtCName.setEditable(bool);
		txtTime.setEditable(bool);
		txtTeacher.setEditable(bool);
		txtCredit.setEditable(bool);
		txtClassroom.setEditable(bool);
		txtTA.setEditable(bool);
	}
	
	private void drawTable() {
		
		if (count != 0){
			mainPanel.remove(scrollPane);
			scrollPane.removeAll();
		}else{
			count = 1;
		}
		
		int size = eduSystem.getScoreList().getStudentScoreList().size();
		int i;
		for (i = 0; i < size; i++){
			if (eduSystem.getScoreList().getStudentScoreList().get(i).getStudentId().equals(this.student.getId())){
				break;
			}
		}		
		ArrayList<Course> list = new ArrayList<Course>();
		ArrayList<String> score = new ArrayList<String>();
		for (int j = 0; j < eduSystem.getScoreList().getStudentScoreList().get(i)
				.getCourseNum(); j++) {
			for (int k = 0; k < eduSystem.getCourseList().getCourseList().size(); k++) {
				if (eduSystem.getCourseList()
						.getCourseList()
						.get(k)
						.getCourseId()
						.equals(eduSystem.getScoreList().getStudentScoreList().get(i)
								.getCourseList().get(j))) {
					list.add(eduSystem.getCourseList().getCourseList().get(k));
					score.add(String.valueOf(eduSystem.getScoreList().getStudentScoreList().get(i).getScore().get(j)));
					break;
				}
			}
		}		
		size = list.size();
		String[] columnNames = {"Id","Name","Teacher","Credit","Classroom","Time","Score"};
		String[][] rowData = new String[size][7];
		for (int j = 0; j < size; j++){
			Course newCourse = list.get(j);
			rowData[j][0] = newCourse.getCourseId();
			rowData[j][1] = newCourse.getCourseName();
			rowData[j][2] = newCourse.getTeacherUser().getName();
			rowData[j][3] = String.valueOf(newCourse.getScore());
			rowData[j][4] = newCourse.getClassroom();
			rowData[j][5] = newCourse.getTime();
			rowData[j][6] = score.get(j);
		}			
		table = new JTable(new DefaultTableModel(rowData, columnNames));	
		table.repaint();
		scrollPane = new JScrollPane(table);
		scrollPane.setViewportView(table);
		scrollPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setBounds(19, 200, 580, 300);
		scrollPane.repaint();
		mainPanel.add(scrollPane);		
	}
	
	private void drawCombobox() {
		comboBox.removeAllItems();
		int size = eduSystem.getScoreList().getStudentScoreList().size();
		int i;
		for (i = 0; i < size; i++){
			if (eduSystem.getScoreList().getStudentScoreList().get(i).getStudentId().equals(this.student.getId())){
				break;
			}
		}		
		ArrayList<Course> list = new ArrayList<Course>();
		ArrayList<String> score = new ArrayList<String>();
		for (int j = 0; j < eduSystem.getScoreList().getStudentScoreList().get(i)
				.getCourseNum(); j++) {
			for (int k = 0; k < eduSystem.getCourseList().getCourseList().size(); k++) {
				if (eduSystem.getCourseList()
						.getCourseList()
						.get(k)
						.getCourseId()
						.equals(eduSystem.getScoreList().getStudentScoreList().get(i)
								.getCourseList().get(j))) {
					list.add(eduSystem.getCourseList().getCourseList().get(k));
					score.add(String.valueOf(eduSystem.getScoreList().getStudentScoreList().get(i).getScore().get(j)));
					break;
				}
			}
		}		
		size = list.size();
		for (i = 0; i < size; i++){
			comboBox.addItem(list.get(i).getCourseId());
		}		
		if (size != 0)
			comboBox.setSelectedIndex(0);			
	}

	private void showMyCourse(){			
		int size = eduSystem.getScoreList().getStudentScoreList().size();
		int i;
		for (i = 0; i < size; i++){
			if (eduSystem.getScoreList().getStudentScoreList().get(i).getStudentId().equals(this.student.getId())){
				break;
			}
		}		
		ArrayList<Course> list = new ArrayList<Course>();
		ArrayList<String> score = new ArrayList<String>();
		for (int j = 0; j < eduSystem.getScoreList().getStudentScoreList().get(i)
				.getCourseNum(); j++) {
			for (int k = 0; k < eduSystem.getCourseList().getCourseList().size(); k++) {
				if (eduSystem.getCourseList()
						.getCourseList()
						.get(k)
						.getCourseId()
						.equals(eduSystem.getScoreList().getStudentScoreList().get(i)
								.getCourseList().get(j))) {
					list.add(eduSystem.getCourseList().getCourseList().get(k));
					score.add(String.valueOf(eduSystem.getScoreList().getStudentScoreList().get(i).getScore().get(j)));
					break;
				}
			}
		}
		
		Course newCourse = list.get(comboBox.getSelectedIndex());
		txtCName.setText(newCourse.getCourseName());
		txtClassroom.setText(newCourse.getClassroom());
		txtCredit.setText(String.valueOf(newCourse.getScore()));
		txtTeacher.setText(newCourse.getTeacherUser().getName());
		txtTime.setText(newCourse.getTime());
		
		String string = "";
		
		for(i = 0 ; i < newCourse.getAssistStudentList().size(); i++){
			string = string + newCourse.getAssistStudentList().get(i).getName() + "/";
		}
		if (string.length() > 0){
			string = string.substring(0, string.length()-1);
		}else{
			string = "None";
		}		
		txtTA.setText(string);		
	}
	
	private void drawAllCombobox() {	
		comboBox.removeAllItems();
		int size = eduSystem.getCourseList().getCourseList().size();
		for (int i = 0; i < size; i++){
			comboBox.addItem(eduSystem.getCourseList().getCourseList().get(i).getCourseId());
		}
		
		if (size != 0)
			comboBox.setSelectedIndex(0);			
	}
	
	private void showAllCourse(){			
		txtCName.setText(eduSystem.getCourseList().getCourseList().get(comboBox.getSelectedIndex()).getCourseName());
		txtClassroom.setText(eduSystem.getCourseList().getCourseList().get(comboBox.getSelectedIndex()).getClassroom());
		txtCredit.setText(String.valueOf(eduSystem.getCourseList().getCourseList().get(comboBox.getSelectedIndex()).getScore()));
		txtTeacher.setText(eduSystem.getCourseList().getCourseList().get(comboBox.getSelectedIndex()).getTeacherUser().getName());
		txtTime.setText(eduSystem.getCourseList().getCourseList().get(comboBox.getSelectedIndex()).getTime());
		String string = "";
		for(int i = 0 ; i < eduSystem.getCourseList().getCourseList().get(comboBox.getSelectedIndex()).getAssistStudentList().size(); i++){
			string = string + eduSystem.getCourseList().getCourseList().get(comboBox.getSelectedIndex()).getAssistStudentList().get(i).getName() + "/";
		}
		if (string.length() > 0){
			string = string.substring(0, string.length()-1);
		}else{
			string = "None";
		}		
		txtTA.setText(string);		
	}
	
}
