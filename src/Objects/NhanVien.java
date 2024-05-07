/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

import java.text.Collator;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class NhanVien implements Comparable<NhanVien> {

    private String maNhanVien;
    private String hoTen;
    private String gioiTinh;
    private Date ngaySinh;
    private String diaChi;
    private String soDienThoai;
    private String queQuan;
    private String maPhong;
    private String maChucVu;
    private String matKhau;
    private boolean loaiNguoiDung;
    private double heSoLuong;

    public NhanVien(String maNhanVien, String hoTen, String gioiTinh, Date ngaySinh, String diaChi, String soDienThoai, String queQuan, String maPhong, String maChucVu, String matKhau, boolean loaiNguoiDung, double heSoLuong) {
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.queQuan = queQuan;
        this.maPhong = maPhong;
        this.maChucVu = maChucVu;
        this.matKhau = matKhau;
        this.loaiNguoiDung = loaiNguoiDung;
        this.heSoLuong = heSoLuong;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public String getMaChucVu() {
        return maChucVu;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public boolean isLoaiNguoiDung() {
        return loaiNguoiDung;
    }

    public double getHeSoLuong() {
        return heSoLuong;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public void setMaChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setLoaiNguoiDung(boolean loaiNguoiDung) {
        this.loaiNguoiDung = loaiNguoiDung;
    }

    public void setHeSoLuong(double heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    @Override
    public int compareTo(NhanVien o) {
        Collator collator = Collator.getInstance(java.util.Locale.forLanguageTag("vi-VN"));
        return collator.compare(this.maNhanVien, o.maNhanVien);
    }

    @Override
    public String toString() {
        return "Mã nhân viên: " + maNhanVien
                + ", Họ tên: " + hoTen
                + ", Giới tính: " + gioiTinh
                + ", Ngày sinh: " + ngaySinh
                + ", Địa chỉ: " + diaChi
                + ", Số điện thoại: " + soDienThoai
                + ", Quê quán: " + queQuan
                + ", Mã phòng: " + maPhong
                + ", Mã chức vụ: " + maChucVu
                + ", Mật khẩu: " + matKhau
                + ", Loại người dùng: " + loaiNguoiDung
                + ", Hệ số lương: " + heSoLuong;
    }
}
