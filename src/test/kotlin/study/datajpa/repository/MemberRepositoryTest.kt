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
}
