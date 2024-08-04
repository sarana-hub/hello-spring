package hello.hellospring.domain;

import javax.persistence.*;

/** 회원 객체*/

@Entity //JPA가 관리하는 객체
public class Member {

    @Id //기본키(PK)
    @GeneratedValue(strategy =GenerationType.IDENTITY)  //DB가 id값을 자동으로 생성
    private long id;

    //@Column(name='username') //DB에 있는 칼럼명이 username일 경우
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
