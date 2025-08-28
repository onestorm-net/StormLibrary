package net.onestorm.library.paper.context;

import net.onestorm.library.common.context.UserContext;
import org.bukkit.entity.Player;

public interface PlayerContext extends UserContext {

    Player getPlayer();

}
