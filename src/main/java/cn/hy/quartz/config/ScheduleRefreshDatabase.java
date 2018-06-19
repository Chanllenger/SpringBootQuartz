package cn.hy.quartz.config;

import cn.hy.quartz.dao.QuartzRepository;
import cn.hy.quartz.dto.QuartzDto;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


import javax.annotation.Resource;
import java.util.Optional;

/**
 * <p>
 * 创建时间为 下午7:01-2018/6/19
 * 项目名称 SpringBootQuartz
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */

@Configuration
@EnableScheduling
public class ScheduleRefreshDatabase {

    @Autowired
    private QuartzRepository repository;

    @Resource(name = "jobDetail")
    private JobDetail jobDetail;

    @Resource(name = "jobTrigger")
    private CronTrigger cronTrigger;

    @Resource(name = "scheduler")
    private Scheduler scheduler;

    @Scheduled(fixedRate = 1000) // 每隔1s查库，并根据查询结果决定是否重新设置定时任务
    public void scheduleUpdateCronTrigger() throws SchedulerException {
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(cronTrigger.getKey());
        String currentCron = trigger.getCronExpression();// 当前Trigger使用的
        Optional<QuartzDto> one = repository.findById("000001");
        String searchCron = one.get().getCron();
//        System.out.println(currentCron);
//        System.out.println(searchCron);
        if (!currentCron.equals(searchCron)) {

            System.out.println("currentCron:" + currentCron);
            System.out.println("currentCron:" + searchCron);

            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(searchCron);
            // 按新的cronExpression表达式重新构建trigger
            trigger = (CronTrigger) scheduler.getTrigger(cronTrigger.getKey());
            trigger = trigger.getTriggerBuilder().withIdentity(cronTrigger.getKey()).withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(cronTrigger.getKey(), trigger);
            currentCron = searchCron;
        }
        // 如果当前使用的cron表达式和从数据库中查询出来的cron表达式一致，则不刷新任务

    }

}
