package vn.com.stanford.ecommerce.ecommerce_quangthinh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.VaiTro;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.Message;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.VaiTroDao;

import java.util.List;

@RestController
@RequestMapping("api")
public class VaiTroRestController {
    @Autowired
    VaiTroDao vaiTroDao;

    @GetMapping("vaitro/all")
    public ResponseEntity<List<VaiTro>> layDanhSach() {
        List<VaiTro> lstVaiTro = vaiTroDao.layDanhSach();

        return new ResponseEntity<>(lstVaiTro, HttpStatus.OK);
    }

    @GetMapping("vaitro/{id}")
    public ResponseEntity<?> layChiTietTheoMa(@PathVariable("id") int id) {
        if (vaiTroDao.layChiTietTheoMa(id) == null) {
            Message apiErr = new Message("Vai trò có id " + id + " không tồn tại");
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            VaiTro objVaiTro = vaiTroDao.layChiTietTheoMa(id);
            return new ResponseEntity<>(objVaiTro, HttpStatus.OK);
        }
    }

    @PostMapping("vaitro/add")
    public ResponseEntity<?> themMoiVaiTro(@RequestBody VaiTro objVaiTro) {
        boolean ketQua = vaiTroDao.themMoi(objVaiTro);
        if (ketQua) {
            return new ResponseEntity<>(objVaiTro, HttpStatus.OK);
        }

        Message apiErr = new Message("Không thể thêm mới vai trò");

        return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
    }

    @PutMapping("vaitro/{id}")
    public ResponseEntity<?> capNhatVaiTro(@PathVariable("id") int id, @RequestBody VaiTro objVaiTro) {
        VaiTro objVaiTroOld = vaiTroDao.layChiTietTheoMa(id);
        if (objVaiTroOld == null) {
            Message apiErr = new Message("Không tìm thấy vai trò có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            objVaiTroOld.setTenVaiTro(objVaiTro.getTenVaiTro());
            vaiTroDao.capNhat(objVaiTroOld);
            return new ResponseEntity<>(objVaiTroOld, HttpStatus.OK);
        }
    }

    @DeleteMapping("vaitro/{id}")
    public ResponseEntity<?> xoaVaiTro(@PathVariable("id") int id) {
        VaiTro objVaiTro = vaiTroDao.layChiTietTheoMa(id);
        if (objVaiTro == null) {
            Message apiErr = new Message("Không tìm thấy vai trò có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            vaiTroDao.xoa(id);
            Message apiErr = new Message("Xóa vai trò có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.OK);
        }
    }
}
