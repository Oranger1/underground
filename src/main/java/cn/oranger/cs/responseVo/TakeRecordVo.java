package cn.oranger.cs.responseVo;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author Oranger
 * @date 2021/4/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TakeRecordVo {
    private List<Integer> timePoints;

    private List<Long> vaules;
}
