package com.miaoshasha.api.order;

import com.miaoshasha.common.api.order.OrderControllerApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-09-20
 * Time：19:24
 * -----------------------------
 */
@FeignClient(value = "order-service", path = "order-service")
public interface OrderRemoteClient extends OrderControllerApi {
}
