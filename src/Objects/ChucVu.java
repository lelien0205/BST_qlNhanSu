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
public class ChucVu implements Comparable<ChucVu> {

    private String maChucVu;
    private String tenChucVu;
    private double heSoPhuCap;

    public ChucVu(String maChucVu, String tenChucVu, double heSoPhuCap) {
        this.maChucVu = maChucVu;
        this.tenChucVu = tenChucVu;
        this.heSoPhuCap = heSoPhuCap;
    }

    public String getMaChucVu() {
        return maChucVu;
    }

    public String getTenChucVu() {
        return tenChucVu;
    }

    public double getHeSoPhuCap() {
        return heSoPhuCap;
    }

    public void setMaChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
    }

    public void setHeSoPhuCap(double heSoPhuCap) {
        this.heSoPhuCap = heSoPhuCap;
    }

    @Override
    public int compareTo(ChucVu o) {
        Collator collator = Collator.getInstance(java.util.Locale.forLanguageTag("vi-VN"));
        return collator.compare(this.maChucVu, o.maChucVu);
    }

    @Override
    public String toString() {
        return "Mã chức vụ: " + maChucVu
                + ", Tên chức vụ: " + tenChucVu
                + ", Hệ số phụ cấp: " + heSoPhuCap;
    }
}
