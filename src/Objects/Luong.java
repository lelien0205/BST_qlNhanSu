/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

import java.text.Collator;

/**
 *
 * @author Admin
 */
public class Luong implements Comparable<Luong> {

    private String maLuong;
    private String maNhanVien;
    private int thang;
    private int nam;
    private double luong;

    public Luong(String maLuong, String maNhanVien, int thang, int nam, double luong) {
        this.maLuong = maLuong;
        this.maNhanVien = maNhanVien;
        this.thang = thang;
        this.nam = nam;
        this.luong = luong;
    }

    public String getMaLuong() {
        return maLuong;
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

    public double getLuong() {
        return luong;
    }

    public void setMaLuong(String maLuong) {
        this.maLuong = maLuong;
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

    public void setLuong(int luong) {
        this.luong = luong;
    }

    @Override
    public int compareTo(Luong o) {
        Collator collator = Collator.getInstance(java.util.Locale.forLanguageTag("vi-VN"));
        return collator.compare(this.maLuong, o.maLuong);
    }

    @Override
    public String toString() {
        return "Mã lương: " + maLuong
                + ", Mã nhân viên: " + maNhanVien
                + ", Tháng: " + thang
                + ", Năm: " + nam
                + ", Lương: " + (int) luong;
    }
}
