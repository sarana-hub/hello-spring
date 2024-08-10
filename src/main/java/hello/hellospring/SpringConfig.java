package hello.hellospring;


import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;


@Configuration
public class SpringConfig {

    /*private DataSource dataSource;
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/
    /*private EntityManager em; //JPA를 사용하도록 스프링 설정
    public SpringConfig(EntityManager em) {
        this.em = em;
    }*/

    //스프링 데이터 JPA가 SpringDataJpaMemberRepository를 스프링 빈으로 자동 등록
    //MemberRepository만 주입받으면 됨
    private final MemberRepository memberRepository;
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean   //스프링빈 등록 //memberService → memberRepository
    public MemberService memberService() {
        return new MemberService(memberRepository); //MemberRepository만 주입받으면 됨
    }


    /*@Bean   //스프링빈 등록 //JdbcTemplateMemberRepository(dataSource) → memberRepository
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        // return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em);
    }*/

    /*@Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }*/
}
