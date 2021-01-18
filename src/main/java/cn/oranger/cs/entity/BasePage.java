package cn.oranger.cs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Oranger
 * @date 2021/1/3
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasePage {
    /**
     * 页数
     */
    private Integer page;

    /**
     * 页面大小
     */
    private Integer limit;
}
