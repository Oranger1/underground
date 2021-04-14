package cn.oranger.cs.service;

import cn.oranger.cs.entity.Manager;
import cn.oranger.cs.entity.Station;
import cn.oranger.cs.requestVo.AddStationRequest;
import cn.oranger.cs.requestVo.ManagerQueryVo;
import cn.oranger.cs.requestVo.StationQueryVo;
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
public interface StationService extends IService<Station> {
    public IPage<Station> pageStations(StationQueryVo queryVo);

    public List<Station> queryStations(StationQueryVo queryVo);

    public Station getStation(Integer stationId);

    public List<Station> queryOnlyStation();

    public boolean addStation(AddStationRequest request);

    public boolean updateStation(Station station);

    public Integer removeById(Integer stationId);

    public List<Station> queryStationsByLineId(Integer lineId,Boolean isOnlyStation);

}
