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

    private String label;

    private Integer stationNumber;

    private Integer lineId;

    private Integer sequence;

    private Integer x;

    private Integer y;

    private String labelPost;

    private String marker;

    private Boolean canonical;

    private Boolean isStation;
}
