package com.uc.package1;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import javax.swing.JDesktopPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Cursor;
import javax.swing.SwingConstants;

public class SearchJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtsearch;
	private JTable table;
	private int row;
	private String tableClick;
	static String text;
	static String comboBoxSelection;
	private JTextField txtCount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					SearchJFrame frame = new SearchJFrame();
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
	public SearchJFrame() {

		setTitle("Search details");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/tims.png")));

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 1259, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtCount = new JTextField();
		txtCount.setHorizontalAlignment(SwingConstants.CENTER);
		txtCount.setFont(new Font("Dialog", Font.BOLD, 20));
		txtCount.setBounds(839, 549, 124, 30);
		contentPane.add(txtCount);
		txtCount.setColumns(10);
		txtCount.setText("0");
		txtCount.setEditable(false);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(975, 106, 235, 287);
		contentPane.add(desktopPane);

		JLabel driverImage = new JLabel("");
		driverImage.setBounds(0, -3, 235, 290);
		desktopPane.add(driverImage);
		Image imgDefault = new ImageIcon(this.getClass().getResource("/tims.png")).getImage();
		ImageIcon iconDefault = new ImageIcon(ScaledImage(imgDefault, driverImage.getWidth(), driverImage.getHeight()));
		driverImage.setIcon(iconDefault);

		JComboBox comboBoxSearch = new JComboBox();
		comboBoxSearch.setFont(new Font("Dialog", Font.BOLD, 15));
		comboBoxSearch.setModel(new DefaultComboBoxModel(new String[] { "PARK", "NIC NUMBER" }));
		comboBoxSearch.setBounds(12, 61, 207, 30);
		contentPane.add(comboBoxSearch);

		JScrollPane scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setViewportBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		scrollPane.setBounds(12, 106, 951, 425);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setRowHeight(40);
		/*
		 * DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)
		 * table.getDefaultRenderer(Object.class);
		 * renderer.setHorizontalAlignment(SwingConstants.CENTER);
		 */
		/*
		 * table.setFont(new Font("Dialog", Font.PLAIN, 15)); JTableHeader header =
		 * table.getTableHeader(); header.setFont(new Font("Dialog", Font.BOLD, 12));
		 */

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ResultSet rs1 = null;
				PreparedStatement ps1 = null;

				try {
					SearchJFrame object = new SearchJFrame();
					object.setRow(table.getSelectedRow());
					object.setTableClick(table.getModel().getValueAt(object.getRow(), 1).toString());
					StaticMembers.searchParkNoDelete = object.getTableClick();
					String sql1 = "SELECT * FROM `DRIVER` WHERE `PARK NO`= '" + object.getTableClick() + "'";
					ps1 = SqliteConnection.establishSqliteConnection().prepareStatement(sql1);
					rs1 = ps1.executeQuery();
					
					
					if (rs1.next()) {
						byte[] imageData = rs1.getBytes("IMAGES");
						ImageIcon iconThatCreatedFromBytes = new ImageIcon(imageData);
						Image img1 = iconThatCreatedFromBytes.getImage();
						ImageIcon icon = new ImageIcon(
								ScaledImage(img1, driverImage.getWidth(), driverImage.getHeight()));
						driverImage.setIcon(icon);
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				} finally {
					try {
						ps1.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						rs1.close();
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

		JLabel lblNewLabel = new JLabel("Provide details on driver or park that you want to search");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel.setBounds(234, 12, 511, 40);
		contentPane.add(lblNewLabel);

		JButton btnBack = new JButton("Back");
		btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBack.setFont(new Font("Dialog", Font.BOLD, 15));
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DashboardJFrame dashboardJFrame = new DashboardJFrame();
				dashboardJFrame.setVisible(true);
				dashboardJFrame.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnBack.setBounds(776, 56, 114, 40);
		contentPane.add(btnBack);

		txtsearch = new JTextField();
		txtsearch.setFont(new Font("Dialog", Font.BOLD, 15));
		txtsearch.setText("");
		txtsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				txtsearch.setText(txtsearch.getText().toUpperCase());

				PreparedStatement ps = null;
				ResultSet rs = null;

				try {
					String selection = (String) comboBoxSearch.getSelectedItem();
					comboBoxSelection = selection;
					String query = "SELECT `PARK`,`PARK NO`,`WHEEL NO`,`DRIVER NAME`,`ADDRESS`,`NIC NUMBER`,`PHONE NUMBER`,`GS` FROM `DRIVER` WHERE `"
							+ comboBoxSelection + "` = ?";
					ps = SqliteConnection.establishSqliteConnection().prepareStatement(query);
					ps.setString(1, txtsearch.getText());
					text = txtsearch.getText();
					rs = ps.executeQuery();

					table.setModel(DbUtils.resultSetToTableModel(rs));

					SearchJFrame.setJTableColumnsWidth(table, 1024, 5, 5, 10, 20, 30, 15, 15, 5);
					driverImage.setIcon(iconDefault);
					txtCount.setText("0");

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
		txtsearch.setBounds(338, 63, 305, 34);
		contentPane.add(txtsearch);
		txtsearch.setColumns(10);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int p = JOptionPane.showConfirmDialog(null, "Do you really want to delete ?", "Delete",
						JOptionPane.YES_NO_OPTION);
				if (p == 0) {
					PreparedStatement pst = null;
					ResultSet rsm = null;
					String sql = "DELETE FROM `DRIVER` WHERE `PARK NO` = ?";
					try {
						pst = SqliteConnection.establishSqliteConnection().prepareStatement(sql);
						pst.setString(1, StaticMembers.searchParkNoDelete);
						System.out.println(StaticMembers.searchParkNoDelete);
						if (pst.executeUpdate() > 0) {
							JOptionPane.showMessageDialog(null, "Successfully Deleted!");
							driverImage.setIcon(iconDefault);
							ResultSet rs1 = null;
							PreparedStatement ps1 = null;

							try {

								String query = "SELECT `PARK`,`PARK NO`,`WHEEL NO`,`DRIVER NAME`,`ADDRESS`,`NIC NUMBER`,`PHONE NUMBER`,`GS` FROM `DRIVER` WHERE "
										+ comboBoxSelection + " = ?";
								ps1 = SqliteConnection.establishSqliteConnection().prepareStatement(query);
								ps1.setString(1, text);
								rs1 = ps1.executeQuery();

								table.setModel(DbUtils.resultSetToTableModel(rs1));

								SearchJFrame.setJTableColumnsWidth(table, 1024, 5, 5, 10, 20, 30, 15, 15, 5);

							} catch (Exception e2) {
								JOptionPane.showMessageDialog(null, e2);
							} finally {
								try {
									ps1.close();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								try {
									rs1.close();
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
		btnDelete.setBounds(292, 546, 114, 40);
		contentPane.add(btnDelete);

		JButton btnCount = new JButton("Count");
		btnCount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (text.equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill Search information");
				} else {
					PreparedStatement pstt = null;
					ResultSet rst = null;
					try {
						String sql = "SELECT COUNT(`ADDRESS`) FROM `DRIVER` WHERE `" + comboBoxSelection + "` = ?";
						pstt = SqliteConnection.establishSqliteConnection().prepareStatement(sql);
						pstt.setString(1, text);
						System.out.println(sql);
						rst = pstt.executeQuery();
						if (rst.next()) {
							String sum = rst.getString("COUNT(`ADDRESS`)");
							txtCount.setText(sum);
						} else {
							txtCount.setText("0");
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					} finally {
						try {
							pstt.close();
						} catch (SQLException e9) {
							// TODO Auto-generated catch block
							e9.printStackTrace();
						}
						try {
							rst.close();
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
		});
		btnCount.setFont(new Font("Dialog", Font.BOLD, 15));
		btnCount.setBounds(679, 548, 114, 30);
		contentPane.add(btnCount);

		JButton btnAdd = new JButton("Add Entry");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AutoDriverRegistrationJFrame autoDriverRegistrationJFrame = new AutoDriverRegistrationJFrame();
				autoDriverRegistrationJFrame.setVisible(true);
				autoDriverRegistrationJFrame.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnAdd.setFont(new Font("Dialog", Font.BOLD, 15));
		btnAdd.setBounds(101, 546, 124, 40);
		contentPane.add(btnAdd);

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

	private Image ScaledImage(Image img, int w, int h) {
		BufferedImage resizedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = resizedImage.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(img, 0, 0, w, h, null);
		g2.dispose();
		return resizedImage;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getTableClick() {
		return tableClick;
	}

	public void setTableClick(String tableClick) {
		this.tableClick = tableClick;
	}
}
