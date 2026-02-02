package test;
import java.util.List;
public enum SourceEnum {INSTANCE_1, INSTANCE_2;
private class SourceInner {private int innerField;
public int getInnerField() {return innerField;}}
@Deprecatedprivate SourceEnum() {super();TargetEnum target = TargetEnum.INSTANCE_A;TargetEnum.SourceInner targetInner = target.new SourceInner();
private List<? extends Number> boundedList = List.of(1);private int superFieldRef = boundedList.get(0).intValue();
int instanceResult = targetInner.processTargetField(target.field);if (instanceResult != 1) {throw new IllegalArgumentException("Invalid instance method result");}
new Runnable() {public void run() {System.out.println("Anonymous inner uses target: " + target);}}.run();
return;}}
public enum TargetEnum {INSTANCE_A(1), INSTANCE_B(2);
int field;
private TargetEnum(int field) {this.field = field;}
class SourceInner {public int processTargetField(int targetField) {return targetField;}}}