package com.lombok.iak.project_01networking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lombok.iak.project_01networking.api.Repository;
import com.lombok.iak.project_01networking.model.Job;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListJobActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_job);
        final JobAdapter jobAdapter = new JobAdapter();
        final RecyclerView recyclerView = findViewById(R.id.recycleview);
        recyclerView.setAdapter(jobAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListJobActivity.this,LinearLayoutManager.VERTICAL,false));
        Repository repo = new Repository();
        repo.getAllJob("Android", new Callback<List<Job>>() {

            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                List<Job> semuajob = response.body();
                jobAdapter.setJob(semuajob);
                jobAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {

                Toast.makeText(ListJobActivity.this, "Terjadi kesalahan!", Toast.LENGTH_LONG).show();
            }
        });

    }

    class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {
        List<Job> data = new ArrayList<>();

        public void setJob(List<Job> semuaJOb) {
            data = semuaJOb;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(
                    LayoutInflater.from(ListJobActivity.this).inflate(R.layout.item_view,parent,false)
            );
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            ImageView logo;
            TextView judul, perusahaan;

            public ViewHolder(View itemView) {
                super(itemView);
                logo = itemView.findViewById(R.id.logo);
                judul = itemView.findViewById(R.id.judul);
                perusahaan = itemView.findViewById(R.id.perusahaan);

            }

            public void setText(final Job job) {
                Glide.with(itemView.getContext()).load(job.getCompanyLogo()).into(logo);
                judul.setText(job.getTitle());
                perusahaan.setText(job.getCompany());
                job.getId();
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(ListJobActivity.this,MainActivity.class);
                        intent.putExtra("id",job.getId());
                        startActivity(intent);
                    }
                });
            }
        }
    }
}
