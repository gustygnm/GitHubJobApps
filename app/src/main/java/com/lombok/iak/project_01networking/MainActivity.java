package com.lombok.iak.project_01networking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lombok.iak.project_01networking.api.Repository;
import com.lombok.iak.project_01networking.model.Job;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ImageView logo;
    TextView title, deskripsi, lokasi, pekerjaan1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.img_logo);
        title = findViewById(R.id.title);
        deskripsi = findViewById(R.id.deskripsi);
        lokasi = findViewById(R.id.lokasi);
        pekerjaan1 = findViewById(R.id.pekerjaan);

        /**
         * TODO - 07: Panggil API Client di sini untuk mengambil data dari API.
         * Gunakan ID: 2426ebf6-e641-11e5-8030-461e4e18f3ad
         * atau lainnya.
         */

        String id = getIntent().getStringExtra("id");
        Repository repo = new Repository();
        repo.getJob(id, new Callback<Job>() {
            @Override
            public void onResponse(Call<Job> call, Response<Job> response) {
                Job pekerjaan = response.body();
                Glide.with(MainActivity.this).load(pekerjaan.getCompanyLogo()).into(logo);
                title.setText(pekerjaan.getCompany());
                deskripsi.setText("Description : \n" + Html.fromHtml(pekerjaan.getDescription()));
                pekerjaan1.setText(pekerjaan.getTitle());
                lokasi.setText("Location : " + pekerjaan.getLocation());
            }

            @Override
            public void onFailure(Call<Job> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Terjadi kesalahan!", Toast.LENGTH_LONG).show();
            }
        });

    }
}
