package com.summer.bingyan.gitpopular.Trending;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.summer.bingyan.gitpopular.R;
import com.summer.bingyan.gitpopular.adapters.TrendingAdapter;
import com.summer.bingyan.gitpopular.base_and_contract.Contract;
import com.summer.bingyan.gitpopular.setting.SettingFragment;

import java.util.ArrayList;
import java.util.List;

public class TrendingFragment extends Fragment implements Contract.View{
    private TrendingAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Contract.Prsenter trendingPresenter;
    private List<Trend>trends=new ArrayList<>();
    private String url="https://github.com/trending?since=daily";
    private String suburl;
    private String suburl2,suburl3;
    private final static String TAG="luchixiang";
    private SwipeRefreshLayout swipeRefreshLayout;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    public TrendingFragment() {
        // Required empty public constructor
    }
    public static TrendingFragment newInstance() {
        TrendingFragment fragment = new TrendingFragment();
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
        View view=themeinflater.inflate(R.layout.fragment_trending, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.show_trending);
        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_green_light,
                android.R.color.holo_blue_bright, android.R.color.holo_orange_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        trendingPresenter.refresh(url);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },1000);
            }
        });
        layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        Context context=getActivity();
        toolbar=(Toolbar)view.findViewById(R.id.trend_toolbar);
        toolbar.setTitle("Trending");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        if (SettingFragment.issetted==0)
        {
        toolbar.inflateMenu(R.menu.trend_menu);
        }
        if (SettingFragment.issetted==1)
        {
            getActivity().supportInvalidateOptionsMenu();
        }
        TrendingPresenter Presenter=new TrendingPresenter(context,this);
        adapter = new TrendingAdapter(context,recyclerView,trends);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        progressBar=(ProgressBar)view.findViewById(R.id.progressbar);
        progressBar.setIndeterminateDrawable(ContextCompat.getDrawable(context,R.drawable.progress));
        Log.d(TAG, "onCreateView: "+"toolbarinflate");
        trendingPresenter.start("https://github.com/trending");
        getActivity().invalidateOptionsMenu();
        return view;
    }
    public void setPresenter(Contract.Prsenter trendingPresenter)
    {
        this.trendingPresenter=trendingPresenter;
    }
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setHasOptionsMenu(true);
    }
    public void ListChanged(List list){
        adapter.trendListChanged(list);
        adapter.notifyDataSetChanged();
        Log.d(TAG, "dataset:"+"true");
    }
    public void showDialog()
    {
        progressBar.setVisibility(View.VISIBLE);
    }
    public void dismissDialog()
    {
        progressBar.setVisibility(View.GONE);
    }
    public void showToast()
    {
        Toast.makeText(getActivity(),"网络连接失败",Toast.LENGTH_LONG).show();
    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d(TAG, "onCreateOptionsMenu: ");
        //menu.clear();
        Menu time=menu.addSubMenu("time");
        Menu language=menu.addSubMenu("language");
        time.add(0,R.id.today,0,"day");
        time.add(0,R.id.week,0,"week");
        time.add(0,R.id.month,0,"month");
        language.add(1,R.id.all,0,"all");
        Log.d(TAG, "onPrepareOptionsMenu: "+SettingFragment.chosen_trend[3]);
        if (!SettingFragment.chosen_trend[0].equals(""))
        {
            language.add(1,R.id.java,0,"java");
        }
        if (!SettingFragment.chosen_trend[1].equals(""))
        {
            language.add(1,R.id.css,0,"css");
        }
        if (!SettingFragment.chosen_trend[2].equals(""))
        {
            language.add(1,R.id.cjiajia,0,"c++");
        }
        if (!SettingFragment.chosen_trend[3].equals(""))
        {
            language.add(1,R.id.javascript,0,"javascript");
        }
        if (!SettingFragment.chosen_trend[4].equals(""))
        {
            language.add(1,R.id.c,0,"c#");
        }
        super.onCreateOptionsMenu(menu, inflater);
    }
    /*public void onPrepareOptionsMenu(Menu menu)
    {
        super.onPrepareOptionsMenu(menu);
        menu.clear();
        Menu time=menu.addSubMenu("time");
        Menu language=menu.addSubMenu("language");
        time.add(0,R.id.today,0,"day");
        time.add(0,R.id.week,0,"week");
        time.add(0,R.id.month,0,"month");
        language.add(1,R.id.all,0,"all");
        Log.d(TAG, "onPrepareOptionsMenu: "+SettingFragment.chosen_trend[3]);
        if (!SettingFragment.chosen_trend[0].equals(""))
        {
            language.add(1,R.id.java,0,"java");
        }
        if (!SettingFragment.chosen_trend[1].equals(""))
        {
            language.add(1,R.id.css,0,"css");
        }
        if (!SettingFragment.chosen_trend[2].equals(""))
        {
            language.add(1,R.id.cjiajia,0,"c++");
        }
        if (!SettingFragment.chosen_trend[3].equals(""))
        {
            language.add(1,R.id.javascript,0,"javascript");
        }
        if (!SettingFragment.chosen_trend[4].equals(""))
        {
            language.add(1,R.id.c,0,"c#");
        }
    }*/
    public boolean onOptionsItemSelected(MenuItem item) {
        int i=url.indexOf("?");
        suburl=url.substring(0,i+7);
        int j=url.indexOf(".com/");
        suburl2=url.substring(0,j+13);
        suburl3=url.substring(i,url.length());
        switch (item.getItemId()) {
            case R.id.week:
                url=suburl+"weekly";
                trendingPresenter.start(url);
                Toast.makeText(getActivity(),"change to week",Toast.LENGTH_LONG).show();
                return true;
            case R.id.today:
                Toast.makeText(getActivity(),"change to today",Toast.LENGTH_LONG).show();
                url=suburl+"daily";
                Log.d(TAG, "onOptionsItemSelected: "+url);
                trendingPresenter.start(url);
                return true;
            case R.id.month:
                Toast.makeText(getActivity(),"change to month",Toast.LENGTH_LONG).show();
                url=suburl+"monthly";
                trendingPresenter.start(url);
                return true;
            case R.id.all:
                url=suburl2+suburl3;
                    Toast.makeText(getActivity(),"change to all",Toast.LENGTH_LONG).show();
                    trendingPresenter.start(url);
                    return true;
            case R.id.java:
                url=suburl2+"/java"+suburl3;
                Toast.makeText(getActivity(),"change to java",Toast.LENGTH_LONG).show();
                trendingPresenter.start(url);
                return true;
            case R.id.c:
                url=suburl2+"/c%23"+suburl3;
                Toast.makeText(getActivity(),"change to c#",Toast.LENGTH_LONG).show();
                trendingPresenter.start(url);
                return true;
            case R.id.cjiajia:
                url=suburl2+"/c++"+suburl3;
                Toast.makeText(getActivity(),"change to c++",Toast.LENGTH_LONG).show();
                trendingPresenter.start(url);
                return true;
            case R.id.javascript:
                url=suburl2+"/javascript"+suburl3;
                Toast.makeText(getActivity(),"change to javascript",Toast.LENGTH_LONG).show();
                trendingPresenter.start(url);
                return true;
            case R.id.css:
                url=suburl2+"/css"+suburl3;
                Toast.makeText(getActivity(),"change to css",Toast.LENGTH_LONG).show();
                trendingPresenter.start(url);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
