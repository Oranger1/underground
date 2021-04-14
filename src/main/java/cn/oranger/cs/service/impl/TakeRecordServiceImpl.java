package cn.oranger.cs.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.oranger.cs.entity.Bill;
import cn.oranger.cs.entity.TakeRecord;
import cn.oranger.cs.mapper.TakeRecordMapper;
import cn.oranger.cs.requestVo.AddRecordRequestVo;
import cn.oranger.cs.requestVo.TakeRecordQueryVo;
import cn.oranger.cs.responseVo.TakeRecordVo;
import cn.oranger.cs.service.TakeRecordService;
import cn.oranger.cs.utils.Beans;
import cn.oranger.cs.utils.DateUtils;
import cn.oranger.cs.utils.SnowFlakeUtils;
import cn.oranger.cs.utils.StreamUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Oranger
 * @since 2020-12-29
 */
@Service
public class TakeRecordServiceImpl extends ServiceImpl<TakeRecordMapper, TakeRecord> implements TakeRecordService {
    @Autowired
    private TakeRecordMapper takeRecordMapper;

    @Override
    public IPage<TakeRecord> pageTakeRecord(TakeRecordQueryVo queryVo) {
        QueryWrapper<TakeRecord> queryWrapper = new QueryWrapper<TakeRecord>();
        Page<TakeRecord> page;
        if (queryVo.getPage()!=null){
            page = new Page<TakeRecord>(queryVo.getPage(),queryVo.getLimit());
        }else{
            page = new Page<TakeRecord>();
        }
        TakeRecord takeRecord = Beans.copy(queryVo, TakeRecord.class);
        queryWrapper.setEntity(takeRecord);

        IPage<TakeRecord> takeRecordIPage = takeRecordMapper.selectPage(page, queryWrapper);
        return takeRecordIPage;
    }

    @Override
    public TakeRecordVo queryTakeRecord(TakeRecordQueryVo queryVo) {
        QueryWrapper<TakeRecord> queryWrapper = new QueryWrapper<TakeRecord>();
//        if (StringUtils.isNotBlank(queryVo.getCreationTime().toString())){
//            queryWrapper.between("creation_time",queryVo.getCreationTime(), DateUtil.offset(queryVo.getCreationTime(), DateField.DAY_OF_MONTH,1));
//        }
//        DateTime yesterday = DateUtil.yesterday();
//        DateTime time = DateUtil.offset(yesterday, DateField.DAY_OF_MONTH,1);
//        queryWrapper.between("creation_time",queryVo.getCreationTime(),time);
//        TakeRecord takeRecord = new Ta
//        queryWrapper.setEntity()
        List<TakeRecord> takeRecords = takeRecordMapper.selectList(queryWrapper);

        Map<Integer, Long> group = StreamUtils.groupCount(takeRecords, v -> v.getCreationTime().getHours());

        List<Integer> timepointList = new ArrayList<>();
        List<Long> vaules = new ArrayList<>();
        for (int i = 0 ; i <24; i++ ){
            long zero = 0;
            timepointList.add(i);
            if (group.get(i) != null){
                vaules.add(group.get(i));
            }else {
                vaules.add(zero);
            }
        }
        TakeRecordVo takeRecordVo = new TakeRecordVo();
        takeRecordVo.setTimePoints(timepointList);
        takeRecordVo.setVaules(vaules);

        return takeRecordVo;
    }

    @Override
    public Long addTakeRecord(AddRecordRequestVo addRecordRequestVo) {
        TakeRecord takeRecord = new TakeRecord();
        takeRecord.setOrderNumber(addRecordRequestVo.getOrderNumber());
        takeRecord.setStatus(0);
        takeRecord.setStartStation(addRecordRequestVo.getStartStation());
        takeRecord.setEndStation(addRecordRequestVo.getEndStation());
        takeRecord.setTakeRecordNumber(addRecordRequestVo.getTakeRecordNumber());
        this.save(takeRecord);
        return takeRecord.getTakeRecordNumber();
    }

    @Override
    public List<TakeRecord> addTakeRecord() {
        return null;
    }

    @Override
    public TakeRecord getByTakeRecordNumber(Long takeRecordNumber) {
        TakeRecord takeRecord = this.lambdaQuery().eq(TakeRecord::getTakeRecordNumber, takeRecordNumber).one();
        return takeRecord;
    }

    @Override
    public Boolean updateStatusByTakeRecordNumber(Long taskRecordNumber, Integer status) {
        TakeRecord takeRecord = getByTakeRecordNumber(taskRecordNumber);
        if (status == 1){
            takeRecord.setGetOnTime(new Date());
        }else if(status == 2 ){
            takeRecord.setGetOffTime(new Date());
        }
        takeRecord.setStatus(status);
        return this.updateById(takeRecord);
    }
}
