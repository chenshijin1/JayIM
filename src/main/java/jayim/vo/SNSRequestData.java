package jayim.vo;

/**
 * @Description
 * @Author Stringing
 * @Date 2018/12/15 12:44
 */
public class SNSRequestData {
    private int id;
    private String content;
    private int uid;
    private int from;
    private int from_group;
    private int type;
    private String remark;
    private String href;
    private int read;
    private String time;
    private SNSRequestUser user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getFrom_group() {
        return from_group;
    }

    public void setFrom_group(int from_group) {
        this.from_group = from_group;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public SNSRequestUser getUser() {
        return user;
    }

    public void setUser(SNSRequestUser user) {
        this.user = user;
    }
}
