/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.KhoHang_dao;
import interface_dao.KhoHangInterface;
import java.util.ArrayList;
import model.kho.KhoHang;

/**
 *
 * @author thanh
 */
public class KhoHang_bus implements KhoHangInterface {

    private KhoHang_dao dao;

    public KhoHang_bus() {
        dao = new KhoHang_dao();
    }

    @Override
    public ArrayList<KhoHang> getAllKhoHang() {
        return dao.getAllKhoHang();
    }

    @Override
    public ArrayList<KhoHang> getKhoHangTheoMa(String maKho) {
        return dao.getKhoHangTheoMa(maKho);
    }
}