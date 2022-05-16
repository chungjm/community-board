package community.board.api;

import community.board.config.auth.PrincipalDetail;
import community.board.domain.Reply;
import community.board.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class ReplyApiController {

    private final ReplyService replyService;

    @PostMapping("/api/v1/board/{boardId}/reply")
    public void save(@PathVariable Long boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        replyService.replySave(boardId, reply, principalDetail.getUser());
    }

    @DeleteMapping("/api/v1/board/{boardId}/reply/{replyId}")
    public void delete(@PathVariable Long replyId) {
        replyService.replyDelete(replyId);
    }
}
