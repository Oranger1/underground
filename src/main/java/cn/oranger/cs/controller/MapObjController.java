package cn.oranger.cs.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import cn.oranger.cs.entity.Line;
import cn.oranger.cs.entity.Station;
import cn.oranger.cs.requestVo.LineQueryVo;
import cn.oranger.cs.requestVo.map.MapLineVo;
import cn.oranger.cs.requestVo.map.MapStationVo;
import cn.oranger.cs.responseVo.MapJsonObjVo;
import cn.oranger.cs.service.LineNodeService;
import cn.oranger.cs.service.LineService;
import cn.oranger.cs.service.StationService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Oranger
 * @date 2021/2/26
 */
@RestController
public class MapObjController {

    @Autowired
    private StationService stationService;
    @Autowired
    private LineService lineService;
    @Autowired
    private LineNodeService lIneNodeService;


    @PostMapping("/parseMap")
    public void parse(@RequestBody MapJsonObjVo mapJsonObjVo){
        mapJsonObjVo.getStations();
        JSON json = JSONUtil.parse(mapJsonObjVo.getStations());
        System.out.println(json);
        mapJsonObjVo.getLines();
        System.out.println(mapJsonObjVo.getLines());


    }

    @GetMapping("/getMap")
    public MapJsonObjVo getMap(){
        MapJsonObjVo mapJsonObjVo = new MapJsonObjVo();
        List<Station> stations = stationService.list();
        List<MapStationVo> mapStationVoList = new ArrayList<>();
        stations.forEach(v->{
            MapStationVo mapStationVo = BeanUtil.copyProperties(v, MapStationVo.class);
            System.out.println(mapStationVo.getName());
            mapStationVoList.add(mapStationVo);
        });
        HashMap<String, MapStationVo> mapStations = new HashMap<>();
        mapJsonObjVo.putAll(mapStationVoList, mapStations);
        mapJsonObjVo.setStations(mapStations);
        System.out.println("station: " + mapJsonObjVo);

        List<MapLineVo> lineVoList = new ArrayList<>();
        //先找出每条线路的所有站，按sequence排序
        LineQueryVo lineQueryVo = new LineQueryVo();
        List<Line> lines = lineService.queryLines(lineQueryVo);
        for (Line line : lines) {
            MapLineVo mapLineVo = new MapLineVo();
            mapLineVo.setName(line.getName());
            mapLineVo.setColor(line.getColor());
            mapLineVo.setLabel(line.getLabel());
            List<Integer> shiftCoords = Lists.newArrayList(0,0);
            mapLineVo.setShiftCoords(shiftCoords);
            mapLineVo.setNodes(lIneNodeService.queryLineNodesById(line.getId()));
            lineVoList.add(mapLineVo);
        }
        mapJsonObjVo.setLines(lineVoList);
        return mapJsonObjVo;
    }
}
