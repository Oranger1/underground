package cn.oranger.cs.controller;


import cn.oranger.cs.entity.Line;
import cn.oranger.cs.requestVo.LineQueryVo;
import cn.oranger.cs.service.LineService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/line")
public class LineController {
    @Autowired
    private LineService lineService;

    @GetMapping("/page")
    public IPage<Line> pageLine(){
        LineQueryVo queryVo = new LineQueryVo();
        return lineService.pageLines(queryVo);
    }

    @GetMapping("/query")
    public List<Line> queryLine(){
        return lineService.list();
    }

    @PostMapping("/add")
    public boolean addLine(@RequestBody Line line){
        return lineService.addLine(line);
    }

    @PutMapping("/update")
    public boolean updateLine(Line line){
        return lineService.updateLine(line);
    }

    @GetMapping("/del")
    public Integer delLine(Integer lineId){
        return lineService.removeById(lineId);
    }
}

