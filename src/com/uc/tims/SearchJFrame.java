package com.uc.tims;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;

import com.uc.tims.mysql.MySQLConnection;
import com.uc.tims.mysql.MySQLQuery;
import com.uc.tims.mysql.MySQLQueryMethod;

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
import java.sql.Connection;
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
	private static String comboBoxSelection;
	private JTextField txtCount;
	
	private ResultSet resultSet = null;
	private PreparedStatement preparedStatement = null;
	private Connection connection = null;
	private String searchBy;

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
		comboBoxSearch.setModel(new DefaultComboBoxModel(new String[] { "park", "nic" }));
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
				try {
					// creating SearchJFrame object
					SearchJFrame searchJFrame = new SearchJFrame();
					
					// set the row 
					searchJFrame.setRow(table.getSelectedRow());
					
					// get clicked park number in the JTable and set to TableClick
					searchJFrame.setTableClick(table.getModel().getValueAt(searchJFrame.getRow(), 1).toString());
					
					// establishing MySQL connection
					connection = MySQLConnection.establishMySqlConnection();
					
					// creating prepared statement to execute parameterized query
					preparedStatement = connection.prepareStatement(MySQLQuery.getSqlQueryForSelectDriverDetailsForSearchOperartion());
					
					// setting parkno value using PreparedStatement's setter methods 
					preparedStatement.setString(1, searchJFrame.getTableClick());
					
					// execute the selected query and return an instance of ResultSet
					resultSet = preparedStatement.executeQuery();
					
					
					if (resultSet.next()) {
						// get image from database as byte array
						byte[] imageData = resultSet.getBytes("images");
						
						// creating ImageIcon object using that byte array 
						ImageIcon iconThatCreatedFromBytes = new ImageIcon(imageData);
						
						// creating image object using ImageIcon
						Image image = iconThatCreatedFromBytes.getImage();
						
						// creating SCALED ImageIcon using image object
						ImageIcon icon = new ImageIcon(
								ScaledImage(image, driverImage.getWidth(), driverImage.getHeight()));
						
						// setting this ImageIcon to placeholder in our SearchJFrame
						driverImage.setIcon(icon);
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
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
				// create instance of DashboardJFrame
				DashboardJFrame dashboardJFrame = new DashboardJFrame();
				
				// make it visible 
				dashboardJFrame.setVisible(true);
				
				// center this JFrame
				dashboardJFrame.setLocationRelativeTo(null);
				
				// dispose the current JFrame
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
				
				// as the key released, we convert it to uppercase
				txtsearch.setText(txtsearch.getText().toUpperCase());
				
				try {
					
					// assigning search by 
					setSearchBy((String) comboBoxSearch.getSelectedItem());

					System.out.println(getSearchBy());
					
					// establish MySQL connection
					setConnection(MySQLConnection.establishMySqlConnection());
					
					// can not add to MySQLQuery class as if I put there "? LIKE ?" error occurs
					String query = "SELECT `park`,`parkno`,`wheelno`,`name`,`address`,`nic`,`phoneno`,`gs` FROM `driver` WHERE `" + getSearchBy() + "` LIKE ?";

					// creating prepared statement to execute parameterized query
					preparedStatement = getConnection().prepareStatement(query);
					
					// setting vales using PreparedStatement's setter methods 
					// preparedStatement.setString(1, getSearchBy());
					preparedStatement.setString(1, "%" + txtsearch.getText() + "%");
					
					// execute the selected query and return an instance of ResultSet
					resultSet = preparedStatement.executeQuery();

					// set that resultSet to the table 
					table.setModel(DbUtils.resultSetToTableModel(resultSet));

					SearchJFrame.setJTableColumnsWidth(table, 1024, 5, 5, 10, 20, 30, 15, 15, 5);
					
					// initially load TIMS logo as image placeholder
					driverImage.setIcon(iconDefault);
					
					// initially set number of rows count to 0
					txtCount.setText("0");

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
						getConnection().close();
					} catch (SQLException e1) {
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
				// confirming on delete operation
				int p = JOptionPane.showConfirmDialog(null, "Do you really want to delete ?", "Delete",
						JOptionPane.YES_NO_OPTION);
				
				// can delete
				if (p == 0) {
					try {
						// creating SearchJFrame object
						SearchJFrame searchJFrame = new SearchJFrame();
						
						// set the row 
						searchJFrame.setRow(table.getSelectedRow());
						
						// get clicked park number
						searchJFrame.setTableClick(table.getModel().getValueAt(searchJFrame.getRow(), 5).toString());
						
						// establishing MySQL connection
						setConnection(MySQLConnection.establishMySqlConnection());
						
						preparedStatement = getConnection().prepareStatement(MySQLQuery.getSqlQueryForDeleteDriverByNic());
						preparedStatement.setString(1, searchJFrame.getTableClick());
						
						System.out.println(searchJFrame.getTableClick());
						
						boolean payment = MySQLQueryMethod.deletePaymentByNic(searchJFrame.getTableClick());
						System.out.println(!payment);
						int result = preparedStatement.executeUpdate();
						System.out.println(result);

						
						if (!payment && (result > 0)) {
							JOptionPane.showMessageDialog(null, "Successfully Deleted!");
							driverImage.setIcon(iconDefault);

							// refresh the table after deletion by re executing sqlQueryForSelectDriverDetailsBySearch
							try {
								// establishing MySQL connection
								setConnection(MySQLConnection.establishMySqlConnection());
								
								// creating prepared statement to execute parameterized query
								preparedStatement = getConnection().prepareStatement(MySQLQuery.getSqlQueryForSelectDriverDetailsBySearch());
								
								// setting vales using PreparedStatement's setter methods 
								preparedStatement.setString(1, getSearchBy());
								preparedStatement.setString(2, "%" + txtsearch.getText() + "%");
								
								// execute the selected query and return an instance of ResultSet
								resultSet = preparedStatement.executeQuery();

								// set that resultSet to the table 
								table.setModel(DbUtils.resultSetToTableModel(resultSet));

								SearchJFrame.setJTableColumnsWidth(table, 1024, 5, 5, 10, 20, 30, 15, 15, 5);
								
								// load TIMS logo as image placeholder
								driverImage.setIcon(iconDefault);
								
								txtCount.setText("0");
								
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
									getConnection().close();
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
							}

						} else {
							JOptionPane.showMessageDialog(null, "Deletion failed. Try again later");
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					} finally {
						try {
							preparedStatement.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						try {
							getConnection().close();
						} catch (SQLException e1) {
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
				if (txtsearch.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill Search information");
				} else {
					try {
						// establishing MySQL connection
						setConnection(MySQLConnection.establishMySqlConnection());
						
						// can not add to MySQLQuery class as if I put there "? LIKE ?" error occurs
						String query = "SELECT COUNT(`park`) FROM `driver` WHERE `" + getSearchBy() + "` LIKE ?";
						
						// creating prepared statement to execute parameterized query
						preparedStatement = getConnection().prepareStatement(query);
						
						// setting vales using PreparedStatement's setter methods 
						// preparedStatement.setString(1, getSearchBy());
						preparedStatement.setString(1, "%" + txtsearch.getText() + "%");
						
						// execute the selected query and return an instance of ResultSet
						resultSet = preparedStatement.executeQuery();
						
						// resultSet.next() returns true if the new current row is valid otherwise false if there are no more rows
						if (resultSet.next()) {
							// return number of rows in table by counting address 
							txtCount.setText(resultSet.getString("COUNT(`park`)"));
						} else {
							txtCount.setText("0");
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
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
							getConnection().close();
						} catch (SQLException e1) {
							e1.printStackTrace();
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
				// create instance of AutoDriverRegistrationJFrame
				AutoDriverRegistrationJFrame autoDriverRegistrationJFrame = new AutoDriverRegistrationJFrame();
				
				// make it visible
				autoDriverRegistrationJFrame.setVisible(true);
				
				// center this JFrame 
				autoDriverRegistrationJFrame.setLocationRelativeTo(null);
				
				// dispose current JFrame
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

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}

	public void setPreparedStatement(PreparedStatement preparedStatement) {
		this.preparedStatement = preparedStatement;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public String getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}
}
