package cn.oranger.cs.responseVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Oranger
 * @date 2021/3/14
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseVo {
    private Integer code;

    private String token;
}
