package org.example;

import java.util.List;

class OutPoint {
    private String hash;
    private int n;

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
    //private long nSequence;    // temporarily don't know this variable

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
    private List<TransactionInput> inputs;
    private List<TransactionOutput> outputs;
    private int lockTime;

    public Transaction(List<TransactionInput> inputs, List<TransactionOutput> outputs, int lockTime) {
        this.inputs = inputs;
        this.outputs = outputs;
        this.lockTime = lockTime;
    }

    public List<TransactionInput> getInputs() {
        return inputs;
    }

    public void setInputs(List<TransactionInput> inputs) {
        this.inputs = inputs;
    }

    public List<TransactionOutput> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<TransactionOutput> outputs) {
        this.outputs = outputs;
    }

    public int getLockTime() {
        return lockTime;
    }

    public void setLockTime(int lockTime) {
        this.lockTime = lockTime;
    }
}