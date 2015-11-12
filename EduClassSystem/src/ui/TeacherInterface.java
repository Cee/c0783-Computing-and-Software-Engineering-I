package ui;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.SwingConstants;

import javax.swing.UIManager;
import java.awt.Color;

import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;

import org.omg.CORBA.PRIVATE_MEMBER;

import user.Student;
import user.Teacher;
import user.User;
import user.UserList;
import course.Course;
import course.CourseList;
import course.ScoreList;

import net.ClientHelper;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.Serializable;
import java.util.ArrayList;


public class TeacherInterface extends JFrame {

	
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
	private JButton btnModifyCourseInfo;
	private JButton btnDeleteCourseInfo;
	private JButton btnShowStudentList;
	private JButton btnRecordScore;
	
	private JPanel mainPanel;
	private JLabel lblDetailInformation;
	
	private JComboBox<String> comboBox;
	
	private JTextField txtTime;
	private JTextField txtTeacher;
	private JTextField txtClassroom;
	private JTextField txtCName;
	private JTextField txtTA;
	private JTextField txtCredit;
	private JTextField txtCNO;
	
	private JLabel lblCno;		
	private JLabel lblCName;
	private JLabel lblTeacher;
	private JLabel lblCredit;		
	private JLabel lblClassroom;		
	private JLabel lblTime;		
	private JLabel lblTa;
	
//	private JButton btnOk;
//	private JButton btnReset;
	
	private JButton btnPublishOk;
	private JButton btnPublishReset;	
	private JButton btnDeleteOk;
	private JButton btnDeleteReset;
	private JButton btnModifyOk;
	private JButton btnModifyReset;
	
	
	private JTable table;
	private JScrollPane scrollPane;
	
	private ClientHelper cHelper;
		
	String indexString;
	
