package cn.oranger.cs.controller;


import cn.oranger.cs.entity.Manager;
import cn.oranger.cs.requestVo.ManagerQueryVo;
import cn.oranger.cs.service.ManagerService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.beans.Beans;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Oranger
 * @since 2020-12-29
 */
@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping("/page")
    public IPage<Manager> pageManager(ManagerQueryVo queryVo){
        return managerService.pageManagers(queryVo);
    }

    @GetMapping("/query")
    public List<Manager> queryManager(ManagerQueryVo queryVo){
        return managerService.queryManagers(queryVo);
    }

    @PostMapping("/add")
    public boolean addManager(Manager manager){
        return managerService.addManager(manager);
    }

    @PostMapping("/update")
    public boolean updateManager(Manager manager){
        return managerService.addManager(manager);
    }

    @GetMapping("/del")
    public Integer delManager(Integer managerId){
        return managerService.removeById(managerId);
    }

}

