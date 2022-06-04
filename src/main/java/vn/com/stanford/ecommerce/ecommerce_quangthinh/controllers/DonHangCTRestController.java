package vn.com.stanford.ecommerce.ecommerce_quangthinh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.DonHang;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.DonHangChiTiet;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.Message;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.DonHangChiTietDao;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api")
public class DonHangCTRestController {
    @Autowired
    DonHangChiTietDao donHangChiTietDao;

    @GetMapping("donhangct/all")
    public ResponseEntity<List<DonHangChiTiet>> layDanhSach() {
        List<DonHangChiTiet> lstDonHangCT = donHangChiTietDao.layDanhSach();

        return new ResponseEntity<>(lstDonHangCT, HttpStatus.OK);
    }

    @GetMapping("donhangct/ds/{id}")
    public ResponseEntity<List<DonHangChiTiet>> layDanhSachTheoMaDonHang(@PathVariable("id") long id) {
        List<DonHangChiTiet> lstDonHangCT = donHangChiTietDao.layDanhSachTheoMaDonHang(id);

        return new ResponseEntity<>(lstDonHangCT, HttpStatus.OK);
    }

    @GetMapping("donhangct/{id}")
    public ResponseEntity<?> layChiTietTheoMa(@PathVariable("id") long id) {
        if (donHangChiTietDao.layChiTietTheoMa(id) == null) {
            Message apiErr = new Message("Đơn hàng có id " + id + " không tồn tại");
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            DonHangChiTiet objDonHangCT = donHangChiTietDao.layChiTietTheoMa(id);
            return new ResponseEntity<>(objDonHangCT, HttpStatus.OK);
        }
    }

    @PostMapping("donhangct/add")
    public ResponseEntity<?> themMoiDonHangCT(@RequestBody DonHangChiTiet objDonHangCT) {
        boolean ketQua = donHangChiTietDao.themMoi(objDonHangCT);
        if (ketQua) {
            return new ResponseEntity<>(objDonHangCT, HttpStatus.OK);
        }

        Message apiErr = new Message("Không thể thêm mới đơn hàng");

        return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
    }

    @PutMapping("donhangct/{id}")
    public ResponseEntity<?> capNhatDonHangCT(@PathVariable("id") long id, @RequestBody DonHangChiTiet objDonHangCT) {
        DonHangChiTiet objDonHangCTOld = donHangChiTietDao.layChiTietTheoMa(id);
        if (objDonHangCTOld == null) {
            Message apiErr = new Message("Không tìm thấy đơn hàng có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            objDonHangCTOld.setDonGia(objDonHangCT.getDonGia());
            objDonHangCTOld.setSoLuong(objDonHangCT.getSoLuong());
            objDonHangCTOld.setMaDonHang(objDonHangCT.getMaDonHang());
            objDonHangCTOld.setMaSanPham(objDonHangCT.getMaSanPham());

            donHangChiTietDao.capNhat(objDonHangCTOld);
            return new ResponseEntity<>(objDonHangCTOld, HttpStatus.OK);
        }
    }

    @DeleteMapping("donhangct/{id}")
    public ResponseEntity<?> xoaDonHangCT(@PathVariable("id") long id) {
        DonHangChiTiet objDonHangCT = donHangChiTietDao.layChiTietTheoMa(id);
        if (objDonHangCT == null) {
            Message apiErr = new Message("Không tìm thấy đơn hàng có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            donHangChiTietDao.xoa(id);
            Message apiErr = new Message("Xóa đơn hàng có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.OK);
        }
    }
}
