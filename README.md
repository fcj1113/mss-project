##项目结构
``` lua		
mss-product
├── mss-api-client -- 服务调用的封装 
├── mss-commons-parent -- 公共模块 
├    ├── mss-common -- 业务公共组件
├    ├── mss-common-entity -- 公共实体
├    ├── mss-common-utils -- 工具
├── mss-modules-parent -- 微服务基础平台模块
├    ├── mss-auth-service -- 授权服务[9040]
├    ├── mss-eureka-service -- 注册中心[9001-9009]
├    ├── mss-gateway-service -- zuul网关[9010]，
├    ├── mss-msgcenter-service -- 消息中心[9020]
├    └── mss-logcenter-service -- 日志中心[9030]
├── mss-services-parent  -- 微服务业务模块 
├    ├── mss-base-service -- 基础服务 [8001]
├    ├── mss-order-service -- 订单中心 [8002]
├    ├── mss-seckill-service -- 秒杀服务[8003]
├    └── 
├── mss-starers-parent  -- 封装的starter 
├    ├── mss-mongo-spring-boot-starter-parent -- MONGO组件
├    ├── mss-ratelimiter-spring-boot-starter-parent -- API限流组件
├    ├── mss-redis-spring-boot-starter-parent -- JEDIS组件，缓存注解，分布式锁等
├── mss-services-admin-parent  -- 服务治理组件 
├    ├── mss-monitor-service -- 服务监控[9100]
├    ├── mss-zipkin-service -- 链路跟踪[9411]，接入ElasticSearch 
└── mss-ui -- 前端界面[8080]

```

## 系统功能
>- 登录鉴权：基于JWT完成的token鉴权
>- 分库分表：sharding-jdbc分库分表策略
>- ZUUL网关：实现动态路由，支持DB和redis的可配置化，支持网关日志，网关限流，网关鉴权
>- 日志中心：记录服务日志和网关日志，系统日志接入ELK，使用rabbitMq；（访问量大时建议使用kafka）；日志加入sleuth的traceID信息，各服务通过logback配置记录到ELK；
>- 服务限流：支持网关层的限流，包括服务和api配置化限流；支持单应用限流，注解模式；
>- API文档：swagger各个模块的实现
>- 缓存组件：基于Jedis封装的缓存组件，支持高可用，并对缓存穿透，缓存雪崩进行了高并发处理；
>- 链路追踪：sleuth+zipkin的trace跟踪，数据保存ELK，Kibana图形化展示；
>- 服务监控: Spring Boot Admin
>- 注册中心：eureka
>- 抢购秒杀：支持高并发抢购或秒杀，使用了基于redis的分布式锁，rabbitMq等等，可借助于网关进行限流；
>- 分布式锁：基于redis的分布式锁，lua实现；
>- 分布式ID：基于Snowflake算法实现分布式ID，已解决偶数偏多的问题；时间回拨引发的问题方案：记录最后生成id的毫秒时间，与当前生成时间判断，若相差小于10毫秒，等待后再生成，超过可换成新的workid；
>- 基础服务：包括用户管理，菜单管理，角色管理，权限管理，数据字典等等
>- 消息中心：短信、邮件  待实现...
>- 任务调度：基于elastic-job的分布式任务 待实现...
>- 配置中心：spring cloud config  待实现...
>- 前端界面：基于avue框架实现  待实现...


## 未完待续...
