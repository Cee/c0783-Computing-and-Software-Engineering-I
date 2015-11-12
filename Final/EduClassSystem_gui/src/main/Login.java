package main;

import instruction.Instruction;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import user.Admin;
import user.Student;
import user.Teacher;
import user.User;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel mainPanel;

	private JLabel lblGroup;

	private JComboBox<String> comboBox;

	private JLabel lblUsername;
	private JLabel lblPassword;

	private JTextField usernameTextField;
	private JPasswordField passwordTextField;

	private JButton btnLogin;
	private JButton btnReset;
	private JButton btnRegister;
	
	private MainSystem eduSystem;
	private Instruction instruction;
	
	private User newUser;

	public Login(MainSystem system,Instruction instr) {
		
		this.eduSystem = system;
		this.instruction = instr;
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setType(Type.UTILITY);

		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// draw mainPanel
		mainPanel = new JPanel();
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);

		lblGroup = new JLabel("Group:");
		lblGroup.setBounds(84, 37, 64, 22);
		lblGroup.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		mainPanel.add(lblGroup);

		comboBox = new JComboBox<String>(new String[] {"admin", "teacher", "student" });
		comboBox.setBounds(156, 38, 200, 21);
		mainPanel.add(comboBox);

		lblUsername = new JLabel("Username:");
		lblUsername.setBounds(55, 73, 81, 22);
		lblUsername.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		mainPanel.add(lblUsername);

		usernameTextField = new JTextField();
		usernameTextField.setBounds(156, 74, 200, 22);
		mainPanel.add(usernameTextField);
		usernameTextField.setColumns(10);

		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(58, 107, 80, 34);
		lblPassword.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		mainPanel.add(lblPassword);

		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(156, 114, 200, 21);
		mainPanel.add(passwordTextField);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(43, 181, 100, 31);
		btnLogin.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		mainPanel.add(btnLogin);

		btnReset = new JButton("Reset");
		btnReset.setBounds(287, 181, 100, 31);
		btnReset.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		mainPanel.add(btnReset);
		
		btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btnRegister.setBounds(166, 181, 100, 31);
		mainPanel.add(btnRegister);
		
		
		//Listeners
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usernameTextField.setText("");
				passwordTextField.setText("");
				comboBox.setSelectedIndex(0);
			}
		});
		
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		
		comboBox.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					login();
				}
			}
		});
		
		passwordTextField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					login();
				}
			}
		});
		
		usernameTextField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					login();
				}
			}
		});
	}
	
	private void login(){
		newUser = new User();
		newUser.setType((String) comboBox.getSelectedItem());
		newUser.setId(usernameTextField.getText());
		newUser.setPassword(String.valueOf(passwordTextField.getPassword()));
		String replyString = instruction.logIn(newUser, eduSystem.getUserList(), eduSystem.getCourseList(), eduSystem.getScoreList());
		switch(replyString){
		case "teacher":
			dispose();					
			Teacher teacher = new Teacher();
			teacher.setType((String) comboBox.getSelectedItem());
			teacher.setId(usernameTextField.getText());
			teacher.setPassword(String.valueOf(passwordTextField.getPassword()));
			new TeacherUI(teacher, eduSystem, instruction);
			break;
		case "student":
			dispose();
			Student student = new Student();
			student.setType((String) comboBox.getSelectedItem());
			student.setId(usernameTextField.getText());
			student.setPassword(String.valueOf(passwordTextField.getPassword()));
			new StudentUI(student, eduSystem, instruction);
			break;
		case "admin":
			dispose();
			Admin admin = new Admin();
			admin.setType((String) comboBox.getSelectedItem());
			admin.setId(usernameTextField.getText());
			admin.setPassword(String.valueOf(passwordTextField.getPassword()));
			new AdminUI(admin, eduSystem, instruction);
			break;
		default:
			usernameTextField.setText("");
			passwordTextField.setText("");
			comboBox.setSelectedIndex(0);
			break;
		}
	}
	
	private void register(){
		newUser = new User();
		newUser.setType((String) comboBox.getSelectedItem());
		newUser.setId(usernameTextField.getText());
		newUser.setPassword(String.valueOf(passwordTextField.getPassword()));
		String replyString = instruction.register(newUser, eduSystem.getUserList(), eduSystem.getScoreList(), eduSystem.getCourseList());
		switch(replyString){
		case "teacher":
			break;
		case "student":
			break;
		default:
			usernameTextField.setText("");
			passwordTextField.setText("");
			comboBox.setSelectedIndex(0);
			break;
		}
	}
	
}
