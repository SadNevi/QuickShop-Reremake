/*
 * This file is a part of project QuickShop, the name is DisplayWatcher.java
 * Copyright (C) Ghost_chu <https://github.com/Ghost-chu>
 * Copyright (C) Bukkit Commons Studio and contributors
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.maxgamer.quickshop.watcher;

import lombok.Data;
import org.bukkit.scheduler.BukkitRunnable;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.shop.DisplayItem;
import org.maxgamer.quickshop.shop.DisplayType;
import org.maxgamer.quickshop.shop.Shop;

@Data
public class DisplayWatcher {
    private QuickShop plugin;

    public DisplayWatcher(QuickShop plugin) {
        this.plugin = plugin;
        registerTask();
    }

    private void registerTask() {
        plugin.getLogger().info("Registering DisplayCheck task....");
        if (DisplayItem.getNowUsing() == DisplayType.VIRTUALITEM) {
            return;
        }
        if (plugin.isDisplay() && plugin.getDisplayItemCheckTicks() > 0) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (plugin.getConfig().getInt("shop.display-items-check-ticks") < 3000) {
                        plugin.getLogger().severe("Shop.display-items-check-ticks is too low! It may cause HUGE lag! Pick a number > 3000");
                    }
                    for (Shop shop : plugin.getShopManager().getLoadedShops()) {
                        shop.checkDisplay();
                    }
                }
            }.runTaskTimer(plugin, 1L, plugin.getDisplayItemCheckTicks());
        }
    }

}
