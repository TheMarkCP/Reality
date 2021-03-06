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
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


/**
 * Created by Mark on 2015-07-20.
 */
public class BossBlaze implements Bosses {

    Reality plugin;
    BossesAPI bossesAPI;
    String name = ChatColor.GOLD.toString() + "Blaze";
    String skill = "Set target on fire for 3 seconds upon hit.";
    Integer health = 500;
    Integer damage = 3;
    Integer rewardXP = 15000;
    Integer rewardKey = 10;
    Double rewardMoney = 15000.0;
    NPC npc;

    public BossBlaze(Reality plugin){
        this.plugin = plugin;
        bossesAPI = new BossesAPI(plugin);
    }

    @Override
    public String getType(){
        return "blaze";
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
        npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.BLAZE, name);
        return npc;
    }

    @Override
    public void spawn(String w, double x, double y, double z, float pitch, float yaw){
        Location loc = new Location(Bukkit.getWorld(w), x, y, z, pitch, yaw);
        npc.spawn(loc);
        npc.setProtected(false);
        Creature creature = (Creature) npc.getEntity();
        PotionEffect potionEffect = new PotionEffect(PotionEffectType.SPEED, 10000, 10);
        creature.addPotionEffect(potionEffect);
        bossesAPI.basicSetup(creature, health);
    }

    public void spawn(Location loc){
        spawn(loc.getWorld().getName(), loc.getX(), loc.getY(), loc.getZ(), loc.getPitch(), loc.getYaw());
    }

    public void useAbility(Player p){
        p.setFireTicks(20*3);
        EffectsAPI.effect(p.getLocation(), EffectsAPI.PlayEffect.FIRE);
    }
}