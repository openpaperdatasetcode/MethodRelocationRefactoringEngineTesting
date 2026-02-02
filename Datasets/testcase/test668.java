package com.refactor.test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Source record class - protected modifier, same package as target, two anonymous inner classes
protected record SourceRecord(String id, TargetRecord targetField) { // per_condition: contains target class field
    // First anonymous inner class (feature requirement)
    private final Consumer<String> firstAnonymous = new Consumer<>() {
        @Override
        public void accept(String s) {
            System.out.println("First anonymous: " + s);
        }
    };

    // Second anonymous inner class (feature requirement - duplicate as specified)
    private final Runnable secondAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("Second anonymous");
        }
    };

    // Target method to move - meets all specified features
    public List<String> methodToMove() {
        // Expression statement (feature)
        String temp = "test";
        List<String> result = new ArrayList<>();
        
        // Variable call (feature)
        firstAnonymous.accept(temp);
        secondAnonymous.run();
        
        // Depends on inner class (feature - uses anonymous inner classes)
        result.add(firstAnonymous.toString());
        result.add(secondAnonymous.toString());
        
        // ThisExpression with numbers=2, public modifier (feature)
        SourceRecord thisInstance = this;
        result.add(thisInstance.id() + 2);
        result.add(String.valueOf(2));
        
        // Constructor invocation (feature)
        TargetRecord targetInstance = new TargetRecord("target-1", new Runnable() {});
        
        // Requires try-catch (feature)
        try {
            // Expression statement within try block
            int num = Integer.parseInt(temp + 2);
            result.add(String.valueOf(num));
        } catch (NumberFormatException e) {
            result.add(e.getMessage());
        }
        
        return result;
    }
}

// Target record class - public modifier, anonymous inner class (target_feature)
public record TargetRecord(String name, Runnable anonymousField) { // anonymous inner class as target_feature
    public TargetRecord {
        // Anonymous inner class in target record constructor (fulfills target_feature)
        Runnable targetAnonymous = new Runnable() {
            @Override
            public void run() {
                System.out.println("Target anonymous inner class");
            }
        };
    }
}