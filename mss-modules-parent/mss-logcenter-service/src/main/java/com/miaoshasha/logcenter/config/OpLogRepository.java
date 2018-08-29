package com.miaoshasha.logcenter.config;

import com.miaoshasha.common.bean.OpLog;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author：fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-08-24
 * Time：17:49
 * ================================
 */
public interface OpLogRepository extends MongoRepository<OpLog, Long> {
}
