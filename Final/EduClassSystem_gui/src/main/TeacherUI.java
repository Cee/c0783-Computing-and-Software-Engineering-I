package main;

import instruction.Instruction;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import user.Teacher;
import course.Course;

public class TeacherUI extends JFrame {

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
	private JButton btnPublishNewCourses;
	private JButton btnUpdateCourseInfo;
	private JButton btnShowStudentList;
	private JButton btnRecordScore;

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
	
	private JButton btnPublishOk;
	private JButton btnPublishReset;	
	private JButton btnUpdateOk;
	private JButton btnUpdateReset;
	private JButton btnScore;
	
	private MainSystem eduSystem;
	private Instruction instruction;
	private Teacher teacher;
	
	public int status = 1;
	public int count = 0;

	/**
	 * Create the frame.
	 */
	public TeacherUI(Teacher user, MainSystem mainSystem, Instruction instr) {

		this.eduSystem = mainSystem;
		this.instruction = instr;
		this.teacher = user;
		
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
		
		btnPublishNewCourses = new JButton("Publish");		
		btnPublishNewCourses.setBounds(0, 45, 155, 23);
		myCoursePanel.add(btnPublishNewCourses);
		
		btnUpdateCourseInfo = new JButton("Update");
		btnUpdateCourseInfo.setBounds(0, 68, 155, 23);
		myCoursePanel.add(btnUpdateCourseInfo);
		
	
		btnShowStudentList= new JButton("Show Student");
		btnShowStudentList.setBounds(0, 91, 155, 23);
		myCoursePanel.add(btnShowStudentList);
		
		btnRecordScore= new JButton("Record Score");
		btnRecordScore.setBounds(0, 114, 155, 23);
		myCoursePanel.add(btnRecordScore);
		
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
		drawAllTable();		
		
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
		
		showText();		
		
		mainPanel.repaint();
		

		//JButtons
		
		btnPublishOk = new JButton("Publish");
		btnPublishOk.setBounds(183, 139, 80, 23);
		
		btnPublishReset = new JButton("Reset");
		btnPublishReset.setBounds(392, 139, 80, 23);
		
		btnUpdateOk = new JButton("Update");
		btnUpdateOk.setBounds(183, 139, 80, 23);
		
		btnUpdateReset = new JButton("Reset");
		btnUpdateReset.setBounds(392, 139, 80, 23);
		
		btnScore = new JButton("Record Score");
		btnScore.setBounds(183, 139, 160, 23);
		
		mainPanel.setVisible(true);
		setVisible(true);

		//Listeners
		
		btnCourseInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				mainPanel.remove(btnPublishOk);
				mainPanel.remove(btnPublishReset);
				mainPanel.remove(btnUpdateOk);
				mainPanel.remove(btnUpdateReset);
				mainPanel.remove(btnScore);
				mainPanel.remove(txtCNO);				
				mainPanel.add(comboBox);
				txtCNO.setEditable(false);
				setEdit(false);				
				comboBox.removeAllItems();
				drawCombobox();		
				status = 1;
				drawAllTable();
				mainPanel.add(scrollPane);		
				scrollPane.setVisible(true);
				showText();					
				repaint();			
			}
		});
		
		btnPublishNewCourses.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent arg0) {
				mainPanel.remove(comboBox);
				mainPanel.add(btnPublishOk);
				mainPanel.add(btnPublishReset);
				mainPanel.remove(btnUpdateOk);
				mainPanel.remove(btnUpdateReset);	
				mainPanel.remove(btnScore);
				mainPanel.add(txtCNO);
				reset();
				txtCNO.setEditable(true);				
				setEdit(true);
				txtTeacher.setEditable(false);
				txtTeacher.setText(teacher.getId());
				status = 1;
				drawAllTable();
				mainPanel.add(scrollPane);		
				scrollPane.setVisible(true);
				repaint();				
			}
		});
		
		btnUpdateCourseInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.remove(txtCNO);
				mainPanel.remove(btnPublishOk);
				mainPanel.remove(btnPublishReset);
				mainPanel.add(btnUpdateOk);
				mainPanel.add(btnUpdateReset);
				mainPanel.remove(btnScore);
				mainPanel.add(comboBox);
				txtCNO.setEditable(false);
				setEdit(true);
				txtTeacher.setEditable(false);
				txtTeacher.setText(teacher.getId());
				showText();
				status = 1;
				drawAllTable();
				mainPanel.add(scrollPane);		
				scrollPane.setVisible(true);
				repaint();
				
			}
		});
		
		btnShowStudentList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainPanel.remove(txtCNO);
				mainPanel.remove(btnPublishOk);
				mainPanel.remove(btnPublishReset);
				mainPanel.remove(btnUpdateOk);
				mainPanel.remove(btnUpdateReset);
				mainPanel.remove(btnScore);
				mainPanel.add(comboBox);
				setEdit(false);
				showText();
				status = -1;
				drawAllTable();
				mainPanel.add(scrollPane);
				scrollPane.setVisible(true);				
				repaint();
				
			}
		});
		
		btnRecordScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.remove(txtCNO);
				mainPanel.remove(btnPublishOk);
				mainPanel.remove(btnPublishReset);
				mainPanel.remove(btnUpdateOk);
				mainPanel.remove(btnUpdateReset);
				mainPanel.add(btnScore);
				mainPanel.add(comboBox);
				setEdit(false);
				showText();
				status = -1;
				drawAllTable();
				mainPanel.add(scrollPane);
				scrollPane.setVisible(true);				
				repaint();				
			}
		});
		
		comboBox.addItemListener(new ItemListener() {			
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					showText();		
					drawAllTable();
				}				
			}
		});

		btnPublishOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String send = null;
				send = txtCNO.getText() + "/";
				send = send + txtCName.getText() + "/";
				send = send + "false/";
				send = send + "0/";
				send = send + txtCredit.getText() + "/";
				send = send + txtTime.getText() + "/";
				send = send + txtClassroom.getText() + "/";				
				send = send + txtTA.getText();				
				teacher.publish(send, eduSystem.getCourseList(), eduSystem.getScoreList());
				instruction.refresh(eduSystem.getCourseList().getCourseList(), eduSystem.getUserList());
				eduSystem.output.save(eduSystem.getUserList(), eduSystem.getCourseList(), eduSystem.getScoreList());
				drawCombobox();
				drawAllTable();
			}
		});
		
		btnPublishReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reset();
				txtTeacher.setEditable(false);
				txtTeacher.setText(teacher.getId());
			}
		});
		
		btnUpdateOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String send = null;
				send = (String)(comboBox.getSelectedItem()) + "/";
				send = send + txtCName.getText() + "/";
				send = send + "false/";
				send = send + "0/";
				send = send + txtCredit.getText() + "/";
				send = send + txtTime.getText() + "/";
				send = send + txtClassroom.getText() + "/";				
				send = send + txtTA.getText();	
				teacher.updateCourse(send, eduSystem.getCourseList());
				instruction.refresh(eduSystem.getCourseList().getCourseList(), eduSystem.getUserList());
				eduSystem.output.save(eduSystem.getUserList(), eduSystem.getCourseList(), eduSystem.getScoreList());
				drawCombobox();
				drawAllTable();
			}
		});
		
	
		btnUpdateReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showText();
				txtTeacher.setText(teacher.getId());
			}
		});
		
		btnScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String send = (String)(comboBox.getSelectedItem());
				teacher.recordScore(send, eduSystem.getScoreList());
				eduSystem.output.save(eduSystem.getUserList(), eduSystem.getCourseList(), eduSystem.getScoreList());
			}
		});
		

				
	
	}
	
	private void reset(){
		txtCNO.setText("");
		txtCName.setText("");
		txtTime.setText("");
		txtTeacher.setText("");
		txtCredit.setText("");
		txtClassroom.setText("");
		txtTA.setText("");
	}

	private void setEdit(boolean bool){
		txtCName.setEditable(bool);
		txtTime.setEditable(bool);
		txtTeacher.setEditable(bool);
		txtCredit.setEditable(bool);
		txtClassroom.setEditable(bool);
		txtTA.setEditable(bool);
	}
	
	private void drawCombobox() {		
		comboBox.removeAllItems();
		int size = eduSystem.getCourseList().getCourseList().size();
		for (int i = 0; i < size; i++){
			comboBox.addItem(eduSystem.getCourseList().getCourseList().get(i).getCourseId());
		}
		
		if (size != 0)
			comboBox.setSelectedIndex(0);			
	}

	private void drawAllTable() {
		
		if (status == 1){
			if (count != 0){
				mainPanel.remove(scrollPane);
				scrollPane.removeAll();
			}else{
				count = 1;
			}
			
			
			int size = eduSystem.getCourseList().getCourseList().size();
			int count = 0;
			String[] columnNames = {"Id","Name","Teacher","Credit","Classroom","Time"};
			for (int i = 0; i < size; i++){
				Course newCourse = eduSystem.getCourseList().getCourseList().get(i);
				if (newCourse.getTeacherUser().getId().equals(teacher.getId())){
					count++;
				}
			}
			String[][] rowData = new String[count][6];
			for (int i = 0; i < count; i++){
				Course newCourse = eduSystem.getCourseList().getCourseList().get(i);
				if (newCourse.getTeacherUser().getId().equals(teacher.getId())){
					rowData[i][0] = newCourse.getCourseId();
					rowData[i][1] = newCourse.getCourseName();
					rowData[i][2] = newCourse.getTeacherUser().getName();
					rowData[i][3] = String.valueOf(newCourse.getScore());
					rowData[i][4] = newCourse.getClassroom();
					rowData[i][5] = newCourse.getTime();
				}				
			
			}				
			
			table = new JTable(new DefaultTableModel(rowData, columnNames));
			table.repaint();
						
		}else{
			String courseID = String.valueOf(comboBox.getSelectedItem());
			
			mainPanel.remove(scrollPane);
			scrollPane.removeAll();
			
			int size = eduSystem.getScoreList().getCourseScoreList().size();
			String[] columnNames = {"Student ID","Score"};
			int i;
			for (i = 0; i < size; i++){
				if (eduSystem.getScoreList().getCourseScoreList().get(i).getCourseId().equals(courseID)){
					break;
				}
			}
			int count = eduSystem.getScoreList().getCourseScoreList().get(i).getStudentNum();
			String[][] rowData = new String[count][2];
			for (int j = 0; j < count; j++){
				rowData[j][0] = eduSystem.getScoreList().getCourseScoreList().get(i).getStudentList().get(j);
				rowData[j][1] = String.valueOf(eduSystem.getScoreList().getCourseScoreList().get(i).getScore().get(j));
			}		
			
			table = new JTable(new DefaultTableModel(rowData, columnNames));
			table.repaint();
		}
		
				
		scrollPane = new JScrollPane(table);
		scrollPane.setViewportView(table);
		scrollPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setBounds(19, 200, 580, 300);
		scrollPane.repaint();
		mainPanel.add(scrollPane);	
			
		
	}

	
	private void showText(){
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
