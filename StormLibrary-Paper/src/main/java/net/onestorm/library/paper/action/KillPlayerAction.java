package net.onestorm.library.paper.action;


import net.onestorm.library.paper.user.PaperUser;
import org.bukkit.entity.Player;

public class KillPlayerAction extends PaperAction {

    @Override
    public void execute(PaperUser user) {
        Player player = user.asPlayer();

        // todo change source to default

        player.setHealth(0.0D);
    }
}
