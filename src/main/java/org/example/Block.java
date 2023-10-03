package org.example;

import java.util.Date;
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
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
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

    public boolean addTransaction(Transaction transaction) {
        //process transaction and check if valid, unless block is genesis block then ignore.
        if(transaction == null){
            return false;
        }
        // 不是 Genesis Block
//        if((!"0".equals(header.getPreviousBlockHash()))) {
//            // check 交易是否合法
//            if((transaction.process() != true)) {
//                System.out.println("Transaction failed to process. Discarded.");
//                return false;
//            }
//        }

        transactions.add(transaction);
        System.out.println("Transaction Successfully added to Block");
        return true;
    }

    public BlockHeader getHeader() {
        return header;
    }

    public Block setHeader(BlockHeader header) {
        this.header = header;
        return this;
    }

    public int getSize() {
        return size;
    }

    public Block setSize(int size) {
        this.size = size;
        return this;
    }

    public long getTransactionCounter() {
        return transactionCounter;
    }

    public Block setTransactionCounter(long transactionCounter) {
        this.transactionCounter = transactionCounter;
        return this;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public Block setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
        return this;
    }
}

class BlockChain {
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    private int difficultyTarget = 2;
    public void createGenesisBlock(){
        blockchain.add(new Block("0","Genesis Merkle Root", difficultyTarget));
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
    //转换成 json 格式，方便查看
    public String toJson(){
        return new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
    }
}