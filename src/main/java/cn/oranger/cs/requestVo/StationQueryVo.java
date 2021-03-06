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
public class StationQueryVo extends BasePage {
    private String name;

    private Integer lineId;

    private Boolean isOnlyStation;


}
