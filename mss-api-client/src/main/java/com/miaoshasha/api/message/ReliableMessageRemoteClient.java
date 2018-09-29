package com.miaoshasha.api.message;

import com.miaoshasha.common.api.message.ReliableMessageControllerApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-09-29
 * Time：15:56
 * -----------------------------
 */
@FeignClient(value = "mss-logcenter-service",path = "mss-logcenter-service")
public interface ReliableMessageRemoteClient extends ReliableMessageControllerApi {

}
