package study.datajpa.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import study.datajpa.entity.Member

@SpringBootTest
@Transactional
@Rollback(false)
internal class MemberRepositoryTest(@Autowired val memberRepository: MemberRepository) {

    @Test
    internal fun testMember() {
        val member = Member("memberA2", 10)
        val savedMember = memberRepository.save(member)

        val findMember = memberRepository.findById(savedMember.id).get()

        assertThat(findMember.id).isEqualTo(member.id)
        assertThat(findMember.username).isEqualTo(member.username)
        assertThat(findMember).isEqualTo(member)
    }

    @Test
    internal fun emptyResult() {
        val memberOptional = memberRepository.findById(Long.MAX_VALUE)
        println("memberOptional = $memberOptional")
        assertThat(memberOptional).isEmpty
    }

    @Test
    internal fun basicCRUD() {
        val member1 = Member("member1", 10)
        val member2 = Member("member2", 20)
        memberRepository.save(member1)
        memberRepository.save(member2)

        val findMember1 = memberRepository.findById(member1.id).get()
        val findMember2 = memberRepository.findById(member2.id).get()
        assertThat(findMember1).isEqualTo(member1)
        assertThat(findMember2).isEqualTo(member2)

        val all = memberRepository.findAll()
        assertThat(all.size).isEqualTo(2)
        assertThat(all).hasSize(2)

        val count = memberRepository.count()
        assertThat(count).isEqualTo(2)

        memberRepository.delete(member1)
        memberRepository.delete(member2)

        val deletedCount = memberRepository.count()
        assertThat(deletedCount).isEqualTo(0)
    }

    @Test
    internal fun findByUsernameAndAgeGreaterThen() {
        val m1 = Member("AAA", 10)
        val m2 = Member("AAA", 20)
        memberRepository.save(m1)
        memberRepository.save(m2)

        val members = memberRepository.findByUsernameAndAgeGreaterThan("AAA", 15)
        assertThat(members.get(0).username).isEqualTo("AAA")
        assertThat(members.get(0).age).isEqualTo(20)
        assertThat(members).hasSize(1)
    }
}
