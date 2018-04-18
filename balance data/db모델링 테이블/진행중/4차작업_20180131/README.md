-- 회원
DROP TABLE IF EXISTS memb RESTRICT;

-- 미아찾기
DROP TABLE IF EXISTS lost RESTRICT;

-- 아지백과
DROP TABLE IF EXISTS pedia RESTRICT;

-- 훈련정보
DROP TABLE IF EXISTS trainning RESTRICT;

-- 펫스타그램
DROP TABLE IF EXISTS pet_str RESTRICT;

-- 리뷰
DROP TABLE IF EXISTS bus_rv RESTRICT;

-- 애완견
DROP TABLE IF EXISTS pet RESTRICT;

-- 회원미디어
DROP TABLE IF EXISTS m_mda RESTRICT;

-- 리뷰댓글
DROP TABLE IF EXISTS bus_rv_cmt RESTRICT;

-- 산책정보
DROP TABLE IF EXISTS walk RESTRICT;

-- 병원
DROP TABLE IF EXISTS hp RESTRICT;

-- 진료자료
DROP TABLE IF EXISTS dgn RESTRICT;

-- 훈련미디어
DROP TABLE IF EXISTS t_mda RESTRICT;

-- 리뷰미디어
DROP TABLE IF EXISTS bus_rv_mda RESTRICT;

-- 팻스타미디어
DROP TABLE IF EXISTS str_mda RESTRICT;

-- 아지미디어
DROP TABLE IF EXISTS ped_mda RESTRICT;

-- 미아미디어
DROP TABLE IF EXISTS lost_mda RESTRICT;

-- 병원미디어
DROP TABLE IF EXISTS hp_mda RESTRICT;

-- 사업체미디어
DROP TABLE IF EXISTS bus_mda RESTRICT;

-- 팻스타댓글
DROP TABLE IF EXISTS str_cmt RESTRICT;

-- 아지댓글
DROP TABLE IF EXISTS ped_cmt RESTRICT;

-- 훈련댓글
DROP TABLE IF EXISTS t_cmt RESTRICT;

-- 사업체
DROP TABLE IF EXISTS bus RESTRICT;

-- 병원리뷰댓글
DROP TABLE IF EXISTS hp_rv_cmt RESTRICT;

-- 병원리뷰
DROP TABLE IF EXISTS hp_rv RESTRICT;

-- 병원리뷰미디어
DROP TABLE IF EXISTS hp_rv_mda RESTRICT;

-- 회원
CREATE TABLE memb (
	m_no     INTEGER      NOT NULL, -- 회원번호
	email    VARCHAR(40)  NOT NULL, -- 이메일
	pwd      VARCHAR(50)  NOT NULL, -- 암호
	nic_name VARCHAR(50)  NOT NULL, -- 닉네임
	name     VARCHAR(50)  NOT NULL, -- 이름
	tel      VARCHAR(30)  NOT NULL, -- 전화번호
	push     BOOLEAN      NOT NULL, -- 알림수신여부
	pst_no   VARCHAR(6)   NOT NULL, -- 우편번호
	prm_addr VARCHAR(255) NOT NULL, -- 기본주소
	det_addr VARCHAR(255) NULL,     -- 상세주소
	m_type   CHAR(10)     NOT NULL  -- 유형
);

-- 회원
ALTER TABLE memb
	ADD CONSTRAINT PK_memb -- 회원 기본키
		PRIMARY KEY (
			m_no -- 회원번호
		);

-- 회원 유니크 인덱스
CREATE UNIQUE INDEX UIX_memb
	ON memb ( -- 회원
		email ASC -- 이메일
	);

-- 회원 유니크 인덱스2
CREATE UNIQUE INDEX UIX_memb2
	ON memb ( -- 회원
		nic_name ASC -- 닉네임
	);

-- 회원 유니크 인덱스3
CREATE UNIQUE INDEX UIX_memb3
	ON memb ( -- 회원
		tel ASC -- 전화번호
	);

ALTER TABLE memb
	MODIFY COLUMN m_no INTEGER NOT NULL AUTO_INCREMENT;

