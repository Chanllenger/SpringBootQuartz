package cn.hy.quartz.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <p>
 * 创建时间为 下午6:52-2018/6/19
 * 项目名称 SpringBootQuartz
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */

@Document(collection = "quartz_dto")
@Data
public class QuartzDto {

    @Id
    public String id;

    public String cron;

}
