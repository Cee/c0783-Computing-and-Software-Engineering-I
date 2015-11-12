package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;

import javax.swing.UIManager;

import net.ClientHelper;

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

	private JButton btnOk;
	private JButton btnReset;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {

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
		lblGroup.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
		mainPanel.add(lblGroup);

		comboBox = new JComboBox<String>(new String[] { "admin", "teacher",
				"student" });
		comboBox.setBounds(156, 38, 200, 21);
		mainPanel.add(comboBox);

		lblUsername = new JLabel("Username:");
		lblUsername.setBounds(55, 73, 81, 22);
		lblUsername.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
		mainPanel.add(lblUsername);

		usernameTextField = new JTextField();
		usernameTextField.setBounds(156, 74, 200, 22);
		mainPanel.add(usernameTextField);
		usernameTextField.setColumns(10);

		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(58, 107, 80, 34);
		lblPassword.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
		mainPanel.add(lblPassword);

		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(156, 114, 200, 21);
		mainPanel.add(passwordTextField);

		btnOk = new JButton("OK");
		btnOk.setBounds(99, 190, 100, 31);
		btnOk.setBackground(UIManager.getColor("Button.background"));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String reply = null;
				String usernameString = usernameTextField.getText();
				String passwordString = String.valueOf(passwordTextField
						.getPassword());
				String item = (String) comboBox.getSelectedItem();

				ClientHelper cHelper = new ClientHelper();
				reply = cHelper.sendToNet("Login" + " " + usernameString + " "
						+ passwordString + " " + item);
				
				
				if (reply != null){
					String[] split = reply.split(" ");
					switch (split[0]) {
					case "admin":
						break;
					case "teacher":
						System.out.println("teacher");
						
					
						dispose();
						System.out.println("teacher");
						
						
						new TeacherInterface(cHelper,split[1],split[2]);
						break;
					case "student":
						dispose();
//						new StudentInterface(cHelper,split[1],split[2]);
						break;
					default:

					}
					dispose();
				}

				
				

			}
		});
		btnOk.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
		mainPanel.add(btnOk);

		btnReset = new JButton("Reset");
		btnReset.setBounds(244, 190, 100, 31);
		btnReset.setToolTipText("");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usernameTextField.setText("");
				passwordTextField.setText("");
				comboBox.setSelectedIndex(0);
			}
		});
		btnReset.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
		mainPanel.add(btnReset);

	}
}
