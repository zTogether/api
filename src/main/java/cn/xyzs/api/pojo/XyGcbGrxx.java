package cn.xyzs.api.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Table;
/***
 *
 * @Description: 工人档案库
 * @author: GeWeiliang
 * @date: 2018\9\14 0014 15:24
 * @param:
 * @return:
 */
@Table(name = "XY_GCB_GRXX")
public class XyGcbGrxx {
    //工人号
    @Id
    @Getter
    @Setter
    private String grId;
    //工人姓名
    @Getter
    @Setter
    private String grName;
    //身份证号
    @Getter
    @Setter
    private String  grIdcard;
    //电话号
    @Getter
    @Setter
    private String  grTel;
    //工种
    @Getter
    @Setter
    private String  grGz;
    //职务
    @Getter
    @Setter
    private String  gzZw;
    //级别
    @Getter
    @Setter
    private String  grLevel;
    //入职时间
    @Getter
    @Setter
    private String  grInDate;
    //离职日期
    @Getter
    @Setter
    private String  grOutDate;
    //工作状态
    @Getter
    @Setter
    private String  grStatu;
    //家庭地址
    @Getter
    @Setter
    private String  grAddr;
    //银行卡号
    @Getter
    @Setter
    private String  grBankid;
    //X定位
    @Getter
    @Setter
    private String  grGpsX;
    //Y定位
    @Getter
    @Setter
    private String  grGpsY;
    //地图版本
    @Getter
    @Setter
    private String  grGpsVersion;
    //所属机构
    @Getter
    @Setter
    private String  grOrg;
    @Getter
    @Setter
    private String  grLevelVm;
    //密码
    @Getter
    @Setter
    private String  password;

}
