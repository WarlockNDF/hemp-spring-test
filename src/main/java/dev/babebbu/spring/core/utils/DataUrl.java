package dev.babebbu.spring.core.utils;

import lombok.Getter;

public class DataUrl {

    @Getter
    private String group;

    @Getter
    private String extension;

    @Getter
    private String encoding;

    @Getter
    private String data;

    private DataUrl() {}

    public static DataUrl fromUrl(String s) {
        String[] data = s.split("([[:alnum:]]+):([[:alnum:]]+)\\/([[:alnum:]]+);([[:alnum:]]+),(.+)");
        DataUrl dataUrl = new DataUrl();
        // Ignore data[0]
        dataUrl.group = data[1];
        dataUrl.extension = data[2];
        dataUrl.encoding = data[3];
        dataUrl.data = data[4];
        return dataUrl;
    }

    public String getMimeType() {
        return group + "/" + extension;
    }

    public boolean isEncodingEquals(String s) {
        return !encoding.equalsIgnoreCase(s);
    }

    public boolean isEncodingNotEquals(String s) {
        return !encoding.equalsIgnoreCase(s);
    }

}
