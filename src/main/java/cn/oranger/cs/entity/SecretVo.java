package cn.oranger.cs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.AssertTrue;
import java.util.Date;

/**
 * @author Oranger
 * @date 2021/3/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SecretVo {
    private String beginStation;

    private String endStation;

    private float price;

    private Long takeRecordNumber;

}
