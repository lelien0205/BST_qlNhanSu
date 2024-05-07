/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package quanlynhansu_bst_lvtn;

import java.sql.*;
import Objects.*;
import BST.*;

/**
 *
 * @author Admin
 */
public class QuanLyNhanSu_BST_LVTN {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        Connection connection = DBConnector.connect();
        ResultSet phongResultSet = GenericClass.getResultSet(connection, "select * from phong");
        ResultSet chucVuResultSet = GenericClass.getResultSet(connection, "select * from chucvu");
        ResultSet nhanVienResultSet = GenericClass.getResultSet(connection, "select * from nhanvien");
        ResultSet chamCongResultSet = GenericClass.getResultSet(connection, "select * from chamcong");
        ResultSet luongResultSet = GenericClass.getResultSet(connection, "select * from luong");
        while (phongResultSet.next()) {
            String maPhong = phongResultSet.getString("MaPhong");
            String tenPhong = phongResultSet.getString("TenPhong");
            String diaChiPhong = phongResultSet.getString("DiaChiPhong");
            String soDienThoai = phongResultSet.getString("SoDienThoai");
            Phong newPhong = new Phong(maPhong, tenPhong, diaChiPhong, soDienThoai);
            MyTrees.phongTree.insert(newPhong);
        }
        while (chucVuResultSet.next()) {
            String maChucVu = chucVuResultSet.getString("MaChucVu");
            String tenChucVu = chucVuResultSet.getString("TenChucVu");
            double heSoPhuCap = chucVuResultSet.getDouble("HeSoPhuCap");
            ChucVu newChucVu = new ChucVu(maChucVu, tenChucVu, heSoPhuCap);
            MyTrees.chucVuTree.insert(newChucVu);
        }
        while (nhanVienResultSet.next()) {
            String maNhanVien = nhanVienResultSet.getString("MaNhanVien");
            String hoTen = nhanVienResultSet.getString("HoTen");
            String gioiTinh = nhanVienResultSet.getString("GioiTinh");
            Date ngaySinh = nhanVienResultSet.getDate("NgaySinh");
            String diaChi = nhanVienResultSet.getString("DiaChi");
            String soDienThoai = nhanVienResultSet.getString("SoDienThoai");
            String queQuan = nhanVienResultSet.getString("QueQuan");
            String maPhong = nhanVienResultSet.getString("MaPhong");
            String maChucVu = nhanVienResultSet.getString("MaChucVu");
            String matKhau = nhanVienResultSet.getString("MatKhau");
            boolean loaiNguoiDung = nhanVienResultSet.getBoolean("LoaiNguoiDung");
            double heSoLuong = nhanVienResultSet.getDouble("HeSoLuong");
            NhanVien newNhanVien = new NhanVien(maNhanVien, hoTen, gioiTinh, ngaySinh, diaChi, soDienThoai, queQuan, maPhong, maChucVu, matKhau, loaiNguoiDung, heSoLuong);
            MyTrees.nhanVienTree.insert(newNhanVien);
        }
        while (chamCongResultSet.next()) {
            String maChamCong = chamCongResultSet.getString("MaChamCong");
            String maNhanVien = chamCongResultSet.getString("MaNhanVien");
            int thang = chamCongResultSet.getInt("Thang");
            int nam = chamCongResultSet.getInt("Nam");
            int soNgayLamVien = chamCongResultSet.getInt("SoNgayLamViec");
            ChamCong newChamCong = new ChamCong(maChamCong, maNhanVien, thang, nam, soNgayLamVien);
            MyTrees.chamCongTree.insert(newChamCong);
        }
        while (luongResultSet.next()){
            String maLuong = luongResultSet.getString("MaLuong");
            String maNhanVien = luongResultSet.getString("MaNhanVien");
            int thang = luongResultSet.getInt("Thang");
            int nam = luongResultSet.getInt("Nam");
            double luong = luongResultSet.getDouble("Luong");
            Luong newLuong = new Luong(maLuong, maNhanVien, thang, nam, luong);
            MyTrees.luongTree.insert(newLuong);
        }
        DangNhap dangNhap = new DangNhap();
        dangNhap.setVisible(true);
    }
    
}
