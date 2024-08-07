package net.onestorm.library.paper.action;

import net.onestorm.library.action.Action;
import net.onestorm.library.common.factory.Builder;
import net.onestorm.library.storage.StorageMap;

public class KillPlayerActionBuilder implements Builder<Action> {

    private static final String ACTION_BUILDER_NAME = "kill-player";

    @Override
    public String getName() {
        return ACTION_BUILDER_NAME;
    }

    @Override
    public Action build(StorageMap storage) {
        return new KillPlayerAction();
    }
}
