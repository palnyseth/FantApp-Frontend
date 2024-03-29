package no.nyseth.fantasd.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import no.nyseth.fantasd.R;
import no.nyseth.fantasd.network.ApiLinks;
import no.nyseth.fantasd.network.FantApi;
import no.nyseth.fantasd.shopnuser.LoggedInUser;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentLogin extends Fragment {
    //Fields
    TextView uidV;
    TextView pwdV;

    private String token;
    LoggedInUser loggedInUser = new LoggedInUser();

    public FragmentLogin() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container,false);

        uidV = view.findViewById(R.id.login_uid);
        pwdV = view.findViewById(R.id.login_pwd);
        Button submitV = (Button) view.findViewById(R.id.login_submit);
        Button testKnapp = (Button) view.findViewById(R.id.testknapp);

        submitV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getActivity();
                RequestQueue queue = Volley.newRequestQueue(context);
                String url = "http://192.168.0.149:8080/FantProsjekt/api/auth/login" + "?uid=" + uidV.getText() + "&pwd=" + pwdV.getText();

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    SharedPreferences preferences = getActivity().getSharedPreferences("MyPref",0);
                    SharedPreferences.Editor editor = preferences.edit();

                    LoggedInUser.getInstance().setJwt(response);
                    LoggedInUser.getInstance().setLoggedIn(true);

                    System.out.println("Pog?");
                }, error -> {
                    System.out.println("Shit very fuck");
                });
                queue.add(stringRequest);

                /*switch (view.getId()) {
                    case R.id.login_submit:
                        loginUser();
                        break;
                }*/
            }
        });

        testKnapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testpiss();
            }
        });


        return view;
    }


    public void testpiss2() {
        System.out.println("PROBABLY RETARDED: " + LoggedInUser.getInstance().getJwt());
    }

    public void testpiss() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.149:8080/FantProsjekt/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<ResponseBody> call = FantApi.getSINGLETON().getApi().currentUser("Bearer " + LoggedInUser.getInstance().getJwt());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }



    public void loginUser() {
        String uid = uidV.getText().toString();
        String pwd = pwdV.getText().toString();

        if (uid.isEmpty()) {
            uidV.setError("Intet brukernavn fylt inn");
            uidV.requestFocus();
            return;
        }
        if (pwd.isEmpty()) {
            uidV.setError("Intet passord fylt inn");
            uidV.requestFocus();
            return;
        }



        Call<ResponseBody> call = FantApi.getSINGLETON().getApi().userLogin(uid,pwd);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Ya logged inn!", Toast.LENGTH_LONG).show();
                    Navigation.findNavController(getView()).popBackStack();


                    token = response.headers().get("Authorization");
                    //System.out.println("TOKEN: " + token);
                    //loggedInUser.setUserToken(token);
                }
                else {
                    Toast.makeText(getActivity(), "Noe gikk galt", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.clear();
        super.onPrepareOptionsMenu(menu);
    }
}
