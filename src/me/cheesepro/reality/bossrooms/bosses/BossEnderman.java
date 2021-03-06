package me.cheesepro.reality.bossrooms.bosses;

import me.cheesepro.reality.Reality;
import me.cheesepro.reality.bossrooms.Bosses;
import me.cheesepro.reality.bossrooms.BossesAPI;
import me.cheesepro.reality.utils.EffectsAPI;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Endermite;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


/**
 * Created by Mark on 2015-07-20.
 */
public class BossEnderman implements Bosses {

    Reality plugin;
    BossesAPI bossesAPI;
    String name = ChatColor.LIGHT_PURPLE.toString() + "Enderman";
    String skill = "Spawns 6 Endermites upon spawn.";
    Integer health = 150;
    Integer damage = 6;
    Integer rewardXP = 5000;
    Integer rewardKey = 2;
    Double rewardMoney = 5000.0;
    NPC npc;
    
    public BossEnderman(Reality plugin){
        this.plugin = plugin;
        bossesAPI = new BossesAPI(plugin);
    }

    @Override
    public String getType(){
        return "enderman";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSkills() {
        return skill;
    }

    @Override
    public Integer getHealth() {
        return health;
    }

    @Override
    public Integer getDamage() {
        return damage;
    }

    @Override
    public Integer getRewardXP() {
        return rewardXP;
    }

    @Override
    public Integer getRewardKey() {
        return rewardKey;
    }

    @Override
    public Double getRewardMoney() {
        return rewardMoney;
    }

    @Override
    public NPC getNPC() {
        npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.ENDERMAN, name);
        return npc;
    }

    @Override
    public void spawn(String w, double x, double y, double z, float pitch, float yaw){
        Location loc = new Location(Bukkit.getWorld(w), x, y, z, pitch, yaw);
        npc.spawn(loc);
        npc.setProtected(false);
        Creature creature = (Creature) npc.getEntity();
        PotionEffect potionEffect = new PotionEffect(PotionEffectType.SPEED, 10000, 2);
        creature.addPotionEffect(potionEffect);
        bossesAPI.basicSetup(creature, health);
        useAbility(loc);
    }

    public void spawn(Location loc){
        spawn(loc.getWorld().getName(), loc.getX(), loc.getY(), loc.getZ(), loc.getPitch(), loc.getYaw());
    }

    public void useAbility(Location loc){
        for(int i = 0; i<6; i++){
            Endermite endermite = loc.getWorld().spawn(loc, Endermite.class);
            endermite.setCustomName(ChatColor.LIGHT_PURPLE + "Enderman");
            endermite.setCustomNameVisible(true);
        }
    }

    public void onHit(Player p){
        EffectsAPI.effect(p.getLocation(), EffectsAPI.PlayEffect.ENDER);
    }
}