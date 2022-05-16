package com.upclicks.ffc.commons;

public class Keys {
    public static String BASE_URL = "https://api-v1.freshfruitskw.com/";
    public static String API_URL = BASE_URL + "api/";

    public static final long SPLASH_DISPLAY_LENGTH = 2000;
    public static final Integer BIO_MAX_LENGTH = 72;

    //Media types
    public static class PublicKeys {
        public static int CHAT_SENDER_KEY = -1;
    }
    //Media types
    public static class MediaTypes {
        public static int IMAGE = 0;
        public static int VIDEO = 1;
        public static int DOC = 2;
        public static int EXEL = 3;
        public static int PDF = 4;
    }

    public static class Intent_Constants {
        public static String START_UP_VIDEO_ID = "start_up_video_id";
        public static String PROFILE_VIDEO_ID = "profile_video_id";
        public static String RECEIVER_ID = "receiver_id";
        public static String CONVERSATION_ID = "conversation_id";
        public static String VENDOR_PROFILE_ID = "vendor_profile_id";
        public static String MEMBER_PROFILE_ID = "member_profile_id";
        public static String POST_ID = "post_id";
        public static String POST_CONTENT = "post_content_view";
        public static String CHAT_USER_ID = "chat_user_id";
        public static String EDITED_POST = "edited_post";
        public static String VIDEO_ID = "video_id";
        public static String TARGET = "Target";
        public static String SERVICE_CATEGORY = "Service_category";
        public static String CATEGORY_NAME = "category_name";
        public static String CATEGORY_ID = "category_id";
        public static String PROVIDER_ID = "provider_id";
        public static String VENDOR_SERVICE = "vendor_service";
        public static String PRODUCT_MESSAGE = "product_message";
        public static String PRODUCT_ID = "product_id";
        public static String ORDER_ID = "order_id";
        public static String GROUP_ID = "group_id";
        public static String NOTIFY_ID = "notify_id";
        public static String OFFER_ID = "offer_id";
        public static String VIDEO_URL = "video_url";
        public static String PRODUCT_DETAILS = "product_details";
        public static String PRODUCT_REVIEWS_COUNT = "product_reviews_count";
        public static String TITLE = "title";
        public static String CHECKOUT = "checkout";
        public static String PRODUCT = "product";
        public static int PICK_FROM_GALLERY = 10048;
        public static String MEMBER_REQUEST = "memberRequest";
        public static String MEMBER_RESPONSE = "memberResponse";
        public static String FILTER = "filter";
        public static int FILTER_CODE = 1;
    }


    public static class FileType {
        public static int Image = 0;
        public static int Video = 1;
        public static int Doc = 2;
        public static int Exel = 3;
        public static int PDF = 4;
    }

    public static class MessageType {
        public static int Normal = 0;
        public static int ShareLocation = 1;
        public static int ShareProduct = 2;
        public static int ShareExternalLink = 3;
    }

    public static class MainScreens {
        final public static int HOME = 0;
        final public static int VIDEOS = 1;
        final public static int CHAT = 2;
        final public static int COMMUNITY = 3;
        final public static int SERVICES = 4;
        final public static int PROFILE = 5;
    }


    public static class FcmNotificationsTypes {
        public final static String OrderRequested = "10";
        public final static String OrderConfirmed = "20";
        public final static String OrderCancelled = "30";
        public final static String OrderRefused = "40";
        public final static String OrderFinished = "50";
        public final static String Public_Notification = "500";
        public final static String LOGOUT_NOTIFY = "1000";
    }

}