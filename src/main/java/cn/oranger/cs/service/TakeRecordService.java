package cn.oranger.cs.service;

import cn.oranger.cs.entity.Station;
import cn.oranger.cs.entity.TakeRecord;
import cn.oranger.cs.requestVo.AddRecordRequestVo;
import cn.oranger.cs.requestVo.StationQueryVo;
import cn.oranger.cs.requestVo.TakeRecordQueryVo;
import cn.oranger.cs.responseVo.TakeRecordVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Oranger
 * @since 2020-12-29
 */
public interface TakeRecordService extends IService<TakeRecord> {

    public IPage<TakeRecord> pageTakeRecord(TakeRecordQueryVo queryVo);

    public TakeRecordVo queryTakeRecord(TakeRecordQueryVo queryVo);

    public Long addTakeRecord(AddRecordRequestVo addRecordRequestVo);

    public List<TakeRecord> addTakeRecord();

    public TakeRecord getByTakeRecordNumber(Long takeRecordNumber);

    public Boolean updateStatusByTakeRecordNumber(Long taskRecordNumber,Integer status);

}
