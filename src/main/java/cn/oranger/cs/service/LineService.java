package cn.oranger.cs.service;

import cn.oranger.cs.entity.Line;
import cn.oranger.cs.entity.Station;
import cn.oranger.cs.requestVo.LineQueryVo;
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
public interface LineService extends IService<Line> {

    public IPage<Line> pageLines(LineQueryVo queryVo);

    public List<Line> queryLines(LineQueryVo queryVo);

    public Line getLineById(Integer lineId);

    public boolean addLine(Line line);

    public boolean updateLine(Line line);

    public Integer removeById(Integer lineId);

}
