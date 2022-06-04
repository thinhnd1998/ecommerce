package vn.com.stanford.ecommerce.ecommerce_quangthinh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.AnhSanPham;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.Message;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.AnhSanPhamDao;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api")
public class AnhSanPhamRestController {
    @Autowired
    AnhSanPhamDao anhSanPhamDao;

    @GetMapping("anhsp/all")
    public ResponseEntity<List<AnhSanPham>> layDanhSach() {
        List<AnhSanPham> lstAnhSanPham = anhSanPhamDao.layDanhSach();

        return new ResponseEntity<>(lstAnhSanPham, HttpStatus.OK);
    }

    @GetMapping("anhsp/{id}")
    public ResponseEntity<?> layChiTietTheoMa(@PathVariable("id") int id) {
        if (anhSanPhamDao.layChiTietTheoMa(id) == null) {
            Message apiErr = new Message("Ảnh sản phẩm có id " + id + " không tồn tại");
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            AnhSanPham objAnhSanPham = anhSanPhamDao.layChiTietTheoMa(id);
            return new ResponseEntity<>(objAnhSanPham, HttpStatus.OK);
        }
    }

    @GetMapping("anhsp/dsAnh/{maSP}")
    public ResponseEntity<?> layChiTietTheoMaSP(@PathVariable("maSP") String maSP) {
        if (anhSanPhamDao.layAnhTheoMaSanPham(maSP).size() == 0) {
            Message apiErr = new Message("Ảnh có mã sản phẩm " + maSP + " không tồn tại");
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            List<AnhSanPham> lstAnhSanPham = anhSanPhamDao.layAnhTheoMaSanPham(maSP);
            return new ResponseEntity<>(lstAnhSanPham, HttpStatus.OK);
        }
    }

    @PostMapping("anhsp/add")
    public ResponseEntity<?> themMoiAnhSanPham(@RequestBody AnhSanPham objAnhSanPham) {
        boolean ketQua = anhSanPhamDao.themMoi(objAnhSanPham);
        if (ketQua) {
            return new ResponseEntity<>(objAnhSanPham, HttpStatus.OK);
        }

        Message apiErr = new Message("Không thể thêm mới ảnh sản phẩm");

        return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
    }

    @PostMapping("anhsp/upload")
    public ResponseEntity<Object> fileUpload(@RequestParam("fUpload") MultipartFile fUpload) {
        String fileName = "";
        if (fUpload != null) {
            //Lấy tên file
            fileName = fUpload.getOriginalFilename();
            //Lấy đường dẫn upload trong file web.xml
            String strPath = "E:\\Github\\JE112101HV\\HocVien\\Quang\\QuangThinh\\ECommerce_QuangThinh\\src\\main\\resources\\static\\images";

            try {
                //Tạo file
                File file = new File(strPath, fileName);

                //Ghi ra file
                fUpload.transferTo(file);

            } catch (IOException e) {
                System.err.println("Có lỗi xảy ra trong quá trình upload file. Chi tiết: " + e.getMessage());
            }
        }
        return new ResponseEntity<Object>("Upload Successfully", HttpStatus.OK);
    }

    @PutMapping("anhsp/{id}")
    public ResponseEntity<?> capNhatAnhSanPham(@PathVariable("id") int id, @RequestBody AnhSanPham objAnhSanPham) {
        AnhSanPham objAnhSanPhamOld = anhSanPhamDao.layChiTietTheoMa(id);
        if (objAnhSanPhamOld == null) {
            Message apiErr = new Message("Không tìm thấy ảnh sản phẩm có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            objAnhSanPhamOld.setTenAnh(objAnhSanPham.getTenAnh());
            objAnhSanPhamOld.setMaSanPham(objAnhSanPham.getMaSanPham());
            anhSanPhamDao.capNhat(objAnhSanPhamOld);
            return new ResponseEntity<>(objAnhSanPhamOld, HttpStatus.OK);
        }
    }

    @DeleteMapping("anhsp/{id}")
    public ResponseEntity<?> xoaAnhSanPham(@PathVariable("id") int id) {
        AnhSanPham objAnhSanPham = anhSanPhamDao.layChiTietTheoMa(id);
        if (objAnhSanPham == null) {
            Message apiErr = new Message("Không tìm thấy ảnh sản phẩm có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            anhSanPhamDao.xoa(id);
            Message apiErr = new Message("Xóa ảnh sản phẩm có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.OK);
        }
    }

    @DeleteMapping("anhsp/{maSP}/{tenAnh}")
    public ResponseEntity<?> xoaAnhTheoMaSPVaTenAnh(@PathVariable("maSP") String maSanPham, @PathVariable("tenAnh") String tenAnh) {
        AnhSanPham objAnhSanPham = anhSanPhamDao.layAnhTheoMaSanPhamVaTenAnh(maSanPham, tenAnh);
        if (objAnhSanPham == null) {
            Message apiErr = new Message("Không tìm thấy ảnh có mã sản phẩm " + maSanPham + " và tên ảnh " + tenAnh);
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            anhSanPhamDao.xoaTheoMaVaTenAnh(maSanPham, tenAnh);
            Message apiErr = new Message("Xóa ảnh có mã sản phẩm " + maSanPham + " và tên ảnh " + tenAnh);
            return new ResponseEntity<>(apiErr, HttpStatus.OK);
        }
    }
}
