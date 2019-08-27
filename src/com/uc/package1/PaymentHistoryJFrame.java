package com.uc.package1;

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

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
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

		setTitle("Payment history");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/tims.png")));

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 1111, 603);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "PARK", "NIC NUMBER" }));
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

				PreparedStatement ps = null;
				ResultSet rs = null;
				try {
					String selection = (String) comboBox.getSelectedItem();
					String query = "SELECT `PARK`,`NAME`,`NIC NUMBER`,`2013`,`2014`,`2015`,`2016`,`2017`,`2018`,`2019`,`2020`,`2021`,`2022`,`TOTAL` FROM `PAYMENT` WHERE `"
							+ selection + "` = ?";
					System.out.println(query);
					ps = SqliteConnection.establishSqliteConnection().prepareStatement(query);
					ps.setString(1, txtsearch.getText());
					rs = ps.executeQuery();

					table.setModel(DbUtils.resultSetToTableModel(rs));

					setJTableColumnsWidth(table, 1024, 5, 20, 10, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 10);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					try {
						ps.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						rs.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						SqliteConnection.establishSqliteConnection().close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
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
				MessageFormat header = new MessageFormat("Payment History");
				MessageFormat footer = new MessageFormat("Generated by TIMS " + "-{0}-");
				try {
					table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				} catch (PrinterException e1) {
					JOptionPane.showMessageDialog(null, "Printing process has failed. Try again.");
					e1.printStackTrace();
				}
			}
		});
		btnPrint.setFont(new Font("Dialog", Font.BOLD, 15));
		btnPrint.setBounds(358, 507, 114, 35);
		contentPane.add(btnPrint);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int p = JOptionPane.showConfirmDialog(null, "Do you really want to delete ?", "Delete",
						JOptionPane.YES_NO_OPTION);
				if (p == 0) {
					PreparedStatement pst = null;
					ResultSet rsm = null;
					String sql = "DELETE FROM `PAYMENT` WHERE `NAME` = ?";
					try {
						pst = SqliteConnection.establishSqliteConnection().prepareStatement(sql);
						pst.setString(1, StaticMembers.paymentDeleteName);
						System.out.println(StaticMembers.paymentDeleteName);
						if (pst.executeUpdate() > 0) {
							JOptionPane.showMessageDialog(null, "Successfully Deleted!");
							PreparedStatement ps = null;
							ResultSet rs = null;
							try {
								String selection = (String) comboBox.getSelectedItem();
								String query = "SELECT `PARK`,`NAME`,`NIC NUMBER`,`2013`,`2014`,`2015`,`2016`,`2017`,`2018`,`2019`,`2020`,`2021`,`2022`,`TOTAL` FROM `PAYMENT` WHERE `"
										+ selection + "` = ?";
								System.out.println(query);
								ps = SqliteConnection.establishSqliteConnection().prepareStatement(query);
								ps.setString(1, txtsearch.getText());
								rs = ps.executeQuery();

								table.setModel(DbUtils.resultSetToTableModel(rs));

								setJTableColumnsWidth(table, 1024, 5, 20, 10, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 10);

							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} finally {
								try {
									ps.close();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								try {
									rs.close();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								try {
									SqliteConnection.establishSqliteConnection().close();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}

						} else {
							JOptionPane.showMessageDialog(null, "Deletion failed. Try again");
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					} finally {
						try {
							pst.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else {
					// Do nothing
				}
			}
		});
		btnDelete.setFont(new Font("Dialog", Font.BOLD, 15));
		btnDelete.setBounds(194, 507, 114, 35);
		contentPane.add(btnDelete);
	}

	static void setJTableColumnsWidth(JTable table, int tablePreferredWidth, double... percentages) {
		double total = 0;
		for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
			total += percentages[i];
		}

		for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
			TableColumn column = table.getColumnModel().getColumn(i);
			column.setPreferredWidth((int) (tablePreferredWidth * (percentages[i] / total)));
		}
	}
	
	static void deletePaymentRow() {
		PreparedStatement pst = null;
		ResultSet rsm = null;
		String sql = "DELETE FROM `PAYMENT` WHERE `NAME` = ?";
		try {
			pst = SqliteConnection.establishSqliteConnection().prepareStatement(sql);
			pst.setString(1, StaticMembers.paymentDeleteName);
			System.out.println(StaticMembers.paymentDeleteName);
			if (pst.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(null, "Payment table deleted!");
				}
		}
		catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		} finally {
			try {
				pst.close();
			} catch (SQLException e9) {
				// TODO Auto-generated catch block
				e9.printStackTrace();
			}
			try {
				rsm.close();
			} catch (SQLException e8) {
				// TODO Auto-generated catch block
				e8.printStackTrace();
			}
			try {
				SqliteConnection.establishSqliteConnection().close();
			} catch (SQLException e7) {
				// TODO Auto-generated catch block
				e7.printStackTrace();
			}
		}

}
}
