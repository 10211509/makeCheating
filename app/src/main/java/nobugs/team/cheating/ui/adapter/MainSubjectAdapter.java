package nobugs.team.cheating.ui.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import nobugs.team.cheating.R;
import nobugs.team.cheating.model.Subject;

/**
 * Created by wangyf on 2015/9/13 0013.
 */
public class MainSubjectAdapter extends RecyclerView.Adapter<MainSubjectAdapter.ViewHolder> {

    private List<Subject> subjects = new ArrayList<>();

    private OnItemClickListener listener;

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        if (subjects != null) {
            this.subjects = subjects;
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
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.updateView(subjects.get(position));
        holder.setCardClickListener(position, subjects.get(position));
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_subject_name)
        TextView tvSubjectName;
        @Bind(R.id.tv_subject_time)
        TextView tvSubjectTime;
        @Bind(R.id.card_subject)
        CardView cardSubject;

        float cardElevation;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            cardElevation = cardSubject.getCardElevation();
        }

        public void updateView(Subject subject) {
            if (subject != null) {
                tvSubjectName.setText(subject.getName());
                tvSubjectTime.setText(subject.getTime());
            }
        }

        public void setCardClickListener(final int position, final Subject subject) {
            cardSubject.setOnTouchListener(new View.OnTouchListener() {
                public ObjectAnimator animatorPress;
                public ObjectAnimator animatorUnPress;

                @Override
                public boolean onTouch(View v, final MotionEvent event) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        Log.w("ViewHolder", MotionEvent.actionToString(event.getAction()));
                    }
                    final int action = event.getAction();
                    switch (action) {
                        case MotionEvent.ACTION_DOWN:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                                if (animatorPress == null || !animatorPress.isRunning()) {
                                    animatorPress = ObjectAnimator.ofFloat(cardSubject, "cardElevation", cardElevation, 0);
                                    animatorPress.start();
                                }
                            }
                            break;
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL:
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                                if (animatorPress != null && animatorPress.isRunning()) {
                                    animatorPress.end();
                                }
                                animatorUnPress = ObjectAnimator.ofFloat(cardSubject, "cardElevation", 0, cardElevation);
                                animatorUnPress.addListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {
                                        cardSubject.setEnabled(false);
                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        cardSubject.setEnabled(true);
                                        if (listener != null && action == MotionEvent.ACTION_UP) {
                                            listener.onItemClick(position, subject);
                                        }
                                    }
                                });
                                animatorUnPress.start();
                            }
                            break;
                    }
                    return false;
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int index, Subject subject);
    }
}
