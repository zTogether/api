package cn.xyzs.api.pojo.API;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "XY_API_INFO")
public class ClientAPI {
    @Id
    @Column(name = "CLIENT_ID")
    private String clientId;
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "PT_TYPE")
    private Integer ptType;
    @Column(name = "LAST_LOGIN_TIME")
    private Long lastLoginTime;
    @Column(name = "LAST_LOGIN_ADDR")
    private String lastLoginAddr;
    @Column(name = "REMARK")
    private String remark;
}
