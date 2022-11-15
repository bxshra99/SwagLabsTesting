package com.sparta.SwagLabsTesting.framework.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InventoryPage {
    private WebDriver webDriver;

    // -------------------- anastasiia
    private List<WebElement> inventoryItems = null; // the list with all the items
    private final By itemImageLink = By.cssSelector(".inventory_item_img a"); // selector of the item's image
    private final By itemAddRemoveButton = By.cssSelector(".btn_inventory"); // selector of the item's button
    private final By itemLink = By.cssSelector(".inventory_item_label a"); // selector of the item's link
    // -------------------------------

    // Cart button objects
    private By cartButton = new By.ById("shopping_cart_container");
    private By cartBadge = new By.ByClassName("shopping_cart_badge");

    // ------------ may be removed (likely won't be used) -----------------------------
    // Filter button objects
    private By productSortButton = new By.ByClassName("product_sort_container");
    private By productSortNameAZ = new By.ByCssSelector("[value=\"az\"]");
    private By productSortNameZA = new By.ByCssSelector("[value=\"za\"]");
    private By productSortPriceLH = new By.ByCssSelector("[value=\"lohi\"]");
    private By productSortPriceHL = new By.ByCssSelector("[value=\"hilo\"]");
    // --------------------------------------------------------------------------------

    // menu objects
    private By openMenuButton = new By.ById("react-burger-menu-btn");

    // --------- may be removed (repeats method from Menu class) ----------------------
    private By closeMenuButton = new By.ById("react-burger-cross-btn");
    // --------------------------------------------------------------------------------



    public InventoryPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        // -------------------- anastasiia
        this.inventoryItems = webDriver.findElements(By.className("inventory_item"));
        // -------------------------------
    }


    // ----------------------- may be removed ------------------------------------------
    // backpack objects
    private By addBackpackToCartButton = new By.ById("add-to-cart-sauce-labs-backpack");
    private By removeBackpackFromCartButton = new By.ById("remove-sauce-labs-backpack");
    private By backPackImage = new By.ById("item_4_img_link");
    private By backPackNameLink = new By.ById("item_4_title_link");

    // bike light objects
    private By addBikeLightToCartButton = new By.ById("add-to-cart-sauce-labs-bike-light");
    private By removeBikeLightFromCartButton = new By.ById("remove-sauce-labs-bike-light");
    private By bikeLightImage = new By.ById("item_0_img_link");
    private By bikeLightNameLink = new By.ById("item_0_title_link");

    // bolt t-shirt objects
    private By addBoltTShirtToCartButton = new By.ById("add-to-cart-sauce-labs-bolt-t-shirt");
    private By removeBoltTShirtFromCartButton = new By.ById("remove-sauce-labs-bolt-t-shirt");
    private By boltTShirtImage = new By.ById("item_1_img_link");
    private By boltTShirtNameLink = new By.ById("item_1_title_link");

    // fleece jacket objects
    private By addFleeceJacketToCartButton = new By.ById("add-to-cart-sauce-labs-fleece-jacket");
    private By removeFleeceJacketFromCartButton = new By.ById("remove-sauce-labs-fleece-jacket");
    private By fleeceJacketImage = new By.ById("item_5_img_link");
    private By fleeceJacketNameLink = new By.ById("item_5_title_link");

    // onesie objects
    private By addOnesieToCartButton = new By.ById("add-to-cart-sauce-labs-onesie");
    private By removeOnesieFromCartButton = new By.ById("remove-sauce-labs-onesie");
    private By onesieImage = new By.ById("item_2_img_link");
    private By onesieNameLink = new By.ById("item_2_title_link");

    // red t-shirt objects
    private By addRedTShirtToCartButton = new By.ById("add-to-cart-test.allthethings()-t-shirt-(red)");
    private By removeRedTShirtFromCartButton = new By.ById("remove-test.allthethings()-t-shirt-(red)");
    private By redTShirtImage = new By.ById("item_3_img_link");
    private By redTShirtNameLink = new By.ById("item_3_title_link");
    // --------------------------------------------------------------------------------

    // get url method
    public String getUrl() {
        return webDriver.getCurrentUrl();
    }

    // -------------------- anastasiia ---------------------------------------------------
    public void clickItemButton(int index) {
        inventoryItems.get(index).findElement(itemAddRemoveButton).click();
    }

    public void addOrRemoveSeveralItems(int number) {
        for (int i = 0; i < number; i ++) {
            clickItemButton(i);
        }
    }

    public void removeAllItemsFromCart() {
        for (int i = 0; i < inventoryItems.size(); i++)
            if (removeButtonIsPresent(i))
                inventoryItems.get(i).findElement(itemAddRemoveButton).click();
    }

    // do we need this method for testing?
    public void clickImageLink(int index) {
        inventoryItems.get(index).findElement(itemImageLink).click();
    }

    // do we need this method for testing?
    public void clickItemLink(int index) {
        inventoryItems.get(index).findElement(itemLink).click();
    }

    public boolean addButtonIsPresent(int index) {
        return inventoryItems.get(index).findElement(itemAddRemoveButton).getText().equals("ADD TO CART");
    }

    public boolean removeButtonIsPresent(int index) {
        return inventoryItems.get(index).findElement(itemAddRemoveButton).getText().equals("REMOVE");
    }
    // ------------------------------------------------------------------------------------

    // ----------------------- may be removed ------------------------------------------
    // backpack methods
    public void clickAddBackpackToCartButton() {
        webDriver.findElement(addBackpackToCartButton).click();
    }
    public void clickRemoveBackpackFromCart() {
        webDriver.findElement(removeBackpackFromCartButton).click();
    }
    public void clickBackPackImage() {
        webDriver.findElement(backPackImage).click();
    }
    public void clickBackPackNameLink() {
        webDriver.findElement(backPackNameLink).click();
    }
    public boolean addBackpackToCartButtonIsPresent() {
        return webDriver.findElements(addBackpackToCartButton).size() != 0;
    }
    public boolean removeBackpackFromCartButtonIsPresent() {
        return webDriver.findElements(removeBackpackFromCartButton).size() != 0;
    }

    // bike light methods
    public void clickAddBikeLightToCartButton() {
        webDriver.findElement(addBikeLightToCartButton).click();
    }
    public void clickRemoveBikeLightFromCart() {
        webDriver.findElement(removeBikeLightFromCartButton).click();
    }
    public void clickBikeLightImage() {
        webDriver.findElement(bikeLightImage).click();
    }
    public void clickBikeLightNameLink() {
        webDriver.findElement(bikeLightNameLink).click();
    }
    public boolean addBikeLightToCartButtonIsPresent() {
        return webDriver.findElements(addBikeLightToCartButton).size() != 0;
    }
    public boolean removeBikeLightFromCartButtonIsPresent() {
        return webDriver.findElements(removeBikeLightFromCartButton).size() != 0;
    }

    // bolt t-shirt methods
    public void clickAddBoltTShirtToCartButton() {
        webDriver.findElement(addBoltTShirtToCartButton).click();
    }
    public void clickRemoveBoltTShirtFromCart() {
        webDriver.findElement(removeBoltTShirtFromCartButton).click();
    }
    public void clickBoltTShirtImage() {
        webDriver.findElement(boltTShirtImage).click();
    }
    public void clickBoltTShirtNameLink() {
        webDriver.findElement(boltTShirtNameLink).click();
    }
    public boolean addBoltTShirtToCartButtonIsPresent() {
        return webDriver.findElements(addBoltTShirtToCartButton).size() != 0;
    }
    public boolean removeBoltTShirtFromCartButtonIsPresent() {
        return webDriver.findElements(removeBoltTShirtFromCartButton).size() != 0;
    }

    // fleece methods
    public void clickAddFleeceJacketToCartButton() {
        webDriver.findElement(addFleeceJacketToCartButton).click();
    }
    public void clickRemoveFleeceJacketFromCart() {
        webDriver.findElement(removeFleeceJacketFromCartButton).click();
    }
    public void clickFleeceJacketImage() {
        webDriver.findElement(fleeceJacketImage).click();
    }
    public void clickFleeceJacketNameLink() {
        webDriver.findElement(fleeceJacketNameLink).click();
    }
    public boolean addFleeceJacketToCartButtonIsPresent() {
        return webDriver.findElements(addFleeceJacketToCartButton).size() != 0;
    }
    public boolean removeFleeceJacketFromCartButtonIsPresent() {
        return webDriver.findElements(removeFleeceJacketFromCartButton).size() != 0;
    }

    // onesie methods
    public void clickAddOnesieToCartButton() {
        webDriver.findElement(addOnesieToCartButton).click();
    }
    public void clickRemoveOnesieFromCart() {
        webDriver.findElement(removeOnesieFromCartButton).click();
    }
    public void clickOnesieImage() {
        webDriver.findElement(onesieImage).click();
    }
    public void clickOnesieNameLink() {
        webDriver.findElement(onesieNameLink).click();
    }
    public boolean addOnesieToCartButtonIsPresent() {
        return webDriver.findElements(addOnesieToCartButton).size() != 0;
    }
    public boolean removeOnesieFromCartButtonIsPresent() {
        return webDriver.findElements(removeOnesieFromCartButton).size() != 0;
    }

    // red t-shirt methods
    public void clickAddRedTShirtToCartButton() {
        webDriver.findElement(addRedTShirtToCartButton).click();
    }
    public void clickRemoveRedTShirtFromCart() {
        webDriver.findElement(removeRedTShirtFromCartButton).click();
    }
    public void clickRedTShirtImage() {
        webDriver.findElement(redTShirtImage).click();
    }
    public void clickRedTShirtNameLink() {
        webDriver.findElement(redTShirtNameLink).click();
    }
    public boolean addRedTShirtToCartButtonIsPresent() {
        return webDriver.findElements(addRedTShirtToCartButton).size() != 0;
    }
    public boolean removeRedTShirtFromCartButtonIsPresent() {
        return webDriver.findElements(removeRedTShirtFromCartButton).size() != 0;
    }
    // --------------------------------------------------------------------------------

    // cart methods
    public void clickCartButton() {
        webDriver.findElement(cartButton).click();
    }

    // Get cart badge number
    public int getCartTotal() {
        WebElement badgeNumElement;
        try {
            badgeNumElement = webDriver.findElement(cartBadge);
        } catch (NoSuchElementException nsee) {
            return 0;
        }
        return Integer.parseInt(badgeNumElement.getText());
    }

    // ------------ may be removed (likely won't be used) -----------------------------
    // filter methods
    // Filter buttons clicks
    public void clickProductSortButton() {
        webDriver.findElement(productSortButton).click();
    }

    public void clickProductSortNameAZ() {
        webDriver.findElement(productSortNameAZ).click();
    }
    public void clickProductSortNameZA() {
        webDriver.findElement(productSortNameZA).click();
    }
    public void clickProductSortPriceLH() {
        webDriver.findElement(productSortPriceLH).click();
    }
    public void clickProductSortPriceHL() {
        webDriver.findElement(productSortPriceHL).click();
    }
    // --------------------------------------------------------------------------------

    // menu methods
    public Menu openMenu() {
        webDriver.findElement(openMenuButton).click();
        return new Menu(webDriver);
    }

    // ---------- may be removed (repeats method from Menu class)---------------
    public Menu closeMenu() {
        webDriver.findElement(closeMenuButton).click();
        return new Menu(webDriver);
    }
    // -------------------------------------------------------------------------






}
