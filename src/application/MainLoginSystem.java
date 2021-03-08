package application;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

/**
 * Cette classe permet acceder a l'application principale avec username et
 * password.
 * 
 * @author Desebel MESNAGER
 *
 */
public class MainLoginSystem {

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JFrame frmLoginSystem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainLoginSystem window = new MainLoginSystem();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainLoginSystem() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 30));
		frame.setBounds(200, 200, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Login System");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(153, 11, 168, 31);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUsername.setBounds(75, 93, 107, 31);
		frame.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPassword.setBounds(75, 135, 97, 31);
		frame.getContentPane().add(lblPassword);

		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtUsername.setBounds(207, 93, 222, 30);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPassword.setBounds(207, 135, 222, 31);
		frame.getContentPane().add(txtPassword);

		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(Color.DARK_GRAY);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 27));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String password = txtPassword.getText();
				String username = txtUsername.getText();

				if (password.contains("java") && username.contains("java")) {
					txtPassword.setText(null);
					txtUsername.setText(null);

					MainBibliotheque info = new MainBibliotheque();
					// MainBibliotheque.main(null);
					info.main(null);

				} else {
					JOptionPane.showMessageDialog(null, "Invalid Login Details", "Login Error",
							JOptionPane.ERROR_MESSAGE);
					txtPassword.setText(null);
					txtUsername.setText(null);

				}

			}
		});

		btnLogin.setBounds(47, 201, 115, 36);
		frame.getContentPane().add(btnLogin);

		JButton btnReset = new JButton("Reset");
		btnReset.setForeground(Color.DARK_GRAY);
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 27));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				txtUsername.setText(null);
				txtPassword.setText(null);
			}
		});
		btnReset.setBounds(194, 201, 115, 36);
		frame.getContentPane().add(btnReset);

		JButton btnExit = new JButton("Exit");
		btnExit.setForeground(Color.DARK_GRAY);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 27));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frmLoginSystem = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frmLoginSystem, "Vous voulez vraiment fermer?", "Login System",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnExit.setBounds(336, 201, 115, 36);
		frame.getContentPane().add(btnExit);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 190, 464, 2);
		frame.getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 53, 464, 2);
		frame.getContentPane().add(separator_1);
	}
}
