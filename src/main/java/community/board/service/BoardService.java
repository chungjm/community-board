package community.board.service;


import community.board.domain.Board;
import community.board.domain.User;
import community.board.dto.BoardSaveRequestDto;
import community.board.dto.BoardUpdateRequestDto;
import community.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 글작성 로직
     */
    @Transactional
    public Long save(BoardSaveRequestDto boardSaveRequestDto, User user) {
        boardSaveRequestDto.setUser(user);
        return boardRepository.save(boardSaveRequestDto.toEntity()).getId();
    }

    /**
     * 글목록 로직
     */
    public Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable) {
        return boardRepository.findByTitleContainingOrContentContaining(title, content, pageable);
    }

    /**
     * 글상세 로직
     */
    public Board detail(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id가 없습니다. id=" + id));
    }

    /**
     * 글삭제 로직
     */
    @Transactional
    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }

    /**
     * 글수정 로직
     */
    @Transactional
    public Long update(Long id, BoardUpdateRequestDto boardUpdateRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id가 없습니다. id=" + id));
        board.update(boardUpdateRequestDto.getTitle(), boardUpdateRequestDto.getContent());
        return id;
    }

    /**
     * 글 조회수 로직
     */
    @Transactional
    public int updateCount(Long id) {
        return boardRepository.updateCount(id);
    }

}
