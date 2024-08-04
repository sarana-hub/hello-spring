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

    /*private final MemberRepository memberRepository;
    //@Autowired  //생성자 하나만 있을땐 생략 가능
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    /*private DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    private EntityManager em;
    //JPA를 사용하도록 스프링 설정
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean   //스프링빈 등록 //memberService → memberRepository
    public MemberService memberService() {

        return new MemberService(memberRepository());
    }

    @Bean   //스프링빈 등록 //JdbcTemplateMemberRepository(dataSource) → memberRepository
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        // return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }

    /*@Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }*/
}
