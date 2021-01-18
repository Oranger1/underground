package cn.oranger.cs.service.impl;
import com.google.common.collect.Lists;

import cn.oranger.cs.entity.Manager;
import cn.oranger.cs.mapper.ManagerMapper;
import cn.oranger.cs.requestVo.ManagerQueryVo;
import cn.oranger.cs.service.ManagerService;
import cn.oranger.cs.utils.Beans;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Oranger
 * @since 2020-12-29
 */
@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements ManagerService {
    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public IPage<Manager> pageManagers(ManagerQueryVo queryVo) {
        QueryWrapper<Manager> queryWrapper = new QueryWrapper<Manager>();
        Page<Manager> page;
        if (queryVo.getPage()!=null){
            page = new Page<Manager>(queryVo.getPage(),queryVo.getLimit());
        }else{
            page = new Page<Manager>();
        }
        Manager manager = Beans.copy(queryVo, Manager.class);
        queryWrapper.setEntity(manager);

        return managerMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Manager> queryManagers(ManagerQueryVo queryVo) {
        QueryWrapper<Manager> queryWrapper = new QueryWrapper<Manager>();
        Manager manager = Beans.copy(queryVo, Manager.class);
        queryWrapper.setEntity(manager);
        return managerMapper.selectList(queryWrapper);
    }

    @Override
    public Manager getManager(Integer managerId) {
        return null;
    }

    @Override
    public boolean addManager(Manager manager) {
        return this.saveOrUpdate(manager);
    }

    @Override
    public boolean updateManager(Manager manager) {
        return this.saveOrUpdate(manager);
    }

    @Override
    public Integer removeById(Integer managerId) {
        return managerMapper.deleteById(managerId);
    }


}
