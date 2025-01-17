package stelnet.util;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignClockAPI;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CampaignUIAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.listeners.ListenerManagerAPI;

/**
 * Provides easy access to Global.getSector() methods.
 */
public class SectorUtils {

    public static void addTransientScript(EveryFrameScript script) {
        getSector().addTransientScript(script);
    }

    public static CampaignUIAPI getCampaignUI() {
        return getSector().getCampaignUI();
    }

    public static CampaignClockAPI getCurrentClock() {
        return getSector().getClock();
    }

    public static ListenerManagerAPI getListenerManager() {
        return getSector().getListenerManager();
    }

    public static FactionAPI getPlayerFaction() {
        return getSector().getPlayerFaction();
    }

    public static CampaignFleetAPI getPlayerFleet() {
        return getSector().getPlayerFleet();
    }

    public static boolean isPaused() {
        return getSector().isPaused();
    }

    public static void layInCourseFor(SectorEntityToken target) {
        getSector().layInCourseFor(target);
    }

    public static void removeTransientScript(EveryFrameScript script) {
        getSector().removeTransientScript(script);
    }

    public static void removeTransientScripts(Class<?> className) {
        getSector().removeTransientScriptsOfClass(className);
    }

    private static SectorAPI getSector() {
        return Global.getSector();
    }
}
