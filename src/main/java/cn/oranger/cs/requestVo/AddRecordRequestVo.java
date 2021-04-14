package cn.oranger.cs.requestVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Oranger
 * @date 2021/3/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRecordRequestVo {

    private String startStation;

    private String endStation;

    private Long orderNumber;

    private Long takeRecordNumber;

}
