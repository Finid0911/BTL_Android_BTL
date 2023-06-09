package com.example.btl_android.recycle;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_android.IncomeActivity;
import com.example.btl_android.objectClass.Account;
import com.example.btl_android.R;
import com.example.btl_android.TransactionActivity;
import com.example.btl_android.fragment.HomeFragment;

import java.util.List;

public class Recycle_Account {
    private Context mcontext;
    private AccountAdapter accountAdapter;

    // Firebase adapter <- Recycle_Account (setconfig())
    public void setConfig(RecyclerView recyclerView, Context context, List<Account> accounts, List<String> keys){
        mcontext = context;
        accountAdapter = new AccountAdapter(accounts, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(accountAdapter);
        accountAdapter.notifyDataSetChanged();
    }

    // AccountItemView
    class AccountItemView extends RecyclerView.ViewHolder{
        private TextView tName;
        private TextView tMoney;
        private String key;

        public AccountItemView(ViewGroup parent) {
            super(LayoutInflater.from(mcontext).inflate(R.layout.account_item, parent, false));
            tName = (TextView) itemView.findViewById(R.id.txtAccName);
            tMoney = (TextView) itemView.findViewById(R.id.txtAccMoney);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(HomeFragment.check == true){
                        Intent intent = new Intent(mcontext, IncomeActivity.class);
                        intent.putExtra("key", key);
                        //intent.putExtra("id", tId.getText().toString());
                        intent.putExtra("name", tName.getText().toString());
                        intent.putExtra("money", tMoney.getText().toString());
                        mcontext.startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(mcontext, TransactionActivity.class);
                        intent.putExtra("key", key);
                        //intent.putExtra("id", tId.getText().toString());
                        intent.putExtra("name", tName.getText().toString());
                        intent.putExtra("money", tMoney.getText().toString());
                        mcontext.startActivity(intent);
                    }

                }
            });

        }
        public void bind(Account account, String key){
            tName.setText(account.getName());
            tMoney.setText(account.getMoney());
            System.out.println(account.getName());
            System.out.println(account.getMoney());
            this.key = key;
        }

    }

    class AccountAdapter extends RecyclerView.Adapter<AccountItemView> {
        private List<Account> accList;
        private List<String> keys;

        public AccountAdapter(List<Account> accList, List<String> keys) {
            this.accList = accList;
            this.keys = keys;
        }
        @Override
        public AccountItemView onCreateViewHolder(ViewGroup parent, int viewType) {
            return new AccountItemView(parent);
        }

        @Override
        public void onBindViewHolder(AccountItemView holder, int position) {
            holder.bind(accList.get(position), keys.get(position));
        }

        @Override
        public int getItemCount() {
            if(accList != null){
                return accList.size();
            }
            return 0;
        }
    }
}
