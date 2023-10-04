package org.example;
import java.util.Stack;

public class Script {

    private Stack<String> stack;
    private String[] commands;

    public Script(String script) {
        this.stack = new Stack<>();
        this.commands = script.split(" ");
    }

    public boolean execute() {
        for (String command : commands) {
            if (command.equals("OP_DUP")) {
                if (stack.isEmpty()) return false;
                stack.push(stack.peek());
            } else if (command.equals("OP_HASH160")) {
                if (stack.isEmpty()) return false;
                String data = stack.pop();
                stack.push(Util.sha256(data));
            } else if (command.equals("OP_EQUALVERIFY")) {
                if (stack.size() < 2) return false;
                if (!stack.pop().equals(stack.pop())) return false;
            } else if (command.equals("OP_CHECKSIG")) {
                // Implement signature checking here
                // !!!
            } else {
                stack.push(command);
            }
        }
        return true;
    }

    public Stack<String> getStack() {
        return stack;
    }
}
