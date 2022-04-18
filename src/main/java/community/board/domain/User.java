package community.board.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = true)
    protected String nickname;

    @Column(unique = true)
    protected String email;
    protected String password;

    @Enumerated(EnumType.STRING)
    protected UserLevel userLevel;
    public void modify(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

}
