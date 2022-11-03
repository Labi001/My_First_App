package com.example.myapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.RecyclerViewAdapter;
import com.example.myapplication.databinding.FragmentFirstBinding;
import com.example.myapplication.helper.Constants;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private RecyclerView recyclerView;
    private MyFragmentItemSelectedListener mListener;
    ArrayList<String> itemNews;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof MyFragmentItemSelectedListener){

           this.mListener = (MyFragmentItemSelectedListener) context;

        }else {

            throw new ClassCastException(context.toString() + "duhet patjeter klasa te jete e tipit MyFragmentItemSelectedListener");

        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState

    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        itemNews = new ArrayList<>(Arrays.asList(Constants.newsMenu));

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView = view.findViewById(R.id.myRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        LocalAdapter adapter = new LocalAdapter(getContext(), itemNews, new LocalAdapter.OnItemClick() {
            @Override
            public void onItemClick(LocalAdapter.ViewHolder viewHolder, int position) {

                mListener.onNewsItemSelected(position);

            }
        });

        recyclerView.setAdapter(adapter);


    }

    public interface MyFragmentItemSelectedListener{

        void onNewsItemSelected(int position);
    }

    private static class LocalAdapter extends RecyclerView.Adapter<LocalAdapter.ViewHolder>{

        private Context context;
        private final ArrayList<String> mData;
        private final OnItemClick onItemClick;

        public LocalAdapter(Context context, ArrayList<String> mData, OnItemClick onItemClick) {
            this.context = context;
            this.mData = mData;
            this.onItemClick = onItemClick;
        }

        @NonNull
        @Override
        public LocalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.simple_local_item_for_fragment_layout,parent,false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull LocalAdapter.ViewHolder holder, int position) {

              holder.textView.setText(mData.get(position));
              holder.bindClickListener(holder,onItemClick,position);

        }

        public interface OnItemClick{

            void onItemClick(ViewHolder viewHolder,int position);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView textView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                textView = itemView.findViewById(R.id.textView);
            }

            public void bindClickListener(ViewHolder myviewHolder,OnItemClick listener,int position){

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(myviewHolder,position);
                    }
                });

            }
        }
    }

}