package com.example.btl_android.adapter;

import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_android.itemView.TransactionItemView;
import com.example.btl_android.objectClass.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionItemView> {

    public List<Transaction> transactionList;
    private List<String> key;

    private ArrayList<Transaction> trans;
    public TransactionAdapter(ArrayList<Transaction> list){
        trans = list;
    }

    public TransactionAdapter(){}

    public TransactionAdapter(List<Transaction> transactionList, List<String> key) {
        this.transactionList = transactionList;
        this.key = key;
    }

    @NonNull
    @Override
    public TransactionItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TransactionItemView(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionItemView holder, int position) {
        holder.bind(transactionList.get(position), key.get(position));
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }
    public List<Transaction> getExpense(){ return transactionList;}
    public List<String> getKey(){return key;}

    public void setDataList(ArrayList<Transaction> dataList, ArrayList<String> khoa){
        transactionList = dataList;
        key = khoa;
        notifyDataSetChanged();
    }
}
