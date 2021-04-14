package cn.oranger.cs.controller;


import cn.oranger.cs.entity.TakeRecord;
import cn.oranger.cs.requestVo.TakeRecordQueryVo;
import cn.oranger.cs.responseVo.TakeRecordVo;
import cn.oranger.cs.service.TakeRecordService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/takeRecord")
public class TakeRecordController {

    @Autowired
    private TakeRecordService takeRecordService;


    @PostMapping("/page")
    public IPage<TakeRecord> pageTakeRecord(TakeRecordQueryVo takeRecordQueryVo){
        return  takeRecordService.pageTakeRecord(takeRecordQueryVo);
    }

    @PostMapping("/query")
    public TakeRecordVo queryTakeRecord(TakeRecordQueryVo takeRecordQueryVo){
        return  takeRecordService.queryTakeRecord(takeRecordQueryVo);
    }


    /**
     * 入站
     * @param takeRecordNumber
     * @return
     */
    @GetMapping("/getOn")
    public Boolean getOn(@RequestParam Long takeRecordNumber){
        return takeRecordService.updateStatusByTakeRecordNumber(takeRecordNumber,1);
    }

    /**
     * 出站
     *
     * 判断是否在对应的站出站、
     * 不对的话做对应的处理
     * 少给钱重新支付，
     * 多给钱不退直接支付。
     * @param takeRecordNumber
     * @return
     */
    @GetMapping("/getOff")
    public Boolean getOff(@RequestParam Long takeRecordNumber){
        return takeRecordService.updateStatusByTakeRecordNumber(takeRecordNumber,2);
    }
}

