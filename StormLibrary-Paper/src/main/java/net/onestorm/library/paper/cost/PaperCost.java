package net.onestorm.library.paper.cost;

import net.onestorm.library.common.context.UserContext;
import net.onestorm.library.cost.Cost;
import net.onestorm.library.cost.Refundable;
import net.onestorm.library.paper.context.PlayerContext;

public abstract class PaperCost implements Cost {

    @Override
    public boolean canPay(UserContext context) {
        if (!(context instanceof PlayerContext playerContext)) {
            return false;
        }
        return canPay(playerContext);
    }

    public abstract boolean canPay(PlayerContext context);

    @Override
    public Refundable pay(UserContext context) {
        if (!(context instanceof PlayerContext playerContext)) {
            throw new IllegalArgumentException("This Cost needs a PlayerContext (Paper) to be able to pay");
        }
        return pay(playerContext);
    }

    public abstract Refundable pay(PlayerContext context);

}
