package com.gildedrose;

class GildedRose {
    Item[] items;

    private static final int MAX_QUALITY = 50;
    private static final int MIN_QUALITY = 0;
    private static final int BACKSTAGE_CLOSE = 10;
    private static final int BACKSTAGE_VERY_CLOSE = 5;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int itemIndex = 0; itemIndex < items.length; itemIndex++) {
            Item currentItem = items[itemIndex];

            /* Ideally I would refactor the Item class to have a "type"
               Alternatively we could do string.contains('Backstage passes') & string.contains('Conjured') if we want to improve it a little,
               without having to touch the Item class.
            */
            switch(currentItem.name) {
                case "Aged Brie":
                    updateAgedBrie(currentItem);
                    break;
                case "Backstage passes to a TAFKAL80ETC concert":
                    updateBackstagePasses(currentItem);
                    break;
                case "Sulfuras, Hand of Ragnaros":
                    // Legendary item, no need to update
                    break;
                case "Conjured Mana Cake":
                    updateConjured(currentItem);
                    break;
                default:
                    updateRegularItem(currentItem);
                    break;
            }
            updateSellIn(currentItem);
        }
    }

    private void updateAgedBrie(Item item) {
        if(sellDateReached(item)){
            increaseQuality(item, 2);
        }
        else {
            increaseQuality(item, 1);
        }
    }

    private void updateBackstagePasses(Item item) {
        if (sellDateReached(item)) {
            item.quality = MIN_QUALITY;
        }
        else if (item.sellIn <= BACKSTAGE_VERY_CLOSE) {
            increaseQuality(item, 3);
        }
        else if (item.sellIn <= BACKSTAGE_CLOSE) {
            increaseQuality(item, 2);
        }
        else {
            increaseQuality(item, 1);
        }
    }

    // Should be twice as fast as regular item
    private void updateConjured(Item item) {
        updateRegularItem(item);
        updateRegularItem(item);
    }

    private void updateRegularItem(Item item) {
        decreaseQuality(item);
    }

    private void increaseQuality(Item item) {
        increaseQuality(item, 1);
    }

    private void increaseQuality(Item item, int amount) {
        item.quality = Math.min(item.quality + amount, MAX_QUALITY);
    }

    private void decreaseQuality(Item item) {
        decreaseQuality(item, 1);
    }

    private void decreaseQuality(Item item, int amount) {
        item.quality = Math.max(item.quality - amount, MIN_QUALITY);
    }

    private boolean sellDateReached(Item item) {
        return (item.sellIn <= 0);
    }

    private void updateSellIn(Item item) {
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn--;
        }
    }   
}
