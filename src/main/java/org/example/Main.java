package org.example;



public class Main {

    public static void main(String[] args) {
//        KeyGenerator keypair = new KeyGenerator();
//        // 打印公钥和私钥
//        System.out.println("FreeCoin");
//        System.out.println("Public Key (hex decimal): " + keypair.getPublicKey("hex"));
//        System.out.println("Public Key (base58Check): " + keypair.getPublicKey("base58"));
//        System.out.println("Private Key (hex decimal): " + keypair.getPrivateKey("hex"));
//        System.out.println("Private Key (base58Check): " + keypair.getPrivateKey("base58"));

        BlockChain chain = new BlockChain();
        chain.createGenesisBlock();
        chain.createBlock();
        chain.createBlock();
        chain.blockchain.get(1).getHeader().setMerkleRoot("666");
        System.out.println(chain.isChainValid());
//        System.out.println(chain.toJson());
    }
}