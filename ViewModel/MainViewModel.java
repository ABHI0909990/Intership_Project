package com.example.intership_project.ViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.intership_project.Model.SliderModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private MutableLiveData<List<SliderModel>> slider = new MutableLiveData<>();

    public LiveData<List<SliderModel>> getSlider(){
        return slider;
    }

    public void loadSlider(){
        DatabaseReference ref = firebaseDatabase.getReference("Banner");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<SliderModel> lists = new ArrayList<>();
                for(DataSnapshot childSnapshot : snapshot.getChildren()){
                    SliderModel list = childSnapshot.getValue(SliderModel.class);
                    if(list != null){
                        lists.add(list);
                    }
                    slider.setValue(lists);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
