package com.miaoshasha.admin.monitor.notify;

import cn.hutool.core.date.DateUtil;
import de.codecentric.boot.admin.event.ClientApplicationEvent;
import de.codecentric.boot.admin.event.ClientApplicationStatusChangedEvent;
import de.codecentric.boot.admin.notify.AbstractStatusChangeNotifier;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务状态变更，发送通知
 * @author：fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-08-31
 * Time：09:56
 * ================================
 */
@Slf4j
public class ServiceStatusChangeNotifier extends AbstractStatusChangeNotifier {


    @Override
    protected void doNotify(ClientApplicationEvent clientApplicationEvent) throws Exception {
        String applicationName = clientApplicationEvent.getApplication().getName();
        String serviceId = clientApplicationEvent.getApplication().getId();
        log.info("-----Notify-----Application:{},{}",applicationName,serviceId);

        if (clientApplicationEvent instanceof ClientApplicationStatusChangedEvent) {

            String status = ((ClientApplicationStatusChangedEvent) clientApplicationEvent).getTo().getStatus();
            String time = DateUtil.date(clientApplicationEvent.getTimestamp()).toString();

            // todo 接入消息中心，发送通知给对应负责人


        }
    }
}
