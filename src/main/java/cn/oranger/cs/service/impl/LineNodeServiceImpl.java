package cn.oranger.cs.service.impl;

import cn.oranger.cs.entity.LineNode;
import cn.oranger.cs.mapper.LineNodeMapper;
import cn.oranger.cs.requestVo.map.MapNodeVo;
import cn.oranger.cs.service.LineNodeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Oranger
 * @date 2021/2/28
 */
@Service
public class LineNodeServiceImpl extends ServiceImpl<LineNodeMapper, LineNode> implements LineNodeService {
    @Override
    public List<MapNodeVo> queryLineNodesById(Integer lineId) {
        QueryWrapper<LineNode> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sequence");
        queryWrapper.eq("line_id",lineId);
        this.list();

        List<LineNode> list = this.list(queryWrapper);
        return parseToMapNode(list);
    }

    public List<MapNodeVo> parseToMapNode(List<LineNode> list){
        List<MapNodeVo> mapNodeVoList = new ArrayList<>();
        for (LineNode lineNode : list) {
            List<Integer> coords = Lists.newArrayList(lineNode.getX(),lineNode.getY());
            MapNodeVo mapNodeVo = new MapNodeVo();
            mapNodeVo.setCoords(coords);
            mapNodeVo.setCanonical(lineNode.getCanonical());
            mapNodeVo.setLabelPos(lineNode.getLabelPos());
            mapNodeVo.setMarker(lineNode.getMarker());
            mapNodeVo.setName(lineNode.getName());
            mapNodeVoList.add(mapNodeVo);
        }
        return mapNodeVoList;
    }
}
