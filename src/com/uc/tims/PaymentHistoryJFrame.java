package com.uc.tims;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumn;

import com.uc.tims.mysql.MySQLConnection;
import com.uc.tims.mysql.MySQLQuery;
import com.uc.tims.mysql.MySQLQueryMethod;
import com.uc.tims.utilities.Printer;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaymentHistoryJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtsearch;
	private JTable table;
	
	private ResultSet resultSet;
	private Printer printer;
	private MySQLQueryMethod mySQLQueryMethod;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					PaymentHistoryJFrame frame = new PaymentHistoryJFrame();
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
	public PaymentHistoryJFrame() {

		printer = new Printer();
		
		// create MySQLQueryMethod instance
		mySQLQueryMethod = new MySQLQueryMethod();
		
		setTitle("Payment history");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/tims.png")));

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 1111, 603);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "park", "nic" }));
		comboBox.setFont(new Font("Dialog", Font.BOLD, 15));
		comboBox.setBounds(24, 56, 207, 32);
		contentPane.add(comboBox);

		JLabel lblPaymentHistory = new JLabel("Payment History");
		lblPaymentHistory.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaymentHistory.setFont(new Font("Dialog", Font.BOLD, 20));
		lblPaymentHistory.setBounds(318, 0, 302, 40);
		contentPane.add(lblPaymentHistory);

		txtsearch = new JTextField();
		txtsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtsearch.setText(txtsearch.getText().toUpperCase());
				try {
					String selection = (String) comboBox.getSelectedItem();
					
					resultSet = mySQLQueryMethod.findPaymentHistory(selection, txtsearch.getText());
					
					table.setModel(DbUtils.resultSetToTableModel(resultSet));

					setJTableColumnsWidth(table, 1024, 5, 20, 10, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 10);

				} finally {
					try {
						resultSet.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		});
		txtsearch.setText("");
		txtsearch.setFont(new Font("Dialog", Font.BOLD, 15));
		txtsearch.setColumns(10);
		txtsearch.setBounds(318, 56, 305, 34);
		contentPane.add(txtsearch);

		JScrollPane scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBounds(24, 116, 1033, 372);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StaticMembers.paymentDeleteName = table.getModel().getValueAt(table.getSelectedRow(), 1).toString();
			}
		});
		scrollPane.setViewportView(table);
		table.setRowHeight(40);
		
		/*
		 * DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)
		 * table.getDefaultRenderer(Object.class);
		 * renderer.setHorizontalAlignment(SwingConstants.CENTER);
		 */


		JButton btnBack = new JButton("Back\n");
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DashboardJFrame dashboardJFrame = new DashboardJFrame();
				dashboardJFrame.setVisible(true);
				dashboardJFrame.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnBack.setFont(new Font("Dialog", Font.BOLD, 15));
		btnBack.setBounds(24, 507, 114, 35);
		contentPane.add(btnBack);

		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				printer.printDocuments(table);
			}
		});
		btnPrint.setFont(new Font("Dialog", Font.BOLD, 15));
		btnPrint.setBounds(318, 507, 114, 35);
		contentPane.add(btnPrint);
	}

	private static void setJTableColumnsWidth(JTable table, int tablePreferredWidth, double... percentages) {
		double total = 0;
		for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
			total += percentages[i];
		}

		for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
			TableColumn column = table.getColumnModel().getColumn(i);
			column.setPreferredWidth((int) (tablePreferredWidth * (percentages[i] / total)));
		}
	}
	
	
}
