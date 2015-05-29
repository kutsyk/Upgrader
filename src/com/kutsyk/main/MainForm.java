package com.kutsyk.main;

import com.kutsyk.upgrader.Updater;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
/*
 * Created by JFormDesigner on Wed May 27 20:41:17 MSD 2015
 */


/**
 * @author Vasyl Kutsyk
 */
public class MainForm extends JFrame implements PropertyChangeListener{

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Vasyl Kutsyk
    private JPanel dialogPane;
    private JButton cancelButton;
    private JButton okButton;
    private JButton updateButton;
    private JLabel label2;
    private JLabel label3;
    private JProgressBar progressBar1;
    private JLabel infoLabel;

    private String userDir = System.getProperty("user.dir");
    private Updater updater;

    public MainForm() {
        initComponents();
        initUpdatingSystem();
//        initUpdateButton();
    }

    private void initUpdatingSystem(){
        updater = new Updater(userDir, progressBar1);
        infoLabel.setText("Checking for new version...");
        progressBar1.setIndeterminate(true);
    }

    public void initUpdateButton(){
        boolean newer = updater.existNewVersion();
        updateButton.setEnabled(newer);
        progressBar1.setIndeterminate(false);
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
//        Thread updaterThread = new Thread(new Runnable() {
//            public void run() {
                updater.upgrade();
//            }
//        });
//        updaterThread.start();
        JOptionPane.showMessageDialog(this, "Upgraded. Please, restart the proogram");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
            progressBar1.setValue(progress);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Vasyl Kutsyk
        dialogPane = new JPanel();
        cancelButton = new JButton();
        okButton = new JButton();
        updateButton = new JButton();
        label2 = new JLabel();
        label3 = new JLabel();
        progressBar1 = new JProgressBar();
        infoLabel = new JLabel();

        //======== this ========
        setTitle("LaTeXtoXML");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

            // JFormDesigner evaluation mark
//			dialogPane.setBorder(new javax.swing.border.CompoundBorder(
//				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
//					"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
//					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
//					java.awt.Color.red), dialogPane.getBorder())); dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});


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

            //---- infoLabel ----
            infoLabel.setText("...");
            infoLabel.setFont(new Font("Calibri", Font.PLAIN, 14));

            GroupLayout dialogPaneLayout = new GroupLayout(dialogPane);
            dialogPane.setLayout(dialogPaneLayout);
            dialogPaneLayout.setHorizontalGroup(
                    dialogPaneLayout.createParallelGroup()
                            .addGroup(dialogPaneLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(dialogPaneLayout.createParallelGroup()
                                            .addComponent(progressBar1, GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                                            .addGroup(GroupLayout.Alignment.TRAILING, dialogPaneLayout.createSequentialGroup()
                                                    .addComponent(infoLabel, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(updateButton, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                                                    .addGap(5, 5, 5)
                                                    .addComponent(okButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))))
                            .addGroup(GroupLayout.Alignment.TRAILING, dialogPaneLayout.createSequentialGroup()
                                    .addGap(101, 101, 101)
                                    .addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(6, 6, 6)
                                    .addComponent(label3, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                                    .addGap(99, 99, 99))
            );
            dialogPaneLayout.setVerticalGroup(
                    dialogPaneLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, dialogPaneLayout.createSequentialGroup()
                                    .addGroup(dialogPaneLayout.createParallelGroup()
                                            .addGroup(dialogPaneLayout.createSequentialGroup()
                                                    .addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGap(3, 3, 3))
                                            .addGroup(dialogPaneLayout.createSequentialGroup()
                                                    .addGap(3, 3, 3)
                                                    .addComponent(label3, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(progressBar1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(dialogPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(infoLabel)
                                            .addGroup(dialogPaneLayout.createSequentialGroup()
                                                    .addGap(1, 1, 1)
                                                    .addComponent(okButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(dialogPaneLayout.createSequentialGroup()
                                                    .addGap(1, 1, 1)
                                                    .addComponent(cancelButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(updateButton))
                                    .addContainerGap())
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(dialogPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addComponent(dialogPane, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
