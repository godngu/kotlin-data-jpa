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

    @Test
    internal fun findByUsernameAndAgeGreaterThen() {
        val m1 = Member("AAA", 10)
        val m2 = Member("AAA", 20)
        memberJpaRepository.save(m1)
        memberJpaRepository.save(m2)

        val members = memberJpaRepository.findByUsernameAndAgeGreaterThan("AAA", 15)
        assertThat(members.get(0).username).isEqualTo("AAA")
        assertThat(members.get(0).age).isEqualTo(20)
        assertThat(members).hasSize(1)
    }

    @Test
    internal fun paging() {
        // given
        memberJpaRepository.save(Member("member1", 10))
        memberJpaRepository.save(Member("member2", 10))
        memberJpaRepository.save(Member("member3", 10))
        memberJpaRepository.save(Member("member4", 10))
        memberJpaRepository.save(Member("member5", 10))

        val age = 10
        val offset = 0
        val limit = 3

        // when
        val members = memberJpaRepository.findByPage(age, offset, limit)
        val totalCount = memberJpaRepository.totalCount(age)

        // then
        assertThat(members.size).isEqualTo(3)
        assertThat(totalCount).isEqualTo(5)
    }
}
