package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    // Test suite is limited due to time pressure
    @Test
    void foo() {
        // ITEM is name, sellIn, quality
         Item[] items = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20), //
                new Item("Aged Brie", 2, 0), //
                new Item("Elixir of the Mongoose", 5, 7), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10),
                // this conjured item does not work properly yet
                new Item("Conjured Mana Cake", 3, 6) };
        GildedRose app = new GildedRose(items);
        // DAY 0
        app.updateQuality();
        // DAY 1
        
        // The quality of brie should increase with time
        assertEquals(1, app.items[1].quality);

        app.updateQuality();
        // DAY 2
        app.updateQuality();
        // DAY 3
        // The quality of brie should double if sellIn is negative 
        assertEquals(4, app.items[1].quality);
        // Sulfuras does not change
        assertEquals(80, app.items[4].quality);

        app.updateQuality();
        // DAY 4
        app.updateQuality();
        // DAY 5
        app.updateQuality();
        // DAY 6
        // Backstage passes drop to 0 after concert
        assertEquals(0, app.items[8].quality);
    }

}
