package net.onestorm.library.cost;

import net.onestorm.library.common.context.UserContext;


public interface Cost {

    String getName();

    boolean canPay(UserContext user);

    Refundable pay(UserContext user);

}
