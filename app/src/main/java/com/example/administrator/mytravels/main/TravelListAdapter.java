package com.example.administrator.mytravels.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.administrator.mytravels.BaseActivity;
import com.example.administrator.mytravels.EditTravelsActivity;
import com.example.administrator.mytravels.MainActivity;
import com.example.administrator.mytravels.R;
import com.example.administrator.mytravels.entity.Travel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TravelListAdapter extends RecyclerView.Adapter<TravelListAdapter.TravelViewHolder> {
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private List<Travel> mList;
    private ListItemClickListener mListItemClickListener;

    public TravelListAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setList(List<Travel> list){
        this.mList=list;
        notifyDataSetChanged();
    }

    private Travel getItem(int position){
        if (getItemCount()==0) return null;
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        if (getItemCount()==0) return RecyclerView.NO_ID;
        return mList.get(position).getId();
    }

    @NonNull
    @Override
    public TravelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.main_travel_item,parent,false);
        return new TravelViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TravelViewHolder holder, int position) {
        if (getItemCount()==0) return;
        Travel item = mList.get(position);
        holder.titleTxt.setText(item.getTitle());
        holder.placeTxt.setText(item.getPlaceName()+"/"+item.getPlaceAddr());
        holder.dateTxt.setText(item.getStartDtText()+" ~ "+item.getEndDtText());
    }

    @Override
    public int getItemCount() {
        if (mList==null)
        return 0;
        return mList.size();
    }

    public interface ListItemClickListener {
        void onListItemClick(View view, int position, Travel travel);
    }

    public void setListItemClickListener(ListItemClickListener listItemClickListener) {
        mListItemClickListener = listItemClickListener;
    }

    class TravelViewHolder extends RecyclerView.ViewHolder{
        private final TextView titleTxt;
        private final TextView placeTxt;
        private final TextView dateTxt;
        private final ImageButton editBtn;

        private TravelViewHolder(View v) {
            super(v);
            if (mListItemClickListener != null) {
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListItemClickListener.onListItemClick(v
                                , getAdapterPosition()
                                , getItem(getAdapterPosition()));
                    }
                });
            }
            titleTxt = v.findViewById(R.id.title_txt);
            placeTxt = v.findViewById(R.id.place_txt);
            dateTxt = v.findViewById(R.id.date_txt);
            editBtn = v.findViewById(R.id.editBtn);
            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Travel travel = getItem(getAdapterPosition());
                    Intent intent = new Intent(mContext, EditTravelsActivity.class);
                    intent.putExtra(BaseActivity.REQKEY_TRAVEL, travel);
                    intent.setAction(BaseActivity.REQACTION_EDIT_TRAVEL);
                    ((MainActivity) mContext).startActivityForResult(intent,BaseActivity.REQCD_TRAVEL_EDIT);
                }
            });
        }
    }
}
