package vn.com.stanford.ecommerce.ecommerce_quangthinh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.*;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.AnhSanPhamDao;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.DanhMucDao;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.SanPhamDao;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.ThuongHieuDao;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Controller
@RequestMapping("call-api")
public class SanPhamCallAPI {
    @Autowired
    SanPhamDao sanPhamDao;

    @Autowired
    AnhSanPhamDao anhSanPhamDao;

    @RequestMapping(value = "sanpham/all")
    public String hienThiDanhSachSanPham(Model model, HttpSession session) {
        List<SanPhamExtend> lstSanPham = sanPhamDao.layDanhSachExtend();

        model.addAttribute("lstSanPham", lstSanPham);
        model.addAttribute("sanPhamSearch", new SanPhamViewModel());
        model.addAttribute("QL_SANPHAM_XEM", session.getAttribute("QL_SANPHAM_XEM"));
        model.addAttribute("QL_SANPHAM_THEM", session.getAttribute("QL_SANPHAM_THEM"));
        model.addAttribute("QL_SANPHAM_SUA", session.getAttribute("QL_SANPHAM_SUA"));
        model.addAttribute("QL_SANPHAM_XOA", session.getAttribute("QL_SANPHAM_XOA"));

        if (Integer.parseInt(session.getAttribute("QL_SANPHAM_XEM") + "") == 1) {
            return "QuanLySanPham";
        } else {
            return "AuthErr";
        }
    }

    @RequestMapping(value = "sanpham/timkiem")
    public String timKiemSanPham(@ModelAttribute("sanPhamSearch") SanPhamViewModel sanPham, Model model, HttpSession session) {
        List<SanPhamExtend> lstSanPham = sanPhamDao.timKiemSanPham(sanPham.getTuKhoa(), sanPham.getDanhMucId(),
                sanPham.getThuongHieuId(), sanPham.getStartDate(), sanPham.getEndDate(), sanPham.getDuyet());

        model.addAttribute("lstSanPham", lstSanPham);
        model.addAttribute("sanPhamSearch", sanPham);
        model.addAttribute("QL_SANPHAM_XEM", session.getAttribute("QL_SANPHAM_XEM"));
        model.addAttribute("QL_SANPHAM_THEM", session.getAttribute("QL_SANPHAM_THEM"));
        model.addAttribute("QL_SANPHAM_SUA", session.getAttribute("QL_SANPHAM_SUA"));
        model.addAttribute("QL_SANPHAM_XOA", session.getAttribute("QL_SANPHAM_XOA"));
        if (Integer.parseInt(session.getAttribute("QL_SANPHAM_XEM") + "") == 1) {
            return "QuanLySanPham";
        } else {
            return "AuthErr";
        }
    }

    @Autowired
    DanhMucDao danhMucDao;

    @ModelAttribute("lstDanhMuc")
    public List<DanhMuc> hienThiDanhSachDanhMuc() {
        List<DanhMuc> lstDanhMuc = danhMucDao.layDanhSach();

        return lstDanhMuc;
    }

    @Autowired
    ThuongHieuDao thuongHieuDao;

    @ModelAttribute("lstThuongHieu")
    public List<ThuongHieu> hienThiDanhSachThuongHieu() {
        List<ThuongHieu> lstThuongHieu = thuongHieuDao.layDanhSach();

        return lstThuongHieu;
    }
}
