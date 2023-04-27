/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import interface_dao.ThongKeInterface;
import java.time.LocalDate;
import java.util.ArrayList;
import model.sanpham.SanPham;
import java.sql.*;
import model.share.ConnectDB;

/**
 *
 * @author thanh
 */
public class ThongKe_dao implements ThongKeInterface {

    @Override
    public ArrayList<SanPham> get3sanPhamBanChay() {
        int thang = LocalDate.now().getMonthValue();
        int nam = LocalDate.now().getYear();
        ArrayList<SanPham> result = new ArrayList<>();

        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("""
                                                                   select top 3 maSanPham
                                                                   from ChiTietHoaDon as ct join HoaDon as hd on ct.maHoaDon = hd.maHoaDon
                                                                   where MONTH(ngayLap) = ? and Year(NgayLap) = ?
                                                                   group by maSanPham 
                                                                   order by SUM(soLuong) desc""");
            st.setInt(1, thang);
            st.setInt(2, nam);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String maSP = rs.getString(1);
                result.add(
                        new SanPham_dao().getSanPhamTheoMa(maSP).get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public ArrayList<SanPham> get3sanPhamBanChay(int thang) {
        int nam = LocalDate.now().getYear();
        ArrayList<SanPham> result = new ArrayList<>();

        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("""
                                                                   select top 3 maSanPham
                                                                   from ChiTietHoaDon as ct join HoaDon as hd on ct.maHoaDon = hd.maHoaDon
                                                                   where MONTH(ngayLap) = ? and Year(NgayLap) = ?
                                                                   group by maSanPham 
                                                                   order by SUM(soLuong) desc""");
            st.setInt(1, thang);
            st.setInt(2, nam);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String maSP = rs.getString(1);
                result.add(
                        new SanPham_dao().getSanPhamTheoMa(maSP).get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public ArrayList<SanPham> get3sanPhamBanChay(int thang, int nam) {
        ArrayList<SanPham> result = new ArrayList<>();

        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("""
                                                                   select top 3 maSanPham
                                                                   from ChiTietHoaDon as ct join HoaDon as hd on ct.maHoaDon = hd.maHoaDon
                                                                   where MONTH(ngayLap) = ? and Year(NgayLap) = ?
                                                                   group by maSanPham 
                                                                   order by SUM(soLuong) desc""");
            st.setInt(1, thang);
            st.setInt(2, nam);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String maSP = rs.getString(1);
                result.add(
                        new SanPham_dao().getSanPhamTheoMa(maSP).get(0));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public double getDoanhThuSanPham(String maSP) {
        int thang = LocalDate.now().getMonthValue();
        int nam = LocalDate.now().getYear();
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("""
                                                                   select sum(ct.tongTien)
                                                                   from ChiTietHoaDon as ct join HoaDon as hd on ct.maHoaDon = hd.maHoaDon
                                                                   where maSanPham = ? and MONTH(ngayLap) = ? and Year(NgayLap) = ?
                                                                   group by maSanPham                                               
                                                                   """);
            st.setString(1, maSP);
            st.setInt(2, thang);
            st.setInt(3, nam);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public double getDoanhThuSanPham(String maSP, int thang) {
        int nam = LocalDate.now().getYear();
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("""
                                                                   select sum(ct.tongTien)
                                                                   from ChiTietHoaDon as ct join HoaDon as hd on ct.maHoaDon = hd.maHoaDon
                                                                   where maSanPham = ? and MONTH(ngayLap) = ? and Year(NgayLap) = ?
                                                                   group by maSanPham                                               
                                                                   """);
            st.setString(1, maSP);
            st.setInt(2, thang);
            st.setInt(3, nam);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public double getDoanhThuSanPham(String maSP, int thang, int nam) {
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("""
                                                                   select sum(ct.tongTien)
                                                                   from ChiTietHoaDon as ct join HoaDon as hd on ct.maHoaDon = hd.maHoaDon
                                                                   where maSanPham = ? and MONTH(ngayLap) = ? and Year(NgayLap) = ?
                                                                   group by maSanPham                                               
                                                                   """);
            st.setString(1, maSP);
            st.setInt(2, thang);
            st.setInt(3, nam);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public double[] getDoanhThu12Thang() {
        double[] result = new double[12];

        for (int i = 0; i < result.length; i++) {
            result[i] = 0;
        }

        int nam = LocalDate.now().getYear();

        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("select MONTH(ngayLap), sum(tongTien) from HoaDon where YEAR(ngayLap) = ? group by MONTH(ngayLap)");
            st.setInt(1, nam);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int thang = rs.getInt("thang");
                double tong = rs.getDouble("tong");

                result[thang - 1] = tong;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}