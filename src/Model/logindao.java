package Model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectdb.connectdatabase;
import Model.Modelatm;

public class logindao {
	private String acc;
	public static logindao getinstance() {
		return new logindao();
	}

	public int kiemtradangnhap1(String sotaikhoan, String matkhau) throws SQLException {
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// Get a connection to the database
			connection = connectdatabase.getConnection();

			// SQL query to check login credentials
			String query = "SELECT * FROM khachang WHERE sotaikhoan = ? AND matkhau = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, sotaikhoan);
			preparedStatement.setString(2, matkhau);

			// Execute the query
			resultSet = preparedStatement.executeQuery();

			// Check if the result set has any rows
			if (resultSet.next()) {
				// Login successful
				result = 1;
			} else {
				// Login failed
				result = 0;
			}
		} finally {
			// Close resources in the reverse order of their creation to avoid resource
			// leaks
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

		return result;
	}

	public int kiemtramapin(String mapin) throws SQLException {
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// Get a connection to the database
			connection = connectdatabase.getConnection();
			// SQL query to check login credentials
			String query = "SELECT * FROM khachang WHERE  mapin = ? ";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, mapin);

			// Execute the query
			resultSet = preparedStatement.executeQuery();
			// Check if the result set has any rows
			if (resultSet.next()) {
				// Login successful
				result = 1;
			} else {
				// Login failed
				result = 0;
			}
		} finally {
			// Close resources in the reverse order of their creation to avoid resource
			// leaks
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

		return result;

	}

//	public void capNhatDuLieu(String tienconlai, String sotaikhoan) {
//	    Connection connection = null;
//	    PreparedStatement statement = null;
//
//	    try {
//	        // Kết nối đến cơ sở dữ liệu
//			connection = connectdatabase.getConnection();
//	        // Chuẩn bị truy vấn SQL
//	        String query = "UPDATE khachang SET tienhienco = ? WHERE sotaikhoan=?";
//	        statement = connection.prepareStatement(query);
//
//	        // Thiết lập tham số cho truy vấn
//	        statement.setString(1, tienconlai);
//	        statement.setString(2, acc);
//
//	        // Thực hiện cập nhật
//	        int rowsAffected = statement.executeUpdate();
//
//	        // Kiểm tra số dòng bị ảnh hưởng
//	        if (rowsAffected > 0) {
//	            System.out.println("Dữ liệu đã được cập nhật thành công.");
//	        } else {
//	            System.out.println("Không tìm thấy dữ liệu phù hợp để cập nhật.");
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    } finally {
//	        // Đóng kết nối và tài nguyên
//	        try {
//	            if (statement != null) {
//	                statement.close();
//	            }
//	            if (connection != null) {
//	                connection.close();
//	            }
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	    }
//	}

}
