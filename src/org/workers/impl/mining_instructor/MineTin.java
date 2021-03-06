package org.workers.impl.mining_instructor;

import org.OrionTutorial;
import org.data.TutorialState;
import org.osbot.rs07.api.map.Area;
import org.workers.TutorialWorker;

import viking.api.Timing;

public class MineTin extends TutorialWorker
{
	private static final Area TIN_AREA = new Area(3070, 9510, 3080, 9500);
	
	public MineTin(OrionTutorial mission)
	{
		super(mission);
	}

	@Override
	public boolean shouldExecute()
	{
		return true;
	}

	@Override
	public void work()
	{
		script.log(this, false, "Mine Tin");
		int confCache = configs.get(TutorialState.CONFIG_ID);
		
		if(iFact.clickObject("Mine", "Rocks", TIN_AREA).execute())
			Timing.waitCondition(() -> configs.get(TutorialState.CONFIG_ID) != confCache, 5000);
	}

}
