package org.example;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.GsonBuilder;

class BlockHeader {
    private String previousBlockHash;
    private String merkleRoot;
    private long timeStamp;
    private int difficultyTarget;
    private int nonce;

    public String calculateHash(){
        return Util.sha256(previousBlockHash + merkleRoot + timeStamp + difficultyTarget + nonce);
    }

    BlockHeader(String previousBlockHash, String merkleRoot, int difficultyTarget) {
        this.previousBlockHash = previousBlockHash;
        this.merkleRoot = merkleRoot;
        this.timeStamp = new Date().getTime();
        this.difficultyTarget = difficultyTarget;
        this.nonce = 0;// nonce 在后面 mint 的过程中不断改变
    }

    public String getPreviousBlockHash() {
        return previousBlockHash;
    }

    public void setPreviousBlockHash(String previousBlockHash) {
        this.previousBlockHash = previousBlockHash;
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public void setMerkleRoot(String merkleRoot) {
        this.merkleRoot = merkleRoot;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getDifficultyTarget() {
        return difficultyTarget;
    }

    public void setDifficultyTarget(int difficultyTarget) {
        this.difficultyTarget = difficultyTarget;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }
}

class Block {
    private BlockHeader header;
    private int size;                    // Size of the block in bytes after this field
    private long transactionCounter;      // Number of transactions in this block (VarInt) 待实现可变整数
    private List<Transaction> transactions;

    public String calculateHash(){
        return header.calculateHash();
    }
    public Block(String previousBlockHash, String merkleRoot, int difficultyTarget)
    {
        this.header = new BlockHeader(previousBlockHash, merkleRoot, difficultyTarget);
    }
//    public Block(BlockHeader header, int size, long transactionCounter, List<Transaction> transactions) {
//        this.header = header;
//        this.size = size;
//        this.transactionCounter = transactionCounter;
//        this.transactions = transactions;
//    }

    public BlockHeader getHeader() {
        return header;
    }

    public void setHeader(BlockHeader header) {
        this.header = header;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTransactionCounter() {
        return transactionCounter;
    }

    public void setTransactionCounter(long transactionCounter) {
        this.transactionCounter = transactionCounter;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}

class BlockChain {
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    private int difficultyTarget = 2;
    public void createGenesisBlock(){
        blockchain.add(new Block("0","1", difficultyTarget));
    }
    public void createBlock(){
        if(blockchain.size() > 1)
            blockchain.add(new Block(getLatestBlock().calculateHash(),"1", difficultyTarget));
        else
            createGenesisBlock();
    }

    public boolean isChainValid(){
        Block currentBlock;
        Block previousBlock;
        // 从 Genesis Block 之后开始循环
        for(int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
            if(!currentBlock.getHeader().getPreviousBlockHash().equals(previousBlock.calculateHash())){
                return false;
            }
        }
        return true;
    }
    // 获取链条顶部的区块
    public Block getLatestBlock(){
        return blockchain.get(blockchain.size() - 1); //!blockchain.isEmpty() ? null :
    }
    public String toJson(){
        return new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
    }
}