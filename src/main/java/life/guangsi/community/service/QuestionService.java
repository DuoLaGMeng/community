package life.guangsi.community.service;

import life.guangsi.community.dto.PageDTO;
import life.guangsi.community.dto.QuestionDTO;
import life.guangsi.community.exception.CustomizeErrorCode;
import life.guangsi.community.exception.CustomizeException;
import life.guangsi.community.mapper.QuestionExtMapper;
import life.guangsi.community.mapper.QuestionMapper;
import life.guangsi.community.mapper.UserMapper;
import life.guangsi.community.model.Question;
import life.guangsi.community.model.QuestionExample;
import life.guangsi.community.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    public PageDTO list(Integer page, Integer size) {
        Integer totalPage;
        PageDTO pageDTO = new PageDTO();

        Integer totalcount = (int) questionMapper.countByExample(new QuestionExample());

        if (totalcount % size == 0) {
            totalPage = totalcount / size;
        } else {
            totalPage = (totalcount / size) + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        pageDTO.setPagination(totalPage, page);
        Integer offset = page < 1 ? 0 : size * (page - 1);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, size));
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        pageDTO.setQuestionDTOS(questionDTOS);

        return pageDTO;
    }

    public PageDTO list(Integer userId, Integer page, Integer size) {
        Integer totalPage;
        PageDTO pageDTO = new PageDTO();
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        Integer totalcount = (int) questionMapper.countByExample(example);

        if (totalcount % size == 0) {
            totalPage = totalcount / size;
        } else {
            totalPage = (totalcount / size) + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        pageDTO.setPagination(totalPage, page);

        Integer offset = page < 1 ? 0 : size * (page - 1);

        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        pageDTO.setQuestionDTOS(questionDTOS);

        return pageDTO;
    }

    public QuestionDTO getById(Integer id) {
        QuestionDTO questionDTO = new QuestionDTO();
        Question question = questionMapper.selectByPrimaryKey(id);
        if(question == null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        BeanUtils.copyProperties(question, questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        } else {
            question.setGmtModified(question.getGmtCreate());
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria().andIdEqualTo(question.getId());
            int update = questionMapper.updateByExampleSelective(updateQuestion, questionExample);
            if(update != 1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Integer id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }
}
