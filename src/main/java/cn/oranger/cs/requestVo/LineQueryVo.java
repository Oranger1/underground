package cn.oranger.cs.requestVo;

import cn.oranger.cs.entity.BasePage;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author Oranger
 * @date 2021/1/17
 */
public class LineQueryVo extends BasePage {

    private Integer id;

    private Integer firstStationId;

    private Integer lastStationId;

    private Integer stationNumber;

    private Integer deleted;

    private Integer version;

    private Date creationTime;

    private Date updationTime;
}
