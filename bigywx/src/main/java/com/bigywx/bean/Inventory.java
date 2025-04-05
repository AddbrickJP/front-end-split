package com.bigywx.bean;

//name VARCHAR(255) ,
//variation VARCHAR(255),
//diy VARCHAR(255),
//buy_price VARCHAR(255),
//sell_price VARCHAR(255),
//color1 VARCHAR(255),
//color2 VARCHAR(255),
//size VARCHAR(255),
//miles_price VARCHAR(255),
//source VARCHAR(255),
//seasonal_availability VARCHAR(255),
//mannequin_piece VARCHAR(255),
//version VARCHAR(255),
//style VARCHAR(255),
//label_themes VARCHAR(255),
//type VARCHAR(255),
//villager_equippable VARCHAR(255),
//catalog VARCHAR(255),
//filename VARCHAR(255),
//internal_id VARCHAR(255),
//unique_entry_id VARCHAR(255)

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Inventory {
    //根据注释生成字段

    // name VARCHAR(255)
    private String name;

    // variation VARCHAR(255)
    private String variation;

    // diy VARCHAR(255)
    private String diy;

    // buy_price VARCHAR(255)
    private String buy;

    // sell_price VARCHAR(255)
    private String sell;

    // color1 VARCHAR(255)
    private String color1;

    // color2 VARCHAR(255)
    private String color2;

    // size VARCHAR(255)
    private String size;

    // miles_price VARCHAR(255)
    private String milesPrice;

    // source VARCHAR(255)
    private String source;

    // seasonal_availability VARCHAR(255)
    private String seasonalAvailability;

    // mannequin_piece VARCHAR(255)
    private String mannequinPiece;

    // version VARCHAR(255)
    private String version;

    // style VARCHAR(255)
    private String style;

    // label_themes VARCHAR(255)
    private String labelThemes;

    // type VARCHAR(255)
    private String type;

    // villager_equippable VARCHAR(255)
    private String villagerEquippable;

    // catalog VARCHAR(255)
    private String catalog;

    // filename VARCHAR(255)
    private String filename;

    // internal_id VARCHAR(255)
    private String internalId;

    // unique_entry_id VARCHAR(255)
    private String uniqueEntryId;




}
