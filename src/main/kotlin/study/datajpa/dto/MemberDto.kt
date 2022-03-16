package study.datajpa.dto

/**
 * projection 을 사용하기 위해서는 인터페이스를 만들어서 같은 property 명으로 선언해야 한다.
 */
interface MemberDto {
    var id: Long
    var username: String
    var teamName: String
}
