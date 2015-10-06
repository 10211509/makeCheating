package nobugs.team.cheating.repo.model;

import java.util.List;

/**
 * Created by wangyf on 2015/9/27 0027.
 */
public class ExamPo {


    /**
     * id : 6
     * name : 概率统计
     * term : 201506
     * info :
     * catalog : [{"name":1,"data":[{"exam_id":"6","question_no":"1","title":"第一题"},{"exam_id":"6","question_no":"2","title":"第二题"},{"exam_id":"6","question_no":"3","title":"第三题"},{"exam_id":"6","question_no":"4","title":"第四题"}]},{"name":2,"data":[{"exam_id":"6","question_no":"5","title":"第五题"},{"exam_id":"6","question_no":"6","title":"第六题"},{"exam_id":"6","question_no":"7","title":"第七题"},{"exam_id":"6","question_no":"8","title":"第八题"}]},{"name":3,"data":[{"exam_id":"6","question_no":"10","title":"第十题"},{"exam_id":"6","question_no":"11","title":"第11题"},{"exam_id":"6","question_no":"12","title":"第12题"},{"exam_id":"6","question_no":"9","title":"第九题"}]}]
     */

    private String id;
    private String name;
    private String term;
    private String info;
    private List<Catalog> catalog;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setCatalog(List<Catalog> catalog) {
        this.catalog = catalog;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTerm() {
        return term;
    }

    public String getInfo() {
        return info;
    }

    public List<Catalog> getCatalog() {
        return catalog;
    }

    public static class Catalog {
        /**
         * name : 1
         * data : [{"exam_id":"6","question_no":"1","title":"第一题"},{"exam_id":"6","question_no":"2","title":"第二题"},{"exam_id":"6","question_no":"3","title":"第三题"},{"exam_id":"6","question_no":"4","title":"第四题"}]
         */

        private int name;
        private List<DataEntity> data;

        public void setName(int name) {
            this.name = name;
        }

        public void setData(List<DataEntity> data) {
            this.data = data;
        }

        public int getName() {
            return name;
        }

        public List<DataEntity> getData() {
            return data;
        }

        public static class DataEntity {
            /**
             * exam_id : 6
             * question_no : 1
             * title : 第一题
             */

            private String exam_id;
            private String question_no;
            private String title;

            public void setExam_id(String exam_id) {
                this.exam_id = exam_id;
            }

            public void setQuestion_no(String question_no) {
                this.question_no = question_no;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getExam_id() {
                return exam_id;
            }

            public String getQuestion_no() {
                return question_no;
            }

            public String getTitle() {
                return title;
            }
        }
    }
}
