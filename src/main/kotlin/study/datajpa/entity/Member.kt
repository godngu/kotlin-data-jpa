package study.datajpa.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType.LAZY
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

typealias MemberId = Long

@Entity
class Member(username: String, age: Int) : JpaBaseEntity() {

    constructor(username: String, age: Int, team: Team) : this(username, age) {
        if (team != null) {
            changeTeam(team)
        }
    }

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    var id: MemberId = 0

    var username: String = username

    val age: Int = age

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "team_id")
    var team: Team? = null

    fun changeTeam(team: Team) {
        this.team = team
        team.members.add(this)
    }

    override fun toString(): String {
        return "Member(id=$id, username='$username', age=$age)"
    }
}
