package com.example.news50.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.news50.MainActivity;
import com.example.news50.R;

import java.util.Objects;


public class FragmentHome extends Fragment {
    ImageView imageViewTech, imageViewSport, imageViewScience, imageViewEnt, imageViewHealth, imageViewBusiness;
    TextView textViewTech, textViewBusiness, textViewScience, textViewSport, textViewEnt, textViewHealth;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.home_fragment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        imageViewTech = view.findViewById(R.id.imageViewTech);
        imageViewBusiness = view.findViewById(R.id.imageViewBusiness);
        imageViewScience = view.findViewById(R.id.imageViewScience);
        imageViewSport = view.findViewById(R.id.imageViewSport);
        imageViewEnt = view.findViewById(R.id.imageViewEnt);
        imageViewHealth = view.findViewById(R.id.imageViewHealth);

        textViewTech = view.findViewById(R.id.textViewTech);
        textViewBusiness = view.findViewById(R.id.textBusiness);
        textViewScience = view.findViewById(R.id.textViewScience);
        textViewSport = view.findViewById(R.id.textViewSport);
        textViewEnt = view.findViewById(R.id.textViewEnt);
        textViewHealth = view.findViewById(R.id.textViewHealth);


        ImageView[] imageViews = {
                imageViewTech,
                imageViewBusiness,
                imageViewScience,
                imageViewSport,
                imageViewEnt,
                imageViewHealth

        };
        TextView[] textViews = {
                textViewTech,
                textViewBusiness,
                textViewScience,
                textViewSport,
                textViewEnt,
                textViewHealth

        };

        for (int i = 0; i < imageViews.length && i < textViews.length; i++) {
            {
                final int position = i;
                imageViews[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (position) {
                            case 0:
                                ((MainActivity) getActivity()).setCurrentItem(1, false);
                                break;
                            case 1:
                                ((MainActivity) getActivity()).setCurrentItem(2, false);
                                break;
                            case 2:
                                ((MainActivity) getActivity()).setCurrentItem(3, false);
                                break;
                            case 3:
                                ((MainActivity) getActivity()).setCurrentItem(4, false);
                                break;
                            case 4:
                                ((MainActivity) getActivity()).setCurrentItem(5, false);
                                break;
                            case 5:
                                ((MainActivity) getActivity()).setCurrentItem(6, false);
                                break;
                        }
                    }
                });
                textViews[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (position) {
                            case 0:
                                ((MainActivity) getActivity()).setCurrentItem(1, false);
                                break;
                            case 1:
                                ((MainActivity) getActivity()).setCurrentItem(2, false);
                                break;
                            case 2:
                                ((MainActivity) getActivity()).setCurrentItem(3, false);
                                break;
                            case 3:
                                ((MainActivity) getActivity()).setCurrentItem(4, false);
                                break;
                            case 4:
                                ((MainActivity) getActivity()).setCurrentItem(5, false);
                                break;
                            case 5:
                                ((MainActivity) getActivity()).setCurrentItem(6, false);
                                break;
                        }
                    }
                });
            }


        }


    }
}
