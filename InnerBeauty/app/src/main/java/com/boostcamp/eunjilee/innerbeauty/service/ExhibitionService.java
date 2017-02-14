package com.boostcamp.eunjilee.innerbeauty.service;
import com.boostcamp.eunjilee.innerbeauty.model.ExhibitionModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by eunjilee on 06/02/2017.
 */
public interface ExhibitionService {
    @GET("getExhibition/{exhibitionId}")
    Call<ExhibitionModel>getExhibition(@Path("exhibitionId") int exhibitionId);

    @GET("getExhibitionList/{page}")
    Call<List<ExhibitionModel>> getExhibitionList(@Path("page")int page);

    interface getExhibitionCallback {
        void success(ExhibitionModel exhibitionModel);
        void error(Throwable throwable);
    }
    interface getExhibitionListCallback {
        void success(List<ExhibitionModel> exhibitionModelList);
        void error(Throwable throwable);
    }
}
