package vn.com.stanford.ecommerce.ecommerce_quangthinh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.KhachHang;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.Message;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.NhanVien;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.KhachHangDao;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.NhanVienDao;

import java.util.List;

@RestController
@RequestMapping("api")
public class NhanVienRestController {
    @Autowired
    NhanVienDao nhanVienDao;

    @GetMapping("nhanvien/all")
    public ResponseEntity<List<NhanVien>> layDanhSach() {
        List<NhanVien> lstNhanVien = nhanVienDao.layDanhSach();

        return new ResponseEntity<>(lstNhanVien, HttpStatus.OK);
    }

    @GetMapping("nhanvien/{id}")
    public ResponseEntity<?> layChiTietTheoMa(@PathVariable("id") int id) {
        if (nhanVienDao.layChiTietTheoMa(id) == null) {
            Message apiErr = new Message("Danh mục có id " + id + " không tồn tại");
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            NhanVien objNhanVien = nhanVienDao.layChiTietTheoMa(id);
            return new ResponseEntity<>(objNhanVien, HttpStatus.OK);
        }
    }

    @PostMapping("nhanvien/add")
    public ResponseEntity<?> themMoiNhanVien(@RequestBody NhanVien objNhanVien) {
        boolean ketQua = nhanVienDao.themMoi(objNhanVien);
        if (ketQua) {
            return new ResponseEntity<>(objNhanVien, HttpStatus.OK);
        }

        Message apiErr = new Message("Không thể thêm mới danh mục");

        return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
    }

    @PutMapping("nhanvien/{id}")
    public ResponseEntity<?> capNhatNhanVien(@PathVariable("id") int id, @RequestBody NhanVien objNhanVien) {
        NhanVien objNhanVienOld = nhanVienDao.layChiTietTheoMa(id);
        if (objNhanVienOld == null) {
            Message apiErr = new Message("Không tìm thấy danh mục có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            objNhanVienOld.setHoTen(objNhanVien.getHoTen());
            nhanVienDao.capNhat(objNhanVienOld);
            return new ResponseEntity<>(objNhanVienOld, HttpStatus.OK);
        }
    }

    @DeleteMapping("nhanvien/{id}")
    public ResponseEntity<?> xoaNhanVien(@PathVariable("id") int id) {
        NhanVien objNhanVien = nhanVienDao.layChiTietTheoMa(id);
        if (objNhanVien == null) {
            Message apiErr = new Message("Không tìm thấy danh mục có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            nhanVienDao.xoa(id);
            Message apiErr = new Message("Xóa danh mục có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.OK);
        }
    }
}
