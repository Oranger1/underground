package cn.oranger.cs.requestVo.map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;
import java.util.List;

/**
 * @author Oranger
 * @date 2021/2/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapLineVo {
    private String name;

    private String label;

    private String color;

    private List<Integer> shiftCoords;

    private List<MapNodeVo> nodes;
}
