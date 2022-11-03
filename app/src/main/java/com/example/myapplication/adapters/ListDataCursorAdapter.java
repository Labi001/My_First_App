package com.example.myapplication.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.helper.DBHelper;

public class ListDataCursorAdapter extends RecyclerView.Adapter<ListDataCursorAdapter.ViewHolder> {

    private Context context;
    private CursorAdapter cursorAdapter;
    private ViewHolder holder;

    public ListDataCursorAdapter(Context context, Cursor cursor) {
        this.context = context;

        cursorAdapter = new CursorAdapter(context,cursor,0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.data_base_single_item_layout,parent,false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {

                String id = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_NAME));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_EMAIL));

                holder.title_textView.setText(name);
                holder.email_textView.setText(email);

            }
        };

    }

    @NonNull
    @Override
    public ListDataCursorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = cursorAdapter.newView(context,cursorAdapter.getCursor(),parent);
        holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListDataCursorAdapter.ViewHolder holder, int position) {
        cursorAdapter.getCursor().moveToPosition(position);
        cursorAdapter.bindView(holder.itemView,context, cursorAdapter.getCursor());

    }

    @Override
    public int getItemCount() {
        return cursorAdapter.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title_textView;
        TextView email_textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title_textView = itemView.findViewById(R.id.title_textView);
            email_textView = itemView.findViewById(R.id.email_textView);
        }
    }
}
