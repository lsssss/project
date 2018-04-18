package java100.app.domain;

import java.util.List;


public class Business {

    protected int businessNo;
    protected String category;
    protected String businessName;
    protected String open_time;
    protected String close_time;
    protected String tel;
    protected String menu;
    protected String content;
    protected String url;
    protected String post_no;
    protected String primary_address;
    protected String detail_address;
    protected Member registrant;
    protected List<BusinessUploadFile> files;
    protected List<BusinessReview> reviews;
    public int getBusinessNo() {
        return businessNo;
    }
    
    public void setBusinessNo(int businessNo) {
        this.businessNo = businessNo;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getBusinessName() {
        return businessName;
    }
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
    public String getOpen_time() {
        return open_time;
    }
    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }
    public String getClose_time() {
        return close_time;
    }
    public void setClose_time(String close_time) {
        this.close_time = close_time;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getMenu() {
        return menu;
    }
    public void setMenu(String menu) {
        this.menu = menu;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getPost_no() {
        return post_no;
    }
    public void setPost_no(String post_no) {
        this.post_no = post_no;
    }
    public String getPrimary_address() {
        return primary_address;
    }
    public void setPrimary_address(String primary_address) {
        this.primary_address = primary_address;
    }
    public String getDetail_address() {
        return detail_address;
    }
    public void setDetail_address(String detail_address) {
        this.detail_address = detail_address;
    }
    public List<BusinessUploadFile> getFiles() {
        return files;
    }
    public void setFiles(List<BusinessUploadFile> files) {
        this.files = files;
    }

    public List<BusinessReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<BusinessReview> reviews) {
        this.reviews = reviews;
    }

    public Member getRegistrant() {
        return registrant;
    }

    public void setRegistrant(Member registrant) {
        this.registrant = registrant;
    }
  
}










