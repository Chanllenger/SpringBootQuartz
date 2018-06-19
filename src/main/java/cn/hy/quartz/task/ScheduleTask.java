package cn.hy.quartz.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>
 * 创建时间为 下午6:54-2018/6/19
 * 项目名称 SpringBootQuartz
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */


@Component // 此注解必加
@EnableScheduling // 此注解必加
@Slf4j
public class ScheduleTask {


    public void sayHello() {
        log.info("============= hello ==========={}==========", new Date());

    }
}
