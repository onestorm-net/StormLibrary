package net.onestorm.library.cost;

import net.onestorm.library.common.context.UserContext;

public interface Refundable {

    void refund(UserContext context);

}
