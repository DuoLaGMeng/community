package life.guangsi.community.mapper;

import life.guangsi.community.model.Comment;
import life.guangsi.community.model.CommentExample;
import life.guangsi.community.model.Question;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}