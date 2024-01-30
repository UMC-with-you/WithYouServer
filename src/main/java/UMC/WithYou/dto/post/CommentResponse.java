package UMC.WithYou.dto.post;

import UMC.WithYou.domain.Post.Comment;
import UMC.WithYou.domain.Post.Post;
import UMC.WithYou.domain.member.Member;
import lombok.Getter;

public class CommentResponse {
    @Getter
    public static class WriteResponseDTO {
        private Long postId;
        private Long memberId;
        private Long commentId;
        private String content;

        public WriteResponseDTO(Comment comment){
            this.postId = comment.getPost().getId();
            this.memberId = comment.getMember().getId();
            this.commentId = comment.getId();
            this.content = comment.getContent();
        }
    }

    @Getter
    public static class DeletionResponseDTO{
        private Long commentId;
        public DeletionResponseDTO( Long commentId){
            this.commentId = commentId;
        }
    }

    @Getter
    public static class EditResponseDTO {
        private Long postId;
        private Long commentId;
        private String content;
        public EditResponseDTO(Comment comment){
            this.postId = comment.getPost().getId();
            this.commentId = comment.getId();
            this.content = comment.getContent();
        }
    }

}