-- 미아찾기
CREATE TABLE lost (
	l_no   INTEGER      NOT NULL, -- 미아번호
	m_no   INTEGER      NOT NULL, -- 회원번호
	p_name VARCHAR(50)  NOT NULL, -- 강아지이름
	l_loc  VARCHAR(100) NOT NULL, -- 분실장소
	l_date DATETIME     NOT NULL, -- 분실시간
	cha    TEXT         NOT NULL, -- 특이사항
	rwd    INTEGER      NOT NULL, -- 사례금
	vwcnt  INTEGER      NOT NULL, -- 조회수
	conts  TEXT         NULL      -- 내용
);

-- 미아찾기
ALTER TABLE lost
	ADD CONSTRAINT PK_lost -- 미아찾기 기본키
		PRIMARY KEY (
			l_no -- 미아번호
		);

ALTER TABLE lost
	MODIFY COLUMN l_no INTEGER NOT NULL AUTO_INCREMENT;

-- 아지백과
CREATE TABLE pedia (
	ped_no  INTEGER     NOT NULL, -- 아지백과번호
	ctg     CHAR(10)    NOT NULL, -- 카테고리
	title   VARCHAR(50) NOT NULL, -- 제목
	conts   TEXT        NOT NULL, -- 설명
	rdt     DATE        NOT NULL, -- 등록일시
	vwcnt   INTEGER     NOT NULL, -- 조회수
	likes   INTEGER     NOT NULL, -- 추천수
	m_conts TEXT        NOT NULL, -- 본문내용
	m_no    INTEGER     NOT NULL  -- 회원번호
);

-- 아지백과
ALTER TABLE pedia
	ADD CONSTRAINT PK_pedia -- 아지백과 기본키
		PRIMARY KEY (
			ped_no -- 아지백과번호
		);

ALTER TABLE pedia
	MODIFY COLUMN ped_no INTEGER NOT NULL AUTO_INCREMENT;

-- 훈련정보
CREATE TABLE trainning (
	t_no    INTEGER     NOT NULL, -- 훈련번호
	m_no    INTEGER     NOT NULL, -- 회원번호
	ctg     CHAR(10)    NOT NULL, -- 카테고리
	title   VARCHAR(50) NOT NULL, -- 제목
	conts   TEXT        NOT NULL, -- 설명
	m_conts TEXT        NOT NULL, -- 본문내용
	vwcnt   INTEGER     NOT NULL, -- 조회수
	rdt     DATE        NOT NULL, -- 등록일시
	likes   INTEGER     NOT NULL  -- 추천수
);

-- 훈련정보
ALTER TABLE trainning
	ADD CONSTRAINT PK_trainning -- 훈련정보 기본키
		PRIMARY KEY (
			t_no -- 훈련번호
		);

ALTER TABLE trainning
	MODIFY COLUMN t_no INTEGER NOT NULL AUTO_INCREMENT;

-- 펫스타그램
CREATE TABLE pet_str (
	str_no INTEGER NOT NULL, -- 팻스타그램번호
	m_no   INTEGER NOT NULL, -- 회원번호
	conts  TEXT    NOT NULL, -- 게시물내용
	rdt    DATE    NOT NULL, -- 등록일시
	likes  INTEGER NOT NULL  -- 좋아요수
);

-- 펫스타그램
ALTER TABLE pet_str
	ADD CONSTRAINT PK_pet_str -- 펫스타그램 기본키
		PRIMARY KEY (
			str_no -- 팻스타그램번호
		);

ALTER TABLE pet_str
	MODIFY COLUMN str_no INTEGER NOT NULL AUTO_INCREMENT;

-- 리뷰
CREATE TABLE bus_rv (
	rv_no  INTEGER     NOT NULL, -- 리뷰번호
	bus_no INTEGER     NOT NULL, -- 사업체번호
	m_no   INTEGER     NOT NULL, -- 회원번호
	title  VARCHAR(50) NOT NULL, -- 제목
	conts  TEXT        NOT NULL, -- 내용
	date   DATE        NOT NULL, -- 일자
	point  DOUBLE      NOT NULL, -- 별점
	vwcnt  INTEGER     NOT NULL  -- 조회수
);

-- 리뷰
ALTER TABLE bus_rv
	ADD CONSTRAINT PK_bus_rv -- 리뷰 기본키
		PRIMARY KEY (
			rv_no -- 리뷰번호
		);

ALTER TABLE bus_rv
	MODIFY COLUMN rv_no INTEGER NOT NULL AUTO_INCREMENT;

