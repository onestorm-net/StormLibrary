package net.onestorm.library.action;

import net.onestorm.library.user.OnlineUser;

public interface Action {

    void execute(OnlineUser user);

}
