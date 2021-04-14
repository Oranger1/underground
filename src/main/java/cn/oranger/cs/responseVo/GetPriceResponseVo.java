package cn.oranger.cs.responseVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.AssertTrue;

/**
 * @author Oranger
 * @date 2021/3/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetPriceResponseVo {
    private float price;

    private String publicKey;
}