-- 애완견
CREATE TABLE pet (
	p_no  INTEGER     NOT NULL, -- 강아지번호
	name  VARCHAR(50) NOT NULL, -- 이름
	brd   VARCHAR(50) NOT NULL, -- 품종
	age   DOUBLE      NOT NULL, -- 나이
	wgt   DOUBLE      NOT NULL, -- 체중
	g_wgt DOUBLE      NOT NULL, -- 목표체중
	m_no  INTEGER     NOT NULL  -- 회원번호
);

-- 애완견
ALTER TABLE pet
	ADD CONSTRAINT PK_pet -- 애완견 기본키
		PRIMARY KEY (
			p_no -- 강아지번호
		);

ALTER TABLE pet
	MODIFY COLUMN p_no INTEGER NOT NULL AUTO_INCREMENT;

-- 회원미디어
CREATE TABLE m_mda (
	mda_no INTEGER      NOT NULL, -- 미디어번호
	m_no   INTEGER      NOT NULL, -- 회원번호
	file   VARCHAR(255) NULL      -- 파일
);

-- 회원미디어
ALTER TABLE m_mda
	ADD CONSTRAINT PK_m_mda -- 회원미디어 기본키
		PRIMARY KEY (
			mda_no -- 미디어번호
		);

ALTER TABLE m_mda
	MODIFY COLUMN mda_no INTEGER NOT NULL AUTO_INCREMENT;

-- 리뷰댓글
CREATE TABLE bus_rv_cmt (
	cmt_no INTEGER NOT NULL, -- 댓글번호
	m_no   INTEGER NOT NULL, -- 회원번호
	rv_no  INTEGER NOT NULL, -- 리뷰번호
	conts  TEXT    NOT NULL, -- 내용
	rdt    DATE    NOT NULL  -- 등록일시
);

-- 리뷰댓글
ALTER TABLE bus_rv_cmt
	ADD CONSTRAINT PK_bus_rv_cmt -- 리뷰댓글 기본키
		PRIMARY KEY (
			cmt_no -- 댓글번호
		);

ALTER TABLE bus_rv_cmt
	MODIFY COLUMN cmt_no INTEGER NOT NULL AUTO_INCREMENT;

-- 산책정보
CREATE TABLE walk (
	walk_no  INTEGER  NOT NULL, -- 산책번호
	walktime DATETIME NOT NULL, -- 산책시간
	walkdate DATETIME NOT NULL, -- 산책일자
	street   DOUBLE   NOT NULL, -- 거리
	calorie  INTEGER  NOT NULL, -- 소모칼로리
	m_no     INTEGER  NOT NULL  -- 회원번호
);

-- 산책정보
ALTER TABLE walk
	ADD CONSTRAINT PK_walk -- 산책정보 기본키
		PRIMARY KEY (
			walk_no -- 산책번호
		);

ALTER TABLE walk
	MODIFY COLUMN walk_no INTEGER NOT NULL AUTO_INCREMENT;

-- 병원
CREATE TABLE hp (
	hp_no     INTEGER      NOT NULL, -- 병원번호
	hp_name   VARCHAR(50)  NOT NULL, -- 병원명
	op_time   DATETIME     NOT NULL, -- 운영시간
	mdc_sub   VARCHAR(50)  NOT NULL, -- 진료과목
	mdc_ani   VARCHAR(50)  NOT NULL, -- 진료동물
	hp_tel    VARCHAR(30)  NOT NULL, -- 병원전화번호
	mdc_staff VARCHAR(50)  NOT NULL, -- 의료진
	conts     TEXT         NULL,     -- 내용
	pst_no    VARCHAR(6)   NOT NULL, -- 우편번호
	prm_addr  VARCHAR(255) NOT NULL, -- 기본주소
	det_addr  VARCHAR(255) NULL,     -- 상세주소
	m_no      INTEGER      NOT NULL  -- 회원번호
);

-- 병원
ALTER TABLE hp
	ADD CONSTRAINT PK_hp -- 병원 기본키
		PRIMARY KEY (
			hp_no -- 병원번호
		);

-- 병원 유니크 인덱스
CREATE UNIQUE INDEX UIX_hp
	ON hp ( -- 병원
		hp_name ASC,  -- 병원명
		det_addr ASC  -- 상세주소
	);

