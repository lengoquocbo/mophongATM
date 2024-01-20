package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import Model.logindao;
import view.Viewatmdnthanhcong;
import view.Viewatmlogin;
import view.Viewgdthanhcong;
import view.Viewmapin;

public class atmactionlistener implements ActionListener {
    private Viewatmlogin vlg;
    private Viewatmdnthanhcong vlgtc;
    private Viewmapin vmp;
    private Viewgdthanhcong vdgtc;
    private JTextField jtext_tendn;
    private JPasswordField jtext_matkhau;

    public atmactionlistener(JTextField jtext_tendn, JPasswordField jtext_matkhau) {
        this.jtext_tendn = jtext_tendn;
        this.jtext_matkhau = jtext_matkhau;
    }

    public atmactionlistener(Viewatmlogin vlg) {
        this.vlg = vlg;
    }

 

    public atmactionlistener(Viewatmdnthanhcong vlgtc) {
        this.vlgtc = vlgtc;
    }

    public atmactionlistener(Viewmapin vmp) {
        this.vmp = vmp;
    }

    
    public atmactionlistener(Viewgdthanhcong vdgtc) {
        this.vdgtc = vdgtc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        String sotaikhoan = jtext_tendn.getText();
        String matkhau = new String(jtext_matkhau.getPassword());

        if ("Đăng nhập".equals(src)) {
            try {
                int result = logindao.getinstance().kiemtradangnhap1(sotaikhoan, matkhau);
                if (result == 1) {
                    JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
                } else {
                    JOptionPane.showMessageDialog(null, "Đăng nhập thất bại");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } else if ("Thoát".equals(src)) {
            System.exit(0);
        }
    }
}
