package cn.oranger.cs.responseVo;

import cn.oranger.cs.requestVo.map.MapLineVo;
import cn.oranger.cs.requestVo.map.MapStationVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

/**
 * @author Oranger
 * @date 2021/2/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapJsonObjVo {
    private HashMap<String, MapStationVo> stations;

    private List<MapLineVo> lines;


    public void put(MapStationVo mapStationVo){
        if (mapStationVo.getName()!=null){
            stations.put(mapStationVo.getName(),mapStationVo);
        }else{
            System.err.println("StationName为null");;
        }
    }

    public HashMap<String, MapStationVo> putAll(List<MapStationVo> list,HashMap<String, MapStationVo> stations){
        list.forEach(v->{
            if (v.getName()!=null){
                stations.put(v.getName(),v);
            }else{
                System.err.println("StationName为null");;
            }
        });
        return stations;

    }

    public MapStationVo getValueByKey(String key){
        return stations.get(key);
    }

    public MapStationVo removeByKey(String key){
        return stations.remove(key);
    }

}
