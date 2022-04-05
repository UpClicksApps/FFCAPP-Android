package com.upclicks.ffc.ui.components;

import androidx.fragment.app.FragmentTransaction;
import static com.upclicks.ffc.commons.keys.HOME;
import static com.upclicks.ffc.commons.keys.MESSAGES;
import static com.upclicks.ffc.commons.keys.OFFERS;
import static com.upclicks.ffc.commons.keys.PROFILE;

public class PagesController {
    public static int switchToPage(int page, FragmentTransaction fragmentTransaction) {
        FragmentTransaction transaction = fragmentTransaction;
        int pageIndex = 0;
        switch (page) {
            case HOME: {
//                transaction.replace(R.id.content, new HomeFragment());
                pageIndex = HOME;
                break;
            }

            case MESSAGES: {
//                transaction.replace(R.id.content, new NotificationsFragment());
                pageIndex = MESSAGES;
                break;
            }

            case OFFERS: {
//                transaction.replace(R.id.content, new MyAdsFragment());
                pageIndex = OFFERS;
                break;
            }

            case PROFILE: {
//                transaction.replace(R.id.content, new ProfileFragment());
                pageIndex = PROFILE;
                break;
            }


        }
        transaction.commit();
        return pageIndex;
    }

}
