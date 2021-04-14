package cn.oranger.cs.requestVo.map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Oranger
 * @date 2021/2/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MapNodeVo {

    private List<Integer> coords;

    @JsonIgnoreProperties
    private String name;

    private String labelPos;

    private String marker;

    private Boolean canonical;

    /**
     * 1： 填所有属性，没有的属性为空会不会报错，
     * 2： 属性的顺序有没有要求。
     */

}
