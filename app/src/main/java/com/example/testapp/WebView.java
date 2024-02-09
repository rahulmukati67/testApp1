package com.example.testapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

public class WebView extends AppCompatActivity {
    private TabLayout tabLayout1;
    private ViewPager viewPager;
    private WebViewFragment webViewFragment;
    private Tab2 tab2;
    private Tab3 tab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        tabLayout1 = findViewById(R.id.TabLayout1);
        viewPager = findViewById(R.id.viewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        webViewFragment = new WebViewFragment();
        tab2 = new Tab2();
        tab3 = new Tab3();
        adapter.addFragment(webViewFragment, "webViewTab");
        adapter.addFragment(tab2, "Tab2");
        adapter.addFragment(tab3, "Tab3");


        viewPager.setAdapter(adapter);

        tabLayout1.setupWithViewPager(viewPager);
        tabLayout1.setTabTextColors(getResources().getColorStateList(R.color.tab_text_color_selector));

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout1));
    }

    private static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }
}
