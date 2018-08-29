package com.miaoshasha.common.config;

import com.miaoshasha.common.utils.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 实例化一个分布式序列
 * Created by fengchaojun on 2018/7/2.
 */
@Configuration
public class SequenceConfig {

    @Autowired
    Environment env;

    @Bean
    public Sequence getSequence() {
        //机器id，首先从配置文件获取
        String workerId = env.getProperty("mss.worker.id");
        //从系统环境变量中获取
        Map<String, String> sysMap = System.getenv();
        if (StringUtils.isEmpty(workerId)){
            workerId = sysMap.get("MSS_WORK_ID");
        }

        if(StringUtils.isEmpty(workerId)){
            workerId = "0" ;
        }

        //数据中心ID，首先从配置文件获取
        String dataCenterId = env.getProperty("mss.data.center.id");

        if (StringUtils.isEmpty(dataCenterId)){
            //从系统环境变量中获取
            dataCenterId = sysMap.get("MSS_DATA_CENTER_ID");
        }

        if(StringUtils.isEmpty(dataCenterId)){
            dataCenterId = "0" ;
        }

        return new Sequence(Long.parseLong(workerId),Long.parseLong(dataCenterId));
    }
}
