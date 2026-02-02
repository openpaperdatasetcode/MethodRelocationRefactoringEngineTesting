package test;
protected class SourceClass<T> {private T sourceData;
public SourceClass(T sourceData) {this.sourceData = sourceData;}
public void initProcessor() {class SourceInner {public TargetClass processTarget(TargetClass target, int depth) {if (depth <= 0) {return new TargetClass(target.getTargetId(), sourceData.toString());}
new Runnable() {@Overridepublic void run() {System.out.println("Processing depth: " + depth);String combined = sourceData.toString() + "-" + target.getTargetData();target.setTargetData(combined);}}.run();
TargetClass updatedTarget = new TargetClass(target.getTargetId() + 1, target.getTargetData());return processTarget(updatedTarget, depth - 1);}}
TargetClass initialTarget = TargetClass.StaticNested.create(1, "InitialData");SourceInner inner = new SourceInner();TargetClass finalTarget = inner.processTarget(initialTarget, 3);System.out.println("Final Target: " + finalTarget.getTargetData());}
public T getSourceData() {return this.sourceData;}}
/**
Target class with static nested helper and data management
Supports ID and data storage for processing*/public class TargetClass {private int targetId;private String targetData;
public TargetClass(int targetId, String targetData) {this.targetId = targetId;this.targetData = targetData;}
public int getTargetId() {return targetId;}
public String getTargetData() {return targetData;}
public void setTargetData(String targetData) {this.targetData = targetData;}
public static class StaticNested {public static TargetClass create(int id, String data) {return new TargetClass(id, data);}}}