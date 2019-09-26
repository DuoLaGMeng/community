package life.guangsi.community.mapper;

import life.guangsi.community.dto.QuestionQueryDTO;
import life.guangsi.community.model.Question;
import life.guangsi.community.model.QuestionExample;

import java.util.List;

public interface QuestionExtMapper {

    int incView(Question record);

    int incCommentCount(Question record);

    List<Question> selectRelated(Question question);

    Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}