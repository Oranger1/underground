package cn.oranger.cs.requestVo;

import cn.oranger.cs.entity.BasePage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Oranger
 * @date 2021/1/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TakeRecordQueryVo extends BasePage {
    private Byte status;

    /***
     * 维度
     */
    private String dimension;

    private Date creationTime;

    private Date getOnTime;

    private Date getOffTime;


}
