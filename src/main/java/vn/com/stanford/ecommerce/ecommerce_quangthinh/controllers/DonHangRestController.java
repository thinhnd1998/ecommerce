package vn.com.stanford.ecommerce.ecommerce_quangthinh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.DonHang;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.DonHangChiTiet;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.Message;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.DonHangChiTietDao;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.DonHangDao;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api")
public class DonHangRestController {
    @Autowired
    DonHangDao donHangDao;

    @Autowired
    DonHangChiTietDao donHangChiTietDao;

    @GetMapping("donhang/all")
    public ResponseEntity<List<DonHang>> layDanhSach() {
        List<DonHang> lstDonHang = donHangDao.layDanhSach();

        return new ResponseEntity<>(lstDonHang, HttpStatus.OK);
    }

    @GetMapping("donhang/{id}")
    public ResponseEntity<?> layChiTietTheoMa(@PathVariable("id") long id) {
        if (donHangDao.layChiTietTheoMa(id) == null) {
            Message apiErr = new Message("Đơn hàng có id " + id + " không tồn tại");
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            DonHang objDonHang = donHangDao.layChiTietTheoMa(id);
            return new ResponseEntity<>(objDonHang, HttpStatus.OK);
        }
    }

    @PostMapping("donhang/add")
    public ResponseEntity<?> themMoiDonHang(@RequestBody DonHang objDonHang) {
        objDonHang.setNgayDat(new Date());
        boolean ketQua = donHangDao.themMoi(objDonHang);
        if (ketQua) {
            return new ResponseEntity<>(objDonHang, HttpStatus.OK);
        }

        Message apiErr = new Message("Không thể thêm mới đơn hàng");

        return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
    }

    @PutMapping("donhang/{id}")
    public ResponseEntity<?> capNhatDonHang(@PathVariable("id") long id, @RequestBody DonHang objDonHang) {
        DonHang objDonHangOld = donHangDao.layChiTietTheoMa(id);
        if (objDonHangOld == null) {
            Message apiErr = new Message("Không tìm thấy đơn hàng có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            objDonHangOld.setTenDonHang(objDonHang.getTenDonHang());
            objDonHangOld.setDiaChi(objDonHang.getDiaChi());
            objDonHangOld.setDienThoai(objDonHang.getDienThoai());
            objDonHangOld.setNgayDat(objDonHang.getNgayDat());
            objDonHangOld.setGhiChu(objDonHang.getGhiChu());
            objDonHangOld.setTrangThai(objDonHang.getTrangThai());
            objDonHangOld.setMaKhachHang(objDonHang.getMaKhachHang());
            objDonHangOld.setMaNhanVien(objDonHang.getMaNhanVien());
            donHangDao.capNhat(objDonHangOld);
            return new ResponseEntity<>(objDonHangOld, HttpStatus.OK);
        }
    }

    @DeleteMapping("donhang/{id}")
    public ResponseEntity<?> xoaDonHang(@PathVariable("id") long id) {
        DonHang objDonHang = donHangDao.layChiTietTheoMa(id);
        if (objDonHang == null) {
            Message apiErr = new Message("Không tìm thấy đơn hàng có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            donHangChiTietDao.xoaTheoMaDonHang(objDonHang.getId());
            donHangDao.xoa(id);
            Message apiErr = new Message("Xóa đơn hàng có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.OK);
        }
    }

    @PutMapping("donhang/approve/{id}")
    public ResponseEntity<?> duyetDonHang(@PathVariable("id") int id, HttpSession session) {
        DonHang objDonHang = donHangDao.layChiTietTheoMa(id);
        if (objDonHang == null) {
            Message apiErr = new Message("Không tìm thấy đơn hàng có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            objDonHang.setMaNhanVien(Integer.parseInt(session.getAttribute("userId")+""));
            objDonHang.setTrangThai("Đã xác nhận");
            donHangDao.capNhat(objDonHang);
            Message apiErr = new Message("Duyệt thành công đơn hàng có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.OK);
        }
    }
}
