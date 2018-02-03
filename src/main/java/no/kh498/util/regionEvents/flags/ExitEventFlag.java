package no.kh498.util.regionEvents.flags;

import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.session.MoveType;
import com.sk89q.worldguard.session.Session;
import no.kh498.util.regionEvents.events.RegionExitEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Set;

public class ExitEventFlag extends RegionMoveEventFlag {


    public static final ExitEventFlag.Factory FACTORY = new Factory();

    ExitEventFlag(final Session session) {
        super(session);
    }

    /**
     * The event to call
     */
    @Override
    boolean callEvent(final Player player, final Location from, final Location to, final Set<ProtectedRegion> entered,
                      final Set<ProtectedRegion> exited, final MoveType moveType, final boolean hasBypass) {
        final RegionExitEvent regionExitEvent = new RegionExitEvent(player, from, to, exited, moveType, hasBypass);
        Bukkit.getPluginManager().callEvent(regionExitEvent);
        return regionExitEvent.isCancelled();
    }

    @Override
    public boolean testNewRegion(final Set<ProtectedRegion> entered, final Set<ProtectedRegion> exited) {
        return exited.size() > 0;
    }

    @Override
    public StateFlag getFlagType() {
        return DefaultFlag.EXIT;
    }

    public static class Factory extends com.sk89q.worldguard.session.handler.Handler.Factory<ExitEventFlag> {

        Factory() {
        }

        @Override
        public ExitEventFlag create(final Session session) {
            return new ExitEventFlag(session);
        }
    }
}