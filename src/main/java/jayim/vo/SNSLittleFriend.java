package jayim.vo;

/**
 * @Description
 * @Author Stringing
 * @Date 2018/12/22 12:50
 */
public class SNSLittleFriend {
    private Integer id;
    private String avatar;
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
