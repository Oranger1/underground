package cn.oranger.cs.requestVo;

import cn.oranger.cs.entity.BasePage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author Oranger
 * @date 2021/3/14
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BillQueryVo extends BasePage {
    private Date creationTime;
}
