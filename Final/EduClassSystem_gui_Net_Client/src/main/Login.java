package main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import net.Client;

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
	
	private Client client;
	

	public Login(Client c) {
		
		this.client = c;
		
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
		
		JButton btnRegister = new JButton("Register");
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
		
		this.addWindowListener(new WindowListener() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				client.close();				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}		
		});
	}
	
	private void login(){
		client.sendMessage("Login");
		client.sendMessage((String) comboBox.getSelectedItem());
		client.sendMessage(usernameTextField.getText());
		client.sendMessage(String.valueOf(passwordTextField.getPassword()));
		String replyString = client.getMessage();
		switch(replyString){
		case "teacher":
			dispose();					
			new TeacherUI(client);
			break;
		case "student":
			dispose();
			new StudentUI(client);
			break;
		case "admin":
			dispose();
			new AdminUI(client);
			break;
		case "ID":
			JOptionPane.showMessageDialog(null,"Wrong Id or Password");	
			usernameTextField.setText("");
			passwordTextField.setText("");
			comboBox.setSelectedIndex(0);
			break;
		default:
			JOptionPane.showMessageDialog(null,"Wrong Input");	
			usernameTextField.setText("");
			passwordTextField.setText("");
			comboBox.setSelectedIndex(0);
			break;
		}
	}
	
	private void register(){
		client.sendMessage("Register");
		client.sendMessage((String) comboBox.getSelectedItem());
		client.sendMessage(usernameTextField.getText());
		client.sendMessage(String.valueOf(passwordTextField.getPassword()));
		String replyString = client.getMessage();
		switch(replyString){
		case "teacher":
			JOptionPane.showMessageDialog(null,"Succeed");
			break;
		case "student":
			JOptionPane.showMessageDialog(null,"Succeed");
			break;
		case "exist":
			JOptionPane.showMessageDialog(null,"The Id Exists");
			usernameTextField.setText("");
			passwordTextField.setText("");
			comboBox.setSelectedIndex(0);
			break;
		case "blank":
			JOptionPane.showMessageDialog(null,"A blank Id or Password");
			usernameTextField.setText("");
			passwordTextField.setText("");
			comboBox.setSelectedIndex(0);
			break;
		default:
			JOptionPane.showMessageDialog(null,"Wrong Input");
			usernameTextField.setText("");
			passwordTextField.setText("");
			comboBox.setSelectedIndex(0);
			break;
		}
		
	}
	
}
