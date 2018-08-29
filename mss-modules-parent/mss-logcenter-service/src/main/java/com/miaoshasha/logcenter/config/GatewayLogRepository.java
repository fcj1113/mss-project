package com.miaoshasha.logcenter.config;

import com.miaoshasha.common.entity.system.SysLog;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author：fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-08-24
 * Time：17:49
 * ================================
 */
public interface GatewayLogRepository extends MongoRepository<SysLog, Long> {
}
