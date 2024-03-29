package no.nyseth.fantasd.shopnuser;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Items {
    //Item fields

    @Expose
    @SerializedName("id")
    private Long id;

    @Expose
    @SerializedName("itemTitle")
    private String itemTitle;

    @Expose
    @SerializedName("itemPrice")
    private String itemPrice;

    @Expose
    @SerializedName("itemDesc")
    private String itemDesc;

    @Expose
    @SerializedName("itemOwner")
    private User itemOwner;

    @Expose
    @SerializedName("itemBuyer")
    private User itemBuyer;

    public Items(String itemTitle, String itemPrice, String itemDesc) {
        this.itemTitle = itemTitle;
        this.itemPrice = itemPrice;
        this.itemDesc = itemDesc;
    }

    private List<ItemImage> itemImage;

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public User getItemOwner() {
        return itemOwner;
    }

    public void setItemOwner(User itemOwner) {
        this.itemOwner = itemOwner;
    }

    public User getItemBuyer() {
        return itemBuyer;
    }

    public void setItemBuyer(User itemBuyer) {
        this.itemBuyer = itemBuyer;
    }
}
