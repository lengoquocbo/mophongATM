package controller;

import java.sql.Connection;

import connectdb.connectdatabase;

public class atmcontroller {
public static void main(String[] args) {
	Connection  connection = connectdatabase.getConnection();
	System.out.println(" connection success "+connection );
	connectdatabase.closeConnection(connection);
System.out.println(" ngat ket noi"+ connection);

}
}
