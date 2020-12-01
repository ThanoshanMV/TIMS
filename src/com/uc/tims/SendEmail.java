package com.uc.tims;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

/*public class SendEmail {

	static long dbTimeInMilli;
	static long actualTimeInMilli;

	static void emailSender() {
		try {
			String host = "smtp.gmail.com";
			String user = "examplejava00@gmail.com";
			String pass = "!examplejava00!";
			String to = "anandanmrv@gmail.com";
			String from = "Fallout_Coders";
			String subject = "TIMS Backup";
			String messageText = "Hey There!, We've attached your TIMS database file.";
			boolean sessionDebug = false;

			Properties props = System.getProperties();

			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.required", "true");

			Session mailSession = Session.getDefaultInstance(props, null);
			mailSession.setDebug(sessionDebug);
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setText(messageText);

			Multipart emailContent = new MimeMultipart();

			// Text Body Part
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText("TIMS Backup Doc");

			// Attach Body Part
			File file = new File(System.getProperty("user.dir") + "\\UrbanCouncil.db");
			MimeBodyPart databaseFileAttachment = new MimeBodyPart();
			databaseFileAttachment.attachFile(file);

			// Attach Body parts
			emailContent.addBodyPart(textBodyPart);
			emailContent.addBodyPart(databaseFileAttachment);

			// Attach multipart to message
			msg.setContent(emailContent);

			Transport transport = mailSession.getTransport("smtp");
			transport.connect(host, user, pass);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			System.out.println("message send successfully");

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	static void UpdateChecker() {

		// creating Calendar instance
		Calendar calendar = Calendar.getInstance();
		// Returns current time in millis
		long timeInMilliSeconds = calendar.getTimeInMillis();
		actualTimeInMilli = timeInMilliSeconds;

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = SqliteConnection.establishSqliteConnection().prepareStatement("SELECT * FROM `BACKUP` WHERE `ID`= ?");
			ps.setInt(1, 1);
			rs = ps.executeQuery();
			if (rs.next()) {
				dbTimeInMilli = rs.getLong("DBTIME");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error while establishing connection.");
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				SqliteConnection.establishSqliteConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	static void backupNow() {
		// 7 days in milli seconds
		long SevenDaysInMilliSeconds = 7 * 24 * 60 * 60 * 1000;
		// long twominutes = 2 * 60 * 1000;

		if ((actualTimeInMilli - dbTimeInMilli) > SevenDaysInMilliSeconds) {
			emailSender();
			SqliteConnection.backupSystem(actualTimeInMilli);
		} else {
			// Do nothing
		}
	}
}*/
