package nobugs.team.cheating.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import nobugs.team.cheating.R;
import nobugs.team.cheating.model.Course;

/**
 * Created by wangyf on 2015/9/13 0013.
 */
public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private List<Course> courses = new ArrayList<>();

    private OnItemClickListener listener;

    public List<Course> getCourses() {
        return courses;
    }

    private Context mContext;

    public void setCourses(List<Course> courses) {
        if (courses != null) {
            this.courses = courses;
        }
    }

    public OnItemClickListener getListener() {
        return listener;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_subjects, parent, false);
        mContext = parent.getContext();

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.updateView(courses.get(position));
        holder.setCardClickListener(position, courses.get(position));
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_name)
        TextView tvCourseName;
        @Bind(R.id.tv_exam_time)
        TextView tvCourseTime;
        @Bind(R.id.card_course)
        CardView cardCourse;

        float cardElevation;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            cardElevation = cardCourse.getCardElevation();
        }

        public void updateView(Course course) {
            if (course != null) {
                tvCourseName.setText(course.getName());
                tvCourseTime.setText(course.getTime());
                switch (course.getStatus()) {
                    case WAIT:
                        cardCourse.setCardBackgroundColor(mContext.getResources().getColor(R.color.course_stat_wait_normal));
                        break;
                    case READY:
                        cardCourse.setCardBackgroundColor(mContext.getResources().getColor(R.color.course_stat_ready_normal));
                        break;
                    case LOCKED:
                        cardCourse.setCardBackgroundColor(mContext.getResources().getColor(R.color.course_stat_locked_normal));
                        break;
                }

            }
        }

        public void setCardClickListener(final int position, final Course subject) {
            cardCourse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(position, subject);
                    }
                }
            });

//            cardCourse.setOnTouchListener(new View.OnTouchListener() {
//                public ObjectAnimator animatorPress;
//                public ObjectAnimator animatorUnPress;
//
//                @Override
//                public boolean onTouch(View v, final MotionEvent event) {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                        Log.w("ViewHolder", MotionEvent.actionToString(event.getAction()));
//                    }
//                    final int action = event.getAction();
//                    switch (action) {
//                        case MotionEvent.ACTION_DOWN:
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//                                if (animatorPress == null || !animatorPress.isRunning()) {
//                                    animatorPress = ObjectAnimator.ofFloat(cardCourse, "cardElevation", cardElevation, 0);
//                                    animatorPress.start();
//                                }
//                            }
//                            break;
//                        case MotionEvent.ACTION_UP:
//                        case MotionEvent.ACTION_CANCEL:
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//                                if (animatorPress != null && animatorPress.isRunning()) {
//                                    animatorPress.end();
//                                }
//                                animatorUnPress = ObjectAnimator.ofFloat(cardCourse, "cardElevation", 0, cardElevation);
//                                animatorUnPress.addListener(new AnimatorListenerAdapter() {
//                                    @Override
//                                    public void onAnimationStart(Animator animation) {
//                                        cardCourse.setEnabled(false);
//                                    }
//
//                                    @Override
//                                    public void onAnimationEnd(Animator animation) {
//                                        cardCourse.setEnabled(true);
//                                        if (listener != null && action == MotionEvent.ACTION_UP) {
//                                            listener.onItemClick(position, subject);
//                                        }
//                                    }
//                                });
//                                animatorUnPress.start();
//                            }
//                            break;
//                    }
//                    return false;
//                }
//            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int index, Course subject);
    }
}
