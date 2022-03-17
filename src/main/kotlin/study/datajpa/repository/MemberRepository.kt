package study.datajpa.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import study.datajpa.dto.MemberDto
import study.datajpa.entity.Member
import java.util.Optional

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByUsernameAndAgeGreaterThan(username: String, age: Int): List<Member>

    @Query("select m from Member m where m.username = :username and m.age = :age")
    fun findUser(@Param("username") username: String, @Param("age") age: Int): List<Member>

    @Query("select m.username from Member m")
    fun findUsernameList(): List<String>

    @Query("select m.id, m.username, t.name from Member m join m.team t")
    fun findMemberDto(): List<MemberDto>

    @Query("select m from Member m where m.username in :names")
    fun findByNames(@Param("names") names: List<String>): List<Member>

    fun findListByUsername(username: String): List<Member>

    fun findMemberByUsername(username: String): Member

    fun findOptionalMemberByUsername(username: String): Optional<Member>

    fun findByAge(age: Int, pageable: Pageable): Page<Member>

    /**
     * 페이징시 totalCount 쿼리에서는 조인이 불필요 하므로 별도의 쿼리를 사용하도록 설정한다.
     */
    @Query(
        value = "select m from Member m left join m.team t where m.age = :age",
        countQuery = "select count(m) from Member m where m.age = :age"
    )
    fun findPageByAge(@Param("age") age: Int, pageable: Pageable): Page<Member>
}
