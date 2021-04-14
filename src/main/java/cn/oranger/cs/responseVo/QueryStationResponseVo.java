package cn.oranger.cs.responseVo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Oranger
 * @date 2021/3/3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryStationResponseVo {

    private Integer id;

    private String name;

    private String label;

    private Integer lineId;

    /**
     * 第几站
     */
    private Integer stationNumber;

    @ApiModelProperty(value = "1启用，0禁用")
    private Integer status;

    @TableLogic
    private Integer deleted;

    @Version
    private Integer version;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date creationTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updationTime;

    private Integer x;

    private Integer y;


}
