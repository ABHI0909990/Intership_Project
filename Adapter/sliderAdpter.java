package com.example.intership_project.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.request.RequestOptions;
import com.example.intership_project.Model.SliderModel;
import com.example.intership_project.R;

import java.util.List;

public class sliderAdpter extends RecyclerView.Adapter<sliderAdpter.sliderViewholder> {
    private List<SliderModel> sliderModels;
    private ViewPager2 viewpager2;
    private Context context;

    public sliderAdpter(ViewPager2 viewpager2, List<SliderModel> sliderModels) {
        this.sliderModels = sliderModels;
        this.viewpager2 = viewpager2;
    }

    @NonNull
    @Override
    public sliderAdpter.sliderViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.slider_image_container, parent,false);
        return new sliderViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull sliderAdpter.sliderViewholder holder, int position) {
        holder.setImage(sliderModels.get(position), context);
        if(position == sliderModels.size() - 1){
            viewpager2.post(()->notifyDataSetChanged());
        }
    }

    @Override
    public int getItemCount() {
        return sliderModels.size();
    }

    public class sliderViewholder extends RecyclerView.ViewHolder {
        private ImageView imageV;


        public sliderViewholder(@NonNull View itemView) {
            super(itemView);
            imageV = itemView.findViewById(R.id.imageSlider);
        }

        public void setImage(SliderModel sliderModel, Context context) {
            if (sliderModel.getUrl() != null && !sliderModel.getUrl().isEmpty()) {
                RequestOptions requestOptions = new RequestOptions().transform(new CenterInside());
                Glide.with(context)
                        .load(sliderModel.getUrl())
                        .apply(requestOptions)
                        .into(imageV);
            } else {
                Log.e("SliderAdapter", "Image URL is null or empty");
            }
        }

    }
}
