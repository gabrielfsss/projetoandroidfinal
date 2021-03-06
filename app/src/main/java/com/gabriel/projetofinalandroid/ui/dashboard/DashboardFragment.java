package com.gabriel.projetofinalandroid.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.gabriel.projetofinalandroid.MainActivity;
import com.gabriel.projetofinalandroid.R;
import com.gabriel.projetofinalandroid.telaCadastro;
import com.gabriel.projetofinalandroid.telaaviao;
import com.gabriel.projetofinalandroid.telaempresa;
import com.gabriel.projetofinalandroid.telafilmes;
import com.google.firebase.auth.FirebaseAuth;

import com.gabriel.projetofinalandroid.R;

public class DashboardFragment extends Fragment {

    private Button btnlogout;
    private ImageView imageViewIcAviao, imageViewIcEmpresa, ImageViewIcFilmes;
    private TextView textConfigAviao, textEmpresa, textFilmes;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        Button btn2 = (Button) root.findViewById(R.id.btnlogout);

        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();

                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);

                getActivity().finish();
            }
        });

        ImageView imageViewIcAviao = (ImageView) root.findViewById(R.id.imageViewIcAviao);
        ImageView imageViewIcEmpresa = (ImageView) root.findViewById(R.id.imageViewIcEmpresa);
        ImageView ImageViewIcFilmes = (ImageView) root.findViewById(R.id.ImageViewIcFilmes);

        TextView textConfigAviao = (TextView) root.findViewById(R.id.textConfigAviao);
        TextView textEmpresa = (TextView) root.findViewById(R.id.textEmpresa);
        TextView textFilmes = (TextView) root.findViewById(R.id.textFilmes);

        imageViewIcAviao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), telaaviao.class);
                startActivity(i);
            }
        });

        imageViewIcEmpresa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), telaempresa.class);
                startActivity(i);
            }
        });

        ImageViewIcFilmes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), telafilmes.class);
                startActivity(i);
            }
        });

        textConfigAviao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), telaaviao.class);
                startActivity(i);
            }
        });

        textEmpresa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), telaempresa.class);
                startActivity(i);
            }
        });

        textFilmes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), telafilmes.class);
                startActivity(i);
            }
        });

        return root;
    }
}