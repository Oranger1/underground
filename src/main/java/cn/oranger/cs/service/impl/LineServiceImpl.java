package cn.oranger.cs.service.impl;

import cn.oranger.cs.entity.Line;
import cn.oranger.cs.entity.Station;
import cn.oranger.cs.mapper.LineMapper;
import cn.oranger.cs.requestVo.LineQueryVo;
import cn.oranger.cs.service.LineService;
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
public class LineServiceImpl extends ServiceImpl<LineMapper, Line> implements LineService {

    @Override
    public IPage<Line> pageLines(LineQueryVo queryVo) {
        QueryWrapper<Line> queryWrapper = new QueryWrapper<Line>();
        Page<Line> page;
        if (queryVo.getPage()!=null){
            page = new Page<Line>(queryVo.getPage(),queryVo.getLimit());
        }else{
            page = new Page<Line>();
        }
        Line line = Beans.copy(queryVo, Line.class);
        queryWrapper.setEntity(line);


        return this.page(page,queryWrapper);
    }

    @Override
    public List<Line> queryLines(LineQueryVo queryVo) {
        QueryWrapper<Line> queryWrapper = new QueryWrapper<Line>();
        Line line = Beans.copy(queryVo, Line.class);
        queryWrapper.setEntity(line);
        return this.list(queryWrapper);
    }

    @Override
    public Line getLineById(Integer lineId) {
        return this.getById(lineId);
    }

    @Override
    public boolean addLine(Line line) {
        return this.saveOrUpdate(line);
    }

    @Override
    public boolean updateLine(Line line) {
        return this.saveOrUpdate(line);
    }

    @Override
    public Integer removeById(Integer lineId) {
        return this.removeById(lineId);
    }
}