-- 병원 인덱스
CREATE INDEX IX_hp
	ON hp( -- 병원
		hp_name ASC -- 병원명
	);

ALTER TABLE hp
	MODIFY COLUMN hp_no INTEGER NOT NULL AUTO_INCREMENT;

-- 진료자료
CREATE TABLE dgn (
	dgn_no    INTEGER     NOT NULL, -- 진료번호
	m_no      INTEGER     NOT NULL, -- 회원번호
	hp_no     INTEGER     NOT NULL, -- 병원번호
	dgn_cg    CHAR(10)    NOT NULL, -- 진료카테고리
	dgn_name  VARCHAR(50) NOT NULL, -- 진료명
	dgn_conts TEXT        NOT NULL, -- 진료내용
	weight    DOUBLE      NOT NULL, -- 체중
	date_rec  DATETIME    NOT NULL, -- 기록일자
	recd      VARCHAR(50) NOT NULL, -- 기록자
	rems      TEXT        NULL      -- 비고
);

-- 진료자료
ALTER TABLE dgn
	ADD CONSTRAINT PK_dgn -- 진료자료 기본키
		PRIMARY KEY (
			dgn_no -- 진료번호
		);

ALTER TABLE dgn
	MODIFY COLUMN dgn_no INTEGER NOT NULL AUTO_INCREMENT;

-- 훈련미디어
CREATE TABLE t_mda (
	mda_no INTEGER      NOT NULL, -- 미디어번호
	t_no   INTEGER      NOT NULL, -- 훈련번호
	file   VARCHAR(255) NULL      -- 파일
);

-- 훈련미디어
ALTER TABLE t_mda
	ADD CONSTRAINT PK_t_mda -- 훈련미디어 기본키
		PRIMARY KEY (
			mda_no -- 미디어번호
		);

ALTER TABLE t_mda
	MODIFY COLUMN mda_no INTEGER NOT NULL AUTO_INCREMENT;

-- 리뷰미디어
CREATE TABLE bus_rv_mda (
	mda_no INTEGER      NOT NULL, -- 미디어번호
	rv_no  INTEGER      NOT NULL, -- 리뷰번호
	file   VARCHAR(255) NULL      -- 파일
);

-- 리뷰미디어
ALTER TABLE bus_rv_mda
	ADD CONSTRAINT PK_bus_rv_mda -- 리뷰미디어 기본키
		PRIMARY KEY (
			mda_no -- 미디어번호
		);

ALTER TABLE bus_rv_mda
	MODIFY COLUMN mda_no INTEGER NOT NULL AUTO_INCREMENT;

-- 팻스타미디어
CREATE TABLE str_mda (
	mda_no INTEGER      NOT NULL, -- 미디어번호
	str_no INTEGER      NOT NULL, -- 팻스타그램번호
	file   VARCHAR(255) NULL      -- 파일
);

-- 팻스타미디어
ALTER TABLE str_mda
	ADD CONSTRAINT PK_str_mda -- 팻스타미디어 기본키
		PRIMARY KEY (
			mda_no -- 미디어번호
		);

ALTER TABLE str_mda
	MODIFY COLUMN mda_no INTEGER NOT NULL AUTO_INCREMENT;

-- 아지미디어
CREATE TABLE ped_mda (
	mda_no INTEGER      NOT NULL, -- 미디어번호
	ped_no INTEGER      NOT NULL, -- 아지백과번호
	file   VARCHAR(255) NULL      -- 파일
);

-- 아지미디어
ALTER TABLE ped_mda
	ADD CONSTRAINT PK_ped_mda -- 아지미디어 기본키
		PRIMARY KEY (
			mda_no -- 미디어번호
		);

ALTER TABLE ped_mda
	MODIFY COLUMN mda_no INTEGER NOT NULL AUTO_INCREMENT;

-- 미아미디어
CREATE TABLE lost_mda (
	mda_no INTEGER      NOT NULL, -- 미디어번호
	l_no   INTEGER      NOT NULL, -- 미아번호
	file   VARCHAR(255) NULL      -- 파일
);

-- 미아미디어
ALTER TABLE lost_mda
	ADD CONSTRAINT PK_lost_mda -- 미아미디어 기본키
		PRIMARY KEY (
			mda_no -- 미디어번호
		);

