package ui;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.ScrollPane;

import javax.swing.SwingConstants;

import javax.swing.UIManager;
import java.awt.Color;

import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;

import net.ClientHelper;

import java.awt.Window.Type;




public class StudentInterface extends JFrame {

	
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
	private JButton btnSelectNewCourses;
	private JButton btnQuitCourse;
	private JButton btnShowCourseScore;
	
	private JPanel mainPanel;
	private JLabel lblDetailInformation;
	
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
	
	private JButton btnOk;
	private JButton btnReset;
	
	private JTable table;

	private ClientHelper cHelper;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentInterface frame = new StudentInterface();
					frame.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StudentInterface(){
		
	}
	
	public StudentInterface(ClientHelper ch,String name) {
		
		this.cHelper = ch;
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setType(Type.UTILITY);
			
		setAlwaysOnTop(true);
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
		
		btnSelectNewCourses = new JButton("选课");		
		btnSelectNewCourses.setBounds(0, 45, 155, 23);
		myCoursePanel.add(btnSelectNewCourses);
		
		btnQuitCourse = new JButton("退选课程");
		btnQuitCourse.setBounds(0, 68, 155, 23);
		myCoursePanel.add(btnQuitCourse);
		
		btnShowCourseScore= new JButton("查看成绩");
		btnShowCourseScore.setBounds(0, 91, 155, 23);
		myCoursePanel.add(btnShowCourseScore);
		
		
		//draw Panels
		
		mainPanel = new JPanel();
		mainPanel.setBounds(168, 41, 611, 515);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		lblDetailInformation = new JLabel("详细信息");
		lblDetailInformation.setBounds(6, 7, 189, 15);
		mainPanel.add(lblDetailInformation);
		
		txtCNO = new JTextField();
		txtCNO.setBounds(59, 29, 130, 21);
		mainPanel.add(txtCNO);
		txtCNO.setColumns(10);
		
		txtCName = new JTextField();
		txtCName.setBounds(270, 29, 130, 21);
		mainPanel.add(txtCName);
		txtCName.setColumns(10);
		
		txtTime = new JTextField();
		txtTime.setBounds(470, 57, 130, 21);
		mainPanel.add(txtTime);
		txtTime.setColumns(10);
		
		txtTeacher = new JTextField();
		txtTeacher.setBounds(470, 29, 130, 21);
		mainPanel.add(txtTeacher);
		txtTeacher.setColumns(10);
		
		txtCredit = new JTextField();
		txtCredit.setBounds(59, 57, 130, 21);
		mainPanel.add(txtCredit);
		txtCredit.setColumns(10);
		
		txtClassroom = new JTextField();
		txtClassroom.setBounds(270, 57, 130, 21);
		mainPanel.add(txtClassroom);
		txtClassroom.setColumns(10);
		
		txtTA = new JTextField();
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
		
//		JComboBox<String> comboBox = new JComboBox<String>();
//		comboBox.setBounds(59, 29, 130, 21);
//		mainPanel.remove(txtCNO);
//		mainPanel.add(comboBox);
		
		btnOk = new JButton("确认");
		btnOk.setBounds(183, 139, 80, 23);
		mainPanel.add(btnOk);
		
		btnReset = new JButton("重置");
		btnReset.setBounds(392, 139, 80, 23);
		mainPanel.add(btnReset);
		
		
		String[] columnNames = {"课程编号","课程名","授课老师","学分","教室","时间"};
		String[][] rowData = new String[1][6];
		
		
		table = new JTable(new DefaultTableModel(rowData, columnNames));

		
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setViewportView(table);
		scrollPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setBounds(19, 200, 580, 300);
		mainPanel.add(scrollPane);
		
		setVisible(true);
		
	}
}