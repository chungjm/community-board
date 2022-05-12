package community.board.repository;

import community.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Modifying
    @Query("update Board b set b.count = b.count + 1 where b.id = :id")
    int updateCount(Long id);

    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
