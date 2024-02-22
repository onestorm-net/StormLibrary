package net.onestorm.library.paper.user;

import net.onestorm.library.user.User;
import org.bukkit.entity.Player;

public interface PaperUser extends User {

    Player asPlayer();

}
