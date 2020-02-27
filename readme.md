# Ebi

Ever know that you have an enchanted book somewhere,
in some chest? But which chest? hm...

The *E*nchanted *B*ook *I*ndex solves all your problems!
*yay*

## overview

* Fill lots of chests with enchanted books (probably from fishing).
* Give each chest some kind of identifier. I like banners,
but you could use signs with 128-bit UUIDs, item frames, or
an x/y location. ![image of chests with banners](images/banner_chest.png)
* Open chest #1. Run Ebi.
* Ebi will hover the mouse over each Enchanted Book in the chest, collecting all enchantment names.
* If any new enchantments are found, you'll be asked to identify each enchantment. The first time you run Ebi, every enchantment is new to the system – but they'll be remembered.
* Ebi writes the index to a CSV file. Import the file into the spreadsheet of your choice.
* When you need a *Looting III* enchantment, search your spreadsheet. There you will find the ID and slot of the chest storing the enchantment. For instance "Chest 90210, row 2, column 9".
* Find that chest and slot, find your enchanted book, celebrate.
* Mark that book as *used*, or remove it from the spreadsheet.

## other projects
If you know of similar projects, let me know so I can add them to the list.

* [Spigot Book Finder](https://github.com/coreequip/spigot-bookfinder) If you run and have access to Spigot, this mod looks super convenient.
