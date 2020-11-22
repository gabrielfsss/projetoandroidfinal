package com.gabriel.projetofinalandroid.ui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gabriel.projetofinalandroid.Messages;
import com.gabriel.projetofinalandroid.R;
import com.gabriel.projetofinalandroid.Usuario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

import java.util.List;

public class NotificationsFragment extends Fragment {

    private EditText txtMessageChat;
    private FloatingActionButton btnSendMessage;
    private Usuario me;
    private GroupAdapter adapter;
    private ImageView imgemerg;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        txtMessageChat = root.findViewById(R.id.txtMessageChat);
        btnSendMessage = root.findViewById(R.id.btnSendChat);
        RecyclerView rcView = root.findViewById(R.id.rcView);
        imgemerg = root.findViewById(R.id.imgemerg);

        adapter = new GroupAdapter();
        rcView.setLayoutManager(new LinearLayoutManager(getContext()));
        rcView.setAdapter(adapter);

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

        imgemerg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessageEmerg();
            }
        });

        FirebaseFirestore.getInstance().collection("/usuarios")
                .document(FirebaseAuth.getInstance().getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        me = documentSnapshot.toObject(Usuario.class);
                        loadMessages();
                    }
                });

        return root;
    }

    private void loadMessages() {
        if (me != null) {
            FirebaseFirestore.getInstance().collection("/conversas")
                    .orderBy("timestamp", Query.Direction.ASCENDING)
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                            List<DocumentChange> documentChanges = queryDocumentSnapshots.getDocumentChanges();
                            if (documentChanges != null) {
                                for (DocumentChange doc: documentChanges) {
                                    if (doc.getType() == DocumentChange.Type.ADDED) {
                                        Messages message = doc.getDocument().toObject(Messages.class);
                                        adapter.add(new MessageItem(message));
                                    }
                                }
                            }
                        }
                    });
        }
    }

    private void sendMessage() {
        String text = txtMessageChat.getText().toString();

        txtMessageChat.setText(null);

        final String fromId = FirebaseAuth.getInstance().getUid();
        long timestamp = System.currentTimeMillis();

        final Messages message = new Messages();
        message.setFromId(fromId);
        message.setToId("aviao");
        message.setTimestamp(timestamp);
        message.setText(text);
        message.setNameUser(me.getNome());
        message.setAviso(false);

        if (!message.getText().isEmpty()) {
            FirebaseFirestore.getInstance().collection("/conversas")
                    .add(message)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("Teste", documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("Teste", e.getMessage(), e);
                        }
                    });

        }
    }

    private class MessageItem extends Item<ViewHolder> {

        private final Messages message;

        private MessageItem(Messages message) {
            this.message = message;
        }

        @Override
        public void bind(@NonNull ViewHolder viewHolder, int position) {
            TextView txtMsg = viewHolder.itemView.findViewById(R.id.txtMessageChatList);
            TextView txtName = viewHolder.itemView.findViewById(R.id.txtNameChat);

            txtName.setText(message.getNameUser());
            txtMsg.setText(message.getText());
        }

        @Override
        public int getLayout() {
            if(message.isAviso() == false) {
                if (message.getFromId().equals(FirebaseAuth.getInstance().getUid()) == true) {
                    return R.layout.item_chat_me;
                } else{
                    return R.layout.item_chat_to;
                }
            }
            else{
                return R.layout.activity_chat_emerg;
            }
        }
    }
    private void sendMessageEmerg() {
        String text = "Preciso de ajuda";

        final String fromId = FirebaseAuth.getInstance().getUid();
        long timestamp = System.currentTimeMillis();

        final Messages message = new Messages();
        message.setFromId(fromId);
        message.setToId("aviao");
        message.setTimestamp(timestamp);
        message.setText(text);
        message.setNameUser(me.getNome());
        message.setAviso(true);

        if (!message.getText().isEmpty()) {
            FirebaseFirestore.getInstance().collection("/conversas")
                    .add(message)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("Teste", documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("Teste", e.getMessage(), e);
                        }
                    });
        }
    }
}