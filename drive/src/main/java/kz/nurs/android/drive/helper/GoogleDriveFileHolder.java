package kz.nurs.android.drive.helper;


import com.google.api.client.util.DateTime;


public class GoogleDriveFileHolder {

    private String id;
    private String name;
    private DateTime modifiedTime;
    private long size;
    private DateTime createdTime;
    private Boolean starred;


    public DateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(DateTime createdTime) {
        this.createdTime = createdTime;
    }

    public Boolean getStarred() {
        return starred;
    }

    public void setStarred(Boolean starred) {
        this.starred = starred;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(DateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

//    public static String TYPE_AUDIO = "application/vnd.google-apps.audio";
//    public static String TYPE_GOOGLE_DOCS = "application/vnd.google-apps.document";
//    public static String TYPE_GOOGLE_DRAWING = "application/vnd.google-apps.drawing";
//    public static String TYPE_GOOGLE_DRIVE_FILE = "application/vnd.google-apps.file";
//    public static String TYPE_GOOGLE_FORMS = "application/vnd.google-apps.form";
//    public static String TYPE_GOOGLE_FUSION_TABLES = "application/vnd.google-apps.fusiontable";
//    public static String TYPE_GOOGLE_MY_MAPS = "application/vnd.google-apps.map";
//    public static String TYPE_PHOTO = "application/vnd.google-apps.photo";
//    public static String TYPE_GOOGLE_SLIDES = "application/vnd.google-apps.presentation";
//    public static String TYPE_GOOGLE_APPS_SCRIPTS = "application/vnd.google-apps.script";
//    public static String TYPE_GOOGLE_SITES = "application/vnd.google-apps.site";
//    public static String TYPE_GOOGLE_SHEETS = "application/vnd.google-apps.spreadsheet";
//    public static String TYPE_UNKNOWN = "application/vnd.google-apps.unknown";
//    public static String TYPE_VIDEO = "application/vnd.google-apps.video";
//    public static String TYPE_3_RD_PARTY_SHORTCUT = "application/vnd.google-apps.drive-sdk";
}