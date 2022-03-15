package study.datajpa.entity

import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

typealias TeamId = Long

@Entity
class Team(name: String) {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    var id: TeamId? = null

    val name: String = name

    @OneToMany(mappedBy = "team")
    var members: MutableList<Member> = ArrayList()

    override fun toString(): String {
        return "Team(id=$id, name='$name')"
    }
}

interface TeamRepository : JpaRepository<Team, Long>