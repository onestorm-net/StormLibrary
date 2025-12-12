package net.onestorm.library.cost;

import net.onestorm.library.common.context.UserContext;


public interface Cost {

    /**
     * Returns the identifier for this cost.
     *
     * @return the unique name of this cost
     */
    String getName();

    /**
     * Determines whether the user represented by the given context is able to pay this cost.
     *
     * @param context the user context in which the payment is evaluated
     * @return {@code true} if the user can pay this cost; {@code false} otherwise
     */
    boolean canPay(UserContext context);

    /**
     * Deducts this cost from the user represented by the given context.
     *
     * @param context the user context in which the payment is processed
     * @return a {@link Refundable} representing the applied payment, used for potential rollback
     * @throws RuntimeException if the cost cannot be paid for any reason
     */
    Refundable pay(UserContext context);

}
