package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ObjectAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //테스트는 테스트 순서에 관계없이, 각각 독립적으로 실행되어야 함!
    @AfterEach      //각 테스트가 종료될 때 마다
    public void afterEach() {
        repository.clearStore();
        // 메모리 DB에 저장된 데이터를 삭제
    }

    @Test
    public void save() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        repository.save(member);

        //then
        Member result = repository.findById(member.getId()).get();
        //제대로 저장됐는지 확인 후, get()으로 꺼내서, result에 저장

        //System.out.println("result="+(result==member));
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
        Member result = repository.findByName("spring1").get();

        //then
        assertThat(result).isEqualTo(member1); //result는 member1
    }

    @Test
    public void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        
        //when
        List<Member> result = repository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);   //result는 2개
    }

}
