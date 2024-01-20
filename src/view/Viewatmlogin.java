package view;

import java.awt.event.*;
import java.net.URL;
import java.sql.SQLException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import Model.Modelatm;
import Model.logindao;
import controller.atmactionlistener;

public class Viewatmlogin extends JFrame {
	private Modelatm modelatm;
	public JTextField jtext_tendn;
	public JPasswordField jtext_matkhau;
	public JButton jbutton_thoat;
	public JButton jbutton_dangnhap;

	public static String acc;
	private void closeWindow() {
	    this.setVisible(false);
	    this.dispose();
	}

	public Viewatmlogin() throws HeadlessException {

		this.modelatm = new Modelatm();
		this.init();
	}

	private void init() {
		this.setTitle("ĐĂNG NHẬP");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		URL urlIconNotepad = Viewatmlogin.class.getResource("Icon-Vietcombank.png");
		Image img = Toolkit.getDefaultToolkit().createImage(urlIconNotepad);
		this.setIconImage(img);
		
		ActionListener ac = new atmactionlistener(this);

		Font font_chu = new Font("arial", Font.BOLD, 14);
		JLabel jlabel_tendn = new JLabel("Số tài khoản");
		jlabel_tendn.setFont(font_chu);
		JLabel jlable_matkhau = new JLabel("Mật khẩu     ");
		jlable_matkhau.setFont(font_chu);
		jtext_tendn = new JTextField();
		jtext_matkhau = new JPasswordField();
		jbutton_thoat = new JButton("Thoát");
		jbutton_thoat.addActionListener(ac);
		jbutton_dangnhap = new JButton("Đăng nhập");
		jbutton_dangnhap.addActionListener(ac);
		jbutton_dangnhap.setBackground(Color.CYAN);
		jbutton_thoat.setBackground(Color.CYAN);

		JPanel jpanel_tendn = new JPanel();
		Dimension setsize_jtext_tendn = new Dimension(170, 30);
		jtext_tendn.setPreferredSize(setsize_jtext_tendn);

		JPanel jpanel_matkhau = new JPanel();
		Dimension setsize_jtext_matkhau = new Dimension(170, 30);
		jtext_matkhau.setPreferredSize(setsize_jtext_matkhau);

		jpanel_tendn.add(jtext_tendn);
		jpanel_matkhau.add(jtext_matkhau);
		JPanel jpanel_tren = new JPanel();
		jpanel_tren.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 30));
		jpanel_tren.add(jlabel_tendn);
		jpanel_tren.add(jpanel_tendn);

		JPanel jpanel_giua = new JPanel();
		jpanel_giua.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 20));
		jpanel_giua.add(jlable_matkhau);
		jpanel_giua.add(jpanel_matkhau);

		JPanel jpanel_duoi = new JPanel();
		jpanel_duoi.add(jbutton_thoat);
		jpanel_duoi.add(jbutton_dangnhap);
		jpanel_duoi.setLayout(new FlowLayout(FlowLayout.CENTER, 65, 30));
		jpanel_duoi.setBackground(Color.LIGHT_GRAY);
		jpanel_duoi.setPreferredSize(new Dimension(500, 30));
		JPanel jpanel_tille = new JPanel();
		Font font_tille = new Font("arial", Font.BOLD, 30);

		JLabel jlabel_tille = new JLabel("QB BANK", JLabel.CENTER);
		jlabel_tille.setFont(font_tille);
		jlabel_tille.setForeground(Color.BLUE);
		jpanel_tille.add(jlabel_tille);
		jpanel_tille.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 40));

		this.add(jpanel_tille, BorderLayout.NORTH);
		this.add(jpanel_tren);
		this.add(jpanel_giua);
		this.add(jpanel_duoi, BorderLayout.SOUTH);
		this.setLayout(new GridLayout(4, 1, 10, 10));
		this.setVisible(true);
		
		jbutton_dangnhap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = jtext_tendn.getText();
				String password = new String(jtext_matkhau.getPassword());


				// Kiểm tra thông tin đăng nhập ở đây
				if (validateLogin(username, password)) {
					try {
						int a = logindao.getinstance().kiemtradangnhap1(username, password);
						if (a == 1) {
							acc = username;
                           
							new Viewatmdnthanhcong( acc, username).setVisible(true);;
							closeWindow();
						} else {
							JOptionPane.showMessageDialog(Viewatmlogin.this, "Số tài khoản hoặc mật khẩu",
									"Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
							jtext_tendn.setText("");
							jtext_matkhau.setText("");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(Viewatmlogin.this, "Thông tin đăng nhập không đúng", "Lỗi đăng nhập",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		jbutton_thoat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // Thoát ứng dụng
			}
		});
		
	}

	private boolean validateLogin(String username, String password) {
		// Thực hiện kiểm tra thông tin đăng nhập ở đây
		// Trả về true nếu thông tin hợp lệ, ngược lại trả về falseprivate boolean
		// validateLogin(String username, String password) {
		// Thực hiện kiểm tra thông tin đăng nhập ở đây
		// Trả về true nếu thông tin hợp lệ, ngược lại trả về false
		return true; // Mẫu giả - luôn trả về true
	}
public static void main(String[] args) {
	new Viewatmlogin();



	
}
	
}
