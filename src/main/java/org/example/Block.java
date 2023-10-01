package org.example;

import java.util.List;

class BlockHeader {
    private String previousBlockHash;
    private String merkleRoot;
    private long timeStamp;
    private int difficultyTarget;
    private int nonce;

    BlockHeader(String previousBlockHash, String merkleRoot, long timeStamp, int difficultyTarget, int nonce) {
        this.previousBlockHash = previousBlockHash;
        this.merkleRoot = merkleRoot;
        this.timeStamp = timeStamp;
        this.difficultyTarget = difficultyTarget;
        this.nonce = nonce;
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

    public Block(BlockHeader header, int size, long transactionCounter, List<Transaction> transactions) {
        this.header = header;
        this.size = size;
        this.transactionCounter = transactionCounter;
        this.transactions = transactions;
    }

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