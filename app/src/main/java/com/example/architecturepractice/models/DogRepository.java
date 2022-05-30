package com.example.architecturepractice.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.architecturepractice.services.ApiClient;
import com.example.architecturepractice.services.DogService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogRepository {
    //create service instance
    private DogService dogService;

    public MutableLiveData<DogRandomResponse> getDogLiveData() {
        return dogLiveData;
    }

    private MutableLiveData<DogRandomResponse> dogLiveData = new MutableLiveData<>();

    //constructor
    //ketika dipanggil akan initialize service nya
    public DogRepository(){
        dogService = ApiClient.getRetrofitInstance().create(DogService.class);
    }

    //service nya dipake untuk memanggil dan assign fetchDog
    public LiveData<DogRandomResponse> getRandomDogData(){
        dogService.fetchRandomDog().enqueue(new Callback<DogRandomResponse>() {
            @Override
            public void onResponse(Call<DogRandomResponse> call, Response<DogRandomResponse> response) {
                //klo sukses livedata nya langsung diset
                dogLiveData.setValue(response.body());

            }

            @Override
            public void onFailure(Call<DogRandomResponse> call, Throwable t) {
                dogLiveData.setValue(null);

            }
        });

        return dogLiveData;

    }
}
