package com.github.maxopoly.SkilUp.listeners.effects;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.github.maxopoly.SkilUp.rewards.AbstractReward;

public class EffectBlockBreakListener implements Listener {

	private Material material;
	private Integer data;
	private AbstractReward reward;

	public EffectBlockBreakListener(AbstractReward reward, Material material,
			Integer data) {
		this.material = material;
		this.data = data;
		this.reward = reward;
	}

	@EventHandler
	public void catchEvent(BlockBreakEvent e) {
		if (reward.deservesReward(e.getPlayer())) {
			if (material == null || material == e.getBlock().getType()) {
				if (data == null || data == e.getBlock().getData()) {
					switch (reward.getRequiredData()) {
					case DROP:
						reward.applyEffect(e.getBlock().getLocation());
						break;
					case BUFF:
						reward.applyEffect(e.getPlayer());
						break;
					default:
						// should never happen
					}
				}
			}
		}
	}

}