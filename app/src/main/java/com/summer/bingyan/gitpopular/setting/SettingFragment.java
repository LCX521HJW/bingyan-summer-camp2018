package com.summer.bingyan.gitpopular.setting;

import android.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.summer.bingyan.gitpopular.R;

public class SettingFragment extends Fragment implements View.OnClickListener
{
private TextView change_theme;
private TextView change_tag_popular;
private TextView change_tag_trend;
public static int theme_selected=1;
public static int issetted=0;
public final String[] choose={"java","css","c++","javascript","c#"};
public final boolean[] trend_isselected={true,true,true,true,true};
public final boolean[] popular_isselected={true,true,true,true,true};
private Toolbar toolbar;
public static  String[] chosen_popular={"java","css","c++","javascript","c#"};
public static  String[] chosen_trend={"java","css","c++","javascript","c#"};
    public SettingFragment() {
    }

    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context contexttheme=new ContextThemeWrapper(getActivity(),R.style.Blue);
        switch (SettingFragment.theme_selected)
        {
            case 0:
                contexttheme=new ContextThemeWrapper(getActivity(),R.style.Red);
                break;
            case 1:
                contexttheme=new ContextThemeWrapper(getActivity(),R.style.Blue);
                break;
            case 2:
                contexttheme=new ContextThemeWrapper(getActivity(),R.style.green);
                break;
            case 3:
                contexttheme=new ContextThemeWrapper(getActivity(),R.style.Purple);
                break;
            case 4:
                contexttheme=new ContextThemeWrapper(getActivity(),R.style.Cyan);
                break;
            case 5:
                contexttheme=new ContextThemeWrapper(getActivity(),R.style.Yellow);
        }
        LayoutInflater themeinflater=inflater.cloneInContext(contexttheme);
        View view=themeinflater.inflate(R.layout.fragment_setting, container, false);
        change_theme=view.findViewById(R.id.change_theme);
        change_tag_popular=view.findViewById(R.id.change_popular_tag);
        change_tag_trend=view.findViewById(R.id.change_trend_tag);
        toolbar=(Toolbar)view.findViewById(R.id.setting_toolbar);
        toolbar.setTitle("Setting");
        //((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        change_theme.setOnClickListener(this);
        change_tag_popular.setOnClickListener(this);
        change_tag_trend.setOnClickListener(this);
        return view;
    }
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.change_theme:
                new AlertDialog.Builder(getActivity()).setTitle("选择主题")
                        .setSingleChoiceItems(new String[]{"red", "blue", "green", "purple", "white", "yellow"},
                                theme_selected, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                      theme_selected=which;
                                      dialog.dismiss();
                                    }}).setNegativeButton("取消",null).show();
                break;
            case R.id.change_trend_tag:
                showDialog();
                issetted=1;

                break;
            case R.id.change_popular_tag:
                showDialog2();
                issetted=1;
                break;
        }
    }
    public void showDialog()
    {
        new AlertDialog.Builder(getActivity()).setTitle("选择要显示的语言")
                .setMultiChoiceItems(choose, trend_isselected, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        trend_isselected[which]=isChecked;
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i=0;i<trend_isselected.length;i++)
                {
                    if (trend_isselected[i])
                    {
                        chosen_trend[i]=choose[i];
                    }
                    else  chosen_trend[i]="";
                }
            }
        }).setNegativeButton("取消",null).show();
    }
    public void showDialog2()
    {
        new AlertDialog.Builder(getActivity()).setTitle("选择要显示的语言")
                .setMultiChoiceItems(choose, popular_isselected, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        popular_isselected[which]=isChecked;
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i=0;i<popular_isselected.length;i++)
                {
                    if (popular_isselected[i])
                    {
                        chosen_popular[i]=choose[i];
                    }
                    else  chosen_popular[i]="";
                }
            }
        }).setNegativeButton("取消",null).show();
    }
}
