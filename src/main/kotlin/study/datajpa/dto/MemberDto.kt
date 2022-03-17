package study.datajpa.dto

data class MemberDto(
    var id: Long,
    var username: String,
    var teamName: String?,
) {
    override fun toString(): String {
        return "MemberDto(id=$id, username='$username', teamName=$teamName)"
    }
}
