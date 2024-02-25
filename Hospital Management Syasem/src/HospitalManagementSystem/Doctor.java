package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
    private Connection connection;

    public Doctor(Connection connection){
        this.connection = connection;


    }


    public void viewDoctors(){
        String quary = "select * from Doctors";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(quary);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Doctors: ");
            System.out.println("+-------------+-----------------+------------------+");
            System.out.println("| Doctor Id   | Name            | specilization    |");
            System.out.println("+-------------+-----------------+------------------+");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getNString("Name");
                String specialization = resultSet.getNString("specialization");
                System.out.printf("|  %-11s|  %-15s|  %-16s|\n",id,name, specialization);
                System.out.println("+-------------+-----------------+------------------+");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean getDoctorByID(int id){
        String query = "SELECT * FROM doctors WHERE id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
