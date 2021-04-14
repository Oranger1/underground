package cn.oranger.cs.broker;

import cn.hutool.core.io.resource.FileObjectResource;
import cn.oranger.cs.entity.Station;
import cn.oranger.cs.service.LineService;
import cn.oranger.cs.service.StationService;
import cn.oranger.cs.utils.StreamUtils;
import com.sun.xml.internal.bind.v2.model.core.EnumLeafInfo;
import io.swagger.models.auth.In;
import org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.FutureOrPresentValidatorForDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.print.attribute.standard.MediaPrintableArea;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Oranger
 * @date 2021/3/4
 */
@Component
public class InitialMatrix {
    private Integer[][]  matrix = null;
    private HashMap<String,Integer> nameMap = null;

    @Autowired
    private StationService stationService;
    @Autowired
    private LineService lineService;

    public Integer[][] getMatrix(){
        if (matrix == null){
            matrix = initMatrix();
            return matrix;
        }else {
            return matrix;
        }
    }

    public HashMap<String,Integer> getNameMap(){
        if (nameMap == null){
            return initNameMap();
        }else {
            return nameMap;
        }
    }

    public HashMap<String,Integer> initNameMap(){
        List<Station> allStations = stationService.queryOnlyStation();
        HashMap<String,Integer> map = new HashMap<>();
        Integer flag = 1;
        for (Station station : allStations) {
            //将地铁站名 映射到对应的下标值
            if (map.get(station.getName())==null){
                map.put(station.getName(),flag);
                System.out.println(flag+"---"+station.getName());
                flag++;
            }
        }
        return map;
    }

    public Integer[][] initMatrix(){
        List<Station> allStations = stationService.queryOnlyStation();
        int size = allStations.size();
        matrix = new Integer[size+1][size+1];

        Map<String, Integer> nameMap = getNameMap();
        int lineSize = lineService.count();

        //找到每个地铁站的
        for (int i =1;i<=lineSize;i++){
            List<Station> stations = stationService.queryStationsByLineId(i, true);
            for (int j = 0; j< stations.size()-1 ;j++){
                Integer x = nameMap.get(stations.get(j).getName());
                Integer y = nameMap.get(stations.get(j+1).getName());
                matrix[x][y] = 1;
                matrix[y][x] = 1;
            }
        }

        return matrix;
    }

    public void updateMatrix(){
        nameMap = initNameMap();
        matrix = initMatrix();
    }

}
