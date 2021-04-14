package cn.oranger.cs.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.oranger.cs.entity.Line;
import cn.oranger.cs.entity.LineNode;
import cn.oranger.cs.entity.Manager;
import cn.oranger.cs.entity.Station;
import cn.oranger.cs.mapper.StationMapper;
import cn.oranger.cs.requestVo.AddStationRequest;
import cn.oranger.cs.requestVo.StationQueryVo;
import cn.oranger.cs.service.LineNodeService;
import cn.oranger.cs.service.LineService;
import cn.oranger.cs.service.StationService;
import cn.oranger.cs.utils.Beans;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
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
public class StationServiceImpl extends ServiceImpl<StationMapper, Station> implements StationService {

    @Autowired
    private LineService lineService;
    @Autowired
    private LineNodeService lineNodeService;

    @Override
    public IPage<Station> pageStations(StationQueryVo queryVo) {
        QueryWrapper<Station> queryWrapper = new QueryWrapper<Station>();
        Page<Station> page;
        if (queryVo.getPage()!=null){
            page = new Page<Station>(queryVo.getPage(),queryVo.getLimit());
        }else{
            page = new Page<Station>();
        }
        Station station = Beans.copy(queryVo, Station.class);
        queryWrapper.setEntity(station).orderByAsc("sequence");

        if (queryVo.getIsOnlyStation() == true){
            queryWrapper.eq("status",1);
        }
        return this.page(page,queryWrapper);
    }

    @Override
    public List<Station> queryStations(StationQueryVo queryVo) {
        QueryWrapper<Station> queryWrapper = new QueryWrapper<Station>();
        Station station = Beans.copy(queryVo, Station.class);
        queryWrapper.setEntity(station).orderByAsc("station_number");
        if (queryVo.getIsOnlyStation()){
            queryWrapper.eq("status",1);
        }
        return this.list(queryWrapper);
    }

    @Override
    public Station getStation(Integer stationId) {
        return this.getById(stationId);
    }

    @Override
    public List<Station> queryOnlyStation() {
        QueryWrapper<Station> queryWrapper = new QueryWrapper<Station>();
        queryWrapper.ne("name","").orderByAsc("station_number");
        return this.list(queryWrapper);
    }

    @Override
    @Transactional("")
    public boolean addStation(AddStationRequest request) {
//        LineNode lineNode = new LineNode();
        Station station = BeanUtil.copyProperties(request,Station.class);
        station.setLabel(request.getName());
        station.setCreationTime(new Date());
        station.setMarker("interchange");
        //修改影响的站的信息

        //增加站
        this.save(station);
        if (request.getIsStation()){
            station.setStatus(1);
        }else {
            station.setStatus(2);
        }

        LineNode lineNode = BeanUtil.copyProperties(station, LineNode.class);
        lineNodeService.save(lineNode);

        //修改线路总站数信息
        return true;
    }

    @Override
    public boolean updateStation(Station station) {
        station.setLabel(station.getName());
        return this.saveOrUpdate(station);
    }

    @Override
    public Integer removeById(Integer stationId) {
        return this.removeById(stationId);
    }

    @Override
    public List<Station> queryStationsByLineId(Integer lineId,Boolean isStation) {
        QueryWrapper<Station> queryWrapper = new QueryWrapper<>();
        if (isStation == true){
            queryWrapper.ge("name","");
        }
            queryWrapper.eq("line_id",lineId)
                .orderByAsc("sequence");
        return this.list(queryWrapper);
    }
}
