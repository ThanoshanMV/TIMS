package com.uc.package1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EditAutoDriverFormJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtparkno;
	private JTextField txtwheelno;
	private JTextField txtdrivername;
	private JTextField txtaddress;
	private JTextField txtnic;
	private JTextField txtphonenumber;

	private String no;
	private String park;
	private String parkno;
	private String wheelno;
	private String drivername;
	private String address;
	private String nic;
	private String phonenumber;
	private String gs;
	private String imageURL;
	private JTextField txtImageUrl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					EditAutoDriverFormJFrame frame = new EditAutoDriverFormJFrame();
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
	public EditAutoDriverFormJFrame() {

		setTitle("Edit details");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/tims.png")));

		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 692);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtImageUrl = new JTextField();
		txtImageUrl.setText((String) null);
		txtImageUrl.setFont(new Font("Dialog", Font.BOLD, 15));
		txtImageUrl.setColumns(10);
		txtImageUrl.setBounds(298, 516, 262, 26);
		contentPane.add(txtImageUrl);
		txtImageUrl.setText(StaticMembers.imageURL);

		JComboBox comboBox1 = new JComboBox();
		comboBox1.setModel(new DefaultComboBoxModel(
				new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q" }));
		comboBox1.setFont(new Font("Dialog", Font.BOLD, 15));
		comboBox1.setBounds(298, 91, 157, 33);
		contentPane.add(comboBox1);
		comboBox1.setSelectedItem(StaticMembers.park);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "YES", "NO" }));
		comboBox.setFont(new Font("Dialog", Font.BOLD, 15));
		comboBox.setBounds(298, 467, 133, 24);
		contentPane.add(comboBox);
		if (StaticMembers.gs.equals("YES")) {
			comboBox.setSelectedIndex(0);
		} else {
			comboBox.setSelectedIndex(1);
		}

		JLabel lblThreewheelerEditForm = new JLabel("Three-Wheeler Edit Form");
		lblThreewheelerEditForm.setFont(new Font("Dialog", Font.BOLD, 18));
		lblThreewheelerEditForm.setHorizontalAlignment(SwingConstants.CENTER);
		lblThreewheelerEditForm.setBounds(137, 23, 262, 42);
		contentPane.add(lblThreewheelerEditForm);

		JLabel lblPark = new JLabel("Park");
		lblPark.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPark.setBounds(52, 94, 71, 30);
		contentPane.add(lblPark);

		JLabel lblParkNo = new JLabel("Park No");
		lblParkNo.setFont(new Font("Dialog", Font.BOLD, 15));
		lblParkNo.setBounds(52, 146, 71, 30);
		contentPane.add(lblParkNo);

		JLabel lblWheelNo = new JLabel("Wheel No");
		lblWheelNo.setFont(new Font("Dialog", Font.BOLD, 15));
		lblWheelNo.setBounds(52, 199, 111, 30);
		contentPane.add(lblWheelNo);

		JLabel lblDriverName = new JLabel("Driver Name");
		lblDriverName.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDriverName.setBounds(52, 255, 111, 30);
		contentPane.add(lblDriverName);

		JLabel lblAddress = new JLabel("Address\n");
		lblAddress.setFont(new Font("Dialog", Font.BOLD, 15));
		lblAddress.setBounds(52, 307, 98, 30);
		contentPane.add(lblAddress);

		JLabel lblNicNumber = new JLabel("NIC Number");
		lblNicNumber.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNicNumber.setBounds(52, 360, 139, 30);
		contentPane.add(lblNicNumber);

		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPhoneNumber.setBounds(52, 412, 139, 30);
		contentPane.add(lblPhoneNumber);

		JLabel lblGs = new JLabel("GS Certification");
		lblGs.setFont(new Font("Dialog", Font.BOLD, 15));
		lblGs.setBounds(52, 464, 139, 30);
		contentPane.add(lblGs);

		txtparkno = new JTextField();
		txtparkno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtparkno.setText(txtparkno.getText().toUpperCase());
			}
		});
		txtparkno.setFont(new Font("Dialog", Font.BOLD, 15));
		txtparkno.setColumns(10);
		txtparkno.setBounds(298, 148, 262, 26);
		contentPane.add(txtparkno);
		txtparkno.setText(StaticMembers.parkNo);

		txtwheelno = new JTextField();
		txtwheelno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtwheelno.setText(txtwheelno.getText().toUpperCase());
			}
		});
		txtwheelno.setFont(new Font("Dialog", Font.BOLD, 15));
		txtwheelno.setColumns(10);
		txtwheelno.setBounds(298, 201, 262, 26);
		contentPane.add(txtwheelno);
		txtwheelno.setText(StaticMembers.wheelNo);

		txtdrivername = new JTextField();
		txtdrivername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtdrivername.setText(txtdrivername.getText().toUpperCase());
			}
		});
		txtdrivername.setFont(new Font("Dialog", Font.BOLD, 15));
		txtdrivername.setColumns(10);
		txtdrivername.setBounds(298, 257, 262, 26);
		contentPane.add(txtdrivername);
		txtdrivername.setText(StaticMembers.driverName);

		txtaddress = new JTextField();
		txtaddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtaddress.setText(txtaddress.getText().toUpperCase());
			}
		});
		txtaddress.setFont(new Font("Dialog", Font.BOLD, 15));
		txtaddress.setColumns(10);
		txtaddress.setBounds(298, 309, 262, 26);
		contentPane.add(txtaddress);
		txtaddress.setText(StaticMembers.address);

		txtnic = new JTextField();
		txtnic.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtnic.setText(txtnic.getText().toUpperCase());
			}
		});
		txtnic.setFont(new Font("Dialog", Font.BOLD, 15));
		txtnic.setColumns(10);
		txtnic.setBounds(298, 362, 262, 26);
		contentPane.add(txtnic);
		txtnic.setText(StaticMembers.nic);

		txtphonenumber = new JTextField();
		txtphonenumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (!((Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();

				}
			}
		});
		txtphonenumber.setFont(new Font("Dialog", Font.BOLD, 15));
		txtphonenumber.setColumns(10);
		txtphonenumber.setBounds(298, 414, 262, 26);
		contentPane.add(txtphonenumber);
		txtphonenumber.setText(StaticMembers.phoneNumber);

		JButton btnSave = new JButton("Save");
		btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSave.setFont(new Font("Dialog", Font.BOLD, 15));
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditAutoDriverFormJFrame object = new EditAutoDriverFormJFrame();
				object.setPark(comboBox1.getSelectedItem().toString());
				object.setParkno(txtparkno.getText());
				object.setWheelno(txtwheelno.getText());
				object.setDrivername(txtdrivername.getText());
				object.setAddress(txtaddress.getText());
				object.setNic(txtnic.getText());
				object.setPhonenumber(txtphonenumber.getText());
				object.setGs(comboBox.getSelectedItem().toString());
				object.setImageURL(txtImageUrl.getText());

				System.out.println(object.getPark());

				PreparedStatement ps = null;
				if (object.getParkno().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid park no!");
				} else if (object.getWheelno().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid wheel no!");
				} else if (object.getDrivername().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid driver name!");
				} else if (object.getAddress().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid address!");
				} else if (object.getNic().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid nic number!");
				} else if (!((object.getNic().length() == 10) || (object.getNic().length() == 12))) {
					JOptionPane.showMessageDialog(null, "Sorry, Check NIC length!");
				} else if (object.getPhonenumber().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid phone number!");
				} else if (!(object.getPhonenumber().length() == 10)) {
					JOptionPane.showMessageDialog(null, "Phone number should be in 10 characters!");
				} else if (object.getImageURL().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid driver image");
				} else {

					try {
						System.out.println(object.getParkno());
						System.out.println(object.getWheelno());
						System.out.println(object.getImageURL());
						System.out.println();
						String sqlQueryForUpdateDriverDetails = "UPDATE `DRIVER` SET `PARK`= ?,`PARK NO`= ? ,`WHEEL NO`= ? ,`DRIVER NAME`= ? ,`ADDRESS`=  ? ,`NIC NUMBER`= ? ,`PHONE NUMBER`= ? ,`GS`= ? ,`IMAGES`= ? ,`IMAGEURL` = ? WHERE `PARK NO`= ?";
						ps = SqliteConnection.establishSqliteConnection()
								.prepareStatement(sqlQueryForUpdateDriverDetails);

						ps.setString(1, object.getPark());
						ps.setString(2, object.getParkno());
						ps.setString(3, object.getWheelno());
						ps.setString(4, object.getDrivername());
						ps.setString(5, object.getAddress());
						ps.setString(6, object.getNic());
						ps.setString(7, object.getPhonenumber());
						ps.setString(8, object.getGs());
						ps.setBytes(9, readFile(object.getImageURL()));
						ps.setString(10, object.getImageURL());
						ps.setString(11, StaticMembers.parkNo);

						System.out.println(sqlQueryForUpdateDriverDetails);

						if (ps.executeUpdate() > 0) {
							JOptionPane.showMessageDialog(null, "Successfully Saved!");
							DashboardJFrame dashboardJFrame = new DashboardJFrame();
							dashboardJFrame.setVisible(true);
							dashboardJFrame.setLocationRelativeTo(null);
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Updation failed. Check entered details or Try again.");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Error while establishing connection.");
					} finally {
						try {
							ps.close();
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
			}
		});
		btnSave.setBounds(449, 585, 111, 37);
		contentPane.add(btnSave);

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
		btnBack.setBounds(60, 585, 114, 37);
		contentPane.add(btnBack);

		JButton btnUpdate = new JButton("Choose Image");
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditAutoDriverFormJFrame object = new EditAutoDriverFormJFrame();
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("Pick the image");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int response = chooser.showOpenDialog(null);
				if (response == JFileChooser.APPROVE_OPTION) {
					File selectedFile = chooser.getSelectedFile();
					txtImageUrl.setText(selectedFile.getAbsolutePath());
				}

			}
		});
		btnUpdate.setHorizontalAlignment(SwingConstants.LEFT);
		btnUpdate.setFont(new Font("Dialog", Font.BOLD, 15));
		btnUpdate.setBounds(52, 517, 158, 25);
		contentPane.add(btnUpdate);

	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park;
	}

	public String getParkno() {
		return parkno;
	}

	public void setParkno(String parkno) {
		this.parkno = parkno;
	}

	public String getWheelno() {
		return wheelno;
	}

	public void setWheelno(String wheelno) {
		this.wheelno = wheelno;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getGs() {
		return gs;
	}

	public void setGs(String gs) {
		this.gs = gs;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	static private byte[] readFile(String file) {
		ByteArrayOutputStream bos = null;
		try {
			File f = new File(file);
			FileInputStream fis = new FileInputStream(f);
			byte[] buffer = new byte[1024];
			bos = new ByteArrayOutputStream();
			for (int len; (len = fis.read(buffer)) != -1;) {
				bos.write(buffer, 0, len);
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e2) {
			System.err.println(e2.getMessage());
		} finally {

		}
		return bos != null ? bos.toByteArray() : null;

	}
}