ALTER TABLE lost_mda
	MODIFY COLUMN mda_no INTEGER NOT NULL AUTO_INCREMENT;

-- 병원미디어
CREATE TABLE hp_mda (
	mda_no INTEGER      NOT NULL, -- 미디어번호
	hp_no  INTEGER      NOT NULL, -- 병원번호
	file   VARCHAR(255) NULL      -- 파일
);

-- 병원미디어
ALTER TABLE hp_mda
	ADD CONSTRAINT PK_hp_mda -- 병원미디어 기본키
		PRIMARY KEY (
			mda_no -- 미디어번호
		);

ALTER TABLE hp_mda
	MODIFY COLUMN mda_no INTEGER NOT NULL AUTO_INCREMENT;

-- 사업체미디어
CREATE TABLE bus_mda (
	mda_no INTEGER      NOT NULL, -- 미디어번호
	bus_no INTEGER      NOT NULL, -- 사업체번호
	file   VARCHAR(255) NULL      -- 파일
);

-- 사업체미디어
ALTER TABLE bus_mda
	ADD CONSTRAINT PK_bus_mda -- 사업체미디어 기본키
		PRIMARY KEY (
			mda_no -- 미디어번호
		);

ALTER TABLE bus_mda
	MODIFY COLUMN mda_no INTEGER NOT NULL AUTO_INCREMENT;

-- 팻스타댓글
CREATE TABLE str_cmt (
	cmt_no INTEGER NOT NULL, -- 댓글번호
	m_no   INTEGER NOT NULL, -- 회원번호
	str_no INTEGER NOT NULL, -- 팻스타그램번호
	conts  TEXT    NOT NULL, -- 내용
	rdt    DATE    NOT NULL  -- 등록일시
);

-- 팻스타댓글
ALTER TABLE str_cmt
	ADD CONSTRAINT PK_str_cmt -- 팻스타댓글 기본키
		PRIMARY KEY (
			cmt_no -- 댓글번호
		);

ALTER TABLE str_cmt
	MODIFY COLUMN cmt_no INTEGER NOT NULL AUTO_INCREMENT;

-- 아지댓글
CREATE TABLE ped_cmt (
	cmt_no INTEGER NOT NULL, -- 댓글번호
	m_no   INTEGER NOT NULL, -- 회원번호
	ped_no INTEGER NOT NULL, -- 아지백과번호
	conts  TEXT    NOT NULL, -- 내용
	rdt    DATE    NOT NULL  -- 등록일시
);

-- 아지댓글
ALTER TABLE ped_cmt
	ADD CONSTRAINT PK_ped_cmt -- 아지댓글 기본키
		PRIMARY KEY (
			cmt_no -- 댓글번호
		);

ALTER TABLE ped_cmt
	MODIFY COLUMN cmt_no INTEGER NOT NULL AUTO_INCREMENT;

-- 훈련댓글
CREATE TABLE t_cmt (
	cmt_no INTEGER NOT NULL, -- 댓글번호
	conts  TEXT    NOT NULL, -- 내용
	rdt    DATE    NOT NULL, -- 등록일시
	t_no   INTEGER NOT NULL, -- 훈련번호
	m_no   INTEGER NOT NULL  -- 회원번호
);

-- 훈련댓글
ALTER TABLE t_cmt
	ADD CONSTRAINT PK_t_cmt -- 훈련댓글 기본키
		PRIMARY KEY (
			cmt_no -- 댓글번호
		);

ALTER TABLE t_cmt
	MODIFY COLUMN cmt_no INTEGER NOT NULL AUTO_INCREMENT;

-- 사업체
CREATE TABLE bus (
	bus_no   INTEGER      NOT NULL, -- 사업체번호
	m_no     INTEGER      NULL,     -- 회원번호
	ctgr     CHAR(10)     NOT NULL, -- 카테고리
	b_name   VARCHAR(50)  NOT NULL, -- 업소명
	op_time  TIME         NULL,     -- 영업시작시간
	cl_time  TIME         NULL,     -- 영업종료시간
	con_inf  VARCHAR(20)  NOT NULL, -- 연락처
	menu     TEXT         NULL,     -- 메뉴
	conts    TEXT         NOT NULL, -- 내용
	url      VARCHAR(255) NULL,     -- 홈페이지
	pst_no   VARCHAR(6)   NOT NULL, -- 우편번호
	prm_addr VARCHAR(255) NOT NULL, -- 기본주소
	det_addr VARCHAR(255) NULL      -- 상세주소
);

