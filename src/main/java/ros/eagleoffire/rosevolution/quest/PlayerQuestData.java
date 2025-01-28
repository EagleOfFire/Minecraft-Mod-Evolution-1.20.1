package ros.eagleoffire.rosevolution.quest;

public class PlayerQuestData {
    private long activeTicks;
    private long loginCooldownEndTime = 0;
    private double totalDistanceTraveled = 0;
    private double lastX = Double.NaN, lastY = Double.NaN, lastZ =Double.NaN;
    private long travelCooldownEndTime = 0;
    private double damageDealt = 0;
    private double damageReceived = 0;
    private long damageCooldownEndTime = 0;

    public void incrementActiveTime() {this.activeTicks++;}
    public void resetActiveTime() {this.activeTicks = 0;}
    public long getActiveTicks() {return activeTicks;}

    public boolean isLoginOnCooldown() {return System.currentTimeMillis() < loginCooldownEndTime;}
    public void setLoginCooldown(long currentTimeMillis){loginCooldownEndTime = currentTimeMillis + LoginQuestTracker.getCooldownMillis();}
    public long getRemainingCooldownTime() {return Math.max(0, loginCooldownEndTime - System.currentTimeMillis());}

    public double calculateDistance(double currentX,double currentY, double currentZ){
        if(Double.isNaN(lastX)) {
            lastX = currentX;
            lastY = currentY;
            lastZ = currentZ;
            return  0;
        }
        double distance = Math.sqrt((currentX - lastX) * (currentX - lastX)
                + (currentY- lastY) * (currentY - lastY)
                + (currentZ - lastZ) * (currentZ - lastZ));
        lastX = currentX;
        lastY = currentY;
        lastZ = currentZ;
        return distance;
    }
    public void addDistanceTraveled(double distance){totalDistanceTraveled += distance;}
    public double getDistanceTraveled(){return totalDistanceTraveled;}
    public void resetDistanceTraveled(){totalDistanceTraveled = 0;}
    public boolean isTravelOnCooldown(){return System.currentTimeMillis() < travelCooldownEndTime;}
    public void setTravelCooldown(long currentTimeMillis){travelCooldownEndTime = currentTimeMillis + 30 * 60 * 1000;}

    public void addDamageDealt(double damage){damageDealt += damage;}
    public void addDamageReceived(double damage){damageReceived += damage;}
    public double getTotalDamage(){return damageDealt + damageReceived;}
    public void resetDamage(){
        damageDealt = 0;
        damageReceived = 0;
    }
    public boolean isDamageOnCooldown(){return System.currentTimeMillis() < damageCooldownEndTime;}
    public void setDamageCooldown(long currentTimeMillis){damageCooldownEndTime = currentTimeMillis + 5 * 60 * 1000;}
}
