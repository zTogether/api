package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_USER_GPS")
public class XyUserGps {

    //用户id
    @Column(name = "USER_ID")
    private String userId;

	//坐标类型
    @Column(name = "COORDINATE_TYPE")
    private String coordinateType;

	//纬度
    @Column(name = "LATITUDE")
    private String latitude;

	//经度
    @Column(name = "LONGITUDE")
    private String longitude;

	//最新修改时间
    @Column(name = "COORDINATE_UPDATE_DATE")
    private String coordinateUpdateDate;
}
