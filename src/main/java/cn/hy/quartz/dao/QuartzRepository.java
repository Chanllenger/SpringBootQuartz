package cn.hy.quartz.dao;

import cn.hy.quartz.dto.QuartzDto;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * <p>
 * 创建时间为 下午7:02-2018/6/19
 * 项目名称 SpringBootQuartz
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */


public interface QuartzRepository  extends PagingAndSortingRepository<QuartzDto, String> {



}
