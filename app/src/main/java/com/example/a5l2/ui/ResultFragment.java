package com.example.a5l2.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a5l2.R;
import com.example.a5l2.databinding.FragmentResultBinding;

public class ResultFragment extends Fragment {
    FragmentResultBinding binding;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentResultBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        getResult();
        initClick();

    }
    private void initClick() {
        binding.reBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navController.navigateUp();
            }
        });
    }
    private void getResult() {
        assert getArguments() != null;
        String fName = getArguments().getString("fName");
        String sName = getArguments().getString("sName");
        String result = getArguments().getString("result");
        String percentage = getArguments().getString("percentage");

        binding.firstName.setText(fName);
        binding.secondName.setText(sName);
        if (result.equals("All the best!")) {
            binding.resultText.setText("You are lucky");
        } else if (result.equals("Not a good choice.")) {
            binding.resultText.setText("You did not good choice");
        } else if (result.equals("May be better next time.")) {
            binding.resultText.setText("You should try one more time");
        } else if (result.equals("Congratulations! Good choice")) {
            binding.resultText.setText("Congratulations! good choice!");
        }
        else if (result.equals("Can choose someone better.")){
            binding.resultText.setText("You can choose someone better!");
        }
        else {
            binding.resultText.setText(result);
        }
        binding.percentageText.setText(percentage+" %");
    }
}


