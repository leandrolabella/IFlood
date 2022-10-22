package br.com.devleo.tccapi.services;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import br.com.devleo.tccapi.models.Arduino;

@Service
public class FBService {

    public String save(Arduino arduino) {
        if (arduino.getValueWater() >= 240) {
            Firestore dbfire = FirestoreClient.getFirestore();
            Map<String, Object> values = new HashMap<>();
            values.put("valueRain", arduino.getValueRain());
            values.put("valueWater", arduino.getValueWater());
            values.put("dtUpdate", new Timestamp(System.currentTimeMillis()));
            ApiFuture<WriteResult> collectionFuture = dbfire.collection("arduino").document(arduino.getId())
                    .update(values);
            try {
                return collectionFuture.get().getUpdateTime().toString();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Arduino get(String id) {
        Firestore dbfire = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbfire.collection("arduino").document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document;
        try {
            document = future.get();
            if (document.exists()) {
                Arduino arduino = document.toObject(Arduino.class);
                return arduino;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

}
