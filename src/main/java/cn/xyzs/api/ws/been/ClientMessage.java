package cn.xyzs.api.ws.been;

import java.io.Serializable;

public class ClientMessage implements Serializable {
    private String userid;
    private String groupid;
    private String context;
    private long datetime=System.currentTimeMillis();
    private String msgtype;

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public long getDatetime() {
        return datetime;
    }
    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }
    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}

