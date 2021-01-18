package cn.oranger.cs.requestVo;

import cn.oranger.cs.entity.BasePage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Oranger
 * @date 2021/1/3
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerQueryVo extends BasePage {
    private Integer id;

    private String name;

    private String password;

    private String sex;

    private Integer mobile;

    private String email;

    private Integer status;

    private Integer version;

    private Date creationTime;

    private Date updationTime;
}
