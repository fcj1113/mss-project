
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
├    ├── mss-gateway-service -- 网关[9010]
├    ├── mss-msgcenter-service -- 消息中心[9020]
├    └── mss-logcenter-service -- 日志中心[9030]
├── mss-services-parent  -- 微服务业务模块 
├    ├── mss-base-service -- 基础服务 [8001]
├    ├── mss-order-service -- 订单中心 [8002]
├    ├── mss-seckill-service -- 秒杀服务[8003]
├    └── 
├── mss-starers-parent  -- 封装的starter 
├    ├── mss-mongo-spring-boot-starter-parent -- MONGO组件
├    ├── mss-redis-spring-boot-starter-parent -- JEDIS组件，缓存注解，分布式锁等
└── mss-ui -- 前端界面[8080]