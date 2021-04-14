package cn.oranger.cs.service;

import cn.oranger.cs.entity.Bill;
import cn.oranger.cs.requestVo.BillQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author Oranger
 * @date 2021/3/7
 */
public interface BillService extends IService<Bill> {

    Integer addBill(float price);

    List<Bill> queryBill(BillQueryVo queryVo);

    IPage<Bill> pageQuery(BillQueryVo queryVo);

}
