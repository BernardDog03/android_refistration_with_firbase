package com.example.project_akhir;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerView_Config2 {
    private Context mContext;
    private RequestsAdapter mRequestsAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Requests>
            requests, List<String> keys)
    {
        mContext = context;
        mRequestsAdapter = new RequestsAdapter(requests,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mRequestsAdapter);
    }
    class RequestItemView extends RecyclerView.ViewHolder {
        private TextView mfn, mln, mgender, mtob, mdob, madd, mtel, memail, mlocate, mday, mtime, mnote, mmajor;
        private String key;
        public RequestItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.requests_list_item,
                    parent, false));
            mfn = (TextView) itemView.findViewById(R.id.txfn);
            mln = (TextView) itemView.findViewById(R.id.txln);
            mgender = (TextView) itemView.findViewById(R.id.txgender);
            mtob = (TextView) itemView.findViewById(R.id.txtob);
            mdob = (TextView) itemView.findViewById(R.id.txdob);
            madd = (TextView) itemView.findViewById(R.id.txadd);
            mtel = (TextView) itemView.findViewById(R.id.txtel);
            memail = (TextView) itemView.findViewById(R.id.txemail);
            mlocate = (TextView) itemView.findViewById(R.id.txlocate);
            mday = (TextView) itemView.findViewById(R.id.txday);
            mtime = (TextView) itemView.findViewById(R.id.txtime);
            mnote = (TextView) itemView.findViewById(R.id.txnote);
            mmajor = (TextView) itemView.findViewById(R.id.txmajor);
        }
        public void bind(Requests requests, final String key)
        {
            mfn.setText(requests.getFirstname());
            mln.setText(requests.getLastname());
            mgender.setText(requests.getGender());
            mtob.setText(requests.getTempatlahir());
            mdob.setText(requests.getDateOB());
            madd.setText(requests.getAddress());
            mtel.setText(requests.getPhoneno());
            memail.setText(requests.getEmail());
            mlocate.setText(requests.getLocate());
            mday.setText(requests.getDay());
            mtime.setText(requests.getTime());
            mnote.setText(requests.getNote());
            mmajor.setText(requests.getMajor());
            this.key = key;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,RequestsDetailsActivity2.class);
                    intent.putExtra("key1",key);
                    intent.putExtra("vfn1",mfn.getText().toString());
                    intent.putExtra("vln1",mln.getText().toString());
                    intent.putExtra("vgender1",mgender.getText().toString());
                    intent.putExtra("vtob1",mtob.getText().toString());
                    intent.putExtra("vdob1",mdob.getText().toString());
                    intent.putExtra("vadd1",madd.getText().toString());
                    intent.putExtra("vtel1",mtel.getText().toString());
                    intent.putExtra("vemail1",memail.getText().toString());
                    intent.putExtra("vlocate1",mlocate.getText().toString());
                    intent.putExtra("vday1",mday.getText().toString());
                    intent.putExtra("vtime1",mtime.getText().toString());
                    intent.putExtra("vnote1",mnote.getText().toString());
                    intent.putExtra("vmajor1",mmajor.getText().toString());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    class RequestsAdapter extends RecyclerView.Adapter<RequestItemView>
    {
        private List<Requests> mRequestList;
        private List<String> mKeys;
        @NonNull
        @Override
        public RequestItemView onCreateViewHolder(@NonNull ViewGroup parent, int
                viewType) {
            return new RequestItemView(parent);
        }
        @Override
        public void onBindViewHolder(@NonNull RequestItemView holder, int position) {
            holder.bind(mRequestList.get(position),mKeys.get(position));
        }
        @Override
        public int getItemCount() {
            return mRequestList.size();
        }
        public RequestsAdapter(List<Requests> mRequestList, List<String> mKeys) {
            this.mRequestList = mRequestList;
            this.mKeys = mKeys;
        }
    }
}
