package com.Service.Service.impl;

import com.Firebase.firebase.FirebaseInitializer;

import com.Service.Service.Post_trabajos_Service;

import com.dto.dto.dto_trabajos;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class Post_trabajos_impl implements Post_trabajos_Service {
    @Autowired
    private FirebaseInitializer firebase;

    @Override
    public List<dto_trabajos> list() {
        List<dto_trabajos> response = new ArrayList<>();
        dto_trabajos post;

        ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();
        try {
            for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
                post = doc.toObject(dto_trabajos.class);
                post.setId_trabajos(doc.getId());
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
    public Boolean add(dto_trabajos post) {
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
    public Boolean edit(String id, dto_trabajos post) {
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
        return firebase.getFirestore().collection("trabajos");
    }

    private Map<String, Object> getDocData(dto_trabajos post) {
        Map<String, Object> docData = new HashMap<>();
        docData.put("adeudada", post.getAdeudada());
        docData.put("creacion", post.getCreacion());
        docData.put("descripcion", post.getDescripcion());
        docData.put("estado", post.getEstado());
        docData.put("nombre", post.getNombre());
        docData.put("tiempo", post.getTiempo());
        docData.put("valor", post.getValor());


        return docData;
    }
}


