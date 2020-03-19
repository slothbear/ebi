# Ebi

Ever know that you have an enchanted book somewhere,
in some chest? But which chest?

**E**nchanted **B**ook **I**ndex solves your problem.
**yay**

If you don't play Minecraft but want an idea of how Ebi works,
check out the
[non-technical overview](https://github.com/slothbear/ebi/wiki/non-technical-overview).

## overview

* Fill lots of chests with enchanted books (probably from fishing).
* Give each chest some kind of identifier. I like banners,
but you could use signs with 128-bit UUIDs, item frames,
[memorable phrases](https://www.youtube.com/watch?v=NFIGX6EfSSc),
or
an x/y location. You could name your chests on an anvil,
but that ID is not visible until you open the chest. Or name an item
(via the anvil) you place in an item frame. That's visible on hover.
![image of chests with banners](images/chest_ids.png)
* Open a chest. Run Ebi.
* Ebi will hover the mouse over each Enchanted Book in the chest,
collecting all enchantment names.
* If any new enchantments are found, you'll be asked to identify
each enchantment. The first time you run Ebi, every enchantment
is new to the system – but they'll be remembered.
* Ebi writes the index to a CSV file. Import the file
into the spreadsheet of your choice.
* When you need a **Looting III** enchantment, search your spreadsheet.
There you will find the ID and slot of the chest storing the
enchantment. For instance
> chest #9, row 2, column 9
* Find the chest and slot, find your enchanted book, celebrate.
* Mark that book as *used*, or remove it from the spreadsheet.

## FAQ

## other projects
If you know of similar projects, let me know so I can add them to the list.

* [Spigot Book Finder](https://github.com/coreequip/spigot-bookfinder)
If you run and have access to Spigot, this mod looks super convenient
and is a lot easier to use.
