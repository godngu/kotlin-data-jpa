package study.datajpa.repository

import study.datajpa.entity.Member
import javax.persistence.EntityManager

class MemberRepositoryImpl(val em: EntityManager) : MemberRepositoryCustom {

    override fun findMemberCustom(): List<Member> {
        return em.createQuery("select m from Member m", Member::class.java)
            .resultList
    }
}
