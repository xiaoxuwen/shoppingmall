package com.etn.shoppingmall.admin.config.quartz;

import com.etn.shoppingmall.core.service.OrderStatusJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Quartz调度配置类
 *
 * @author 10353
 */
@Component
public class QuartzConfiguration {

    @Autowired
    private OrderStatusJobService orderStatusJobService;

    //每分钟启动
    @Scheduled(cron = "0 0/1 * * * ?")
    public void createJobDetail() {
        orderStatusJobService.dealOrder();
    }

}
