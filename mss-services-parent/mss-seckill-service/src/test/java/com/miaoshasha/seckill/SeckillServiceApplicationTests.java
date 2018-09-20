package com.miaoshasha.seckill;

import com.miaoshasha.common.dto.order.PromoDTO;
import com.miaoshasha.common.entity.order.OrderInfo;
import com.miaoshasha.common.entity.order.OrderProduct;
import com.miaoshasha.common.entity.store.PromoInfo;
import com.miaoshasha.common.utils.Sequence;
import com.miaoshasha.common.utils.Utils;
import com.miaoshasha.seckill.service.PromoService;
import com.miaoshasha.seckill.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillServiceApplicationTests {


    @Test
    public void contextLoads() {
    }

    @Test
    public void mongoTest() {

    }

    @Autowired
    PromoService promoService;

    @Autowired
    SeckillService seckillService ;

    @Test
    public void promoSaveTest() {
        PromoInfo promoInfo = new PromoInfo();
        promoInfo.setPromoName("测试一元抢购31");
        promoInfo.setStockQuantity(2000);
        promoInfo.setBeginTime(System.currentTimeMillis() + 60000L);
        promoInfo.setEndTime(System.currentTimeMillis() + 36000000L);
        promoInfo.setCreateTime(new Date());
        promoInfo.setCreateUser(10001L);
        promoInfo.setIsApply(false);
        promoInfo.setSellQuantity(0);
        promoInfo.setLimitQuantity(1);
        promoInfo.setPromoPrice(100L);
        promoInfo.setState(1);
        promoInfo.setStoreId(1000005L);
        promoInfo.setProductId(1L);
        promoInfo.setSkuId(1L);
        promoInfo.setPromoType(1L);
        promoService.save(promoInfo);
    }

    //抢购测试
    @Test
    public void purchaseTest() {
        PromoDTO promoDTO = new PromoDTO();
        OrderInfo orderInfo = new OrderInfo();
        //orderInfo.setOrderNo("LS10002");
        orderInfo.setIsPay(false);

        orderInfo.setPayType(1);
        orderInfo.setTotalAmount(29900L);
        orderInfo.setPayAmount(29900L);
        orderInfo.setState(20);
        orderInfo.setSendType(1);
        orderInfo.setSource(1);
        orderInfo.setStoreId(5L);
        orderInfo.setIsInvoice(false);
        orderInfo.setCreateUser(1L);
        orderInfo.setCreateTime(new Date());

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setCreateUser(1L);
        orderProduct.setCreateTime(new Date());
        //orderProduct.setOrderId(50001L);
        orderProduct.setQuantity(1);
        orderProduct.setSkuId(1L);
        orderProduct.setProductId(1L);
        orderProduct.setProductPrice(29900L);

        promoDTO.setOrderProduct(orderProduct);
        promoDTO.setOrderInfo(orderInfo);
        promoDTO.setPromoId(31L);
        promoDTO.setUserId((long) Utils.genRandom(100000,999999));
        orderInfo.setMemberId((long) Utils.genRandom(1000000,9999999));
        ExecutorService pool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 20; i++) {
            pool.execute(() -> {
//                seckillService.doSecKill(promoDTO);
                seckillService.testSeq();
            });

        }
    }

    @Test
    public void getPromoTest(){
        promoService.findById(4L);
        promoService.getPromoInfo();
    }


    @Autowired
    private Sequence sequence;


    //19630213721751552
    //19630668719849472
    @Test
    public void testSeq(){
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i <1000 ; i++) {
            pool.execute(() -> {
                long l1 = sequence.nextId();
                System.out.println("------------" + l1 + "-------------");
            });
        }

    }

}
