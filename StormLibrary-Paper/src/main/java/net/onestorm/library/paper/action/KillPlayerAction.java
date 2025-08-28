package net.onestorm.library.paper.action;


import net.onestorm.library.paper.context.PlayerContext;
import org.bukkit.entity.Player;

public class KillPlayerAction extends PaperAction {

    @Override
    public void execute(PlayerContext context) {
        Player player = context.getPlayer();

        // todo change source to default

        player.setHealth(0.0D);
    }
}
