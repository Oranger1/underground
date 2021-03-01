package cn.oranger.cs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Oranger
 * @date 2021/2/26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class LineNode {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer lineId;

    private Integer sequence;

    private Integer x;

    private Integer y;

    /**
     * 标志方向
     */
    private String labelPos;

    /**
     * 是否中枢 interchange
     */
    private String marker;

    /**
     *目前还不知道作用
     */
    private Boolean canonical;
}
