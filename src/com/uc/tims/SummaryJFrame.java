package com.uc.tims;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import com.uc.tims.mysql.MySQLConnection;
import com.uc.tims.mysql.MySQLQuery;
import com.uc.tims.utilities.Printer;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class SummaryJFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	Connection connection = null;
	Printer printer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					SummaryJFrame frame = new SummaryJFrame();
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
	public SummaryJFrame() {
		
		// create new Printer instace
		printer = new Printer();
		
		setTitle("Summary details");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/tims.png")));

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 787, 529);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(55, 56, 680, 351);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setRowHeight(40);
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getDefaultRenderer(Object.class);
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		try {
			
			connection = MySQLConnection.establishMySqlConnection();
			
			preparedStatement = connection.prepareStatement(MySQLQuery.sqlQueryForGetSummaryByParkCount);
			
			resultSet = preparedStatement.executeQuery();

			table.setModel(DbUtils.resultSetToTableModel(resultSet));

			SearchJFrame.setJTableColumnsWidth(table, 1024, 5, 5, 10, 20, 30, 10, 15, 5);

		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				resultSet.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		JButton btnBack = new JButton("Back");
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
		btnBack.setBounds(221, 432, 114, 40);
		contentPane.add(btnBack);

		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				printer.printDocuments(table);
			}
		});
		btnPrint.setFont(new Font("Dialog", Font.BOLD, 15));
		btnPrint.setBounds(474, 432, 114, 40);
		contentPane.add(btnPrint);

		JLabel lblSummaryDetails = new JLabel("Summary Details");
		lblSummaryDetails.setFont(new Font("Dialog", Font.BOLD, 15));
		lblSummaryDetails.setBounds(321, 0, 165, 40);
		contentPane.add(lblSummaryDetails);
	}

	public static void setJTableColumnsWidth(JTable table, int tablePreferredWidth, double... percentages) {
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
