package community.board.domain;

import lombok.*;

import javax.persistence.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;    //데이터 베이스 id

    @Column(unique = true, nullable = false, length = 50)
    private String username;    //회원 id

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 30)
    private String email;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    private String provider;

    @Column
    private String providerId;


    /**
     * 회원 수정 메서드
     */
    public void update(String password, String nickname) {
        this.password = password;
        this.nickname = nickname;
    }


    /**
     * BCryptPasswordEncoder 을 위해 추가
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 권한 메소드
     */
    public String getRoleKey() {
        return this.role.getKey();
    }

}
