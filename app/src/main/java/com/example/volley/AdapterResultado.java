package com.example.volley;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterResultado extends RecyclerView.Adapter<AdapterResultado.ResultadoHolder> {
    private List<Resultado> Resultados;

    public AdapterResultado(List<Resultado> resultados) {
        Resultados = resultados;
    }

    @NonNull
    @Override
    public AdapterResultado.ResultadoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_resultados_row, parent, false);

        return new ResultadoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterResultado.ResultadoHolder holder, int position) {
        holder.addData(Resultados.get(position));
    }

    @Override
    public int getItemCount() {
        return Resultados.size();
    }

    public class ResultadoHolder extends RecyclerView.ViewHolder {
        private TextView tvUserId, tvId, tvTitle, tvBody;
        public ResultadoHolder(@NonNull View itemView) {
            super(itemView);
            tvUserId = itemView.findViewById(R.id.userId);
            tvId = itemView.findViewById(R.id.id);
            tvTitle = itemView.findViewById(R.id.title);
            tvBody = itemView.findViewById(R.id.body);
        }

        public void addData(Resultado resultado) {
            tvUserId.setText("UserId: " + String.valueOf(resultado.getUserId()));
            tvId.setText("Id:" + String.valueOf(resultado.getId()));
            tvTitle.setText("Title:" + resultado.getTitle());
            tvBody.setText("Body:" + resultado.getBody());
        }
    }

}
