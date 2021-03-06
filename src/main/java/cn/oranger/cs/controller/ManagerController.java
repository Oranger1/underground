package cn.oranger.cs.controller;


import cn.hutool.core.convert.Convert;
import cn.oranger.cs.entity.Manager;
import cn.oranger.cs.requestVo.ManagerQueryVo;
import cn.oranger.cs.service.ManagerService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/page")
    public IPage<Manager> pageManager(@RequestBody ManagerQueryVo queryVo){
        return managerService.pageManagers(queryVo);
    }

    @GetMapping("/query")
    public List<Manager> queryManager(ManagerQueryVo queryVo){
        return managerService.queryManagers(queryVo);
    }

    @PostMapping("/add")
    public boolean addManager(@RequestBody Manager manager){
        return managerService.addManager(manager);
    }

    @PutMapping("/update")
    public boolean updateManager(@RequestBody Manager manager){
        return managerService.updateManager(manager);
    }

    @GetMapping("/del")
    public Integer delManager(@RequestParam Integer managerId){
        return managerService.removeById(managerId);
    }

}

