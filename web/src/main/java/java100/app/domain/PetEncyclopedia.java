package java100.app.domain;

import java.sql.Date;
import java.util.List;

public class PetEncyclopedia {
	protected int petEncyclopediaNo; // 아지백과번호 ped_no
	protected String category; // 카테고리 ctg
	protected String title; // 제목 title
	protected String contents; // 설명 conts
	protected Date registrationDate; // 등록일시 rdt
	protected int viewCount; // 조회수 vwcnt
	protected int likes; // 추천수 likes
	protected String maintextContent; // 본문내용 m_conts
	protected Member member; // 회원번호 m_no
	protected List<PetEncyclopediaUploadFile> files; // 아지백과미디어

	public int getPetEncyclopediaNo() {
		return petEncyclopediaNo;
	}

	public void setPetEncyclopediaNo(int petEncyclopediaNo) {
		this.petEncyclopediaNo = petEncyclopediaNo;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getMaintextContent() {
		return maintextContent;
	}

	public void setMaintextContent(String maintextContent) {
		this.maintextContent = maintextContent;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List<PetEncyclopediaUploadFile> getFiles() {
		return files;
	}

	public void setFiles(List<PetEncyclopediaUploadFile> files) {
		this.files = files;
	}
}
