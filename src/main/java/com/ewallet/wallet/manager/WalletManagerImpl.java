package com.ewallet.wallet.manager;

import com.ewallet.TransactionManagementSystem.model.TransactionStatus;
import com.ewallet.TransactionManagementSystem.model.TransactionType;
import com.ewallet.TransactionManagementSystem.model.TransactionUpdate;
import com.ewallet.wallet.entity.Wallet;
import com.ewallet.wallet.model.UpdateWalletRequest;
import com.ewallet.wallet.repository.WalletRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WalletManagerImpl implements WalletManager{
    @Autowired
    WalletRepository walletRepository;
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @KafkaListener(topics = "createWallet", groupId = "wallet")
    public void createWallet(String user) {
        Wallet wallet = Wallet.builder()
                .balance(0D)
                .userId(user)
                .build();
        walletRepository.save(wallet);
    }

    @Override
    @KafkaListener(topics = "updateWallet", groupId = "wallet")
    public void updateWallet(String updateRequest) throws JsonProcessingException {
        UpdateWalletRequest updateWalletRequestObj = objectMapper
                .readValue(updateRequest, UpdateWalletRequest.class);

        try {
            Wallet fromUserWallet = walletRepository.findByUserId(updateWalletRequestObj.getFromUser())
                    .orElseThrow(() -> new Exception("wallet is not found for user"));
            Wallet toUserWallet = walletRepository.findByUserId(updateWalletRequestObj.getToUser())
                    .orElseThrow(() -> new Exception("wallet is not found for user"));

            if (updateWalletRequestObj.getTransactionType().name().equalsIgnoreCase(TransactionType.CREDIT.name())) {
                if (fromUserWallet.getBalance() - updateWalletRequestObj.getAmount() < 0) {
                    throw new Exception("no sufficient balance");
                }
                 fromUserWallet.setBalance(fromUserWallet.getBalance() - updateWalletRequestObj.getAmount());
                 toUserWallet.setBalance(toUserWallet.getBalance() + updateWalletRequestObj.getAmount());
            }

            if (updateWalletRequestObj.getTransactionType().name().equalsIgnoreCase(TransactionType.DEBIT.name())) {
                if (toUserWallet.getBalance() - updateWalletRequestObj.getAmount() < 0) {
                    throw new Exception("no sufficient balance");
                }
                toUserWallet.setBalance(toUserWallet.getBalance() - updateWalletRequestObj.getAmount());
                fromUserWallet.setBalance(fromUserWallet.getBalance() + updateWalletRequestObj.getAmount());
            }
            walletRepository.save(fromUserWallet);
            walletRepository.save(toUserWallet);
            TransactionUpdate transactionUpdate = TransactionUpdate
                    .builder()
                    .transactionId(updateWalletRequestObj.getTransactionId())
                    .transactionStatus(TransactionStatus.SUCCESS)
                    .build();
            kafkaTemplate.send("transactionUpdate", objectMapper.writeValueAsString(transactionUpdate));


        }catch (Exception e){
            TransactionUpdate transactionUpdate = TransactionUpdate
                    .builder()
                    .transactionId(updateWalletRequestObj.getTransactionId())
                    .transactionStatus(TransactionStatus.REJECTED)
                    .build();
            kafkaTemplate.send("transactionUpdate", objectMapper.writeValueAsString(transactionUpdate));

        }


    }
}
