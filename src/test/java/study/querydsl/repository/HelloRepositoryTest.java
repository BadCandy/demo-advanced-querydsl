package study.querydsl.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import study.querydsl.entity.Hello;
import study.querydsl.entity.Member;
import study.querydsl.entity.Team;

@Rollback(false)
@Transactional
@SpringBootTest
class HelloRepositoryTest {

    @Autowired
    private HelloRepository helloRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void getHello() {

        Member member = new Member("aa");
        memberRepository.save(member);

        Team team = new Team("aab");
        entityManager.persist(team);

        Hello hello = new Hello();
        hello.setMember(member);
        hello.setTeam(team);
        hello.setName("안녕");
        helloRepository.save(hello);

        entityManager.flush();
        entityManager.clear();

        Optional<Hello> byName = helloRepository.findByName(hello.getName());
    }
}