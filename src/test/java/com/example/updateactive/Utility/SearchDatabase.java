package com.example.updateactive.Utility;

import java.sql.*;

public class SearchDatabase {
    //String path = "C:\\Users\\low85\\Desktop\\Active_2022 (version 1) (version 1).xlsx";
    Connection conn = DBConnection.startConnection();

    public ResultSet searchDatabase(String deptName){
        ResultSet resultSet = null;

        String sql = "SELECT location_code,mail_code,mail_code_secondary,mail_code_tertiary,other_location,hospital_mail_code FROM dept_location_mc WHERE dept_name = '" + deptName + "'";
       // String sql = "SELECT location_code,mail_code FROM dept_location_mc WHERE dept_name = '" + deptName + "'";

       try {

           Statement statement = conn.createStatement();

           statement.execute(sql);

           resultSet = statement.getResultSet();


       } catch (SQLException e) {

           throw new RuntimeException(e);
       }
       return resultSet;
    }

    public ResultSet searchDatabaseByDeptCode(int deptCode){
            ResultSet resultSet = null;

        try {
            String nextSQL = "SELECT location_code,mail_code,mail_code_secondary,mail_code_tertiary,other_location,hospital_mail_code FROM dept_location_mc WHERE dept_code = " + deptCode;

            //String nextSQL = "SELECT location_code,mail_code FROM dept_location_mc WHERE dept_code = " + deptCode;

            Statement nextStatement = conn.createStatement();

            nextStatement.execute(nextSQL);

            resultSet = nextStatement.getResultSet();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

        return resultSet;
    }
}
