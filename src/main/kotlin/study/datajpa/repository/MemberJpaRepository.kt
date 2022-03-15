package study.datajpa.repository

import org.springframework.stereotype.Repository
import study.datajpa.entity.Member
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class MemberJpaRepository(em: EntityManager) {

    @PersistenceContext
    private val em: EntityManager = em

    fun save(member: Member): Member {
        em.persist(member)
        return member
    }

    fun find(id: Long?): Member = em.find(Member::class.java, id)
}