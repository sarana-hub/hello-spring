package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**스프링 데이터 JPA 회원 리포지토리*/
//스프링 데이터 JPA가 SpringDataJpaMemberRepository 를 스프링 빈으로 자동 등록해준다

public interface SpringDataJpaMemberRepository extends
        JpaRepository<Member, Long>, MemberRepository {

    Optional<Member> findByName(String name);
}
