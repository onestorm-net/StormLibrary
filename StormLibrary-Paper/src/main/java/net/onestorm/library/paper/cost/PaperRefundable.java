package net.onestorm.library.paper.cost;

import net.onestorm.library.common.context.UserContext;
import net.onestorm.library.cost.Refundable;
import net.onestorm.library.paper.context.PlayerContext;

public abstract class PaperRefundable implements Refundable {

    @Override
    public void refund(UserContext context) {
        if (!(context instanceof PlayerContext playerContext)) {
            throw new IllegalArgumentException("This Cost needs a PlayerContext (Paper) to be able to refund");
        }

        refund(playerContext);
    }

    public abstract void refund(PlayerContext context);
}
