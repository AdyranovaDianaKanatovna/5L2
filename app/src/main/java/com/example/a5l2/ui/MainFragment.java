package com.example.a5l2.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a5l2.App;
import com.example.a5l2.R;
import com.example.a5l2.databinding.FragmentMainBinding;
import com.example.a5l2.network.LoveModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {
    FragmentMainBinding binding;
    private final String HOST = "love-calculator.p.rapidapi.com";
    private final String KEY = "7f31bf3e6fmshc9fe327e036f21dp1d7268jsnc447467dd4ff";
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = NavHostFragment.findNavController(this);
       initClick();
    }

    private void initClick() {
        binding.resultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataFromLoveApi();
            }
        });
    }


    private void getDataFromLoveApi() {
        String firstName = binding.firstNameEdit.getText().toString();
        String secondName = binding.secondNameEdit.getText().toString();
        App.api.loveCalculate(firstName, secondName, HOST, KEY).enqueue(new Callback<LoveModel>() {
            @Override
            public void onResponse(Call<LoveModel> call, Response<LoveModel> response) {
                if (response.isSuccessful()) {
                    Bundle result = new Bundle();
                    assert response.body() != null;
                    String fName = response.body().firstName;
                    String sName = response.body().secondName;
                    String percentage = response.body().percentage;
                    String resultNum = response.body().result;
                    result.putString("fName", fName);
                    result.putString("sName", sName);
                    result.putString("percentage", percentage);
                    result.putString("resultNum", resultNum);
                    navController.navigate(R.id.result_fragment,result);
                }
            }

            @Override
            public void onFailure(Call<LoveModel> call, Throwable t) {
                Toast.makeText(requireActivity(), "Error:" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}