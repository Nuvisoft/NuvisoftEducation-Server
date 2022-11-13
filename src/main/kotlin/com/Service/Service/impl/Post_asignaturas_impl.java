package com.Service.Service.impl;

import com.Service.Service.Post_asignaturas_Service;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

import com.dto.dto.dto_asignaturas;
import com.Firebase.firebase.FirebaseInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class Post_asignaturas_impl implements Post_asignaturas_Service {
    @Autowired
    private FirebaseInitializer firebase;

    @Override
    public List<dto_asignaturas> list() {
        List<dto_asignaturas> response = new ArrayList<>();
        dto_asignaturas post;

        ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();
        try {
            for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
                post = doc.toObject(dto_asignaturas.class);
                post.setId_asignaturas(doc.getId());
                response.add(post);
            }
            return response;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean add(dto_asignaturas post) {
        Map<String, Object> docData = getDocData(post);

        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document().create(docData);

        try {
            if(null != writeResultApiFuture.get()){
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }


    @Override
    public Boolean edit(String id, dto_asignaturas post) {
        Map<String, Object> docData = getDocData(post);
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(id).set(docData);
        try {
            if(null != writeResultApiFuture.get()){
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean delete(String id) {
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(id).delete();
        try {
            if(null != writeResultApiFuture.get()){
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    private CollectionReference getCollection() {
        return firebase.getFirestore().collection("prueba");
    }

    private Map<String, Object> getDocData(dto_asignaturas post) {
        Map<String, Object> docData = new HashMap<>();
        docData.put("Titulo", post.getTitulo());
        docData.put("descripcion", post.getDescripcion());
        docData.put("estudiante", post.getEstudiante());
        docData.put("profesor", post.getProfesor());
        docData.put("tarea", post.getTarea());


        return docData;
    }
}


