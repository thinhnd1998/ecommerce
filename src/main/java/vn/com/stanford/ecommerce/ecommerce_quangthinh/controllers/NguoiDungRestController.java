package vn.com.stanford.ecommerce.ecommerce_quangthinh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.Message;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.NguoiDung;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.NguoiDungDao;

import java.util.List;

@RestController
@RequestMapping("api")
public class NguoiDungRestController {
    @Autowired
    NguoiDungDao nguoiDungDao;

    @GetMapping("nguoidung/all")
    public ResponseEntity<List<NguoiDung>> layDanhSach() {
        List<NguoiDung> lstNguoiDung = nguoiDungDao.layDanhSach();

        return new ResponseEntity<>(lstNguoiDung, HttpStatus.OK);
    }

    @GetMapping("nguoidung/{id}")
    public ResponseEntity<?> layChiTietTheoMa(@PathVariable("id") int id) {
        if (nguoiDungDao.layChiTietTheoMa(id) == null) {
            Message apiErr = new Message("Người dùng có id " + id + " không tồn tại");
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            NguoiDung objNguoiDung = nguoiDungDao.layChiTietTheoMa(id);
            return new ResponseEntity<>(objNguoiDung, HttpStatus.OK);
        }
    }

    @PostMapping("nguoidung/add")
    public ResponseEntity<?> themMoiNguoiDung(@RequestBody NguoiDung objNguoiDung) {
        boolean ketQua = nguoiDungDao.themMoi(objNguoiDung);
        if (ketQua) {
            return new ResponseEntity<>(objNguoiDung, HttpStatus.OK);
        }

        Message apiErr = new Message("Không thể thêm mới người dùng");

        return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
    }

    @PutMapping("nguoidung/{id}")
    public ResponseEntity<?> capNhatNguoiDung(@PathVariable("id") int id, @RequestBody NguoiDung objNguoiDung) {
        NguoiDung objNguoiDungOld = nguoiDungDao.layChiTietTheoMa(id);
        if (objNguoiDungOld == null) {
            Message apiErr = new Message("Không tìm thấy người dùng có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            objNguoiDungOld.setTenDangNhap(objNguoiDung.getTenDangNhap());
            objNguoiDungOld.setMatKhau(objNguoiDung.getMatKhau());
            objNguoiDungOld.setHoTen(objNguoiDung.getHoTen());
            objNguoiDungOld.setDienThoai(objNguoiDung.getDienThoai());
            objNguoiDungOld.setEmail(objNguoiDung.getEmail());
            objNguoiDungOld.setDiaChi(objNguoiDung.getDiaChi());
            objNguoiDungOld.setVaiTroID(objNguoiDung.getVaiTroID());
            nguoiDungDao.capNhat(objNguoiDungOld);
            return new ResponseEntity<>(objNguoiDungOld, HttpStatus.OK);
        }
    }

    @DeleteMapping("nguoidung/{id}")
    public ResponseEntity<?> xoaNguoiDung(@PathVariable("id") int id) {
        NguoiDung objNguoiDung = nguoiDungDao.layChiTietTheoMa(id);
        if (objNguoiDung == null) {
            Message apiErr = new Message("Không tìm thấy người dùng có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            nguoiDungDao.xoa(id);
            Message apiErr = new Message("Xóa người dùng có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.OK);
        }
    }
}
