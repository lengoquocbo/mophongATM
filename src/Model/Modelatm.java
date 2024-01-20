package Model;

public class Modelatm {
	private String tenguoidung;
	private String sotaikhoan;
	private String makhau;
	private String mapin;
	private int sotienhienco;
	public Modelatm() {
		
	}
	
	
	Modelatm(String tenguoidung, String sotaikhoan, String makhau, String mapin, int sotienhienco) {
		
		this.tenguoidung = tenguoidung;
		this.sotaikhoan = sotaikhoan;
		this.makhau = makhau;
		this.mapin = mapin;
		this.sotienhienco = sotienhienco;
	}

	private String sotienmuonrut;

	

	public String getTenguoidung() {
		return tenguoidung;
	}

	public void setTenguoidung(String tenguoidung) {
		this.tenguoidung = tenguoidung;
	}

	public String getSotaikhoan() {
		return sotaikhoan;
	}

	public void setSotaikhoan(String sotaikhoan) {
		this.sotaikhoan = sotaikhoan;
	}

	public String getMakhau() {
		return makhau;
	}

	public void setMakhau(String makhau) {
		this.makhau = makhau;
	}

	public String getMapin() {
		return mapin;
	}

	public void setMapin(String mapin) {
		this.mapin = mapin;
	}

	public int getSotienhienco() {
		return sotienhienco;
	}

	public void setSotienhienco(int sotienhienco) {
		this.sotienhienco = sotienhienco;
	}

	public String getSotienmuonrut() {
		return sotienmuonrut;
	}

	public void setSotienmuonrut(String sotienmuonrut) {
		this.sotienmuonrut = sotienmuonrut;
	}

	@Override
	public String toString() {
		return "Modelatm [tenguoidung=" + tenguoidung +  "]";
	}

	public String toString1() {
		return "Modelatm [sotienhienco=" + sotienhienco +  "]";
	}


	

}
