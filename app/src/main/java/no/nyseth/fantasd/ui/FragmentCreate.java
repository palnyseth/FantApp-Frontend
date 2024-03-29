package no.nyseth.fantasd.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import no.nyseth.fantasd.R;
import no.nyseth.fantasd.network.FantApi;
import no.nyseth.fantasd.shopnuser.User;
import no.nyseth.fantasd.ui.home.HomeFragment;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentCreate extends Fragment {
    //Fields
    TextView emlV;
    TextView uidV;
    TextView pwdV;
    private User user = new User();

    public FragmentCreate() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create, container,false);

        uidV = view.findViewById(R.id.login_uid);
        pwdV = view.findViewById(R.id.login_pwd);
        emlV = view.findViewById(R.id.login_eml);
        Button submitV = (Button) view.findViewById(R.id.login_submit);

        submitV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.login_submit:
                        createUser();
                        break;
                }
            }
        });

        return view;
    }

    public void createUser() {
        String uid = uidV.getText().toString();
        String pwd = pwdV.getText().toString();
        String eml = emlV.getText().toString();

        if (eml.isEmpty()) {
            emlV.setError("intet epost fylt inn");
            emlV.requestFocus();
        }
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


        Call<ResponseBody> call = FantApi.getSINGLETON().getApi().createUser(uid,pwd,eml);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Registrering ok", Toast.LENGTH_SHORT).show();
                    System.out.println(response.body().toString());

                    Navigation.findNavController(getView()).popBackStack();
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