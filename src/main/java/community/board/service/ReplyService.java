package community.board.service;

import community.board.domain.Board;
import community.board.domain.Reply;
import community.board.domain.User;
import community.board.repository.BoardRepository;
import community.board.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void replySave(Long boardId, Reply reply, User user) {

        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("해당 boardId가 없습니다. id=" + boardId));
        reply.save(board, user);
        replyRepository.save(reply);
    }

    @Transactional
    public void replyDelete(Long replyId) {
        replyRepository.deleteById(replyId);
    }
}
