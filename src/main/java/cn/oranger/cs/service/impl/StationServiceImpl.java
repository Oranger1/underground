package cn.oranger.cs.service.impl;

import cn.oranger.cs.entity.Line;
import cn.oranger.cs.entity.Manager;
import cn.oranger.cs.entity.Station;
import cn.oranger.cs.mapper.StationMapper;
import cn.oranger.cs.requestVo.AddStationRequest;
import cn.oranger.cs.requestVo.StationQueryVo;
import cn.oranger.cs.service.LineService;
import cn.oranger.cs.service.StationService;
import cn.oranger.cs.utils.Beans;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        queryWrapper.setEntity(station);


        return this.page(page,queryWrapper);
    }

    @Override
    public List<Station> queryStations(StationQueryVo queryVo) {
        QueryWrapper<Station> queryWrapper = new QueryWrapper<Station>();
        Station station = Beans.copy(queryVo, Station.class);
        queryWrapper.setEntity(station);
        return this.list(queryWrapper);
    }

    @Override
    public Station getStation(Integer stationId) {
        return this.getById(stationId);
    }

    @Override
    @Transactional("")
    public boolean addStation( AddStationRequest request) {
        Station station = Beans.copy(request,Station.class);
        station.setCreationTime(new Date());
        //修改影响的站的信息
        UpdateWrapper<Station> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                .setSql("station_number = (station_number + 1)")
                .eq("line_id",request.getLineId())
                .ge("station_number",request.getLineId());
        boolean save = this.update(updateWrapper);

        //增加站
        this.save(station);

        //修改线路总站数信息
        UpdateWrapper<Line> lineUpdateWrapper = new UpdateWrapper<>();
        lineUpdateWrapper.eq("id",request.getLineId()).setSql("total_station = total_station + 1");
        return lineService.update(lineUpdateWrapper);
    }

    @Override
    public boolean updateStation(Station station) {
        return this.saveOrUpdate(station);
    }

    @Override
    public Integer removeById(Integer stationId) {
        return this.removeById(stationId);
    }

    @Override
    public List<Station> queryStationsByLineId(Integer lineId) {
        QueryWrapper<Station> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("line_id",lineId)
                .orderByAsc("station_number");
        return this.list(queryWrapper);
    }
}
