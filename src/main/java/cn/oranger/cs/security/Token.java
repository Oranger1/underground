package cn.oranger.cs.security;

import lombok.Data;

import java.util.Date;

/**
 * @author Oranger
 * @date 2021/1/15
 */
@Data
public class Token {
    private Integer id;
    private String openId;
    private String role;
    private Date lastLogin;
}
