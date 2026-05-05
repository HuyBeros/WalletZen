package com.example.walletzen.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walletzen.R;
import com.example.walletzen.model.Transaction;

import java.util.List;

public class TransactionAdapter
        extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    List<Transaction> list;

    public TransactionAdapter(List<Transaction> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transaction,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position
    ) {

        Transaction transaction = list.get(position);

        holder.txtTitle.setText(transaction.getTitle());
        holder.txtDate.setText(transaction.getDate());
        holder.txtAmount.setText(transaction.getAmount());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtDate, txtAmount;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtAmount = itemView.findViewById(R.id.txtAmount);

        }
    }
}