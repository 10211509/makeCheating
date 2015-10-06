package nobugs.team.cheating.repo.api.mapper;

import android.text.TextUtils;

import nobugs.team.cheating.model.Question;
import nobugs.team.cheating.repo.model.QuestionPo;

/**
 * Created by wangyf on 2015/9/29 0029.
 */
public class QuestionMapper implements IModelMapper<Question, QuestionPo>{

    @Override
    public QuestionPo fromModel(Question question) {
        return null;
    }

    @Override
    public Question toModel(QuestionPo questionPo) {
        Question question = new Question();
        if (!TextUtils.isEmpty(questionPo.getId())) {
            question.setId(Integer.parseInt(questionPo.getId()));
        }
        question.setTitle(questionPo.getTitle());
        if (!TextUtils.isEmpty(questionPo.getUnit())) {
            question.setSection(Integer.parseInt(questionPo.getUnit()));
        }
        if (!TextUtils.isEmpty(questionPo.getQuestion_no())) {
            question.setSn(Integer.parseInt(questionPo.getQuestion_no()));
        }
        question.setAnswer(questionPo.getAnswer());

        return question;
    }
}
