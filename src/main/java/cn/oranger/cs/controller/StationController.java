package cn.oranger.cs.controller;


import cn.oranger.cs.entity.Manager;
import cn.oranger.cs.entity.Station;
import cn.oranger.cs.requestVo.ManagerQueryVo;
import cn.oranger.cs.requestVo.StationQueryVo;
import cn.oranger.cs.service.StationService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/station")
public class StationController {
    @Autowired
    private StationService stationService;

    @GetMapping("/page")
    public IPage<Station> pageStation(StationQueryVo queryVo){
        return stationService.pageStations(queryVo);
    }

    @GetMapping("/query")
    public List<Station> queryStation(StationQueryVo queryVo){
        return stationService.queryStations(queryVo);
    }

    @PostMapping("/add")
    public boolean addStation(Station station){
        return stationService.addStation(station);
    }

    @PostMapping("/update")
    public boolean updateStation(Station station){
        return stationService.updateStation(station);
    }

    @GetMapping("/del")
    public Integer delStation(Integer stationId){
        return stationService.removeById(stationId);
    }
}

