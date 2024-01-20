package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import connectdb.connectdatabase;
import controller.atmactionlistener;

public class Viewatmdnthanhcong extends JFrame {
	private JButton jbutton_rut;
	private JLabel jlabel_tienhienco;
	private JLabel jlabel_tiencanrut;
	private JLabel jlabel_tienconlai;
	private JLabel jLabel_trongtienhientai;
	private JLabel jLabel_trongtienconlai;
	private JLabel jlabel_tronghoten;
	public int sotien;
	public static String sotienrutduoc;
	public int tienconlai1;
	private String acc;
	private String username;

	private void closeWindow() {
		this.setVisible(false);
		this.dispose();
	}

	public Viewatmdnthanhcong(String acc, String username) throws HeadlessException {
		this.acc = acc;
		this.username = username;
		init();
		System.out.println(acc);

		getData(acc);
	}

	private void getData(String acc) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			Connection con = connectdatabase.getConnection();
			String query = "SELECT * FROM khachang WHERE sotaikhoan = ?";
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, acc);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				// Retrieve data from the result set
				String hoTen = resultSet.getString("hovaten");
				sotien = resultSet.getInt("sotienhienco");
//                sotienrutduoc = sotien ;
				// Update the labels with the retrieved data
				jlabel_tronghoten.setText(hoTen);
				jLabel_trongtienhientai.setText(sotien + "    VND");
				System.out.println(hoTen);
				System.out.println(sotien);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Close the database resources
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void init() {
		setTitle("GIAO DỊCH");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		ActionListener ac = new atmactionlistener(this);
		URL urlIconNotepad = Viewatmlogin.class.getResource("Icon-Vietcombank.png");
		Image img = Toolkit.getDefaultToolkit().createImage(urlIconNotepad);
		this.setIconImage(img);

		JLabel jlaybel_hoten = new JLabel("               Họ và tên: ");
		jlabel_tienhienco = new JLabel("               Số tiền hiện có: ");
		jlabel_tiencanrut = new JLabel("Số tiền cần rút");
		jLabel_trongtienhientai = new JLabel();
		jlabel_tronghoten = new JLabel();

		JTextField jtext_tiencanrut = new JTextField(15);
		jbutton_rut = new JButton("Tiếp tục");
		jbutton_rut.setBackground(Color.cyan);
		jbutton_rut.addActionListener(ac);
		JPanel jpanel_tren = new JPanel();
		jpanel_tren.setLayout(new GridLayout(2, 2, 10, 1));
		jpanel_tren.add(jlaybel_hoten);
		jpanel_tren.add(jlabel_tronghoten);
		jpanel_tren.add(jlabel_tienhienco);
		jpanel_tren.add(jLabel_trongtienhientai);

		JPanel jpanel_giuatren = new JPanel();
		jpanel_giuatren.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 30));
		jpanel_giuatren.add(jlabel_tiencanrut);
		jpanel_giuatren.add(jtext_tiencanrut);

		JPanel jpanel_duoicung = new JPanel();
		jpanel_duoicung.add(jbutton_rut);
		jpanel_duoicung.setBackground(Color.LIGHT_GRAY);
		jpanel_duoicung.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 50));

		this.add(jpanel_tren);
		this.add(jpanel_giuatren);

		this.add(jpanel_duoicung);
		this.setLayout(new GridLayout(3, 1, 0, 0));

		this.setVisible(true);
		jbutton_rut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String tiencanrutText = jtext_tiencanrut.getText();
				String acc = jlabel_tronghoten.getText();

				// Convert the text to an integer
				int tiencanrut;

				try {
					tiencanrut = Integer.parseInt(tiencanrutText);

				} catch (NumberFormatException e1) {
					// Handle the case where the text is not a valid integer
					tiencanrut = 0; // or another default valu
				}
				System.out.println(tiencanrut + "   VND");

				tienconlai1 = sotien - tiencanrut;
				String tienconlai = String.valueOf(tienconlai1);

				if (tienconlai1 >= 0) {
					if (tiencanrut >= 20000 && tiencanrut <= 50000000) {
						System.out.println("sotienconlaila: " + tienconlai1 + " VND");
						System.out.println("acc" + acc);
						System.out.println("username" + username);
						new Viewmapin(tiencanrutText, tienconlai, username, acc);
						closeWindow();
					} else {
						JOptionPane.showMessageDialog(Viewatmdnthanhcong.this,
								"Số tiền muốn rút cần nằm trong khoảng 20 000 VND đến 50 000 000 VND",
								"SỐ TIỀN MUỐN RÚT KHÔNG HỢP LỆ", JOptionPane.ERROR_MESSAGE);
						jtext_tiencanrut.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(Viewatmdnthanhcong.this,
							"Số tiền muốn rút cần đã vượt quá số tiền bạn có ", "SỐ TIỀN MUỐN RÚT KHÔNG HỢP LỆ",
							JOptionPane.ERROR_MESSAGE);
					jtext_tiencanrut.setText("");
				}
			}
		});

	}

	public static void main(String[] args) {
		String acc = "your_account_number"; // Replace with your actual account number
		new Viewatmdnthanhcong(acc, acc);
	}

}