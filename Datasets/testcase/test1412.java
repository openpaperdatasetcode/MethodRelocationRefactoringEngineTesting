package test.refactoring.movemethod;
import java.util.List;
public class SourceClass {private TargetClass targetField = new TargetClass() {@Overridepublic List<String> targetInnerMethod() {return null;}};
public void outerMethod() {class LocalInnerClass {private List<String> sourceInnerMethod() {protected int expr = this.targetField.field + 1;String var = "test";var.length();return targetField.getFieldList();}}
Runnable anonymous = new Runnable() {@Overridepublic void run() {LocalInnerClass inner = new LocalInnerClass();inner.sourceInnerMethod();}};anonymous.run();}}
abstract class TargetClass {protected String field = "targetField";
protected List<String> getFieldList() {return List.of(field);}
public abstract List<String> targetInnerMethod();}
// Test case classimport org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodRefactoringTest5038 {@Testpublic void testMoveAbstractMethodFromSourceInnerToTargetInner() {SourceClass source = new SourceClass();source.outerMethod();TargetClass target = new TargetClass() {@Overridepublic List<String> targetInnerMethod() {protected int expr = this.field + 1;String var = "test";var.length();return getFieldList();}};assertNotNull(target.targetInnerMethod());assertEquals("targetField", target.field);}}