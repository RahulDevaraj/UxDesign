package com.example.fragdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.fragdemo.databinding.LayoutColoritemBinding;

import java.util.List;

public class ColorSpecAdapter extends BaseAdapter {
    List<ColorSpec> colorList;
    LayoutColoritemBinding binding;

    public ColorSpecAdapter(List<ColorSpec> colorList) {
        this.colorList = colorList;
    }

    @Override
    public int getCount() {
        return colorList.size();
    }

    @Override
    public Object getItem(int i) {
        return colorList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            binding = LayoutColoritemBinding.inflate(
                    LayoutInflater.from(viewGroup.getContext()),
                    viewGroup,false);
        }
        binding.textViewColorItem.setText(colorList.get(i).getColorDesc());
        binding.textViewColorItem.setTextColor(
                colorList.get(i).getColorVal());
        return binding.getRoot();
    }
}
