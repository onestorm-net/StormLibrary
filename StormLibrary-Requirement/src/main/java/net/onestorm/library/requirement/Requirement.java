package net.onestorm.library.requirement;

import net.onestorm.library.user.OnlineUser;

public interface Requirement {

    /**
     * Checks if the user meets the requirement.
     * @param user The user.
     * @return True when user meets the requirement.
     */
    boolean check(OnlineUser user);

}
