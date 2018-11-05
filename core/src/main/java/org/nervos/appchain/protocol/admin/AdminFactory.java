package org.nervos.appchain.protocol.admin;

import org.nervos.appchain.protocol.AppChainjService;

public class AdminFactory {
    public static Admin build(AppChainjService web3jService) {
        return new JsonRpc2_0Admin(web3jService);
    }
}
