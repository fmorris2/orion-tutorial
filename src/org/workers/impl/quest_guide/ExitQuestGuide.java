package org.workers.impl.quest_guide;

import org.OrionTutorial;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.workers.TutorialWorker;

import viking.api.Timing;

public class ExitQuestGuide extends TutorialWorker
{
	private static final Area LADDER_AREA = new Area(3087, 3120, 3089, 3118);
	private static final Position FALLBACK_POS = new Position(3088, 3120, 0);
	private static final Position INSTRUCTOR_POS = new Position(3081, 9508, 0);
	
	public ExitQuestGuide(OrionTutorial mission)
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
		script.log(this, false, "Exit Quest Guide");
		if(myPosition().getZ() == 1 && iFact.clickObject("Climb-down", "Staircase", 10).execute())
			Timing.waitCondition(() -> myPosition().getZ() == 0, 4500);
		else if(iFact.clickObject("Climb-down", "Ladder", LADDER_AREA, FALLBACK_POS).execute() 
				&& Timing.waitCondition(() -> myPosition().getY() > 9000, 4500))
			walkUtils.walkTo(INSTRUCTOR_POS);
		else
		{
			camera.movePitch(random(0, 360));
			camera.moveYaw(random(0, 360));
		}
	}

}
