package com.miaoshasha.mongo.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author：fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-08-24
 * Time：11:22
 * ================================
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.miaoshasha")
public class MongoAutoConfiguration {

}