-- 사업체
ALTER TABLE bus
	ADD CONSTRAINT PK_bus -- 사업체 기본키
		PRIMARY KEY (
			bus_no -- 사업체번호
		);

ALTER TABLE bus
	MODIFY COLUMN bus_no INTEGER NOT NULL AUTO_INCREMENT;

-- 병원리뷰댓글
CREATE TABLE hp_rv_cmt (
	cmt_no INTEGER NOT NULL, -- 댓글번호
	m_no   INTEGER NOT NULL, -- 회원번호
	rv_no  INTEGER NOT NULL, -- 리뷰번호
	conts  TEXT    NOT NULL, -- 내용
	rdt    DATE    NOT NULL  -- 등록일시
);

-- 병원리뷰댓글
ALTER TABLE hp_rv_cmt
	ADD CONSTRAINT PK_hp_rv_cmt -- 병원리뷰댓글 기본키
		PRIMARY KEY (
			cmt_no -- 댓글번호
		);

-- 병원리뷰
CREATE TABLE hp_rv (
	rv_no INTEGER     NOT NULL, -- 리뷰번호
	hp_no INTEGER     NULL,     -- 병원번호
	m_no  INTEGER     NOT NULL, -- 회원번호
	title VARCHAR(50) NOT NULL, -- 제목
	conts TEXT        NOT NULL, -- 내용
	date  DATE        NOT NULL, -- 일자
	point DOUBLE      NOT NULL, -- 별점
	vwcnt INTEGER     NOT NULL  -- 조회수
);

-- 병원리뷰
ALTER TABLE hp_rv
	ADD CONSTRAINT PK_hp_rv -- 병원리뷰 기본키
		PRIMARY KEY (
			rv_no -- 리뷰번호
		);

-- 병원리뷰미디어
CREATE TABLE hp_rv_mda (
	mda_no INTEGER      NOT NULL, -- 미디어번호
	rv_no  INTEGER      NOT NULL, -- 리뷰번호
	file   VARCHAR(255) NULL      -- 파일
);

-- 병원리뷰미디어
ALTER TABLE hp_rv_mda
	ADD CONSTRAINT PK_hp_rv_mda -- 병원리뷰미디어 기본키
		PRIMARY KEY (
			mda_no -- 미디어번호
		);

-- 미아찾기
ALTER TABLE lost
	ADD CONSTRAINT FK_memb_TO_lost -- 회원 -> 미아찾기
		FOREIGN KEY (
			m_no -- 회원번호
		)
		REFERENCES memb ( -- 회원
			m_no -- 회원번호
		);

-- 아지백과
ALTER TABLE pedia
	ADD CONSTRAINT FK_memb_TO_pedia -- 회원 -> 아지백과
		FOREIGN KEY (
			m_no -- 회원번호
		)
		REFERENCES memb ( -- 회원
			m_no -- 회원번호
		);

-- 훈련정보
ALTER TABLE trainning
	ADD CONSTRAINT FK_memb_TO_trainning -- 회원 -> 훈련정보
		FOREIGN KEY (
			m_no -- 회원번호
		)
		REFERENCES memb ( -- 회원
			m_no -- 회원번호
		);

-- 펫스타그램
ALTER TABLE pet_str
	ADD CONSTRAINT FK_memb_TO_pet_str -- 회원 -> 펫스타그램
		FOREIGN KEY (
			m_no -- 회원번호
		)
		REFERENCES memb ( -- 회원
			m_no -- 회원번호
		);

-- 리뷰
ALTER TABLE bus_rv
	ADD CONSTRAINT FK_memb_TO_bus_rv -- 회원 -> 리뷰
		FOREIGN KEY (
			m_no -- 회원번호
		)
		REFERENCES memb ( -- 회원
			m_no -- 회원번호
		);

-- 리뷰
ALTER TABLE bus_rv
	ADD CONSTRAINT FK_bus_TO_bus_rv -- 사업체 -> 리뷰
		FOREIGN KEY (
			bus_no -- 사업체번호
		)
		REFERENCES bus ( -- 사업체
			bus_no -- 사업체번호
		);

