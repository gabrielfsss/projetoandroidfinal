package com.gabriel.projetofinalandroid.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gabriel.projetofinalandroid.R;
import com.gabriel.projetofinalandroid.Usuario;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.xwray.groupie.GroupAdapter;

public class NotificationsFragment extends Fragment {

    private EditText txtMessageChat;
    private FloatingActionButton btnSendMessage;
    private Usuario me;
    private GroupAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        txtMessageChat = root.findViewById(R.id.txtMessageChat);
        btnSendMessage = root.findViewById(R.id.btnSendChat);
        RecyclerView rcView = root.findViewById(R.id.rcView);

        adapter = new GroupAdapter();
        rcView.setLayoutManager(new LinearLayoutManager(getContext()));
        rcView.setAdapter(adapter);

        FirebaseFirestore.getInstance().collection("/usuarios")
                .document(FirebaseAuth.getInstance().getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Usuario a = documentSnapshot.toObject(Usuario.class);
                        fetchMessages();
                    }
                });

        return root;
    }
}