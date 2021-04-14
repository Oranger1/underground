package cn.oranger.cs.controller;

import cn.oranger.cs.entity.Bill;
import cn.oranger.cs.entity.SecretVo;
import cn.oranger.cs.entity.TakeRecord;
import cn.oranger.cs.requestVo.AddRecordRequestVo;
import cn.oranger.cs.responseVo.GetPriceResponseVo;
import cn.oranger.cs.service.BillService;
import cn.oranger.cs.service.MatrixService;
import cn.oranger.cs.service.TakeRecordService;
import cn.oranger.cs.utils.AESUtil;
import cn.oranger.cs.utils.JSONUtils;
import cn.oranger.cs.utils.SnowFlakeUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @author Oranger
 * @date 2021/3/7
 */
@RestController
public class BuyTicketController {
    @Autowired
    private MatrixService matrixService;
    @Autowired
    private BillService billService;
    @Autowired
    private TakeRecordService takeRecordService;

    @GetMapping("/station/getPrice")
    public GetPriceResponseVo selectPrice(@RequestParam String beginStation, @RequestParam String endStation) throws JsonProcessingException {
        //广搜算出最短站数。
        int distance = matrixService.queryDistance(beginStation, endStation);
        int price = 0;
        //根据站数算价格
        if (distance <= 4){
            price = 2 ;
        }else {
             price= 2 + (distance-4)/4;
            if (distance%4 != 0){
                price++;
            }
        }

        SecretVo secretVo = new SecretVo();
        secretVo.setBeginStation(beginStation);
        secretVo.setPrice(price);
        secretVo.setEndStation(endStation);
        secretVo.setTakeRecordNumber(SnowFlakeUtils.getSnowFlakeId());

        String content = JSONUtils.toJSONString(secretVo);
        AESUtil aesUtil = new AESUtil();
        String publicKey = aesUtil.generatePublicKey(content);


        GetPriceResponseVo getPriceResponseVo = new GetPriceResponseVo();
        getPriceResponseVo.setPublicKey(publicKey);
        getPriceResponseVo.setPrice(price);

        return getPriceResponseVo;
    }

    @GetMapping("/queryDistance")
    public Integer queryDistance(@RequestParam String beginStation, @RequestParam String endStation){
        return matrixService.queryDistance(beginStation,endStation);
    }

    /**
     * 支付成功，回调函数
     *
     * @// TODO: 2021/3/7  生成对应的订单，乘车记录
     * @return  乘车码
     */

    @GetMapping("/apily")
    @Transactional("")
    public Long apily(@RequestParam String publicKey){
        AESUtil aesUtil = new AESUtil();
        String content = aesUtil.parsePublicKey(publicKey);
        SecretVo secretVo = JSONUtils.parseIgnoreErrors(content, SecretVo.class);
        Integer billId = billService.addBill(secretVo.getPrice());
        Bill bill = billService.getById(billId);
        AddRecordRequestVo requestVo = new AddRecordRequestVo();
        requestVo.setOrderNumber(bill.getOrderNumber());
        requestVo.setStartStation(secretVo.getBeginStation());
        requestVo.setEndStation(secretVo.getEndStation());
        requestVo.setTakeRecordNumber(secretVo.getTakeRecordNumber());
        return takeRecordService.addTakeRecord(requestVo);
    }

    @GetMapping("/queryIsApily")
    @Transactional("")
    public Long queryIsApily(@RequestParam String publicKey){
        AESUtil aesUtil = new AESUtil();
        String content = aesUtil.parsePublicKey(publicKey);
        SecretVo secretVo = JSONUtils.parseIgnoreErrors(content, SecretVo.class);
        TakeRecord takeRecord = takeRecordService.getByTakeRecordNumber(secretVo.getTakeRecordNumber());
        if (takeRecord != null){
            return takeRecord.getTakeRecordNumber();
        }else {
            return null;
        }

    }
}
