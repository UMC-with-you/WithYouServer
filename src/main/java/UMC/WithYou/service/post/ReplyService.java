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
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final CommentService commentService;
    private final MemberService memberService;

    public ReplyService(ReplyRepository replyRepository, CommentService commentService, MemberService memberService) {
        this.replyRepository = replyRepository;
        this.commentService = commentService;
        this.memberService = memberService;
    }

    public Reply writeReply(String token, Long commentId, String content) {
        Comment comment = commentService.findCommentById(commentId);
        Member writer = memberService.findByMemberIdToken(token);

        Reply reply =  new Reply(comment, writer, content);

        replyRepository.save(reply);

        return reply;
    }

    public void deleteReply(String token, Long replyId) {
        Reply reply = this.findReplyById(replyId);
        validateReplyOwnerShip(token, reply);

        replyRepository.delete(reply);
    }

    public void editReply(String token, Long replyId, String content) {
        Reply reply = this.findReplyById(replyId);
        validateReplyOwnerShip(token, reply);

        reply.setContent(content);
    }


    private void validateReplyOwnerShip(String token, Reply reply){
        Member writer = memberService.findByMemberIdToken(token);
        if (!reply.getMember().isSameId(writer.getId())) {
            throw new CommonErrorHandler(ErrorStatus.UNAUTHORIZED_ACCESS_TO_REPLY);
        }

    }

    private Reply findReplyById(Long replyId){
        return replyRepository.findById(replyId).orElseThrow(
                ()->new CommonErrorHandler(ErrorStatus.REPLY_NOT_FOUND)
        );
    }

}
