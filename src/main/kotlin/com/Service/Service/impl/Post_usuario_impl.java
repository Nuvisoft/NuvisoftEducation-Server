package com.Service.Service.impl;

import com.Firebase.firebase.FirebaseInitializer;
import com.Service.Service.Post_usuario_Service;
import com.dto.dto.dto_usuario;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Post_usuario_impl implements Post_usuario_Service {
    @Autowired
    private FirebaseInitializer firebase;

    @Override
    public List<dto_usuario> list() {
        List<dto_usuario> response = new ArrayList<>();
        dto_usuario post;

        ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();
        try {
            for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
                post = doc.toObject(dto_usuario.class);
                post.setId_usuario(doc.getId());
                response.add(post);
            }
            return response;
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public Boolean delete(String id) {
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(id).delete();
        try {
            if (null != writeResultApiFuture.get()) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean add(dto_usuario post) {
        Map<String, Object> docData = getDocData(post);

        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document().create(docData);

        try {
            if (null != writeResultApiFuture.get()) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean edit(String id, dto_usuario post) {
        Map<String, Object> docData = getDocData(post);
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(id).set(docData);
        try {
            if (null != writeResultApiFuture.get()) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }


    private CollectionReference getCollection() {
        return firebase.getFirestore().collection("usuario");
    }

    private Map<String, Object> getDocData(dto_usuario post) {
        Map<String, Object> docData = new HashMap<>();
        docData.put("apellido", post.getApellido());
        docData.put("cedula", post.getCedula());
        docData.put("contraseña", post.getContraseña());
        docData.put("email", post.getEmail());
        docData.put("nombre", post.getNombre());
        docData.put("rol", post.getRol());


        return docData;
    }
}




