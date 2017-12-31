package cn.edu.gdmec.android.boxuegu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.activity.ExercisesDetailActivity;
import cn.edu.gdmec.android.boxuegu.bean.ExercisesBean;

/**
 * Created by Administrator on 2017/12/31 0031.
 */

public class ExercisesAdapter extends BaseAdapter{
    private Context mContext;

    public ExercisesAdapter(Context mContext){
        this.mContext = mContext;
    }

    private List<ExercisesBean> ebl;

    public void setData(List<ExercisesBean> ebl){
        this.ebl = ebl;
        notifyDataSetChanged();
    }

    /**
     * item总数
     * @return
     */

    @Override
    public int getCount() {
        return ebl  == null ? 0 : ebl.size();
    }

    /**
     * 根据position得到对应item的对象
     * @param position
     * @return
     */

    @Override
    public Object getItem(int position) {
        return ebl == null ? null: ebl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if(convertView == null){
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.exercises_list_item,null);
            vh.title = (TextView) convertView.findViewById(R.id.tv_title);
            vh.content = (TextView) convertView.findViewById(R.id.tv_content);
            vh.order = (TextView) convertView.findViewById(R.id.tv_order);
            convertView.setTag(vh);

        }else {
            vh = (ViewHolder)convertView.getTag();

        }
        final ExercisesBean bean = (ExercisesBean)getItem(position);
        if(bean!=null) {
            vh.order.setText(position + 1 + "");
            vh.title.setText(bean.title);
            vh.content.setText(bean.content);
            vh.order.setBackgroundResource(bean.background);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean == null){
                    return;

                }
                //跳转习题界面
                Intent intent = new Intent(mContext, ExercisesDetailActivity.class);
                intent.putExtra("id",bean.id);
                intent.putExtra("title",bean.title);
                mContext.startActivity(intent);

            }
        });
        return convertView;
    }
    class ViewHolder{
        public TextView title;
        public TextView content;
        public TextView order;
    }
}
