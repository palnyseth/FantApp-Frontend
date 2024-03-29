package no.nyseth.fantasd.network;

import java.math.BigDecimal;
import java.util.List;

import no.nyseth.fantasd.shopnuser.Items;
import no.nyseth.fantasd.shopnuser.LoggedInUser;
import no.nyseth.fantasd.shopnuser.User;
import no.nyseth.fantasd.ui.itemlist.ItemListings;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface FantApi2 {

    @FormUrlEncoded
    @POST("auth/create")
    public Call<ResponseBody> createUser(@Field("uid") String username, @Field("pwd") String password, @Field("eml") String email);

    @GET("auth/login")
    public Call<ResponseBody> userLogin(@Query("uid") String username, @Query("pwd") String password);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    //@Multipart
    @POST("shop/additem")
    //public Call<ResponseBody> addItem(@Header("Authorization") String token, @Part("itemTitle") String itemTitle, @Part("itemPrice") String itemPrice, @Part("itemDesc") String itemDesc);
    public Call<Items> addItem(@Header("Authorization") String token, @Body Items item);

    @POST("shop/buyitem")
    public Call<ResponseBody> buyItem(@Header("Authorization") String token, @Query("itemId") String itemId);

    @DELETE("shop/removeitem")
    public Call<ResponseBody> removeItem(@Header("Authorization") String token, @Query("itemId") String itemId);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("auth/currentuser")
    public Call<ResponseBody> currentUser(@Header("Authorization") String token);

    @GET("shop/getitems")
    public Call<List<ItemListings>> getItems();
}