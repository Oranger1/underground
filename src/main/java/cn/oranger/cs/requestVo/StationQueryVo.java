package cn.oranger.cs.requestVo;

import cn.oranger.cs.entity.BasePage;

import java.util.Date;

/**
 * @author Oranger
 * @date 2021/1/17
 */
public class StationQueryVo extends BasePage {
    private Integer id;

    private String name;

    private Integer lineId;

    private Integer lastStationId;

    private Integer nextStationId;

    private Integer status;

    private Integer deleted;

    private Integer version;

    private Date creationTime;

    private Date updationTime;
}
