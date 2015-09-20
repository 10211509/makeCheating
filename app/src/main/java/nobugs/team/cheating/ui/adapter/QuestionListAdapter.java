package nobugs.team.cheating.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import nobugs.team.cheating.R;
import nobugs.team.cheating.model.Question;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by xiayong on 2015/9/14.
 */
public class QuestionListAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    private List<Question> questions;
    private Context context;

    public QuestionListAdapter(Context context, List<Question> questions) {
        this.context = context;
        this.questions = questions;
    }

    @Override
    public View getHeaderView(int i, View view, ViewGroup viewGroup) {
        HeaderViewHolder headerViewHolder;
        if (view == null) {
            headerViewHolder = new HeaderViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_exampaper_question_header, null);
            headerViewHolder.tvQuestionHeader = (TextView) view.findViewById(R.id.tv_question_header);
            view.setTag(headerViewHolder);
        } else {
            headerViewHolder = (HeaderViewHolder) view.getTag();
        }
        headerViewHolder.tvQuestionHeader.setText("第x部分");
        return view;
    }

    @Override
    public long getHeaderId(int i) {
        return questions.get(i).getSection();
    }

    @Override
    public int getCount() {
        return questions.size();
    }

    @Override
    public Object getItem(int position) {
        return questions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_exampaper_question, null);
            viewHolder.tvQuestionIndex = (TextView) convertView.findViewById(R.id.tv_question_index);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        CharSequence content =
        viewHolder.tvQuestionIndex.setText("第x题");
        return convertView;
    }

    class ViewHolder {
        public TextView tvQuestionIndex;
    }

    class HeaderViewHolder {
        public TextView tvQuestionHeader;
    }
}
