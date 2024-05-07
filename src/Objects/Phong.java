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
public class Phong implements Comparable<Phong> {

    private String maPhong;
    private String tenPhong;
    private String diaChiPhong;
    private String soDienThoai;

    public Phong(String maPhong, String tenPhong, String diaChiPhong, String soDienThoai) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.diaChiPhong = diaChiPhong;
        this.soDienThoai = soDienThoai;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public String getDiaChiPhong() {
        return diaChiPhong;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public void setDiaChiPhong(String diaChiPhong) {
        this.diaChiPhong = diaChiPhong;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    @Override
    public int compareTo(Phong o) {
        Collator collator = Collator.getInstance(java.util.Locale.forLanguageTag("vi-VN"));
        return collator.compare(this.maPhong, o.maPhong);
    }

    @Override
    public String toString() {
        return "Mã phòng: " + maPhong
                + ", Tên phòng: " + tenPhong
                + ", Địa chỉ phòng: " + diaChiPhong
                + ", Số điện thoại: " + soDienThoai;
    }
}
