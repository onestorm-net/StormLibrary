package net.onestorm.library.cost;

import net.onestorm.library.common.context.UserContext;

public interface Refundable {

    /**
     * Reverts the previously applied payment associated with this {@code Refundable}.
     *
     * @param context the user context in which the refund is applied
     * @throws RuntimeException if the refund cannot be completed
     */
    void refund(UserContext context);

}
