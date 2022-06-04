package vn.com.stanford.ecommerce.ecommerce_quangthinh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.*;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class SanPhamClientController {
    @Autowired
    SanPhamDao sanPhamDao;

    @Autowired
    DanhMucDao danhMucDao;

    @Autowired
    ThuongHieuDao thuongHieuDao;

//    @GetMapping(value = "trang-chu")
//    public String hienThiDanhSachSP(Model model, HttpSession session) {
//
//        //Lấy danh sách sách
//        List<SanPham> lstSanPham = sanPhamDao.layDanhSach();
//
//        //Đưa để hiển thị ra view
//        model.addAttribute("lstSanPham", lstSanPham);
//        model.addAttribute("sanPhamSearch", new SanPhamViewModel());
//        session.setAttribute("userOnline1", session.getAttribute("userOnline"));
//
//
//        return "SanPhamClient";
//    }

    @GetMapping(value = "trang-chu")
    public String trangChu(Model model, HttpSession session) {
        Page<SanPham> page = sanPhamDao.phanTrangSanPham(1);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<SanPham> lstSanPham = page.getContent();

        model.addAttribute("currentPage", 1);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("lstSanPham", lstSanPham);
        model.addAttribute("sanPhamSearch", new SanPhamViewModel());
        session.setAttribute("userOnline1", session.getAttribute("userOnline"));
        return "SanPhamClient";
    }

    @GetMapping(value = "trang-chu/page/{pageNumber}")
    public String listByPage(Model model, @PathVariable("pageNumber")int currentPage) {

        Page<SanPham> page = sanPhamDao.phanTrangSanPham(currentPage);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<SanPham> lstSanPham = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("lstSanPham", lstSanPham);
        model.addAttribute("sanPhamSearch", new SanPhamViewModel());

        return "SanPhamClient";
    }

    @GetMapping(value = "checkoutSuccess")
    public String checkoutSucess(Model model) {
        model.addAttribute("sanPhamSearch", new SanPhamViewModel());
        return "CheckoutSuccess";
    }

    @Autowired
    AnhSanPhamDao anhSPDao;

    @GetMapping(value = "trang-chu/chi-tiet/{ma}")
    public String layChiTietSP(@PathVariable("ma")String ma, Model model) {
        List<SanPhamExtend> lstChiTietSP = new ArrayList<>();
        List<AnhSanPham> lstAnh = anhSPDao.layAnhTheoMaSanPham(ma);

        SanPhamExtend objSanPham = sanPhamDao.layChiTietTheoMaExtend(ma);
        SanPham objSP = sanPhamDao.layChiTietTheoMa(ma);

        List<SanPham> lstSanPhamLienQuan = sanPhamDao.layDanhSachTheoDanhMuc(objSP.getDanhMucId());

        lstChiTietSP.add(objSanPham);
        model.addAttribute("lstChiTietSP", lstChiTietSP);
        model.addAttribute("lstAnh", lstAnh);
        model.addAttribute("lstSanPhamLienQuan", lstSanPhamLienQuan);
        model.addAttribute("sanPhamSearch", new SanPhamViewModel());

        return "ChiTietSanPhamClient";
    }

    @RequestMapping(value = "trang-chu/timkiem")
    public String timKiemSanPham(@ModelAttribute("sanPhamSearch") SanPhamViewModel sanPham, Model model) {
        List<SanPham> lstSanPham = sanPhamDao.timKiemSanPhamClient(sanPham.getTuKhoa());

        model.addAttribute("currentPage", 1);
        model.addAttribute("totalItems", 1);
        model.addAttribute("totalPages", 1);

        model.addAttribute("lstSanPham", lstSanPham);
        model.addAttribute("sanPhamSearch", sanPham);

        if (sanPham.getTuKhoa() == "") {
            return "redirect:/trang-chu";
        }
        return "SanPhamClient";
    }

    @Autowired
    DonHangDao donHangDao;

    @GetMapping(value = "trang-chu/donhang/{ma}")
    public String layDanhSachDonHang(@PathVariable("ma")int ma, Model model) {

        List<DonHang> lstDonHang = donHangDao.layDanhSachTheoMaKH(ma);

        model.addAttribute("lstDonHang", lstDonHang);
        model.addAttribute("sanPhamSearch", new SanPhamViewModel());

        return "DonHangClient";
    }

    @GetMapping(value = "trang-chu/danhmuc/{ma}")
    public String laySanPhamTheoDanhMuc(@PathVariable("ma")int ma, Model model) {

        List<SanPham> lstSanPham = sanPhamDao.layDanhSachTheoDanhMuc(ma);

        long totalItems = lstSanPham.size();
        int totalPages = (int) lstSanPham.size() / 12;

        model.addAttribute("currentPage", 1);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);

        model.addAttribute("lstSanPham", lstSanPham);
        model.addAttribute("sanPhamSearch", new SanPhamViewModel());

        return "SanPhamClient";
    }

    @GetMapping(value = "trang-chu/thuonghieu/{ma}")
    public String laySanPhamTheoThuongHieu(@PathVariable("ma")int ma, Model model) {

        List<SanPham> lstSanPham = sanPhamDao.layDanhSachTheoThuongHieu(ma);

        long totalItems = lstSanPham.size();
        int totalPages = (int) lstSanPham.size() / 12;

        model.addAttribute("currentPage", 1);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);

        model.addAttribute("lstSanPham", lstSanPham);
        model.addAttribute("sanPhamSearch", new SanPhamViewModel());

        return "SanPhamClient";
    }



    @ModelAttribute("lstDanhMuc")
    public List<DanhMuc> hienThiDanhSachDanhMuc() {
        List<DanhMuc> lstDanhMuc = danhMucDao.layDanhSach();

        return lstDanhMuc;
    }



    @ModelAttribute("lstThuongHieu")
    public List<ThuongHieu> hienThiDanhSachThuongHieu() {
        List<ThuongHieu> lstThuongHieu = thuongHieuDao.layDanhSach();

        return lstThuongHieu;
    }
}
