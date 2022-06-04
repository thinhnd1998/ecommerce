package vn.com.stanford.ecommerce.ecommerce_quangthinh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.*;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.SanPham;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.SanPham;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.AnhSanPhamDao;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.SanPhamDao;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api")
public class  SanPhamRestController {
    @Autowired
    SanPhamDao sanPhamDao;

    @GetMapping("sanpham/all")
    public ResponseEntity<List<SanPham>> layDanhSach() {
        List<SanPham> lstSanPham = sanPhamDao.layDanhSach();

        return new ResponseEntity<>(lstSanPham, HttpStatus.OK);
    }

    @GetMapping("sanpham/{id}")
    public ResponseEntity<?> layChiTietTheoMa(@PathVariable("id") String id) {
        if (sanPhamDao.layChiTietTheoMa(id) == null) {
            Message apiErr = new Message("Sản phẩm có mã " + id + " không tồn tại");
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            SanPham objSanPham = sanPhamDao.layChiTietTheoMa(id);
            return new ResponseEntity<>(objSanPham, HttpStatus.OK);
        }
    }

    @PostMapping("sanpham/add")
    public ResponseEntity<?> themMoiSanPham(@RequestBody SanPham objSanPham) {

        objSanPham.setNgayTao(new Date());
        objSanPham.setNgayCapNhat(new Date());
//        objSanPham.setNgayDuyet(new Date());
        objSanPham.setNguoiTao(1);
        objSanPham.setNguoiDuyet(3);
        objSanPham.setDuyet(0);
        boolean ketQua = sanPhamDao.themMoi(objSanPham);
        if (ketQua) {
            return new ResponseEntity<>(objSanPham, HttpStatus.OK);
        }

        Message apiErr = new Message("Không thể thêm mới sản phẩm");

        return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
    }

    @PostMapping("sanpham/upload")
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
        return new ResponseEntity<>("Upload Successfully", HttpStatus.OK);
    }

    @PostMapping("approve")
    public ResponseEntity<?> duyetSanPham(@RequestBody String[] arrMaSP, HttpSession session) {
        Message apiErr = new Message("");
        for (String maSP : arrMaSP) {
            SanPham objSanPham = sanPhamDao.layChiTietTheoMa(maSP);
            if (objSanPham == null) {
                apiErr = new Message("Không tìm thấy sản phẩm có id " + maSP);
                return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
            } else {
                objSanPham.setDuyet(1);
                objSanPham.setNguoiDuyet(Integer.parseInt(session.getAttribute("userId") + ""));
                objSanPham.setNgayDuyet(new Date());
                sanPhamDao.capNhat(objSanPham);
                apiErr = new Message("Duyệt thành công sản phẩm có id " + maSP);
            }
        }
        return new ResponseEntity<>(apiErr, HttpStatus.OK);
    }

    @PostMapping("disapprove")
    public ResponseEntity<?> huyDuyetSanPham(@RequestBody String[] arrMaSP, HttpSession session) {
        Message apiErr = new Message("");

        for (String maSP : arrMaSP) {
            SanPham objSanPham = sanPhamDao.layChiTietTheoMa(maSP);
            if (objSanPham == null) {
                apiErr = new Message("Không tìm thấy sản phẩm có id " + maSP);
                return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
            } else {
                objSanPham.setDuyet(0);
                objSanPham.setNguoiDuyet(3);
                objSanPham.setNgayDuyet(null);
                sanPhamDao.capNhat(objSanPham);
                apiErr = new Message("Duyệt thành công sản phẩm có id " + maSP);

            }
        }
        return new ResponseEntity<>(apiErr, HttpStatus.OK);
    }

    @PutMapping("sanpham/{id}")
    public ResponseEntity<?> capNhatSanPham(@PathVariable("id") String id, @RequestBody SanPham objSanPham) {
        SanPham objSanPhamOld = sanPhamDao.layChiTietTheoMa(id);
        objSanPhamOld.setNgayCapNhat(new Date());
        if (objSanPhamOld == null) {
            Message apiErr = new Message("Không tìm thấy sản phẩm có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            objSanPhamOld.setTenSanPham(objSanPham.getTenSanPham());
            objSanPhamOld.setCpu(objSanPham.getCpu());
            objSanPhamOld.setRam(objSanPham.getRam());
            objSanPhamOld.setDungLuongPin(objSanPham.getDungLuongPin());
            objSanPhamOld.setManHinh(objSanPham.getManHinh());
            objSanPhamOld.setHeDieuHanh(objSanPham.getHeDieuHanh());
            objSanPhamOld.setThietKe(objSanPham.getThietKe());
            objSanPhamOld.setBaoHanh(objSanPham.getBaoHanh());
            objSanPhamOld.setThongTinChung(objSanPham.getThongTinChung());
            objSanPhamOld.setAnhSanPham(objSanPham.getAnhSanPham());
            objSanPhamOld.setGiaBan(objSanPham.getGiaBan());
            objSanPhamOld.setDanhMucId(objSanPham.getDanhMucId());
            objSanPhamOld.setThuongHieuId(objSanPham.getThuongHieuId());

            sanPhamDao.capNhat(objSanPhamOld);
            return new ResponseEntity<>(objSanPhamOld, HttpStatus.OK);
        }
    }

    @Autowired
    AnhSanPhamDao anhSanPhamDao;

    @DeleteMapping("sanpham/{id}")
    public ResponseEntity<?> xoaSanPham(@PathVariable("id") String id) {
        SanPham objSanPham = sanPhamDao.layChiTietTheoMa(id);
        if (objSanPham == null) {
            Message apiErr = new Message("Không tìm thấy sản phẩm có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            anhSanPhamDao.xoaTheoMaSanPham(id);
            sanPhamDao.xoa(id);
            Message apiErr = new Message("Xóa sản phẩm có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.OK);
        }
    }
}
