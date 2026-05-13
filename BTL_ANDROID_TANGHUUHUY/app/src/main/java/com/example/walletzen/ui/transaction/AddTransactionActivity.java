package com.example.walletzen.ui.transaction;

import android.content.Intent;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.walletzen.R;
import com.example.walletzen.model.Transaction;
import com.example.walletzen.network.ApiService;
import com.example.walletzen.network.RetrofitClient;
import com.example.walletzen.ui.home.HomeActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTransactionActivity extends AppCompatActivity {

    MaterialCardView categoryCard;
    TextView txtCategoryIcon;

    ImageView btnBack;
    MaterialButton btnSave;

    TextInputEditText edtTitle;
    TextInputEditText edtAmount;
    TextInputEditText edtNote;
    TextInputEditText edtDate;
    TextInputEditText edtTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        categoryCard = findViewById(R.id.categoryCard);
        txtCategoryIcon = findViewById(R.id.txtCategoryIcon);

        btnBack = findViewById(R.id.btnBack);
        btnSave = findViewById(R.id.btnSave);

        edtTitle = findViewById(R.id.edtTitle);
        edtAmount = findViewById(R.id.edtAmount);
        edtNote = findViewById(R.id.edtNote);
        edtDate = findViewById(R.id.edtDate);
        edtTime = findViewById(R.id.edtTime);

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat dateFormat =
                new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        SimpleDateFormat timeFormat =
                new SimpleDateFormat("HH:mm", Locale.getDefault());

        edtDate.setText(dateFormat.format(calendar.getTime()));
        edtTime.setText(timeFormat.format(calendar.getTime()));

        edtDate.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();

            new DatePickerDialog(this, (view, y, m, d) -> {
                String date = String.format(Locale.getDefault(),
                        "%02d/%02d/%d", d, m + 1, y);
                edtDate.setText(date);
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
        });

        edtTime.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();

            new TimePickerDialog(this, (view, h, m) -> {
                String time = String.format(Locale.getDefault(),
                        "%02d:%02d", h, m);
                edtTime.setText(time);
            }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
        });

        btnBack.setOnClickListener(v -> finish());

        categoryCard.setOnClickListener(v -> showCategoryDialog());

        // SAVE TO API
        btnSave.setOnClickListener(v -> {

            String title = edtTitle.getText().toString();
            String amount = edtAmount.getText().toString();
            String note = edtNote.getText().toString();
            String date = edtDate.getText().toString();
            String time = edtTime.getText().toString();
            String icon = txtCategoryIcon.getText().toString();

            Transaction transaction = new Transaction(
                    title,
                    amount,
                    icon,
                    date,
                    time,
                    note
            );

            ApiService apiService =
                    RetrofitClient.getRetrofit().create(ApiService.class);

            apiService.addTransaction(transaction)
                    .enqueue(new Callback<Transaction>() {

                        @Override
                        public void onResponse(Call<Transaction> call, Response<Transaction> response) {

                            Toast.makeText(
                                    AddTransactionActivity.this,
                                    "Đã lưu giao dịch",
                                    Toast.LENGTH_SHORT
                            ).show();

                            startActivity(new Intent(
                                    AddTransactionActivity.this,
                                    HomeActivity.class
                            ));

                            finish();
                        }

                        @Override
                        public void onFailure(Call<Transaction> call, Throwable t) {

                            Toast.makeText(
                                    AddTransactionActivity.this,
                                    t.getMessage(),
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    });
        });
    }

    private void showCategoryDialog() {

        String[] typeList = {"💸 Chi tiêu", "💰 Thu nhập"};

        new AlertDialog.Builder(this)
                .setTitle("Chọn loại")
                .setItems(typeList, (dialog, which) -> {
                    boolean isExpense = which == 0;
                    showCategoryList(isExpense);
                })
                .show();
    }

    private void showCategoryList(boolean isExpense) {

        String[] expenseIcons = {
                "🍴","👕","🍎","🛍","🚗","🏠","✈️","🍺","💡","🎁",
                "🎓","❤️","🎮","🚘","🌍","🛡","💼","📚","🚬","🐶","💄"
        };

        String[] expenseCategories = {
                "Ăn uống","Quần áo","Hoa quả","Mua sắm","Giao thông",
                "Nhà ở","Du lịch","Rượu bia","Điện nước","Quà",
                "Giáo dục","Sức khỏe","Giải trí","Ô tô","Xã hội",
                "Bảo hiểm","Văn phòng","Sách","Thuốc lá","Thú cưng","Làm đẹp"
        };

        String[] incomeIcons = {"🏠","❤️","📈","💸","💰","🛒","🎉","🎟","📦"};

        String[] incomeCategories = {
                "Cho thuê","Quyên góp","Cổ tức","Hoàn tiền",
                "Tiền lương","Bán hàng","Tiền thưởng","Phiếu giảm giá","Khác"
        };

        String[] icons = isExpense ? expenseIcons : incomeIcons;
        String[] categories = isExpense ? expenseCategories : incomeCategories;

        String[] items = new String[categories.length];

        for (int i = 0; i < categories.length; i++) {
            items[i] = icons[i] + "  " + categories[i];
        }

        new AlertDialog.Builder(this)
                .setTitle(isExpense ? "Chi tiêu" : "Thu nhập")
                .setItems(items, (d, which) -> {
                    txtCategoryIcon.setText(icons[which]);
                })
                .setNegativeButton("Quay lại", (d, w) -> showCategoryDialog())
                .show();
    }
}