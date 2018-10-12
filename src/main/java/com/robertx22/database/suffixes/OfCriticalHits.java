package com.robertx22.database.suffixes;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.database.stats.mods.flat.CriticalHitFlat;
import com.robertx22.gearitem.Suffix;
import com.robertx22.interfaces.IWeighted;
import com.robertx22.stats.StatMod;

public class OfCriticalHits extends Suffix {

	public OfCriticalHits() {
	}

	@Override
	public String Name() {
		return "Of Critical Hits";
	}

	@Override
	public List<StatMod> StatMods() {

		List<StatMod> mods = new ArrayList<StatMod>();
		mods.add(new CriticalHitFlat());

		return mods;

	}

	@Override
	public int Weight() {
		return IWeighted.NormalWeight;
	}

}