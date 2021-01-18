package cn.oranger.cs.mapper;

import cn.oranger.cs.entity.Manager;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Oranger
 * @since 2020-12-29
 */
@Mapper
public interface ManagerMapper extends BaseMapper<Manager> {

}
