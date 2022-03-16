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
internal class MemberJpaRepositoryTest(@Autowired val memberJpaRepository: MemberJpaRepository) {

    @Test
    internal fun testMember() {
        val member = Member("memberA1", 10)
        val savedMember = memberJpaRepository.save(member)

        val findMember = memberJpaRepository.find(savedMember.id)

        assertThat(findMember.id).isEqualTo(member.id)
        assertThat(findMember.username).isEqualTo(member.username)
        assertThat(findMember).isEqualTo(member)
    }

    @Test
    internal fun basicCRUD() {
        val member1 = Member("member1", 10)
        val member2 = Member("member2", 20)
        memberJpaRepository.save(member1)
        memberJpaRepository.save(member2)

        val findMember1 = memberJpaRepository.findById(member1.id).get()
        val findMember2 = memberJpaRepository.findById(member2.id).get()
        assertThat(findMember1).isEqualTo(member1)
        assertThat(findMember2).isEqualTo(member2)

        val all = memberJpaRepository.findAll()
        assertThat(all.size).isEqualTo(2)
        assertThat(all).hasSize(2)

        val count = memberJpaRepository.count()
        assertThat(count).isEqualTo(2)

        memberJpaRepository.delete(member1)
        memberJpaRepository.delete(member2)

        val deletedCount = memberJpaRepository.count()
        assertThat(deletedCount).isEqualTo(0)
    }
}
