package cn.oranger.cs.requestVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Oranger
 * @date 2021/1/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequsetVo {
    private String username;
    private String password;
}
