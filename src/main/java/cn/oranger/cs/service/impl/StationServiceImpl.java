package cn.oranger.cs.service.impl;

import cn.oranger.cs.entity.Manager;
import cn.oranger.cs.entity.Station;
import cn.oranger.cs.mapper.StationMapper;
import cn.oranger.cs.requestVo.StationQueryVo;
import cn.oranger.cs.service.StationService;
import cn.oranger.cs.utils.Beans;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
    public boolean addStation(Station station) {
        return this.saveOrUpdate(station);
    }

    @Override
    public boolean updateStation(Station station) {
        return this.saveOrUpdate(station);
    }

    @Override
    public Integer removeById(Integer stationId) {
        return this.removeById(stationId);
    }
}
