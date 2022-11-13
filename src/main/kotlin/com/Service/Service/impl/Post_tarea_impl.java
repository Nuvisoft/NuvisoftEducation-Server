package com.Service.Service.impl;

import com.Firebase.firebase.FirebaseInitializer;
import com.Service.Service.Post_tarea_Service;
import com.dto.dto.dto_tarea;
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
public class Post_tarea_impl implements Post_tarea_Service {
    @Autowired
    private FirebaseInitializer firebase;

    @Override
    public List<dto_tarea> list() {
        List<dto_tarea> response = new ArrayList<>();
        dto_tarea post;

        ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();
        try {
            for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
                post = doc.toObject(dto_tarea.class);
                post.setId_tarea(doc.getId());
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
    public Boolean add(dto_tarea post) {
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
    public Boolean edit(String id, dto_tarea post) {
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
        return firebase.getFirestore().collection("tarea");
    }

    private Map<String, Object> getDocData(dto_tarea post) {
        Map<String, Object> docData = new HashMap<>();
        docData.put("estado", post.getEstado());
        docData.put("profesor", post.getProfesor());
        docData.put("titulo", post.getTitulo());
        docData.put("trabajo", post.getTrabajo());


        return docData;
    }
}




