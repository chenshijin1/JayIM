package jayim.vo;

import java.util.List;

/**
 * @Description
 * @Author Stringing
 * @Date 2018/12/15 10:47
 */
public class SNSRequest {
    private int code;
    private int pages;
    private List<SNSRequestData> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<SNSRequestData> getData() {
        return data;
    }

    public void setData(List<SNSRequestData> data) {
        this.data = data;
    }
}
