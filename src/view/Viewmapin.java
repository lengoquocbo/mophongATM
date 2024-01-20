package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Model.Modelatm;
import Model.logindao;

public class Viewmapin extends JFrame {
	private Modelatm modelatm;
	private String tiencanrutText;
	private String tienconlai;
	private String username;
	private String acc;
	private void closeWindow() {
	    this.setVisible(false);
	    this.dispose();
	}

	public Viewmapin(String tiencanrutText, String tienconlai,String acc, String username) {
		this.acc= acc;
		this.tiencanrutText = tiencanrutText;
		this.tienconlai = tienconlai;
		this.modelatm = new Modelatm();
		this.init();
	}

	private void init() {
		this.setTitle("NHẬP MÃ PIN");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		Font font_chu = new Font("arial", Font.BOLD, 17);

		URL urlIconNotepad = Viewatmlogin.class.getResource("Icon-Vietcombank.png");
		Image img = Toolkit.getDefaultToolkit().createImage(urlIconNotepad);
		this.setIconImage(img);

		JLabel jlabel_mapin = new JLabel("Nhập mã pin để thực hiện lệnh rút tiền");
		jlabel_mapin.setFont(font_chu);
		JPasswordField jtextfield = new JPasswordField(6);
		JButton jbutton_ruttien = new JButton("Rút tiền");
		jbutton_ruttien.setBackground(Color.CYAN);

		JPanel jpanel_tren = new JPanel();
		jpanel_tren.add(jlabel_mapin);
		jpanel_tren.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 40));

		JPanel jpanel_giua = new JPanel();
		// set kich thuoc thanh phan cho nhuwng thu pannel
		Dimension preferredSize = new Dimension(200, 30);
		jtextfield.setPreferredSize(preferredSize);

		jpanel_giua.add(jtextfield);
		JPanel jpanel_duoi = new JPanel();
		jpanel_duoi.add(jbutton_ruttien);
		jpanel_duoi.setBackground(Color.LIGHT_GRAY);
		jpanel_duoi.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 40));
		this.setLayout(new GridLayout(3, 1, 10, 10));
		this.add(jpanel_tren);
		this.add(jpanel_giua, BorderLayout.CENTER);
		this.add(jpanel_duoi, BorderLayout.SOUTH);
		this.setVisible(true);
		jbutton_ruttien.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String mapin = new String(jtextfield.getPassword());

				// Kiểm tra thông tin đăng nhập ở đây
				if (validateLogin(mapin)) {
					try {
						int a = logindao.getinstance().kiemtramapin(mapin);
						if (a == 1) {
							System.out.println("tiencanrut: " + tiencanrutText);
							System.out.println("tienconlai: " + tienconlai);
							System.out.println("acc"+acc);
							System.out.println("use"+username);
							new Viewgdthanhcong(tiencanrutText, tienconlai, username, acc).setVisible(true);
                           closeWindow();
						} else 
							JOptionPane.showMessageDialog(Viewmapin.this, "Mã pin không đúng ", "Lỗi nhập mã pin",
									JOptionPane.ERROR_MESSAGE);
							jtextfield.setText("");

						}
					 catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(Viewmapin.this, "Thông tin đăng nhập không đúng", "Lỗi đăng nhập",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	private boolean validateLogin(String mapin) {
		// Thực hiện kiểm tra thông tin đăng nhập ở đây
		// Trả về true nếu thông tin hợp lệ, ngược lại trả về falseprivate boolean
		// validateLogin(String username, String password) {
		// Thực hiện kiểm tra thông tin đăng nhập ở đây
		// Trả về true nếu thông tin hợp lệ, ngược lại trả về false
		return true; // Mẫu giả - luôn trả về true
	}

}
