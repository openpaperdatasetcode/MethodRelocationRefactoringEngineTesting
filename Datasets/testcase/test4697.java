package test;
class ParentSourceClass {protected String parentField = "ParentSourceData";}
public class SourceClass extends ParentSourceClass {private TargetClass targetField;
static class StaticNestedHelper {public static TargetClass.TargetInner createTargetInner(int value) {return new TargetClass.TargetInner(value);}}
public void initTarget() {class LocalTargetInitializer {public TargetClass buildTarget(String data) {return new TargetClass(data);}}LocalTargetInitializer initializer = new LocalTargetInitializer();this.targetField = initializer.buildTarget("InitialTargetData");}
int processTargetInner(TargetClass.TargetInner inner, int depth) {if (depth <= 0) {return inner.getValue();}
System.out.println("Current depth: " + depth + ", Parent field: " + SourceClass.this.parentField);System.out.println("Target inner value: " + inner.getValue());
TargetClass.TargetInner newInner = StaticNestedHelper.createTargetInner(inner.getValue() + 1);return processTargetInner(newInner, depth - 1);}
public int startProcessing(int depth) {if (this.targetField == null) {initTarget();}return processTargetInner(this.targetField.createInner(), depth);}}
final class TargetClass {private String targetData;
public TargetClass(String targetData) {this.targetData = targetData;}
public static class TargetInner {private int innerValue;
public TargetInner(int innerValue) {this.innerValue = innerValue;}
public int getValue() {return innerValue;}
public void updateValue(int delta) {this.innerValue += delta;}}
public TargetInner createInner() {return new TargetInner(this.targetData.length());}
public String getTargetData() {return targetData;}}