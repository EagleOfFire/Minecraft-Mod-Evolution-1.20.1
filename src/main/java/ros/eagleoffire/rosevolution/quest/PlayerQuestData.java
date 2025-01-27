package ros.eagleoffire.rosevolution.quest;

public class PlayerQuestData {
    private long activeTicks;

    public void incrementActiveTime() {
        this.activeTicks++;
    }

    public void resetActiveTime() {
        this.activeTicks = 0;
    }

    public long getActiveTicks() {
        return activeTicks;
    }
}
//TODO : addthe other quest player data