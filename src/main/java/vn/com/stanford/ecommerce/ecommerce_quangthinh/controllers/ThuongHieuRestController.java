package vn.com.stanford.ecommerce.ecommerce_quangthinh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.Message;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.ThuongHieu;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.ThuongHieuDao;

import java.util.List;

@RestController
@RequestMapping("api")
public class ThuongHieuRestController {
    @Autowired
    ThuongHieuDao thuongHieuDao;

    @GetMapping("thuonghieu/all")
    public ResponseEntity<List<ThuongHieu>> layDanhSach() {
        List<ThuongHieu> lstThuongHieu = thuongHieuDao.layDanhSach();

        return new ResponseEntity<>(lstThuongHieu, HttpStatus.OK);
    }

    @GetMapping("thuonghieu/{id}")
    public ResponseEntity<?> layChiTietTheoMa(@PathVariable("id") int id) {
        if (thuongHieuDao.layChiTietTheoMa(id) == null) {
            Message apiErr = new Message("Thương hiệu có id " + id + " không tồn tại");
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            ThuongHieu objThuongHieu = thuongHieuDao.layChiTietTheoMa(id);
            return new ResponseEntity<>(objThuongHieu, HttpStatus.OK);
        }
    }

    @PostMapping("thuonghieu/add")
    public ResponseEntity<?> themMoiThuongHieu(@RequestBody ThuongHieu objThuongHieu) {
        boolean ketQua = thuongHieuDao.themMoi(objThuongHieu);
        if (ketQua) {
            return new ResponseEntity<>(objThuongHieu, HttpStatus.OK);
        }

        Message apiErr = new Message("Không thể thêm mới thương hiệu");

        return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
    }

    @PutMapping("thuonghieu/{id}")
    public ResponseEntity<?> capNhatThuongHieu(@PathVariable("id") int id, @RequestBody ThuongHieu objThuongHieu) {
        ThuongHieu objThuongHieuOld = thuongHieuDao.layChiTietTheoMa(id);
        if (objThuongHieuOld == null) {
            Message apiErr = new Message("Không tìm thấy thương hiệu có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            objThuongHieuOld.setTenThuongHieu(objThuongHieu.getTenThuongHieu());
            thuongHieuDao.capNhat(objThuongHieuOld);
            return new ResponseEntity<>(objThuongHieuOld, HttpStatus.OK);
        }
    }

    @DeleteMapping("thuonghieu/{id}")
    public ResponseEntity<?> xoaThuongHieu(@PathVariable("id") int id) {
        ThuongHieu objThuongHieu = thuongHieuDao.layChiTietTheoMa(id);
        if (objThuongHieu == null) {
            Message apiErr = new Message("Không tìm thấy thương hiệu có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            thuongHieuDao.xoa(id);
            Message apiErr = new Message("Xóa thương hiệu có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.OK);
        }
    }
}
