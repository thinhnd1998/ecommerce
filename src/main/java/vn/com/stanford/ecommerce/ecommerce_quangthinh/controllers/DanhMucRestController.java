package vn.com.stanford.ecommerce.ecommerce_quangthinh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.DanhMuc;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.Message;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.DanhMucDao;

import java.util.List;

@RestController
@RequestMapping("api")
public class DanhMucRestController {
    @Autowired
    DanhMucDao danhMucDao;

    @GetMapping("danhmuc/all")
    public ResponseEntity<List<DanhMuc>> layDanhSach() {
        List<DanhMuc> lstDanhMuc = danhMucDao.layDanhSach();

        return new ResponseEntity<>(lstDanhMuc, HttpStatus.OK);
    }

    @GetMapping("danhmuc/{id}")
    public ResponseEntity<?> layChiTietTheoMa(@PathVariable("id") int id) {
        if (danhMucDao.layChiTietTheoMa(id) == null) {
            Message apiErr = new Message("Danh mục có id " + id + " không tồn tại");
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            DanhMuc objDanhMuc = danhMucDao.layChiTietTheoMa(id);
            return new ResponseEntity<>(objDanhMuc, HttpStatus.OK);
        }
    }

    @PostMapping("danhmuc/add")
    public ResponseEntity<?> themMoiDanhMuc(@RequestBody DanhMuc objDanhMuc) {
        boolean ketQua = danhMucDao.themMoi(objDanhMuc);
        if (ketQua) {
            return new ResponseEntity<>(objDanhMuc, HttpStatus.OK);
        }

        Message apiErr = new Message("Không thể thêm mới danh mục");

        return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
    }

    @PutMapping("danhmuc/{id}")
    public ResponseEntity<?> capNhatDanhMuc(@PathVariable("id") int id, @RequestBody DanhMuc objDanhMuc) {
        DanhMuc objDanhMucOld = danhMucDao.layChiTietTheoMa(id);
        if (objDanhMucOld == null) {
            Message apiErr = new Message("Không tìm thấy danh mục có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            objDanhMucOld.setTenDanhMuc(objDanhMuc.getTenDanhMuc());
            danhMucDao.capNhat(objDanhMucOld);
            return new ResponseEntity<>(objDanhMucOld, HttpStatus.OK);
        }
    }

    @DeleteMapping("danhmuc/{id}")
    public ResponseEntity<?> xoaDanhMuc(@PathVariable("id") int id) {
        DanhMuc objDanhMuc = danhMucDao.layChiTietTheoMa(id);
        if (objDanhMuc == null) {
            Message apiErr = new Message("Không tìm thấy danh mục có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            danhMucDao.xoa(id);
            Message apiErr = new Message("Xóa danh mục có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.OK);
        }
    }
}
