package cn.oranger.cs.requestVo.map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * @author Oranger
 * @date 2021/2/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapMapVo {
    private HashMap<String,MapStationVo> map;
}
