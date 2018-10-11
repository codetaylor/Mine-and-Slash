package com.robertx22.database.stats.types;

import com.robertx22.enums.Elements;
import com.robertx22.stats.Stat;

public class MinDamage extends Stat {

	@Override
	public String Name() {
		return "Min DMG";
	}

	@Override
	public boolean ScalesToLevel() {		
		return true;
	}

	@Override
	public Elements Element() {		
		return null;
	}
	
	@Override
	public boolean IsPercent() {		
		return false;
	}
}