package com.ewallet.wallet.manager;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface WalletManager {
    public void createWallet(String user);
    public void updateWallet(String updateRequest) throws JsonProcessingException;
}
