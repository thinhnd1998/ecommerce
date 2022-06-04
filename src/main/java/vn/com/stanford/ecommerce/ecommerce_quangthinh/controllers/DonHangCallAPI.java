package vn.com.stanford.ecommerce.ecommerce_quangthinh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.*;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.DonHangDao;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.KhachHangDao;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.NguoiDungDao;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.NhanVienDao;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("call-api")
public class DonHangCallAPI {
    @Autowired
    DonHangDao donHangDao;

    @RequestMapping(value = "donhang/all")
    public String hienThiDanhSachDonHang(Model model, HttpSession session) {
        List<DonHangExtend> lstDonHang = donHangDao.layDanhSachExtend();

        model.addAttribute("lstDonHang", lstDonHang);
        model.addAttribute("donHangSearch", new DonHangViewModel());
        model.addAttribute("QL_DONHANG_XEM", session.getAttribute("QL_DONHANG_XEM"));
        model.addAttribute("QL_DONHANG_THEM", session.getAttribute("QL_DONHANG_THEM"));
        model.addAttribute("QL_DONHANG_SUA", session.getAttribute("QL_DONHANG_SUA"));
        model.addAttribute("QL_DONHANG_XOA", session.getAttribute("QL_DONHANG_XOA"));

        if (Integer.parseInt(session.getAttribute("QL_DONHANG_XEM") + "") == 1) {
            return "QuanLyDonHang";
        } else {
            return "AuthErr";
        }
    }

    @RequestMapping(value = "donhang/timkiem")
    public String timKiemDonHang(@ModelAttribute("donHangSearch") DonHangViewModel donHang, Model model, HttpSession session) {
        List<DonHangExtend> lstDonHang = donHangDao.timKiemDonHang(donHang.getTuKhoa());
        model.addAttribute("lstDonHang", lstDonHang);
        model.addAttribute("donHangSearch", donHang);
        model.addAttribute("QL_DONHANG_XEM", session.getAttribute("QL_DONHANG_XEM"));
        model.addAttribute("QL_DONHANG_THEM", session.getAttribute("QL_DONHANG_THEM"));
        model.addAttribute("QL_DONHANG_SUA", session.getAttribute("QL_DONHANG_SUA"));
        model.addAttribute("QL_DONHANG_XOA", session.getAttribute("QL_DONHANG_XOA"));

        if (Integer.parseInt(session.getAttribute("QL_DONHANG_XEM") + "") == 1) {
            return "QuanLyDonHang";
        } else {
            return "AuthErr";
        }
    }

    @Autowired
    NguoiDungDao nguoiDungDao;
    @ModelAttribute("lstKhachHang")
    public List<NguoiDung> hienThiDanhSachKhachHang() {
        List<NguoiDung> lstNguoiDung = nguoiDungDao.layDanhSach();
        List<NguoiDung> lstKhachHang = new ArrayList<>();
        for (int i = 0; i < lstNguoiDung.size(); i++) {
            if (lstNguoiDung.get(i).getVaiTroID() == 2) {
                lstKhachHang.add(lstNguoiDung.get(i));
            }
        }

        return lstKhachHang;
    }

    @ModelAttribute("lstNhanVien")
    public List<NguoiDung> hienThiDanhSachNhanVien() {
        List<NguoiDung> lstNguoiDung = nguoiDungDao.layDanhSach();
        List<NguoiDung> lstNhanVien = new ArrayList<>();

        for (int i = 0; i < lstNguoiDung.size(); i++) {
            if (lstNguoiDung.get(i).getVaiTroID() == 4 || lstNguoiDung.get(i).getVaiTroID() == 1 || lstNguoiDung.get(i).getVaiTroID() == 3) {
                lstNhanVien.add(lstNguoiDung.get(i));
            }
        }

        return lstNhanVien;
    }
}
