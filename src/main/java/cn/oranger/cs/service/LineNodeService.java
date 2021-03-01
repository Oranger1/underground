package cn.oranger.cs.service;

import cn.oranger.cs.entity.Line;
import cn.oranger.cs.entity.LineNode;
import cn.oranger.cs.requestVo.LineQueryVo;
import cn.oranger.cs.requestVo.map.MapNodeVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author Oranger
 * @date 2021/2/28
 */
public interface LineNodeService extends IService<LineNode> {

    public List<MapNodeVo> queryLineNodesById(Integer lineId);
}
