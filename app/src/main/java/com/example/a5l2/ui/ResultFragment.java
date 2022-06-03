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

    private void getResult() {
        assert getArguments() != null;
        String first_name = getArguments().getString("first_name");
        String second_name = getArguments().getString("second_name");
        String result = getArguments().getString("result");
        String percentage = getArguments().getString("percentage");
        binding.firstName.setText(first_name);
        binding.secondName.setText(second_name);
        if (result.equals("Everything for good")) {
            binding.resultText.setText("Everything for good!");
        } else if (result.equals("Not good choice")) {
            binding.resultText.setText("Not good choice!");
        } else if (result.equals("Maybe next time will be better")) {
            binding.resultText.setText("Maybe next time will be better");
        } else if (result.equals("Congratulation you have made best choice")) {
            binding.resultText.setText("Congratulation you have made best choice!");
        } else if (result.equals("You can choice someone better!")) {
            binding.resultText.setText("You can choice someone better!");
        } else {
            binding.resultText.setText(result);
        }
        binding.percentageText.setText(percentage + " % ");
    }

    private void initClick() {
        binding.reBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigateUp();
            }
        });
    }
}

