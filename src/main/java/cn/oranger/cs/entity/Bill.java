package cn.oranger.cs.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Oranger
 * @date 2021/3/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Long orderNumber;

    private Float price;

    private Integer payType;

    @TableLogic
    private Integer deleted;

    @Version
    private Integer version;

    @TableField(fill = FieldFill.INSERT)
    private Date creationTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updationTime;

}
