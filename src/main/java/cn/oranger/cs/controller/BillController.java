package cn.oranger.cs.controller;

import cn.oranger.cs.entity.Bill;
import cn.oranger.cs.requestVo.BillQueryVo;
import cn.oranger.cs.service.BillService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Oranger
 * @date 2021/3/14
 */
@RestController("")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping("/bill/page")
    public IPage<Bill> pageQuery(BillQueryVo queryVo){
       return billService.pageQuery(queryVo);
    }

    @PostMapping("/bill/query")
    public List<Bill> query(BillQueryVo queryVo){
        return billService.queryBill(queryVo);
    }

}
