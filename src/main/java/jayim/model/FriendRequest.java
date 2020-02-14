package jayim.model;

import java.io.Serializable;
import java.util.Date;

public class FriendRequest implements Serializable {
    private Integer id;

    private String content;

    private Integer uid;

    private Integer fromId;

    private Integer fromGroup;

    private Integer type;

    private String remark;

    private String href;

    private Integer isRead;

    private Date time;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getFromGroup() {
        return fromGroup;
    }

    public void setFromGroup(Integer fromGroup) {
        this.fromGroup = fromGroup;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "FriendRequest{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", uid=" + uid +
                ", fromId=" + fromId +
                ", fromGroup=" + fromGroup +
                ", type=" + type +
                ", remark='" + remark + '\'' +
                ", href='" + href + '\'' +
                ", isRead=" + isRead +
                ", time=" + time +
                '}';
    }
}