package com.miaoshasha.order;

import com.miaoshasha.common.dto.order.PromoDTO;
import com.miaoshasha.common.entity.order.OrderInfo;
import com.miaoshasha.common.entity.order.OrderProduct;
import com.miaoshasha.common.utils.Utils;
import com.miaoshasha.order.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceApplicationTests {


	@Autowired
	private OrderService orderService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void saveOrder(){

		PromoDTO promoDTO = new PromoDTO();
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setOrderNo("LS100889");
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
		promoDTO.setPromoId(29L);
		promoDTO.setUserId((long) Utils.genRandom(100000,999999));
		orderInfo.setMemberId((long) Utils.genRandom(1000000,9999999));

		try {
			orderService.savePromoOrder(promoDTO);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}

	}

}
