/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

/**
 *
 * @author Admin
 */
public class ChamCong implements Comparable<ChamCong> {

    private String maChamCong;
    private String maNhanVien;
    private int thang;
    private int nam;
    private int soNgayLamViec;

    public ChamCong(String maChamCong, String maNhanVien, int thang, int nam, int soNgayLamViec) {
        this.maChamCong = maChamCong;
        this.maNhanVien = maNhanVien;
        this.thang = thang;
        this.nam = nam;
        this.soNgayLamViec = soNgayLamViec;
    }

    public String getMaChamCong() {
        return maChamCong;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public int getThang() {
        return thang;
    }

    public int getNam() {
        return nam;
    }

    public int getSoNgayLamViec() {
        return soNgayLamViec;
    }

    public void setMaChamCong(String maChamCong) {
        this.maChamCong = maChamCong;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public void setSoNgayLamViec(int soNgayLamViec) {
        this.soNgayLamViec = soNgayLamViec;
    }

    @Override
    public int compareTo(ChamCong o) {
        return this.maChamCong.compareTo(o.maChamCong);
    }

    @Override
    public String toString() {
        return "Mã chấm công: " + maChamCong
                + ", Mã nhân viên: " + maNhanVien
                + ", Tháng: " + thang
                + ", Năm: " + nam
                + ", Số ngày làm việc: " + soNgayLamViec;
    }
}
