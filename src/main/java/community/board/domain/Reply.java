package community.board.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Data
@Entity
public class Reply extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "reply_id")
    private Long id;

    @Column(nullable = false, length = 500)
    private String content;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * 연관 관계 메서드
     */
    public void save(Board board, User user) {
        this.board = board;
        this.user = user;
    }
}
