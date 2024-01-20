package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.Modelatm;
import connectdb.connectdatabase;

public class Viewgdthanhcong extends JFrame {
    private Modelatm modelatm;
    private JLabel jlabel_gdthanhcong;
    private JLabel jlabel_tienrutduoc;
    private JLabel jlabel_tienconlai;
    private JLabel jlabel_trongtienconlai;
    private JButton jbutton_thoat;
    private JLabel jlabel_trongtienrutduoc;
    private String tiencanrutText;
    private String tienconlai;
    private String username;
    private void closeWindow() {
	    this.setVisible(false);
	    this.dispose();
	}
    public Viewgdthanhcong(String tiencanrutText, String tienconlai, String acc,String username) throws HeadlessException {
        this.tiencanrutText = tiencanrutText;
        this.tienconlai= tienconlai;
        this.username = username;
        this.modelatm = new Modelatm();
        this.init();
        this.capNhatDuLieu(tienconlai, username);
    }
    public void capNhatDuLieu(String tienconlai, String username) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Kết nối đến cơ sở dữ liệu
           Connection con = connectdatabase.getConnection();
            String query = "UPDATE khachang SET sotienhienco = ? WHERE sotaikhoan=?";
            statement = con.prepareStatement(query);

            // Thiết lập tham số cho truy vấn
            statement.setString(1, tienconlai);
            statement.setString(2, username);

            // Thực hiện cập nhật
            int rowsAffected = statement.executeUpdate();

            // Kiểm tra số dòng bị ảnh hưởng
            if (rowsAffected > 0) {
                System.out.println("Dữ liệu đã được cập nhật thành công.");
            } else {
                System.out.println("Không tìm thấy dữ liệu phù hợp để cập nhật.");
                System.out.println(username);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và tài nguyên
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void init() {
        this.setTitle("GIAO DỊCH...");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        Font font_chu = new Font("arial", Font.BOLD, 15);

        URL urlIconNotepad = Viewatmlogin.class.getResource("Icon-Vietcombank.png");
        Image img = Toolkit.getDefaultToolkit().createImage(urlIconNotepad);
        this.setIconImage(img);

        jlabel_gdthanhcong = new JLabel("CHÚC MỪNG BẠN ĐÃ RÚT TIỀN THÀNH CÔNG ", JLabel.CENTER);
        jlabel_gdthanhcong.setForeground(Color.green);
        jlabel_gdthanhcong.setFont(font_chu);
        jlabel_tienrutduoc = new JLabel("SỐ TIỀN BẠN RÚT ĐƯỢC LÀ: ");
        jlabel_tienconlai = new JLabel("SỐ TIỀN CÒN LẠI: ");
        jlabel_trongtienconlai = new JLabel();
        jlabel_trongtienrutduoc = new JLabel();
        jbutton_thoat = new JButton("Đóng");
        jbutton_thoat.setBackground(Color.cyan);

        jlabel_gdthanhcong.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        JPanel jpanel_giuatren = new JPanel();
        jpanel_giuatren.add(jlabel_tienrutduoc);
        jpanel_giuatren.add(jlabel_trongtienrutduoc);
        jpanel_giuatren.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));

        JPanel jpanel_giua = new JPanel();
        jpanel_giua.add(jlabel_tienconlai);
        jpanel_giua.add(jlabel_trongtienconlai);
        jpanel_giua.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 1));

        JPanel jpanel_duoi = new JPanel();
        jpanel_duoi.add(jbutton_thoat);
        jpanel_duoi.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 30));
        jpanel_duoi.setBackground(Color.LIGHT_GRAY);

        jlabel_trongtienrutduoc.setText(tiencanrutText + "  VND");
        jlabel_trongtienrutduoc.setFont(font_chu);
    
        jlabel_trongtienconlai.setText(tienconlai + "  VND");
        jlabel_trongtienconlai.setFont(font_chu);
        this.setLayout(new GridLayout(4, 1));
        this.add(jlabel_gdthanhcong);
        this.add(jpanel_giuatren);
        this.add(jpanel_giua);
        this.add(jpanel_duoi);
        this.setVisible(true);
        jbutton_thoat.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Viewatmlogin().setVisible(true);
                closeWindow();
            }
        });
    }

    

   
}