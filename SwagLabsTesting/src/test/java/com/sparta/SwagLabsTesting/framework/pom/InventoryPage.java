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
    private By inventoryList = new By.ByClassName("inventory_list");
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

    // return the product index from the name
    public int getProductIndexFromName(String name) {

        for (int i = 0; i < inventoryItems.size(); i++) {
            if (getProductName(i).equals(name)) {
                return i;
            }
        }

        return -1;
    }

    public boolean addButtonIsPresent(int index) {
        return inventoryItems.get(index).findElement(itemAddRemoveButton).getText().equals("ADD TO CART");
    }

    public boolean removeButtonIsPresent(int index) {
        return inventoryItems.get(index).findElement(itemAddRemoveButton).getText().equals("REMOVE");
    }

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
    // get product information
    public WebElement getProduct(int index) {
        WebElement product = webDriver.findElement(inventoryList)
                .findElements(By.className("inventory_item"))
                .get(index);

        return product;
    }

    // get product name
    public String getProductName(int index) {
        return getProduct(index)
                .findElement(By.className("inventory_item_name"))
                .getText();
    }
    // get product desc
    public String getProductDescription(int index) {
        return getProduct(index)
                .findElement(By.className("inventory_item_desc"))
                .getText();
    }
    // get product price
    public double getProductPrice(int index) {

        String priceString = getProduct(index)
                .findElement(By.className("inventory_item_price")).getText();
        priceString = priceString.replaceAll("[$]", "");

        double price = Double.parseDouble(priceString);

        return price;
    }

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

    // filter methods is sorted
    public boolean productIsSortedAZ() {
        boolean inOrder = true;
        String nameBefore = "";
        for (int i = 0; i < inventoryItems.size(); i++) {
            String name = getProductName(i);
            if (name.compareTo(nameBefore) < 0) {
                inOrder = false;
            }
            nameBefore = name;
        }
        return inOrder;
    }
    public boolean productIsSortedZA() {
        boolean inOrder = true;
        String nameBefore = getProductName(0);
        for (int i = 0; i < inventoryItems.size(); i++) {
            String name = getProductName(i);
            if (name.compareTo(nameBefore) > 0) {
                inOrder = false;
            }
            nameBefore = name;
        }
        return inOrder;
    }
    public boolean productIsSortedLH() {
        boolean inOrder = true;
        double priceBefore = 0;
        for (int i = 0; i < inventoryItems.size(); i++) {
            double price = getProductPrice(i);
            if (price < priceBefore) {
                inOrder = false;
            }
            priceBefore = price;
        }
        return inOrder;
    }
    public boolean productIsSortedHL() {
        boolean inOrder = true;
        double priceBefore = getProductPrice(0);
        for (int i = 0; i < inventoryItems.size(); i++) {
            double price = getProductPrice(i);
            if (!(price <= priceBefore)) {
                inOrder = false;
            }
            priceBefore = price;
        }
        return inOrder;
    }

    // menu methods
    public Menu openMenu() {
        webDriver.findElement(openMenuButton).click();
        return new Menu(webDriver);
    }


    public Cart openCart() {
        webDriver.findElement(cartButton).click();
        return new Cart(webDriver);
    }






}
