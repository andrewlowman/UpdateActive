package com.example.updateactive;

import com.example.updateactive.Selenium.FillForm;
import com.example.updateactive.Utility.LoadDepartmentList;
import com.example.updateactive.Utility.LoadExcel;
import com.example.updateactive.Utility.SearchDatabase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main extends JFrame {

    private JTextField nameTextField;
    private JTextField locationTextField;
    private JTextField mailcodeTextField;
    private JButton nextButton;
    private JPanel MainPanel;
    private JButton loadDeptButton;
    private JButton loadExcelButton;
    private JTextField sheetNumberTextField;

    private File excel = null;

    private int counter = 0;

    private LoadExcel loadExcel;

    public Main(){
        FillForm fillForm = new FillForm();
        setContentPane(MainPanel);
        setTitle("Update Directory");
        setSize(700,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        SearchDatabase searchDatabase = new SearchDatabase();

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                //fill text fields from excel file
                nameTextField.setText(loadExcel.getEmployeeFirstName(counter) + " " + loadExcel.getEmployeeLastName(counter));

                String deptName = loadExcel.getNameOfDept(counter);
                int deptCode = loadExcel.getDeptCode(counter);
                ResultSet rs = null;

                //search by name
                rs = searchDatabase.searchDatabase(deptName);
                int emplID = loadExcel.getEmployeeID(counter);

                try {
                    if(rs.isBeforeFirst()){
                        rs.next();
                        String locCode = rs.getString("location_code");
                        String mailCode = rs.getString("mail_code");

                        if(locCode==null||mailCode==null||mailCode.equals("VARIOUS MAIL CODE")){
                            rs = null;

                            //search by code
                            rs = searchDatabase.searchDatabaseByDeptCode(deptCode);

                            if(rs.isBeforeFirst()){
                                rs.next();
                                String locCode2 = rs.getString("location_code");
                                String mailCode2 = rs.getString("mail_code");

                                if(locCode==null||mailCode==null||mailCode.equals("VARIOUS MAIL CODE")){
                                    JDialog dialog = new JDialog(Main.this,"Help!");
                                    JLabel label = new JLabel("You're going to need to do it yourself");
                                    dialog.add(label);
                                    dialog.setSize(400,200);
                                    dialog.setVisible(true);

                                    counter++;
                                    return;
                                }

                                locationTextField.setText(locCode2);
                                mailcodeTextField.setText(mailCode2);

                                fillForm.nextEntry(emplID,locCode2,mailCode2);
                                counter++;
                                return;
                            }else{
                                JDialog dialog = new JDialog(Main.this,"Help!");
                                JLabel label = new JLabel("There is no department in the database");
                                dialog.add(label);
                                dialog.setSize(400,200);
                                dialog.setVisible(true);

                                //fillForm.clearPage();
                                counter++;
                                return;

                            }
                        }

                        locationTextField.setText(locCode);
                        mailcodeTextField.setText(mailCode);

                        fillForm.nextEntry(emplID,locCode,mailCode);
                    } else {
                        System.out.println("Other end");
                        rs = null;
                        rs = searchDatabase.searchDatabaseByDeptCode(deptCode);

                        if(rs.isBeforeFirst()){
                            rs.next();
                            String locCode = rs.getString("location_code");
                            String mailCode = rs.getString("mail_code");

                            if(locCode==null||mailCode==null||mailCode.equals("VARIOUS MAIL CODE")){
                                JDialog dialog = new JDialog(Main.this,"Help!");
                                JLabel label = new JLabel("You're going to need to do it yourself");
                                dialog.add(label);
                                dialog.setSize(400,200);
                                dialog.setVisible(true);

                                fillForm.clearPage();
                                counter++;
                                return;
                            }

                            locationTextField.setText(locCode);
                            mailcodeTextField.setText(mailCode);

                            fillForm.nextEntry(emplID,locCode,mailCode);
                        }else{
                            JDialog dialog = new JDialog(Main.this,"Help!");
                            JLabel label = new JLabel("There is no department in the database");
                            dialog.add(label);
                            dialog.setSize(400,200);
                            dialog.setVisible(true);

                            fillForm.clearPage();

                        }

                    }

                } catch (SQLException e) {

                    throw new RuntimeException(e);

                }

                counter++;
            }
        });
        loadDeptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                LoadDepartmentList loadDepartmentList = new LoadDepartmentList();

                JFileChooser jFileChooser = new JFileChooser();

                int jfile = jFileChooser.showOpenDialog(Main.this);

                if(jfile == JFileChooser.APPROVE_OPTION){
                    File selectedFile = jFileChooser.getSelectedFile();
                    loadDepartmentList.truncateTable();
                    loadDepartmentList.loadDepartment(selectedFile);
                }

            }
        });


        loadExcelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(sheetNumberTextField.getText().isEmpty()){
                    JDialog dialog = new JDialog(Main.this,"Help!");
                    JLabel label = new JLabel("There is no sheet number");
                    dialog.add(label);
                    dialog.setSize(400,200);
                    dialog.setVisible(true);

                    return;
                }
                JFileChooser fileChooser = new JFileChooser();

                int file = fileChooser.showOpenDialog(Main.this);

                if(file == JFileChooser.APPROVE_OPTION){
                    excel = fileChooser.getSelectedFile();
                    int sheetNumber = Integer.valueOf(sheetNumberTextField.getText());
                    loadExcel = new LoadExcel(excel,sheetNumber - 1);
                }
            }
        });
    }

    public static void main(String[] args) {

        new Main();

    }
}