-- 애완견
ALTER TABLE pet
	ADD CONSTRAINT FK_memb_TO_pet -- 회원 -> 애완견
		FOREIGN KEY (
			m_no -- 회원번호
		)
		REFERENCES memb ( -- 회원
			m_no -- 회원번호
		);

-- 회원미디어
ALTER TABLE m_mda
	ADD CONSTRAINT FK_memb_TO_m_mda -- 회원 -> 회원미디어
		FOREIGN KEY (
			m_no -- 회원번호
		)
		REFERENCES memb ( -- 회원
			m_no -- 회원번호
		);

-- 리뷰댓글
ALTER TABLE bus_rv_cmt
	ADD CONSTRAINT FK_bus_rv_TO_bus_rv_cmt -- 리뷰 -> 리뷰댓글
		FOREIGN KEY (
			rv_no -- 리뷰번호
		)
		REFERENCES bus_rv ( -- 리뷰
			rv_no -- 리뷰번호
		);

-- 리뷰댓글
ALTER TABLE bus_rv_cmt
	ADD CONSTRAINT FK_memb_TO_bus_rv_cmt -- 회원 -> 리뷰댓글
		FOREIGN KEY (
			m_no -- 회원번호
		)
		REFERENCES memb ( -- 회원
			m_no -- 회원번호
		);

-- 산책정보
ALTER TABLE walk
	ADD CONSTRAINT FK_memb_TO_walk -- 회원 -> 산책정보
		FOREIGN KEY (
			m_no -- 회원번호
		)
		REFERENCES memb ( -- 회원
			m_no -- 회원번호
		);

-- 병원
ALTER TABLE hp
	ADD CONSTRAINT FK_memb_TO_hp -- 회원 -> 병원
		FOREIGN KEY (
			m_no -- 회원번호
		)
		REFERENCES memb ( -- 회원
			m_no -- 회원번호
		);

-- 진료자료
ALTER TABLE dgn
	ADD CONSTRAINT FK_memb_TO_dgn -- 회원 -> 진료자료
		FOREIGN KEY (
			m_no -- 회원번호
		)
		REFERENCES memb ( -- 회원
			m_no -- 회원번호
		);

-- 진료자료
ALTER TABLE dgn
	ADD CONSTRAINT FK_hp_TO_dgn -- 병원 -> 진료자료
		FOREIGN KEY (
			hp_no -- 병원번호
		)
		REFERENCES hp ( -- 병원
			hp_no -- 병원번호
		);

-- 훈련미디어
ALTER TABLE t_mda
	ADD CONSTRAINT FK_trainning_TO_t_mda -- 훈련정보 -> 훈련미디어
		FOREIGN KEY (
			t_no -- 훈련번호
		)
		REFERENCES trainning ( -- 훈련정보
			t_no -- 훈련번호
		);

-- 리뷰미디어
ALTER TABLE bus_rv_mda
	ADD CONSTRAINT FK_bus_rv_TO_bus_rv_mda -- 리뷰 -> 리뷰미디어
		FOREIGN KEY (
			rv_no -- 리뷰번호
		)
		REFERENCES bus_rv ( -- 리뷰
			rv_no -- 리뷰번호
		);

-- 팻스타미디어
ALTER TABLE str_mda
	ADD CONSTRAINT FK_pet_str_TO_str_mda -- 펫스타그램 -> 팻스타미디어
		FOREIGN KEY (
			str_no -- 팻스타그램번호
		)
		REFERENCES pet_str ( -- 펫스타그램
			str_no -- 팻스타그램번호
		);

-- 아지미디어
ALTER TABLE ped_mda
	ADD CONSTRAINT FK_pedia_TO_ped_mda -- 아지백과 -> 아지미디어
		FOREIGN KEY (
			ped_no -- 아지백과번호
		)
		REFERENCES pedia ( -- 아지백과
			ped_no -- 아지백과번호
		);

-- 미아미디어
ALTER TABLE lost_mda
	ADD CONSTRAINT FK_lost_TO_lost_mda -- 미아찾기 -> 미아미디어
		FOREIGN KEY (
			l_no -- 미아번호
		)
		REFERENCES lost ( -- 미아찾기
			l_no -- 미아번호
		);

