package cn.oranger.cs.responseVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Oranger
 * @date 2021/4/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BillMapVo {
    private String timePoint;

    private String vaule;
}
