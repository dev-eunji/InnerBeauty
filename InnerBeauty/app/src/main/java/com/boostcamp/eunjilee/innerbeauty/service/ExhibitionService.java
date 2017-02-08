package com.boostcamp.eunjilee.innerbeauty.service;

import com.boostcamp.eunjilee.innerbeauty.model.ExhibitionModel;
import com.boostcamp.eunjilee.innerbeauty.model.PlayModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by eunjilee on 06/02/2017.
 */
public interface ExhibitionService {
    /*Exhibition*/
    @GET("getExhibition/{exhibitionId}")
    Call<ExhibitionModel>getExhibition(@Path("exhibitionId") int playId);

    @GET("getExhibitionList/{page}")
    Call<List<ExhibitionModel>> getExhibitionList(@Path("page")int page);

    public interface getExhibitionCallback {
        void success(ExhibitionModel exhibitionModel);
        void error(Throwable throwable);
    }
    public interface getExhibitionListCallback {
        void success(List<ExhibitionModel> exhibitionModelList);
        void error(Throwable throwable);
    }
}
