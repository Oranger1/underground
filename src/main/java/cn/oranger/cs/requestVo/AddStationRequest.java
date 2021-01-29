package cn.oranger.cs.requestVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Oranger
 * @date 2021/1/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddStationRequest {

    private String name;

    private Integer stationNumber;

    private Integer lineId;
}
