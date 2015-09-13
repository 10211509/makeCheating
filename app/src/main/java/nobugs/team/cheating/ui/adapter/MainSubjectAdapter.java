package nobugs.team.cheating.ui.adapter;

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
import nobugs.team.cheating.mvp.model.Subject;

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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.updateView(subjects.get(position));
        holder.cardSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onItemClick(position, subjects.get(position));
                }
            }
        });
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

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void updateView(Subject subject) {
            if (subject != null) {
                tvSubjectName.setText(subject.getName());
                tvSubjectTime.setText(subject.getTime());
            }
        }

    }

    public interface OnItemClickListener {
        void onItemClick(int index, Subject subject);
    }
}
