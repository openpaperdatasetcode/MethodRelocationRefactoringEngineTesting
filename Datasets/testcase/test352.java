package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Non-sealed record source class (permits, two local inner classes; same package as target)
non-sealed record SourceRecord(String data) implements RecordOverrideInterface permits ExtendedSourceRecord {
    // Overriding method (final access, List<String> return, target inner param)
    @Override
    public final List<String> overrideMethod(TargetRecord.TargetInner targetInnerParam) {
        // Type declaration statement
        String localVar;
        List<String> resultList;

        // Variable call
        localVar = this.data();
        resultList = new ArrayList<>();
        resultList.add(localVar);

        // First local inner class (source feature)
        class LocalInnerOne {
            void addTargetData() {
                resultList.add(targetInnerParam.innerData());
            }
        }
        LocalInnerOne innerOne = new LocalInnerOne();
        innerOne.addTargetData();

        // Second local inner class (source feature)
        class LocalInnerTwo {
            void processList() {
                resultList.replaceAll(String::toUpperCase);
            }
        }
        LocalInnerTwo innerTwo = new LocalInnerTwo();
        innerTwo.processList();

        // No new exception (no exception instantiation)
        return resultList;
    }
}

// Extended record for permits feature
record ExtendedSourceRecord(String data, int num) extends SourceRecord {
    ExtendedSourceRecord {
        super(data);
    }
}

// Interface for overriding feature
interface RecordOverrideInterface {
    List<String> overrideMethod(TargetRecord.TargetInner targetInnerParam);
}

// Default modifier record target class (anonymous inner class feature)
record TargetRecord(String data) {
    // Target inner class (target_inner)
    public record TargetInner(String innerData) {}

    // Anonymous inner class (target_feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in TargetRecord: " + data);
        }
    };
}