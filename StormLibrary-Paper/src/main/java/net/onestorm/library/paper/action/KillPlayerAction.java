package net.onestorm.library.paper.action;


import net.onestorm.library.paper.user.PaperOnlineUser;
import org.bukkit.entity.Player;

public class KillPlayerAction extends PaperAction {

    @Override
    void execute(PaperOnlineUser user) {
        Player player = user.asPlayer();

        // todo change source to default

        player.setHealth(0.0D);
    }
}
