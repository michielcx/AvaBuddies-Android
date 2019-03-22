package com.projectsoa.avabuddies.screens.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.projectsoa.avabuddies.R;
import com.projectsoa.avabuddies.core.base.BaseActivity;
import com.projectsoa.avabuddies.core.base.BaseFragment;
import com.projectsoa.avabuddies.data.repositories.LoginRepository;
import com.projectsoa.avabuddies.screens.login.LoginActivity;
import com.projectsoa.avabuddies.screens.main.profile.ProfileFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import dagger.multibindings.IntKey;

public class MainActivity extends BaseActivity {

    @Inject
    protected LoginRepository loginRepository;

    protected MainViewModel viewModel;

    @BindView(R.id.frame_container)
    protected FrameLayout frameLayout;

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel(MainViewModel.class);
        if(!loginRepository.isLoggedIn()){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        loadFragment(new ProfileFragment());
    }

    public void loadFragment(BaseFragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .commit();
        }
    }

    public void onClickProfile(MenuItem item) {
        loadFragment(new ProfileFragment());
    }
}