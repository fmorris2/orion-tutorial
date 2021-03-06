package org.workers.impl.bank_stage;

import org.OrionTutorial;
import org.osbot.rs07.api.map.Position;
import org.workers.TutorialWorker;

import viking.api.Timing;
import viking.api.interaction.impl.npc.NpcDialogue;

public class TOpenBank extends TutorialWorker
{
	private static final Position BANK_POS = new Position(3121, 3122, 0);
	private static final int DIST_THRESH = 4;
	
	public TOpenBank(OrionTutorial mission)
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
		script.log(this, false, "Open Bank");
		NpcDialogue dialogue = iFact.dialogue("Talk-to", "Banker", 10, 1);
		dialogue.setNeedsToReach(false);
		
		if(myPosition().distance(BANK_POS) > DIST_THRESH)
			walkUtils.walkTo(BANK_POS);
		else if(dialogue.execute()
				&& Timing.waitCondition(() -> bank.isOpen(), 5000))
			bank.close();
			
	}

}
