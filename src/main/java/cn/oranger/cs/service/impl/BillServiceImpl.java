package cn.oranger.cs.service.impl;

import cn.oranger.cs.entity.Bill;
import cn.oranger.cs.mapper.BillMapper;
import cn.oranger.cs.requestVo.BillQueryVo;
import cn.oranger.cs.service.BillService;
import cn.oranger.cs.utils.Beans;
import cn.oranger.cs.utils.SnowFlakeUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Oranger
 * @date 2021/3/7
 */
@Service
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements BillService {
    @Autowired
    private BillMapper billMapper;

    @Override
    public Integer addBill(float price) {
        Bill bill = new Bill();
        bill.setPrice(price);
        bill.setPayType(1);
        //根据雪花算法算：
        bill.setOrderNumber(SnowFlakeUtils.getSnowFlakeId());
        this.save(bill);
        return bill.getId();

    }

    @Override
    public List<Bill> queryBill(BillQueryVo queryVo) {
        return this.list();
    }

    @Override
    public IPage<Bill> pageQuery(BillQueryVo queryVo) {
        QueryWrapper<Bill> queryWrapper = new QueryWrapper<Bill>();
        Page<Bill> page;
        if (queryVo.getPage()!=null){
            page = new Page<Bill>(queryVo.getPage(),queryVo.getLimit());
        }else{
            page = new Page<Bill>();
        }
        Bill bill = Beans.copy(queryVo, Bill.class);
        queryWrapper.setEntity(bill);

        IPage<Bill> billIPage = billMapper.selectPage(page, queryWrapper);
        return billIPage;
    }
}
