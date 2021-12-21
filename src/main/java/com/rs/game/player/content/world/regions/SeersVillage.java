// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
// 
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
// 
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
//
//  Copyright © 2021 Trenton Kress
//  This file is part of project: Darkan
//
package com.rs.game.player.content.world.regions;

import com.rs.game.player.content.achievements.AchievementSystemDialogue;
import com.rs.game.player.content.achievements.SetReward;
import com.rs.game.player.content.dialogue.Conversation;
import com.rs.game.player.content.dialogue.Dialogue;
import com.rs.game.player.content.dialogue.HeadE;
import com.rs.game.player.content.dialogue.Options;
import com.rs.game.player.content.skills.agility.Agility;
import com.rs.game.player.content.world.AgilityShortcuts;
import com.rs.game.player.quests.Quest;
import com.rs.game.player.quests.handlers.merlinscrystal.knightsroundtable.SirKayMerlinsCrystalD;
import com.rs.plugin.annotations.PluginEventHandler;
import com.rs.plugin.events.NPCClickEvent;
import com.rs.plugin.events.ObjectClickEvent;
import com.rs.plugin.handlers.NPCClickHandler;
import com.rs.plugin.handlers.ObjectClickHandler;

@PluginEventHandler
public class SeersVillage {

	public static NPCClickHandler handleStankers = new NPCClickHandler(383) {
		@Override
		public void handle(NPCClickEvent e) {
			e.getPlayer().startConversation(new Conversation(e.getPlayer()) {
				{
					addNPC(e.getNPCId(), HeadE.CHEERFUL, "Hello, what can I do for you?");
					addOptions("What would you like to say?", new Options() {
						@Override
						public void create() {
							option("About the Achievement System...", new AchievementSystemDialogue(player, e.getNPCId(), SetReward.SEERS_HEADBAND).getStart());
						}
					});
				}
			});
		}
	};
	
	public static NPCClickHandler handleSeer = new NPCClickHandler(388) {
		@Override
		public void handle(NPCClickEvent e) {
			e.getPlayer().startConversation(new Conversation(e.getPlayer()) {
				{
					addNPC(e.getNPCId(), HeadE.DRUNK, "Uh, what was that dark force? I've never sensed anything like it...");
					addNPC(e.getNPCId(), HeadE.NO_EXPRESSION, "Anyway, sorry about that.");
					addOptions("What would you like to say?", new Options() {
						@Override
						public void create() {
							option("About the Achievement System...", new AchievementSystemDialogue(player, e.getNPCId(), SetReward.SEERS_HEADBAND).getStart());
						}
					});
				}
			});
		}
	};
	
	public static NPCClickHandler handleSirKay = new NPCClickHandler(241) {
		@Override
		public void handle(NPCClickEvent e) {
			e.getPlayer().startConversation(new Conversation(e.getPlayer()) {
				{
					addNPC(e.getNPCId(), HeadE.CHEERFUL, "Hello, what can I do for you?");
					addOptions("What would you like to say?", new Options() {
						@Override
						public void create() {
							option("About the Achievement System...", new AchievementSystemDialogue(player, e.getNPCId(), SetReward.SEERS_HEADBAND).getStart());
                            if(!player.getQuestManager().isComplete(Quest.MERLINS_CRYSTAL))
                                option("About Merlin's Crystal", new Dialogue()
                                    .addNext(()->{e.getPlayer().startConversation(new SirKayMerlinsCrystalD(e.getPlayer()).getStart());}));
						}
					});
				}
			});
		}
	};
	
	public static ObjectClickHandler handleRoofLadder = new ObjectClickHandler(new Object[] { 26118, 26119 }) {
		@Override
		public void handle(ObjectClickEvent e) {
			e.getPlayer().ladder(e.getPlayer().transform(0, 0, e.getObjectId() == 26118 ? 2 : -2));
		}
	};
	
	public static ObjectClickHandler handleCoalTruckLogBalance = new ObjectClickHandler(new Object[] { 2296 }) {
		@Override
		public void handle(ObjectClickEvent e) {
			if (!Agility.hasLevel(e.getPlayer(), 20))
				return;
			AgilityShortcuts.walkLog(e.getPlayer(), e.getPlayer().transform(e.getObject().getRotation() == 1 ? -5 : 5, 0, 0), 4);
		}
	};
	
	public static ObjectClickHandler handleSinclairMansionLogBalance = new ObjectClickHandler(new Object[] { 9322, 9324 }) {
		@Override
		public void handle(ObjectClickEvent e) {
			if (!Agility.hasLevel(e.getPlayer(), 48))
				return;
			AgilityShortcuts.walkLog(e.getPlayer(), e.getPlayer().transform(0, e.getObjectId() == 9322 ? -4 : 4, 0), 3);
		}
	};
}
