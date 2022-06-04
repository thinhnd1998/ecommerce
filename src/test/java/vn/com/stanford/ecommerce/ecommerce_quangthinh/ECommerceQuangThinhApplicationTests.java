package vn.com.stanford.ecommerce.ecommerce_quangthinh;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.DonHang;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.DonHangRepository;

import java.util.List;

@SpringBootTest
class ECommerceQuangThinhApplicationTests {

    @Autowired
    DonHangRepository donHangRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetDonHangByMaKH() {
        List<DonHang> lstDonHang = donHangRepository.findByMaKhachHang(2);

        System.out.println(lstDonHang.size());
    }

}
