package com.example.week0101.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.week0101.R;
import com.example.week0101.model.ShowBean;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import static com.example.week0101.R.layout.ilist;


class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context; ShowBean showBean;
    public MyAdapter(Context context, ShowBean showBean) {
        this.context=context;
        this.showBean=showBean;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.ilist,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
            viewHolder.img.setImageURI(showBean.getData().getMiaosha().getList().get(i).getImages());
            viewHolder.name.setText(showBean.getData().getMiaosha().getList().get(i).getTitle());
            viewHolder.price.setText(showBean.getData().getMiaosha().getList().get(i).getBargainPrice()+"");
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().postSticky(showBean.getData().getMiaosha().getList().get(i).getPid());
                    Intent intent = new Intent(context, XiangQActivity.class);
                    context.startActivity(intent);
                }
            });
    }


    @Override
    public int getItemCount() {
        return showBean.getData().getMiaosha().getList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img;
        private final TextView name;
        private final TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
        }
    }
}
