package UMC.WithYou.dto.post;

import UMC.WithYou.domain.Post.Comment;
import UMC.WithYou.domain.Post.Post;
import UMC.WithYou.domain.Post.PostMedia;
import UMC.WithYou.domain.Post.Reply;
import UMC.WithYou.domain.member.Member;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class PostResponse {

    @AllArgsConstructor
    @Getter
    public static class PublishResponse{
        private Long postId;
    }


    @Getter
    public static class ThumbnailResponseDTO{
        private Long postId;
        private String thumbnailUrl;

        public ThumbnailResponseDTO(Post post){
            this.postId = post.getId();
            this.thumbnailUrl = post.getPostMediaList().get(0).getUrl();
        }
    }

    @Getter
    public static class PostResponseDTO{
        private Long postId;
        private Long memberid;
        private List<String> urls;
        private String text;
        private List<CommentDTO> commentDTOs;

        public PostResponseDTO(Post post){
            this.postId = post.getId();
            this.memberid = post.getMember().getId();
            this.urls = new ArrayList<>();
            for (PostMedia postMedia: post.getPostMediaList()){
                this.urls.add(postMedia.getUrl());
            }
            this.text = post.getText();

            this.commentDTOs = new ArrayList<>();
            for (Comment comment: post.getComments()){
                commentDTOs.add(new CommentDTO(comment));
            }
        }
        @Getter
        private static class CommentDTO{
            private Long commentId;
            private Member member;

            private String content;
            private List<ReplyDTO> replyDTOs;

            public CommentDTO(Comment comment){
                this.commentId = comment.getId();
                this.content = comment.getContent();

                this.replyDTOs = new ArrayList<>();
                for (Reply reply: comment.getReplies()){
                    replyDTOs.add(new ReplyDTO(reply));
                }
            }

            @Getter
            private static class ReplyDTO{
                private Long replyId;
                private Long memberId;
                private String content;
                public ReplyDTO(Reply reply){
                    this.replyId = reply.getId();
                    this.memberId = reply.getMember().getId();
                    this.content = reply.getContent();
                }

            }
        }

    }
    @Getter

    public static class DeletionResponseDTO{
        private Long postId;
        public DeletionResponseDTO(Long postId) {
            this.postId = postId;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class EditResponseDTO {
        private Long postId;

    }

    @Getter
    @AllArgsConstructor
    public static class ScrapeResponseDTO{
        private Long postId;
    }
}
