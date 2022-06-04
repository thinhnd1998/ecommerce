package vn.com.stanford.ecommerce.ecommerce_quangthinh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.DanhMuc;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.PhanQuyen;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.Message;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.PhanQuyenDao;

import java.util.List;

@RestController
@RequestMapping("api")
public class PhanQuyenRestController {
    @Autowired
    PhanQuyenDao phanQuyenDao;

    @GetMapping("phanquyen/all")
    public ResponseEntity<List<PhanQuyen>> layDanhSach() {
        List<PhanQuyen> lstPhanQuyen = phanQuyenDao.layDanhSach();

        return new ResponseEntity<>(lstPhanQuyen, HttpStatus.OK);
    }

    @GetMapping("phanquyen/{id}")
    public ResponseEntity<?> layChiTietTheoMa(@PathVariable("id") int id) {
        if (phanQuyenDao.layChiTietTheoMa(id) == null) {
            Message apiErr = new Message("Quyền có id " + id + " không tồn tại");
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            PhanQuyen objPhanQuyen = phanQuyenDao.layChiTietTheoMa(id);
            return new ResponseEntity<>(objPhanQuyen, HttpStatus.OK);
        }
    }

    @PostMapping("phanquyen/add")
    public ResponseEntity<?> themMoiQuyen(@RequestBody PhanQuyen objPhanQuyen) {
        boolean ketQua = phanQuyenDao.themMoi(objPhanQuyen);
        if (ketQua) {
            return new ResponseEntity<>(objPhanQuyen, HttpStatus.OK);
        }

        Message apiErr = new Message("Không thể thêm mới quyền");

        return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
    }

    @PutMapping("phanquyen/{id}")
    public ResponseEntity<?> capNhatQuyen(@PathVariable("id") long id, @RequestBody PhanQuyen objPhanQuyen) {
        PhanQuyen objPhanQuyenOld = phanQuyenDao.layChiTietTheoMa(id);
        if (objPhanQuyenOld == null) {
            Message apiErr = new Message("Không tìm thấy quyền có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            objPhanQuyenOld.setVaiTroId(objPhanQuyen.getVaiTroId());
            objPhanQuyenOld.setChucNangId(objPhanQuyen.getChucNangId());
            objPhanQuyenOld.setXem(objPhanQuyen.getXem());
            objPhanQuyenOld.setThem(objPhanQuyen.getThem());
            objPhanQuyenOld.setSua(objPhanQuyen.getSua());
            objPhanQuyenOld.setXoa(objPhanQuyen.getXoa());
            phanQuyenDao.capNhat(objPhanQuyenOld);
            return new ResponseEntity<>(objPhanQuyenOld, HttpStatus.OK);
        }
    }

    @DeleteMapping("phanquyen/{vaiTroId}/{chucNangId}")
    public ResponseEntity<?> xoaDanhMuc(@PathVariable("vaiTroId") int vaiTroId, @PathVariable("chucNangId") int chucNangId) {
        PhanQuyen objPhanQuyen = phanQuyenDao.layChiTietTheoVaiTro_ChucNang(vaiTroId, chucNangId);
        if (objPhanQuyen == null) {
            Message apiErr = new Message("Không tìm thấy quyền có vai trò id " + vaiTroId + "và chức năng id" + chucNangId);
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            phanQuyenDao.xoaTheoVaiTro_ChucNang(vaiTroId, chucNangId);
            Message apiErr = new Message("Xóa quyền có vai trò id " + vaiTroId + "và chức năng id" + chucNangId);
            return new ResponseEntity<>(apiErr, HttpStatus.OK);
        }
    }
}
