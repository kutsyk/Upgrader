package com.kutsyk.main;

import com.kutsyk.upgrader.DownloadTask;
import com.kutsyk.upgrader.Updater;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
/*
 * Created by JFormDesigner on Wed May 27 20:41:17 MSD 2015
 */


/**
 * @author Vasyl Kutsyk
 */
public class MainForm extends JFrame implements PropertyChangeListener {

    private final String NEWEST_VERSION = "Newest version";
    private String userDir = System.getProperty("user.dir");
    private Updater updater;
    private String server = "ftp.charlesworth-group.com";
    private int port = 21;
    private String user = "guest";
    private String pass = "guest99";
    private boolean programUpdated = false;

    public MainForm() {
        initComponents();
        updater = new Updater(userDir);
        initUpdatingSystem();
    }

    private void initUpdatingSystem() {
        infoLabel.setText("Checking for new version...");
        updateButton.setEnabled(false);
        initUpdateButton();
    }

    public void initUpdateButton() {
        progressBar.setValue(0);
        DownloadTask task = new DownloadTask(server, port, user, pass, this);
        task.setLocal(new File(userDir + "/bin/.version"));
        task.setRemoteFile("/software/.version");
        task.addPropertyChangeListener(this);
        task.execute();
        while (!task.isDone()) ;

        if (task.isDone()) {
            boolean newer = updater.existNewVersion();
            if (newer)
                infoLabel.setText("New version found.");
            else
                infoLabel.setText(NEWEST_VERSION);
            updateButton.setEnabled(newer);
        }
    }

    private void okButtonActionPerformed(ActionEvent e) {
        try {
            Runtime.getRuntime().exec("java -jar " + userDir + "/bin/program.jar");
            System.exit(1);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void cancelButtonActionPerformed(ActionEvent e) {
        System.exit(1);
    }

    private void updateButtonActionPerformed(ActionEvent e) {
        infoLabel.setText("Updating...");
        progressBar.setStringPainted(true);
        progressBar.setValue(0);
        DownloadTask task = new DownloadTask(server, port, user, pass, this);
        task.setLocal(new File(userDir + "/bin/program.jar"));
        task.setRemoteFile("/software/program.jar");
        task.addPropertyChangeListener(this);
        task.execute();
        programUpdated = true;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
            progressBar.setValue(progress);

            if (programUpdated && progressBar.getValue() == 100) {
                programUpdated = false;
                JOptionPane.showMessageDialog(this, "Upgraded.");
                infoLabel.setText(NEWEST_VERSION);
                updater.replaceCurrentVersionWithNew();
                initUpdateButton();
            }
        }
    }

	private void progressBarPropertyChange(PropertyChangeEvent e) {

	}

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Vasyl Kutsyk
		panel1 = new JPanel();
		cancelButton = new JButton();
		okButton = new JButton();
		updateButton = new JButton();
		label2 = new JLabel();
		label3 = new JLabel();
		progressBar = new JProgressBar();
		infoLabel = new JLabel();

		//======== this ========
		setTitle("LaTeXtoXML");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();

		//======== panel1 ========
		{

			// JFormDesigner evaluation mark
//			panel1.setBorder(new javax.swing.border.CompoundBorder(
//				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
//					"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
//					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
//					java.awt.Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});


			//---- cancelButton ----
			cancelButton.setText("Close");
			cancelButton.setFont(new Font("Cambria", Font.PLAIN, 14));
			cancelButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					cancelButtonActionPerformed(e);
				}
			});

			//---- okButton ----
			okButton.setText("Run program");
			okButton.setFont(new Font("Cambria", Font.PLAIN, 14));
			okButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					okButtonActionPerformed(e);
				}
			});

			//---- updateButton ----
			updateButton.setText("Update");
			updateButton.setFont(new Font("Cambria", Font.PLAIN, 14));
			updateButton.setIcon(UIManager.getIcon("OptionPane.informationIcon"));
			updateButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					updateButtonActionPerformed(e);
				}
			});

			//---- label2 ----
			label2.setText("LaTeXtoXML");
			label2.setFont(new Font("Baskerville Old Face", Font.PLAIN, 32));

			//---- label3 ----
			label3.setText("testing version");
			label3.setFont(new Font("Arial", Font.PLAIN, 14));

			//---- progressBar ----
			progressBar.addPropertyChangeListener(new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent e) {
					progressBarPropertyChange(e);
				}
			});

			//---- infoLabel ----
			infoLabel.setText("...");
			infoLabel.setFont(new Font("Calibri", Font.PLAIN, 14));

			GroupLayout panel1Layout = new GroupLayout(panel1);
			panel1.setLayout(panel1Layout);
			panel1Layout.setHorizontalGroup(
				panel1Layout.createParallelGroup()
					.addGroup(panel1Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(panel1Layout.createParallelGroup()
							.addGroup(panel1Layout.createSequentialGroup()
								.addGap(103, 103, 103)
								.addComponent(label2, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
								.addGap(6, 6, 6)
								.addComponent(label3, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
							.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 483, GroupLayout.PREFERRED_SIZE)
							.addGroup(panel1Layout.createSequentialGroup()
								.addComponent(infoLabel, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
								.addGap(10, 10, 10)
								.addComponent(updateButton, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
								.addGap(6, 6, 6)
								.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
								.addGap(10, 10, 10)
								.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(14, Short.MAX_VALUE))
			);
			panel1Layout.setVerticalGroup(
				panel1Layout.createParallelGroup()
					.addGroup(panel1Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(panel1Layout.createParallelGroup()
							.addComponent(label2)
							.addGroup(panel1Layout.createSequentialGroup()
								.addGap(3, 3, 3)
								.addComponent(label3, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
						.addGap(5, 5, 5)
						.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addGap(12, 12, 12)
						.addGroup(panel1Layout.createParallelGroup()
							.addGroup(panel1Layout.createSequentialGroup()
								.addGap(12, 12, 12)
								.addComponent(infoLabel, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
							.addComponent(updateButton)
							.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(16, Short.MAX_VALUE))
			);
		}

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addComponent(panel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		pack();
		setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Vasyl Kutsyk
	private JPanel panel1;
	private JButton cancelButton;
	private JButton okButton;
	private JButton updateButton;
	private JLabel label2;
	private JLabel label3;
	private JProgressBar progressBar;
	private JLabel infoLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
