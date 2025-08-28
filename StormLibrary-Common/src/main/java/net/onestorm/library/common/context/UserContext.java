package net.onestorm.library.common.context;

import net.onestorm.library.user.User;

public interface UserContext {

    User getUser();

    static UserContext of(User user) {
        return () -> user;
    }

}
