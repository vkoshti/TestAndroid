package com.easylife.lrc.lyricsview;

import android.content.Intent;
import android.os.Bundle;

import com.easylife.lrc.lyricsview.application.DemoApplication;
import com.easylife.lrc.lyricsview.base.AbstractBaseActivity;
import com.easylife.lrc.lyricsview.di.component.ActivityComponent;
import com.easylife.lrc.lyricsview.di.component.DaggerActivityComponent;
import com.easylife.lrc.lyricsview.di.module.ActivityModule;

public class MainActivity extends AbstractBaseActivity {
    private ActivityComponent activityComponent;

    private ActivityComponent getActivityComponent()
    {
        if(activityComponent == null )
        {
            activityComponent = DaggerActivityComponent.
                    builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(DemoApplication.get(this).getApplicationComponent())
                    .build();

        }
        return activityComponent;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewReady(Bundle savedInstance, Intent intent) {
        getActivityComponent().inject(this);
    }
        private class CategoryPageAdapter extends PagerAdapter {
        private final FragmentManager fragmentManager;

        private FragmentTransaction currentTransaction = null;

        private CategoryPageAdapter(FragmentManager fragmentManager) {
            this.fragmentManager = fragmentManager;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            if (currentTransaction == null) {
                currentTransaction = fragmentManager.beginTransaction();
            }

            TabLayout.Tab tab = tabLayout.getTabAt(position);

            Fragment fragment = null;
            if (tab != null) {
                fragment = fragmentManager.findFragmentByTag((String)tab.getTag());
            }
            // Check if the fragment is already initialized
            if (fragment != null) {
                // If it exists, simply attach it in order to show it
                currentTransaction.attach(fragment);
            } else {
                // If not, instantiate and add it to the activity
                fragment = instantiateFragment(tab);
                currentTransaction.add(container.getId(), fragment, (String)tab.getTag());
            }

            return fragment;
        }

        Fragment instantiateFragment(TabLayout.Tab tab) {
            return Fragment.instantiate(MusicBrowserActivity.this, (String)tab.getTag());
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            if (currentTransaction == null) {
                currentTransaction = fragmentManager.beginTransaction();
            }
            // Detach the fragment, because another one is being attached
            currentTransaction.detach((Fragment)object);
        }

        @Override
        public void finishUpdate(@NonNull ViewGroup container) {
            if (currentTransaction != null) {
                currentTransaction.commit();
                currentTransaction = null;
            }
        }

        @Override
        public int getCount() {
            return tabLayout.getTabCount();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return ((Fragment)object).getView() == view;
        }
    }

}
