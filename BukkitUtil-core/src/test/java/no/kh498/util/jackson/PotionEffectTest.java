package no.kh498.util.jackson;

import org.bukkit.Color;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Elg
 */
@Ignore
public class PotionEffectTest extends BukkitSerTestHelper {

    @Test
    public void serializeLocation() {
        testSer(new PotionEffect(PotionEffectType.CONFUSION, Integer.MAX_VALUE, 10, true, true, Color.RED));
    }
}