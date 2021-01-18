package cn.oranger.cs.service;

import cn.oranger.cs.entity.Manager;
import cn.oranger.cs.requestVo.ManagerQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Oranger
 * @since 2020-12-29
 */
public interface ManagerService extends IService<Manager> {

    public IPage<Manager> pageManagers(ManagerQueryVo queryVo);

    public List<Manager> queryManagers(ManagerQueryVo queryVo);

    public Manager getManager(Integer managerId);

    public boolean addManager(Manager manager);

    public boolean updateManager(Manager manager);

    public Integer removeById(Integer managerId);



}
