package ru.lgtu.xarxes.hermaeus.model;

import java.time.LocalDate;

/**
 * Краткая информация о моде
 */

public class ModSummary {
    private  int id;
    private  String name;
    private  String authorName;
    private  String gameName;
    private  String version;
    private ModCategory category;
    private  int downloads;
    private  int likes;
    private ModStatus status;
    private String previewImage;
    private LocalDate updateDate;

}