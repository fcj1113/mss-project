package com.miaoshasha.common.entity.message;

import com.miaoshasha.common.base.AbstractBaseEntity;
import lombok.Data;

import java.util.Date;


/**                            
 *                             
 * @author fengchaojun <br/>   
 * ----------------------------
 * Created with IDEA.          
 * Date：2018-09-26            
 * Time：18:49:21                 
 * ----------------------------
 */                            
@Data
public class ReliableMessage extends AbstractBaseEntity {

		/**消息ID*/ 
		private Long messageId ;

		/**消息内容*/ 
		private String messageBody ;

		/**消息数据类型*/ 
		private String messageDataType ;

		/**MQ消费队列*/
		private String consumerQueue ;

		/**MQ路由键值*/
		private String routingKey;

		/**消息重发次数*/ 
		private Integer messageSendTimes ;

		/**是否死亡(Y：已死亡,N：未死亡)*/ 
		private String alreadyDead ;

		/**状态(WAIT_VERIFY：待确认,SENDING：发送中)*/ 
		private String status ;

		/**创建时间*/ 
		private Date createTime ;

		/**修改时间*/ 
		private Date updateTime ;

		/**创建者*/ 
		private Long createUser ;

		/**修改者*/ 
		private Long updateUser ;

		/**备注*/ 
		private String notes ;

		/**版本号*/ 
		private Long version ;

		/**业务系统唯一id*/ 
		private Long bizUniqueId ;

		@Override
		public Long getId() {
 			return this.messageId;
		}

}
