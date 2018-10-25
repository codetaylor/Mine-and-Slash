package com.robertx22.onevent.ontick;

import com.robertx22.database.stats.types.resources.EnergyRegen;
import com.robertx22.database.stats.types.resources.HealthRegen;
import com.robertx22.database.stats.types.resources.ManaRegen;
import com.robertx22.network.Network;
import com.robertx22.network.StringPackage;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.BarsGUI;
import com.robertx22.uncommon.datasaving.UnitSaving;
import com.robertx22.uncommon.datasaving.bases.Saving;
import com.robertx22.uncommon.utilityclasses.HealthUtils;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

@Mod.EventBusSubscriber
public class OnTickRegen {

	static int tick = 0;

	static int syncTick = 0;

	static int time = 100;

	@SubscribeEvent
	public static void onTickRegen(TickEvent.PlayerTickEvent event) {

		if (event.phase == Phase.END && event.side.isServer()) {

			tick++;

			syncTick++;

			if (syncTick > 20) {
				Unit unit = UnitSaving.Load(event.player);
				StringPackage packet = new StringPackage(Saving.ToString(unit));
				Network.INSTANCE.sendTo(packet, (EntityPlayerMP) event.player);
				BarsGUI.Updated = true;
				syncTick = 0;
			}

			if (tick > time) {

				if (event.player.isEntityAlive()) {
					Unit unit = UnitSaving.Load(event.player);
					unit.RecalculateStats(event.player);

					int manarestored = (int) unit.Stats.get(new ManaRegen().Name()).Value;
					unit.RestoreMana(manarestored);

					int energyrestored = (int) unit.Stats.get(new EnergyRegen().Name()).Value;
					unit.RestoreEnergy(energyrestored);

					int healthrestored = (int) unit.Stats.get(new HealthRegen().Name()).Value;
					event.player.heal(HealthUtils.DamageToMinecraftHealth(healthrestored, event.player));

					UnitSaving.Save(event.player, unit);

					tick = 0;
				}
			}

		}

	}

}
