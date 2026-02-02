package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;
interface Operation {int execute();}
class BaseClass {protected String baseField = "base_value";
public int baseOperation() {return baseField.length();}}
class TargetClass extends BaseClass implements Operation {public String targetField1;public int targetField2;
public TargetClass(String targetField1, int targetField2) {this.targetField1 = targetField1;this.targetField2 = targetField2;}
public class TargetInner {private String innerData;
public TargetInner(String innerData) {this.innerData = innerData;}
public String getInnerData() {return innerData;}}
@Overridepublic int execute() {return targetField1.length() + targetField2;}}
class SourceClass extends BaseClass implements Operation {// Member inner classesclass SourceInner1 {}class SourceInner2 {}
@Overridepublic int execute() {return 0; // Default implementation}
public int process(TargetClass target) {// Variable callObject varCall = target.targetField1;
// Super keywordsint baseValue = super.baseOperation();
// Raw typeList rawList = new ArrayList();rawList.add(target.targetField1);
// Switch caseint caseValue = switch (target.targetField2) {case 0 -> 10;case 1 -> 20;default -> 30;};
// ThrowStatement with this.field (2 fields)protected void validateTarget() {if (target.targetField1 == null || target.targetField2 < 0) {throw new IllegalArgumentException("Invalid fields: " + target.targetField1 + ", " + target.targetField2);}}validateTarget();
return baseValue + caseValue + target.execute();}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3193 {@Testpublic void testOverridingMethod() {SourceClass source = new SourceClass();TargetClass target = new TargetClass("test", 2);
int result = source.process(target);assertEquals(8 + 30 + (4 + 2), result); // baseValue(8) + caseValue(30) + target.execute()(6) = 44}}