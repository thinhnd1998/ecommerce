package vn.com.stanford.ecommerce.ecommerce_quangthinh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.*;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.DonHangChiTietDao;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.DonHangDao;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.GioHangDao;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.SanPhamDao;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("gio-hang")
public class GioHangController {
    @Autowired
    GioHangDao gioHangDao;

    @Autowired
    SanPhamDao sanPhamDao;

    @Autowired
    DonHangDao donHangDao;

    @Autowired
    DonHangChiTietDao donHangChiTietDao;

    @GetMapping("view")
    public String viewCart(Model model) {
        model.addAttribute("gioHang", gioHangDao.getAllItems());
        model.addAttribute("tongTien", gioHangDao.getTotal());
        model.addAttribute("sanPhamSearch", new SanPhamViewModel());
        return "Cart";
    }

    @GetMapping("add/{id}")
    public String addCart(@PathVariable("id") String id) {
        SanPham objSanPham = sanPhamDao.layChiTietTheoMa(id);

        if (objSanPham != null) {
            GioHang item = new GioHang();
            item.setMaSanPham(objSanPham.getMaSanPham());
            item.setTenSanPham(objSanPham.getTenSanPham());
            item.setAnhSanPham(objSanPham.getAnhSanPham());
            item.setSoLuong(1);
            item.setGiaBan(objSanPham.getGiaBan());
            gioHangDao.add(item);
        }
        return "redirect:/gio-hang/view";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@RequestParam("id") String id, @RequestParam("qty") Integer qty) {
        gioHangDao.update(id, qty);
        return "redirect:/gio-hang/view";
    }

    @GetMapping("clear")
    public String clearCart() {
        gioHangDao.clear();
        return "redirect:/gio-hang/view";
    }

    @GetMapping("del/{id}")
    public String removeCart(@PathVariable("id") String id) {
        gioHangDao.remove(id);
        return "redirect:/gio-hang/view";
    }

    @GetMapping("checkout")
    public String hienThiCheckout(Model model) {
        model.addAttribute("gioHang", gioHangDao.getAllItems());
        model.addAttribute("tongTien", gioHangDao.getTotal());
        model.addAttribute("donHang", new DonHang());
        model.addAttribute("sanPhamSearch", new SanPhamViewModel());
        return "Checkout";
    }

    @RequestMapping(value = "thucHienCheckout", method = RequestMethod.POST)
    public String thucHienCheckout(@ModelAttribute("donHang")DonHang objDonHang, Model model, HttpSession session) {
        objDonHang.setTenDonHang("Đơn hàng ngày" + new Date());
        objDonHang.setNgayDat(new Date());
        objDonHang.setMaNhanVien(3);
        objDonHang.setTrangThai("Chờ xác nhận");
        objDonHang.setMaKhachHang(Integer.parseInt(session.getAttribute("userId")+""));

        boolean ketQua = false, ketQua2 = false;

        ketQua = donHangDao.themMoi(objDonHang);

        for (GioHang cart : gioHangDao.getAllItems()) {
            DonHangChiTiet objDonHangCT = new DonHangChiTiet();
            objDonHangCT.setMaDonHang(objDonHang.getId());
            objDonHangCT.setMaSanPham(cart.getMaSanPham());
            objDonHangCT.setSoLuong(cart.getSoLuong());
            objDonHangCT.setDonGia(cart.getGiaBan());
            ketQua2 = donHangChiTietDao.themMoi(objDonHangCT);
        }

        if (ketQua && ketQua2) {
            return "redirect:/checkoutSuccess";
        }

        return "Checkout";
    }

}
