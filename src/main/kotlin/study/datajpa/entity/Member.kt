package study.datajpa.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Member(username: String) {

    @Id
    @GeneratedValue
    var id: Long = 0

    var username: String = username
        protected set
}
