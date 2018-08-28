package com.cy.service;

import com.alibaba.fastjson.JSON;
import com.cy.pojo.Block;
import com.cy.pojo.Transaction;
import com.cy.util.BaseUtil;
import com.cy.util.ConstantUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BlockServiceImpl{

    public static void mineBlock(List<Block> blockChain, List<Transaction> txs, String address) {
        Transaction sysTs = new Transaction(BaseUtil.getUUID32(),"",address, ConstantUtil.AMOUNT_OF_REWARD);
        txs.add(sysTs);
        Block latestBlock = blockChain.get(blockChain.size() - 1);

        int nonce = 1;
        String hash = "";
        while (true){
            hash = BaseUtil.String2SHA256(latestBlock.getBHash() + JSON.toJSONString(txs) + nonce);
            if(hash.startsWith("0000")){
                System.out.println("计算结果正确，次数为:"+ nonce + ",hash:" + hash);
                break;
            }
            System.out.println("计算结果错误，次数为:"+ nonce + "，hash:" + hash);
            nonce++;
        }

        Block newBlock = new Block(latestBlock.getBIndex() + 1,hash,System.currentTimeMillis(), txs , nonce ,latestBlock.getBHash() );
        blockChain.add(newBlock);
        System.out.println("挖矿后的区块链为："+ JSON.toJSONString(blockChain));
    }

    public static BigDecimal getWalletBalance(List<Block> blockChain, String address) {
        BigDecimal balance = new BigDecimal(0);
        for(Block block : blockChain){
            List<Transaction> ts = block.getBTransaction();
            for(Transaction transaction : ts){
                if(address.equals(transaction.getMRecipient())){
                    balance = balance.add(transaction.getMAmout());
                }
                if(address.equals(transaction.getMSender())){
                    balance = balance.subtract(transaction.getMAmout());
                }
            }
        }
        return balance;
    }

    public static void main(String[] arg){
        List<Block> blockchain = new ArrayList<Block>();

        Block block = new Block(1,"1",System.currentTimeMillis(),new ArrayList<Transaction>(),1,"1");
        blockchain.add(block);
        System.out.println(JSON.toJSONString(blockchain));

        String sender = "sender_wallet";
        String recipient = "recipient_wallet";

        List<Transaction> txs = new ArrayList<Transaction>();
        mineBlock(blockchain, txs ,sender);
        System.out.println(sender + "钱包余额为：" + getWalletBalance(blockchain, sender));

        List<Transaction> txs1 = new ArrayList<Transaction>();
        Transaction ts1 = new Transaction(BaseUtil.getUUID32(),sender,recipient,new BigDecimal(3));
        Transaction ts2 = new Transaction(BaseUtil.getUUID32(),sender,recipient,new BigDecimal(1));
        txs1.add(ts1);
        txs1.add(ts2);

        mineBlock(blockchain, txs1 ,sender);
        System.out.println(sender + "钱包余额为：" + getWalletBalance(blockchain, sender));
        System.out.println(recipient + "钱包余额为：" + getWalletBalance(blockchain, recipient));
    }

}
