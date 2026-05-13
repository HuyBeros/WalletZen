package com.example.walletzen.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walletzen.R;
import com.example.walletzen.model.Transaction;
import com.example.walletzen.ui.detail.TransactionDetailActivity;

import java.util.List;

public class TransactionAdapter
        extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    Context context;
    List<Transaction> list;

    public TransactionAdapter(Context context, List<Transaction> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_transaction, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Transaction transaction = list.get(position);

        holder.txtTitle.setText(transaction.getTitle());
        holder.txtAmount.setText(transaction.getAmount());
        holder.txtIcon.setText(transaction.getCategoryIcon());

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(context, TransactionDetailActivity.class);

            intent.putExtra("title", transaction.getTitle());
            intent.putExtra("amount", transaction.getAmount());
            intent.putExtra("icon", transaction.getCategoryIcon());
            intent.putExtra("date", transaction.getDate());
            intent.putExtra("time", transaction.getTime());
            intent.putExtra("note", transaction.getNote());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;
        TextView txtAmount;
        TextView txtIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtAmount = itemView.findViewById(R.id.txtAmount);
            txtIcon = itemView.findViewById(R.id.txtIcon);
        }
    }
}