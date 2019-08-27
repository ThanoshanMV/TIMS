package com.uc.package1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Cursor;

public class DashboardJFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					DashboardJFrame frame = new DashboardJFrame();
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
	public DashboardJFrame() {

		setTitle("Dashboard-TIMS");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/tims.png")));

		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAddEntry = new JButton("Add Entry");
		btnAddEntry.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddEntry.setFont(new Font("Dialog", Font.BOLD, 15));
		btnAddEntry.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AutoDriverRegistrationJFrame autoDriverRegistrationJFrame = new AutoDriverRegistrationJFrame();
				autoDriverRegistrationJFrame.setVisible(true);
				autoDriverRegistrationJFrame.setLocationRelativeTo(null);
				dispose();
				SqliteConnection.createDriverTable();
				SqliteConnection.createPaymentTable();
			}
		});
		btnAddEntry.setBounds(74, 109, 130, 39);
		contentPane.add(btnAddEntry);

		JLabel lblNewLabel = new JLabel("Dashboard -");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(74, 32, 137, 46);
		contentPane.add(lblNewLabel);

		JButton btnEdit = new JButton("Edit");
		btnEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEdit.setFont(new Font("Dialog", Font.BOLD, 15));
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChooseEditDriverJFrame obj = new ChooseEditDriverJFrame();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnEdit.setBounds(74, 245, 130, 39);
		contentPane.add(btnEdit);

		JButton btnSearch = new JButton("Search\n");
		btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSearch.setFont(new Font("Dialog", Font.BOLD, 15));
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SearchJFrame searchJFrame = new SearchJFrame();
				searchJFrame.setVisible(true);
				searchJFrame.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnSearch.setBounds(376, 109, 130, 39);
		contentPane.add(btnSearch);

		JButton btnPrint = new JButton("Print");
		btnPrint.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPrint.setFont(new Font("Dialog", Font.BOLD, 15));
		btnPrint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrintJFrame printJFrame = new PrintJFrame();
				printJFrame.setVisible(true);
				printJFrame.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnPrint.setBounds(376, 176, 130, 39);
		contentPane.add(btnPrint);

		JLabel lblLog = new JLabel("");
		lblLog.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StartJFrame startJFrameObject = new StartJFrame();
				startJFrameObject.setVisible(true);
				startJFrameObject.setLocationRelativeTo(null);
				dispose();
			}
		});
		lblLog.setHorizontalAlignment(SwingConstants.CENTER);
		lblLog.setBounds(458, 367, 48, 46);
		Image imageLog = new ImageIcon(this.getClass().getResource("/logout.png")).getImage();
		lblLog.setIcon(new ImageIcon(imageLog));
		contentPane.add(lblLog);

		JLabel lblNewLabel_1 = new JLabel("Log out");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_1.setBounds(453, 406, 70, 22);
		contentPane.add(lblNewLabel_1);

		JLabel lblName = new JLabel("");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Norasi", Font.BOLD | Font.ITALIC, 24));
		lblName.setBounds(214, 29, 364, 46);
		lblName.setText("Welcome " + StaticMembers.name);
		contentPane.add(lblName);

		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PasswordChange ps = new PasswordChange();
				ps.setVisible(true);
				ps.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnChangePassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnChangePassword.setFont(new Font("Dialog", Font.BOLD, 14));
		btnChangePassword.setBounds(375, 245, 180, 39);
		contentPane.add(btnChangePassword);

		JButton btnSummary = new JButton("Summary");
		btnSummary.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SummaryJFrame ps = new SummaryJFrame();
				ps.setVisible(true);
				ps.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnSummary.setFont(new Font("Dialog", Font.BOLD, 15));
		btnSummary.setBounds(74, 176, 130, 39);
		contentPane.add(btnSummary);

		JButton btnPaymentHistory = new JButton("Payment History");
		btnPaymentHistory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PaymentHistoryJFrame object = new PaymentHistoryJFrame();
				object.setVisible(true);
				object.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnPaymentHistory.setFont(new Font("Dialog", Font.BOLD, 14));
		btnPaymentHistory.setBounds(74, 314, 180, 39);
		contentPane.add(btnPaymentHistory);

		JButton btnPaymentView = new JButton("Payment Add");
		btnPaymentView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChooseDriverPaymentJFrame obj = new ChooseDriverPaymentJFrame();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnPaymentView.setFont(new Font("Dialog", Font.BOLD, 14));
		btnPaymentView.setBounds(376, 314, 180, 39);
		contentPane.add(btnPaymentView);
	}
}
