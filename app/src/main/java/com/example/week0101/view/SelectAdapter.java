package com.example.week0101.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.week0101.R;
import com.example.week0101.model.SelectBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;


class SelectAdapter  extends BaseExpandableListAdapter {
    Context context; List<SelectBean.DataBean> data;
    private ParentHolder parentHolder;
    private ChildHolder childHolder;
    private List<SelectBean.DataBean.ListBean> list;

    public SelectAdapter(Context context, List<SelectBean.DataBean> data) {
          this.context=context;
          this.data=data;
    }


    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.parent, null);
        parentHolder = new ParentHolder();
        parentHolder.parent_check = view.findViewById(R.id.parent_check);
        parentHolder. parent_name = view.findViewById(R.id.parent_name);

        parentHolder.parent_check.setChecked(data.get(groupPosition).isCheck());
        parentHolder.parent_name.setText(data.get(groupPosition).getSellerName());

        SelectBean.DataBean dataBean = data.get(groupPosition);
        parentHolder.parent_check.setChecked(dataBean.isCheck());
        parentHolder.parent_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox=(CheckBox)v;
                boolean checked = checkBox.isChecked();
                data.get(groupPosition).isCheck();
                SelectAll(groupPosition,checked);
            }


        });

        return  view;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.child, null);
        childHolder = new ChildHolder();
        childHolder. child_check = view.findViewById(R.id.child_check);
        childHolder. img = view.findViewById(R.id.img);
        childHolder.child_name = view.findViewById(R.id.child_name);
        childHolder .child_price = view.findViewById(R.id.child_price);

        childHolder.child_check.setChecked(data.get(groupPosition).getList().get(childPosition).isCheck());
        childHolder.img.setImageURI(data.get(groupPosition).getList().get(childPosition).getImages());
        childHolder.child_name.setText(data.get(groupPosition).getList().get(childPosition).getTitle());
        childHolder.child_price.setText(data.get(groupPosition).getList().get(childPosition).getPrice()+"");


        list = data.get(groupPosition).getList();
        childHolder.child_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox=(CheckBox)v;
                SelectBean.DataBean dataBean = data.get(groupPosition);
                SelectBean.DataBean.ListBean listBean = data.get(groupPosition).getList().get(childPosition);
                listBean.isCheck(checkBox.isChecked());
                boolean SelectAll=isSelectAll(groupPosition);
                dataBean.isCheck(SelectAll);
                notifyDataSetChanged();

            }


        });

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    class ParentHolder{
         TextView parent_name;
        CheckBox parent_check;
    }
    class  ChildHolder{
        SimpleDraweeView img;
        TextView child_name;
        TextView child_price;
        CheckBox child_check;

    }
    private boolean isSelectAll(int groupPosition) {
        for (int i = 0; i <data.get(groupPosition).getList().size() ; i++) {
            SelectBean.DataBean.ListBean listBean = data.get(groupPosition).getList().get(i);
            boolean check=listBean.isCheck();
            if (!check){
                return  false;
            }
        }
        return  true;
    }
    private void SelectAll(int groupPosition, boolean checked) {
        for (int i = 0; i <data.get(groupPosition).getList().size() ; i++) {
            SelectBean.DataBean.ListBean listBean = data.get(groupPosition).getList().get(i);
            listBean.isCheck(checked);
        }
    }
    }