-- 병원미디어
ALTER TABLE hp_mda
	ADD CONSTRAINT FK_hp_TO_hp_mda -- 병원 -> 병원미디어
		FOREIGN KEY (
			hp_no -- 병원번호
		)
		REFERENCES hp ( -- 병원
			hp_no -- 병원번호
		);

-- 사업체미디어
ALTER TABLE bus_mda
	ADD CONSTRAINT FK_bus_TO_bus_mda -- 사업체 -> 사업체미디어
		FOREIGN KEY (
			bus_no -- 사업체번호
		)
		REFERENCES bus ( -- 사업체
			bus_no -- 사업체번호
		);

-- 팻스타댓글
ALTER TABLE str_cmt
	ADD CONSTRAINT FK_pet_str_TO_str_cmt -- 펫스타그램 -> 팻스타댓글
		FOREIGN KEY (
			str_no -- 팻스타그램번호
		)
		REFERENCES pet_str ( -- 펫스타그램
			str_no -- 팻스타그램번호
		);

-- 팻스타댓글
ALTER TABLE str_cmt
	ADD CONSTRAINT FK_memb_TO_str_cmt -- 회원 -> 팻스타댓글
		FOREIGN KEY (
			m_no -- 회원번호
		)
		REFERENCES memb ( -- 회원
			m_no -- 회원번호
		);

-- 아지댓글
ALTER TABLE ped_cmt
	ADD CONSTRAINT FK_pedia_TO_ped_cmt -- 아지백과 -> 아지댓글
		FOREIGN KEY (
			ped_no -- 아지백과번호
		)
		REFERENCES pedia ( -- 아지백과
			ped_no -- 아지백과번호
		);

-- 아지댓글
ALTER TABLE ped_cmt
	ADD CONSTRAINT FK_memb_TO_ped_cmt -- 회원 -> 아지댓글
		FOREIGN KEY (
			m_no -- 회원번호
		)
		REFERENCES memb ( -- 회원
			m_no -- 회원번호
		);

-- 훈련댓글
ALTER TABLE t_cmt
	ADD CONSTRAINT FK_trainning_TO_t_cmt -- 훈련정보 -> 훈련댓글
		FOREIGN KEY (
			t_no -- 훈련번호
		)
		REFERENCES trainning ( -- 훈련정보
			t_no -- 훈련번호
		);

-- 훈련댓글
ALTER TABLE t_cmt
	ADD CONSTRAINT FK_memb_TO_t_cmt -- 회원 -> 훈련댓글
		FOREIGN KEY (
			m_no -- 회원번호
		)
		REFERENCES memb ( -- 회원
			m_no -- 회원번호
		);

-- 사업체
ALTER TABLE bus
	ADD CONSTRAINT FK_memb_TO_bus -- 회원 -> 사업체
		FOREIGN KEY (
			m_no -- 회원번호
		)
		REFERENCES memb ( -- 회원
			m_no -- 회원번호
		);

-- 병원리뷰댓글
ALTER TABLE hp_rv_cmt
	ADD CONSTRAINT FK_hp_rv_TO_hp_rv_cmt -- 병원리뷰 -> 병원리뷰댓글
		FOREIGN KEY (
			rv_no -- 리뷰번호
		)
		REFERENCES hp_rv ( -- 병원리뷰
			rv_no -- 리뷰번호
		);

-- 병원리뷰
ALTER TABLE hp_rv
	ADD CONSTRAINT FK_hp_TO_hp_rv -- 병원 -> 병원리뷰
		FOREIGN KEY (
			hp_no -- 병원번호
		)
		REFERENCES hp ( -- 병원
			hp_no -- 병원번호
		);

-- 병원리뷰
ALTER TABLE hp_rv
	ADD CONSTRAINT FK_memb_TO_hp_rv -- 회원 -> 병원리뷰
		FOREIGN KEY (
			m_no -- 회원번호
		)
		REFERENCES memb ( -- 회원
			m_no -- 회원번호
		);

-- 병원리뷰미디어
ALTER TABLE hp_rv_mda
	ADD CONSTRAINT FK_hp_rv_TO_hp_rv_mda -- 병원리뷰 -> 병원리뷰미디어
		FOREIGN KEY (
			rv_no -- 리뷰번호
		)
		REFERENCES hp_rv ( -- 병원리뷰
			rv_no -- 리뷰번호
		);