	UserList userList;
	CourseList courseList;
	ScoreList scoreList;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherInterface frame = new TeacherInterface();
					frame.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public TeacherInterface(){
		
	}
	
//	@SuppressWarnings({ "rawtypes" })
	public TeacherInterface(ClientHelper ch,String name,String numberString) {
		
		this.cHelper = ch;
		this.indexString = numberString;
		this.courseList = (CourseList)cHelper.getObject("getCourseList");
	
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		@SuppressWarnings("unchecked")
//		ArrayList<Serializable> rawList = (ArrayList<Serializable>) cHelper.getObject("getList");
//		this.userList = (UserList)rawList.get(0);
//		this.scoreList = (ScoreList)rawList.get(2);
		
		
		
		
		
		setType(Type.UTILITY);
		
//		setAlwaysOnTop(true);
		setTitle("\u9009\u8BFE\u7CFB\u7EDF");
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
		
		welcome = new JLabel("欢迎,"+name);
		welcome.setBounds(0, 0, 180, 33);
		welcome.setHorizontalAlignment(SwingConstants.CENTER);
		welcome.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		headPanel.add(welcome);
		
		//draw myCoursePanel
		myCoursePanel = new JPanel();
		myCoursePanel.setBounds(5, 41, 162, 515);
		myCoursePanel.setBorder(new MatteBorder(0, 0, 0, 3, (Color) new Color(0, 0, 0)));
		contentPane.add(myCoursePanel);
		myCoursePanel.setLayout(null);
		
		myCourseLable = new JLabel("课程管理");
		myCourseLable.setBounds(45, 0, 82, 22);
		myCourseLable.setBackground(UIManager.getColor("Button.background"));
		myCourseLable.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		myCoursePanel.add(myCourseLable);
		
		btnCourseInfo = new JButton("课程信息");
		btnCourseInfo.setBounds(0, 21, 155, 23);
		myCoursePanel.add(btnCourseInfo);
		
		btnPublishNewCourses = new JButton("发布课程");		
		btnPublishNewCourses.setBounds(0, 45, 155, 23);
		myCoursePanel.add(btnPublishNewCourses);
		
		btnModifyCourseInfo = new JButton("修改课程信息");
		btnModifyCourseInfo.setBounds(0, 68, 155, 23);
		myCoursePanel.add(btnModifyCourseInfo);
		
		btnDeleteCourseInfo = new JButton("删除课程");
		btnDeleteCourseInfo.setBounds(0, 91, 155, 23);
		myCoursePanel.add(btnDeleteCourseInfo);
		
		btnShowStudentList= new JButton("查看选课学生");
		btnShowStudentList.setBounds(0, 114, 155, 23);
		myCoursePanel.add(btnShowStudentList);
		
		btnRecordScore= new JButton("登记成绩");
		btnRecordScore.setBounds(0, 137, 155, 23);
		myCoursePanel.add(btnRecordScore);
		
		//draw Panels
		
		
		mainPanel = new JPanel();
		mainPanel.setBounds(168, 41, 611, 515);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		
		//Jcombobox
		comboBox = new JComboBox<String>();
		comboBox.setBounds(59, 29, 130, 21);
		mainPanel.add(comboBox);		
		
		int size = courseList.getCourseList().size();
		for (int i = 0; i < size; i++){
			comboBox.addItem(courseList.getCourseList().get(i).getCourseId());
		}
		
		if (size != 0)
			comboBox.setSelectedIndex(0);		
		
		//Jtable
		String[] columnNames = {"课程编号","课程名","授课老师","学分","教室","时间"};
		String[][] rowData = new String[size][6];
		for (int i = 0; i < size; i++){
			Course newCourse = courseList.getCourseList().get(i);
			rowData[i][0] = newCourse.getCourseId();
			rowData[i][1] = newCourse.getCourseName();
			rowData[i][2] = newCourse.getTeacherUser().getName();
			rowData[i][3] = String.valueOf(newCourse.getScore());
			rowData[i][4] = newCourse.getClassroom();
			rowData[i][5] = newCourse.getTime();
		}
		
		
		table = new JTable(new DefaultTableModel(rowData, columnNames));
		
		
		scrollPane = new JScrollPane(table);
		scrollPane.setViewportView(table);
		scrollPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setBounds(19, 200, 580, 300);
		mainPanel.add(scrollPane);
		
		
		//Jtext
		lblDetailInformation = new JLabel("详细信息");
		lblDetailInformation.setBounds(6, 7, 189, 15);
		mainPanel.add(lblDetailInformation);
		
		txtCNO = new JTextField();
		txtCNO.setEditable(false);
		txtCNO.setBounds(59, 29, 130, 21);
//		mainPanel.add(txtCNO);
		txtCNO.setColumns(10);
		
		txtCName = new JTextField();
		txtCName.setEditable(false);
//		txtCName.setText(courseList.getCourseList().get(comboBox.getSelectedIndex()).getCourseName());
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
		
		lblCno = new JLabel("课程编号");
		lblCno.setBounds(6, 32, 62, 15);
		mainPanel.add(lblCno);
		
		lblCName = new JLabel("课程名");
		lblCName.setBounds(207, 32, 42, 15);
		mainPanel.add(lblCName);
		
		lblTeacher = new JLabel("授课老师");
		lblTeacher.setBounds(411, 32, 54, 15);
		mainPanel.add(lblTeacher);
		
		lblCredit = new JLabel("学分");
		lblCredit.setBounds(18, 60, 42, 15);
		mainPanel.add(lblCredit);
		
		lblClassroom = new JLabel("教室");
		lblClassroom.setBounds(214, 60, 80, 15);
		mainPanel.add(lblClassroom);
		
		lblTime = new JLabel("时间");
		lblTime.setBounds(422, 60, 42, 15);
		mainPanel.add(lblTime);
		
		lblTa = new JLabel("助教");
		lblTa.setBounds(17, 88, 42, 15);
		mainPanel.add(lblTa);
		
		mainPanel.repaint();
		

		
//		btnOk = new JButton("确认");
//		btnOk.setBounds(183, 139, 80, 23);
//		mainPanel.add(btnOk);
//		
//		btnReset = new JButton("重置");
//		btnReset.setBounds(392, 139, 80, 23);
//		mainPanel.add(btnReset);
		
		btnPublishOk = new JButton("确认");
		btnPublishOk.setBounds(183, 139, 80, 23);
//		mainPanel.add(btnPublishOk);
		
		btnPublishReset = new JButton("重置");
		btnPublishReset.setBounds(392, 139, 80, 23);
//		mainPanel.add(btnPublishReset);
		
		btnDeleteOk = new JButton("确认");
		btnDeleteOk.setBounds(183, 139, 80, 23);
//		mainPanel.add(btnDeleteOk);
		
		btnDeleteReset = new JButton("重置");
		btnDeleteReset.setBounds(392, 139, 80, 23);
//		mainPanel.add(btnDeleteReset);
		
		btnModifyOk = new JButton("确认");
		btnModifyOk.setBounds(183, 139, 80, 23);
//		mainPanel.add(btnModifyOk);
		
		btnModifyReset = new JButton("重置");
		btnModifyReset.setBounds(392, 139, 80, 23);
//		mainPanel.add(btnModifyReset);
		
		setVisible(true);
		repaint();
		mainPanel.repaint();
		
	
		//listener
		
		btnCourseInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				mainPanel.remove(btnDeleteOk);
				mainPanel.remove(btnDeleteReset);
				mainPanel.remove(btnPublishOk);
				mainPanel.remove(btnPublishReset);
				mainPanel.remove(btnModifyOk);
				mainPanel.remove(btnModifyReset);
				mainPanel.remove(txtCNO);
				mainPanel.add(comboBox);
				txtCNO.setEditable(false);
				txtCName.setEditable(false);
				txtTime.setEditable(false);
				txtTeacher.setEditable(false);
				txtCredit.setEditable(false);
				txtClassroom.setEditable(false);
				txtTA.setEditable(false);
				
				comboBox.removeAllItems();
				courseList = (CourseList)cHelper.getObject("getCourseList");
				int size = courseList.getCourseList().size();
				for (int i = 0; i < size; i++){
					comboBox.addItem(courseList.getCourseList().get(i).getCourseId());
				}
				
				if (size != 0)
					comboBox.setSelectedIndex(0);		
				
				//Jtable
				String[] columnNames = {"课程编号","课程名","授课老师","学分","教室","时间"};
				String[][] rowData = new String[size][6];
				for (int i = 0; i < size; i++){
					Course newCourse = courseList.getCourseList().get(i);
					rowData[i][0] = newCourse.getCourseId();
					rowData[i][1] = newCourse.getCourseName();
					rowData[i][2] = newCourse.getTeacherUser().getName();
					rowData[i][3] = String.valueOf(newCourse.getScore());
					rowData[i][4] = newCourse.getClassroom();
					rowData[i][5] = newCourse.getTime();
				}
				
				
				table = new JTable(new DefaultTableModel(rowData, columnNames));
				
				scrollPane = new JScrollPane(table);
				scrollPane.setViewportView(table);
				scrollPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
				scrollPane.setBounds(19, 200, 580, 300);
							
				repaint();			
			}
		});
		
		
		btnPublishNewCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.remove(comboBox);
				mainPanel.remove(btnDeleteOk);
				mainPanel.remove(btnDeleteReset);				
				mainPanel.add(btnPublishOk);
				mainPanel.add(btnPublishReset);
				mainPanel.remove(btnModifyOk);
				mainPanel.remove(btnModifyReset);				
				mainPanel.add(txtCNO);
				txtCNO.setEditable(true);
				txtCNO.setText("");
				txtCName.setEditable(true);
				txtCName.setText("");
				txtTime.setEditable(true);
				txtTime.setText("");
				txtTeacher.setEditable(true);
				txtTeacher.setText("");
				txtCredit.setEditable(true);
				txtCredit.setText("");
				txtClassroom.setEditable(true);
				txtClassroom.setText("");
				txtTA.setEditable(true);
				txtTA.setText("");
				repaint();
			}
		});
		
		btnPublishOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component temporaryLostComponent = null;
				if (txtCNO.getText().equals("") || txtCNO.getText().equals(null)){
					JOptionPane.showMessageDialog(temporaryLostComponent, this, "Please Input", 1);	
				}else{
					boolean isExist = false;
					for (int i = 0; i < courseList.getCourseList().size(); i++) {					
						if (courseList.getCourseList().get(i).getCourseId().equals(txtCNO.getText())){
							isExist = true;
							break;
						}										
					}
					if (isExist){
						JOptionPane.showMessageDialog(temporaryLostComponent, this, "CNO Exist", 1);					
					}else{

						Course newCourse = new Course();
						newCourse.setCourseId(txtCNO.getText());
						newCourse.setCourseName(txtCName.getText());
						newCourse.setCompulsory(false);
						/**课时问题
						 * 
						 */
						newCourse.setPeriod(2);
						newCourse.setScore(Integer.parseInt(txtCredit.getText()));
						User newTeacher = new Teacher();
						newTeacher.setId(indexString);
						newTeacher.setType("teacher");
						newTeacher.setPassword(null);
						newCourse.setTeacherUser(newTeacher);

						newCourse.setTime(txtTime.getText());
						newCourse.setClassroom(txtClassroom.getText());


						ArrayList<User> list = new ArrayList<User>();
						String userNameString = txtTA.getText();
						String[] splitStrings = userNameString.split("/");
						int count = splitStrings.length;
						while (count > 0) {
							count--;
							Student newStudent = new Student();
							newStudent.setId(splitStrings[count]);
							newStudent.setType("student");
							newStudent.setPassword(null);
							list.add(newStudent);
						}
						newCourse.setAssistStudentList(list);
						courseList.getCourseList().add(newCourse);
						System.out.println(courseList.getCourseList().size());
						
//						comboBox.removeAllItems();
						int size = courseList.getCourseList().size();
//						for (int i = 0; i < size; i++){
//							comboBox.addItem(courseList.getCourseList().get(i).getCourseId());
//						}
//						
//						if (size != 0)
//							comboBox.setSelectedIndex(0);	
//						
//						comboBox.repaint();
						
						cHelper.sendToNet("sendCourseList");
						ArrayList<Serializable> rawList = new ArrayList<Serializable>();
						rawList.add(courseList);
						cHelper.listToNet(rawList);
						
						mainPanel.remove(scrollPane);		
						table.setVisible(false);
						
						//Jtable
						String[] columnNames = {"课程编号","课程名","授课老师","学分","教室","时间"};
						String[][] rowData = new String[size][6];
						for (int i = 0; i < size; i++){
							newCourse = courseList.getCourseList().get(i);
							rowData[i][0] = newCourse.getCourseId();
							rowData[i][1] = newCourse.getCourseName();
							rowData[i][2] = newCourse.getTeacherUser().getName();
							rowData[i][3] = String.valueOf(newCourse.getScore());
							rowData[i][4] = newCourse.getClassroom();
							rowData[i][5] = newCourse.getTime();
						}
						
						
						table = new JTable(new DefaultTableModel(rowData, columnNames));
						
						scrollPane = new JScrollPane(table);
						scrollPane.setViewportView(table);
						scrollPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
						scrollPane.setBounds(19, 200, 580, 300);
						
						table.setVisible(true);
						mainPanel.add(scrollPane);
						
						txtCNO.setEditable(true);
						txtCNO.setText("");
						txtCName.setEditable(true);
						txtCName.setText("");
						txtTime.setEditable(true);
						txtTime.setText("");
						txtTeacher.setEditable(true);
						txtTeacher.setText("");
						txtCredit.setEditable(true);
						txtCredit.setText("");
						txtClassroom.setEditable(true);
						txtClassroom.setText("");
						txtTA.setEditable(true);
						txtTA.setText("");
						

						repaint();			
					
					}
				}		
				
			}
		});
		
		btnPublishReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCNO.setEditable(true);
				txtCNO.setText("");
				txtCName.setEditable(true);
				txtCName.setText("");
				txtTime.setEditable(true);
				txtTime.setText("");
				txtTeacher.setEditable(true);
				txtTeacher.setText("");
				txtCredit.setEditable(true);
				txtCredit.setText("");
				txtClassroom.setEditable(true);
				txtClassroom.setText("");
				txtTA.setEditable(true);
				txtTA.setText("");
				repaint();				
			}
		});
		
		btnModifyCourseInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				mainPanel.remove(txtCNO);
				mainPanel.remove(btnDeleteOk);
				mainPanel.remove(btnDeleteReset);
				mainPanel.remove(btnPublishOk);
				mainPanel.remove(btnPublishReset);
				mainPanel.add(btnModifyOk);
				mainPanel.add(btnModifyReset);
				mainPanel.add(comboBox);
				txtCNO.setEditable(false);
				txtCName.setEditable(true);
				txtTime.setEditable(true);
				txtTeacher.setEditable(true);
				txtCredit.setEditable(true);
				txtClassroom.setEditable(true);
				txtTA.setEditable(true);
				repaint();
			}
		});
		
		btnModifyOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				courseList.getCourseList().remove(comboBox.getSelectedIndex());
				Course newCourse = new Course();
				newCourse.setCourseId((String)comboBox.getSelectedItem());
				newCourse.setCourseName(txtCName.getText());
				newCourse.setCompulsory(false);
				/**课时问题
				 * 
				 */
				newCourse.setPeriod(2);
				newCourse.setScore(Integer.parseInt(txtCredit.getText()));
				User newTeacher = new Teacher();
				newTeacher.setId(indexString);
				newTeacher.setType("teacher");
				newTeacher.setPassword(null);
				newCourse.setTeacherUser(newTeacher);

				newCourse.setTime(txtTime.getText());
				newCourse.setClassroom(txtClassroom.getText());


				ArrayList<User> list = new ArrayList<User>();
				String userNameString = txtTA.getText();
				String[] splitStrings = userNameString.split("/");
				int count = splitStrings.length;
				while (count > 0) {
					count--;
					Student newStudent = new Student();
					newStudent.setId(splitStrings[count]);
					newStudent.setType("student");
					newStudent.setPassword(null);
					list.add(newStudent);
				}
				newCourse.setAssistStudentList(list);
				courseList.getCourseList().add(newCourse);
				
				cHelper.sendToNet("sendCourseList");
				ArrayList<Serializable> rawList = new ArrayList<Serializable>();
				rawList.add(courseList);
				cHelper.listToNet(rawList);				
				
				comboBox.removeAllItems();
				courseList = (CourseList)cHelper.getObject("getCourseList");
				int size = courseList.getCourseList().size();
				for (int i = 0; i < size; i++){
					comboBox.addItem(courseList.getCourseList().get(i).getCourseId());
				}
				
				if (size != 0)
					comboBox.setSelectedIndex(0);		
				mainPanel.remove(scrollPane);		
				table.setVisible(false);
				
				//Jtable
				String[] columnNames = {"课程编号","课程名","授课老师","学分","教室","时间"};
				String[][] rowData = new String[size][6];
				for (int i = 0; i < size; i++){
					newCourse = courseList.getCourseList().get(i);
					rowData[i][0] = newCourse.getCourseId();
					rowData[i][1] = newCourse.getCourseName();
					rowData[i][2] = newCourse.getTeacherUser().getName();
					rowData[i][3] = String.valueOf(newCourse.getScore());
					rowData[i][4] = newCourse.getClassroom();
					rowData[i][5] = newCourse.getTime();
				}
								
				table = new JTable(new DefaultTableModel(rowData, columnNames));
				
				scrollPane = new JScrollPane(table);
				scrollPane.setViewportView(table);
				scrollPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
				scrollPane.setBounds(19, 200, 580, 300);
				
				table.setVisible(true);
				mainPanel.add(scrollPane);
				

			}
		});
		
		btnDeleteCourseInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.remove(txtCNO);
				mainPanel.remove(btnPublishOk);
				mainPanel.remove(btnPublishReset);
				mainPanel.remove(btnModifyOk);
				mainPanel.remove(btnModifyReset);
				mainPanel.add(btnDeleteOk);
				mainPanel.add(btnDeleteReset);
				mainPanel.add(comboBox);
				txtCNO.setEditable(false);
				txtCName.setEditable(false);
				txtTime.setEditable(false);
				txtTeacher.setEditable(false);
				txtCredit.setEditable(false);
				txtClassroom.setEditable(false);
				txtTA.setEditable(false);
				repaint();
			}
		});
		
		comboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED) {
					txtCName.setText(courseList.getCourseList().get(comboBox.getSelectedIndex()).getCourseName());
					txtClassroom.setText(courseList.getCourseList().get(comboBox.getSelectedIndex()).getClassroom());
					txtCredit.setText(String.valueOf(courseList.getCourseList().get(comboBox.getSelectedIndex()).getScore()));
					txtTeacher.setText(courseList.getCourseList().get(comboBox.getSelectedIndex()).getTeacherUser().getName());
					txtTime.setText(courseList.getCourseList().get(comboBox.getSelectedIndex()).getTime());
					String string = "";
					for(int i = 0 ; i < courseList.getCourseList().get(comboBox.getSelectedIndex()).getAssistStudentList().size(); i++){
						string = string + courseList.getCourseList().get(comboBox.getSelectedIndex()).getAssistStudentList().get(i).getName() + "/";
					}
					string = string.substring(0, string.length()-1);					
					txtTA.setText(string);
				}				
			}
		});
		
		

		
	}
	
	
}