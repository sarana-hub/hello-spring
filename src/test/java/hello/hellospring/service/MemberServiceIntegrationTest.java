package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest     //스프링 컨테이너와 테스트를 함께 실행
@Transactional //테스트 시작 전에 트랜잭션을 시작하고, 테스트 완료 후에 항상 롤백한다
// 각 테스트마다 clearStore할 필요 없어짐
class MemberServiceIntegrationTest {

    //@BeforeEach로 각 테스트 실행 전마다 의존관계 넣어주는 대신에
    //@Autowired로 회원서비스와 회원 리포지토리를 사용할 수 있게 의존관계 넣어주기
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    //@Commit
    void join() throws Exception{
        //Given
        Member member = new Member();
        member.setName("jj");
        //When
        Long saveId = memberService.join(member);
        //Then
        Member findMember = memberService.findOne(saveId).get();
        //assertThat(member.getName()).isEqualTo(findMember.getName());
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    void 중복_회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        //When
        memberService.join(member1);

        IllegalStateException e =assertThrows(
                IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
