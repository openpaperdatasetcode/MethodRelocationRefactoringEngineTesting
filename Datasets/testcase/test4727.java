package com.source;
import com.target.TargetClass;
public class SourceClass {private String sourceField = "SourceInstanceData";
public class MemberInner {public TargetClass getTargetWithField(TargetClass target) {target.setTargetData(sourceField);return target;}}
public strictfp static TargetClass processTarget(int count) {TargetClass target = null;try {target = new TargetClass("InitialStaticData");SourceClass instance = new SourceClass();MemberInner inner = instance.new MemberInner();target = inner.getTargetWithField(target);
int loopCount = 0;do {target = TargetClass.StaticNested.updateTarget(target, loopCount);System.out.println("Loop " + loopCount + ": " + target.getTargetData());loopCount++;} while (loopCount < count);
} catch (Exception e) {System.out.println("Processing caught exception: " + e.getMessage());}return target;}
public String getSourceField() {return sourceField;}}
// Different package: com.targetpackage com.target;
/**
Private target class with static nested helper
Stores and processes target-specific data*/private class TargetClass {private String targetData;
public TargetClass(String targetData) {super();this.targetData = targetData;}
public static class StaticNested {public static TargetClass updateTarget(TargetClass target, int index) {return new TargetClass(target.getTargetData() + "_" + index);}}
public String getTargetData() {return targetData;}
public void setTargetData(String targetData) {this.targetData = targetData;}}