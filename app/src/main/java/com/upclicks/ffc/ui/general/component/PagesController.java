package com.upclicks.ffc.ui.general.component;

import com.upclicks.ffc.R;
import com.upclicks.ffc.commons.Keys;
import com.upclicks.ffc.session.SessionHelper;
import com.upclicks.ffc.ui.main.fragments.CategoriesFragment;
import com.upclicks.ffc.ui.main.fragments.HomeFragment;
import com.upclicks.ffc.ui.main.fragments.ProfileFragment;
import com.upclicks.ffc.ui.main.fragments.SearchFragment;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.fragment.app.FragmentTransaction;

@Singleton
public class PagesController {
    @Inject
    public PagesController() {
    }

    @Inject
    public SessionHelper sessionHelper;

    public int switchToPage(int page, FragmentTransaction fragmentTransaction) {
        FragmentTransaction transaction = fragmentTransaction;
        int pageIndex = 0;
        switch (page) {
            case Keys.NavigationBottom.HOME: {
                transaction.replace(R.id.containerFl, new HomeFragment());
                pageIndex = Keys.NavigationBottom.HOME;
                break;
            }

            case Keys.NavigationBottom.CATEGORIES: {
                transaction.replace(R.id.containerFl, new CategoriesFragment());
                pageIndex = Keys.NavigationBottom.CATEGORIES;
                break;
            }

            case Keys.NavigationBottom.SEARCH: {
                transaction.replace(R.id.containerFl, new SearchFragment());
                pageIndex = Keys.NavigationBottom.SEARCH;
                break;
            }
            case Keys.NavigationBottom.PROFILE: {
                transaction.replace(R.id.containerFl, new ProfileFragment());
                pageIndex = Keys.NavigationBottom.PROFILE;
            }
            break;
        }
        transaction.commit();
        return pageIndex;
    }

}