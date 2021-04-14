package cn.oranger.cs.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Oranger
 * @date 2021/4/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseVo implements Serializable {
    private List<Object> controls;
    private Custom custom;
    private Status status;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Custom implements Serializable {

        @JsonProperty("access_token")
        private String accessToken;

        @JsonProperty("refresh_token")
        private String refreshToken;

        private String jsessionid;

        @JsonProperty("expires_in")
        private String expiresIn;

    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private class Status{
        private String code;

    }
}
