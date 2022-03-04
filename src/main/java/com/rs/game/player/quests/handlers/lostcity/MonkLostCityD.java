package com.rs.game.player.quests.handlers.lostcity;

import com.rs.game.player.Player;
import com.rs.game.player.content.dialogue.Conversation;
import com.rs.game.player.content.dialogue.HeadE;
import com.rs.game.player.quests.Quest;
import com.rs.plugin.annotations.PluginEventHandler;
import com.rs.plugin.events.NPCClickEvent;
import com.rs.plugin.handlers.NPCClickHandler;

import static com.rs.game.player.quests.handlers.lostcity.LostCity.*;

@PluginEventHandler
public class MonkLostCityD extends Conversation {
	public MonkLostCityD(Player p) {
		super(p);
		switch(p.getQuestManager().getStage(Quest.LOST_CITY)) {
		case NOT_STARTED -> {
			addPlayer(HeadE.HAPPY_TALKING, "Why are all of you standing around here?");
			addNPC(MONK, HeadE.FRUSTRATED, "None of your business. Get lost.");
		}
		case TALK_TO_LEPRAUCAN -> {
			addPlayer(HeadE.HAPPY_TALKING, "Have you found the tree with the leprechaun yet?");
			addNPC(MONK, HeadE.SKEPTICAL_THINKING, "No, we've looked for ages but haven't... Hey! Wait a minute! How did you know about that?");
			addPlayer(HeadE.HAPPY_TALKING, "Thanks for the information!");
			addNPC(MONK, HeadE.FRUSTRATED, "...You tricked me. I'm not talking to you anymore.");
		}
		case CHOP_DRAMEN_TREE, FIND_ZANARIS, QUEST_COMPLETE ->  {
			addNPC(MONK, HeadE.FRUSTRATED, "I already told you. I'm not talking to you anymore.");
		}
		}
	}

	public static NPCClickHandler handleMonkDialogue = new NPCClickHandler(new Object[] { MONK }) {
		@Override
		public void handle(NPCClickEvent e) {
			e.getPlayer().startConversation(new MonkLostCityD(e.getPlayer()).getStart());
		}
	};
}
