package nobugs.team.cheating.repo.api.mapper;

import java.util.ArrayList;
import java.util.List;

import nobugs.team.cheating.model.Exam;
import nobugs.team.cheating.model.Question;
import nobugs.team.cheating.repo.model.ExamPo;

/**
 * Created by wangyf on 2015/9/29 0029.
 */
public class ExamMapper implements IModelMapper<Exam, ExamPo>{

    @Override
    public ExamPo fromModel(Exam exam) {
        return null;
    }

    @Override
    public Exam toModel(ExamPo examPo) {
        Exam exam = new Exam();

        exam.setId(Integer.parseInt(examPo.getId()));
        exam.setTitle(examPo.getInfo());
        exam.setName(examPo.getName());
        exam.setTime(examPo.getTerm());

        List<Question> questions = new ArrayList<>();
        for(ExamPo.Catalog catalog: examPo.getCatalog()){
            for (ExamPo.Catalog.DataEntity data: catalog.getData()){
                Question question = new Question();
                question.setSection(catalog.getName());
                question.setSn(Integer.parseInt(data.getQuestion_no()));
                question.setTitle(data.getTitle());

                questions.add(question);
            }
        }

        exam.setQuestions(questions);

        return exam;
    }
}
