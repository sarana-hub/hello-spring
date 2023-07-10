package hello.hellospring;


import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class SpringConfig {
    //private final MemberRepository memberRepository;
    private DataSource dataSource;
    //private final EntityManager em;

    @Autowired
    public SpringConfig(DataSource dataSource) {

        this.dataSource = dataSource;
    }
    /*public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        //this.dataSource = dataSource;
        //this.em = em;
    }*/

    @Bean
    public MemberService memberService() {

        return new MemberService(memberRepository());
    }


    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em);
    }

}
