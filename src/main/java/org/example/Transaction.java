package org.example;

import java.util.ArrayList;
import java.util.List;

class OutPoint {
    private String hash; // 交易的 hash
    private int n;// 指明是哪一个输出

    public OutPoint(String hash, int n) {
        this.hash = hash;
        this.n = n;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    // other methods like equals, hashCode, and toString...
}

class TransactionInput {
    private OutPoint preOutput;
    private String scriptSig;  // wait for it to be perfected into a script type.
    private long nSequence;

    public TransactionInput(OutPoint preOutput, String scriptSig) {//, long nSequence
        this.preOutput = preOutput;
        this.scriptSig = scriptSig;
        //this.nSequence = nSequence;
    }

    public OutPoint getPreOutput() {
        return preOutput;
    }

    public void setPreOutput(OutPoint preOutput) {
        this.preOutput = preOutput;
    }

    public String getScriptSig() {
        return scriptSig;
    }

    public void setScriptSig(String scriptSig) {
        this.scriptSig = scriptSig;
    }

//    public long getnSequence() {
//        return nSequence;
//    }
//
//    public void setnSequence(long nSequence) {
//        this.nSequence = nSequence;
//    }

    //other methods...
}

class TransactionOutput {
    private long value;
    private String scriptPubKey;

    public TransactionOutput(long value, String scriptPubKey) {
        this.value = value;
        this.scriptPubKey = scriptPubKey;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String getScriptPubKey() {
        return scriptPubKey;
    }

    public void setScriptPubKey(String scriptPubKey) {
        this.scriptPubKey = scriptPubKey;
    }
}

class Transaction {
    private ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    private ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();
    private int lockTime;

    public Transaction() {
    }

    public String calculateHash() {
        // 这里可以根据你的需要添加更多的信息
        String data = "";
        for (TransactionInput input : inputs) {
            data += input.getPreOutput().getHash() + input.getPreOutput().getN();
        }
        for (TransactionOutput output : outputs) {
            data += output.getValue() + output.getScriptPubKey();
        }
        data += lockTime;
        return Util.sha256(data);
    }

    public ArrayList<TransactionInput> getInputs() {
        return inputs;
    }

    public void setInputs(ArrayList<TransactionInput> inputs) {
        this.inputs = inputs;
    }

    public ArrayList<TransactionOutput> getOutputs() {
        return outputs;
    }

    public void setOutputs(ArrayList<TransactionOutput> outputs) {
        this.outputs = outputs;
    }

    public int getLockTime() {
        return lockTime;
    }

    public void setLockTime(int lockTime) {
        this.lockTime = lockTime;
    }
}