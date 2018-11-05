package org.nervos.appchain.protocol;

import java.util.concurrent.ScheduledExecutorService;

import org.nervos.appchain.protocol.core.JsonRpc2_0AppChainj;

public class AppChainjFactory {
    /**
     * Construct a new AppChainj instance.
     *
     * @param appChainjService AppChainj service instance - i.e. HTTP or IPC
     * @return new AppChainj instance
     */
    public static AppChainj build(AppChainjService appChainjService) {
        return new JsonRpc2_0AppChainj(appChainjService);
    }

    /**
     * Construct a new AppChainj instance.
     *
     * @param appChainjService AppChainj service instance - i.e. HTTP or IPC
     * @param pollingInterval polling interval for responses from network nodes
     * @param scheduledExecutorService executor service to use for scheduled tasks.
     *                                 <strong>You are responsible for terminating this thread
     *                                 pool</strong>
     * @return new AppChainj instance
     */
    public static AppChainj build(
            AppChainjService appChainjService, long pollingInterval,
            ScheduledExecutorService scheduledExecutorService) {
        return new JsonRpc2_0AppChainj(appChainjService, pollingInterval, scheduledExecutorService);
    }
}
