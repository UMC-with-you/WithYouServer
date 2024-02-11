package UMC.WithYou.service.post;

import UMC.WithYou.common.apiPayload.code.status.ErrorStatus;
import UMC.WithYou.common.apiPayload.exception.handler.CommonErrorHandler;
import UMC.WithYou.domain.Post.Comment;
import UMC.WithYou.domain.Post.Reply;
import UMC.WithYou.domain.member.Email;
import UMC.WithYou.domain.member.Member;
import UMC.WithYou.repository.member.MemberRepository;
import UMC.WithYou.repository.post.CommentRepository;
import UMC.WithYou.repository.post.ReplyRepository;
import UMC.WithYou.service.member.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final CommentService commentService;


    public Reply writeReply(Member member, Long commentId, String content) {
        Comment comment = commentService.findCommentById(commentId);

        Reply reply =  new Reply(comment, member, content);

        replyRepository.save(reply);

        return reply;
    }

    public void deleteReply(Member member, Long replyId) {
        Reply reply = this.findReplyById(replyId);
        validateReplyOwnerShip(member, reply);

        replyRepository.delete(reply);
    }

    public void editReply(Member member, Long replyId, String content) {
        Reply reply = this.findReplyById(replyId);
        validateReplyOwnerShip(member, reply);

        reply.setContent(content);
    }


    private void validateReplyOwnerShip(Member member, Reply reply){
        if (!reply.getMember().isSameId(member.getId())) {
            throw new CommonErrorHandler(ErrorStatus.UNAUTHORIZED_ACCESS_TO_REPLY);
        }

    }

    private Reply findReplyById(Long replyId){
        return replyRepository.findById(replyId).orElseThrow(
                ()->new CommonErrorHandler(ErrorStatus.REPLY_NOT_FOUND)
        );
    }

}
