package vn.com.stanford.ecommerce.ecommerce_quangthinh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.KhachHang;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.Message;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.KhachHangDao;

import java.util.List;

@RestController
@RequestMapping("api")
public class KhachHangRestController {
    @Autowired
    KhachHangDao khachHangDao;

    @GetMapping("khachhang/all")
    public ResponseEntity<List<KhachHang>> layDanhSach() {
        List<KhachHang> lstKhachHang = khachHangDao.layDanhSach();

        return new ResponseEntity<>(lstKhachHang, HttpStatus.OK);
    }

    @GetMapping("khachhang/{id}")
    public ResponseEntity<?> layChiTietTheoMa(@PathVariable("id") int id) {
        if (khachHangDao.layChiTietTheoMa(id) == null) {
            Message apiErr = new Message("Danh mục có id " + id + " không tồn tại");
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            KhachHang objKhachHang = khachHangDao.layChiTietTheoMa(id);
            return new ResponseEntity<>(objKhachHang, HttpStatus.OK);
        }
    }

    @PostMapping("khachhang/add")
    public ResponseEntity<?> themMoiKhachHang(@RequestBody KhachHang objKhachHang) {
        boolean ketQua = khachHangDao.themMoi(objKhachHang);
        if (ketQua) {
            return new ResponseEntity<>(objKhachHang, HttpStatus.OK);
        }

        Message apiErr = new Message("Không thể thêm mới danh mục");

        return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
    }

    @PutMapping("khachhang/{id}")
    public ResponseEntity<?> capNhatKhachHang(@PathVariable("id") int id, @RequestBody KhachHang objKhachHang) {
        KhachHang objKhachHangOld = khachHangDao.layChiTietTheoMa(id);
        if (objKhachHangOld == null) {
            Message apiErr = new Message("Không tìm thấy danh mục có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            objKhachHangOld.setHoTen(objKhachHang.getHoTen());
            khachHangDao.capNhat(objKhachHangOld);
            return new ResponseEntity<>(objKhachHangOld, HttpStatus.OK);
        }
    }

    @DeleteMapping("khachhang/{id}")
    public ResponseEntity<?> xoaKhachHang(@PathVariable("id") int id) {
        KhachHang objKhachHang = khachHangDao.layChiTietTheoMa(id);
        if (objKhachHang == null) {
            Message apiErr = new Message("Không tìm thấy danh mục có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            khachHangDao.xoa(id);
            Message apiErr = new Message("Xóa danh mục có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.OK);
        }
    }
}

