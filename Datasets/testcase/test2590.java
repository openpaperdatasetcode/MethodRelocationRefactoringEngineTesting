package test.refactoring.movemethod;
import java.io.IOException;
class ParentClass {protected int parentField;}
private class TargetClass extends ParentClass {public int targetField;
public TargetClass(int value) {this.targetField = value;new Runnable() {@Overridepublic void run() {parentField = 2;}}.run();}
public TargetClass copy() {return new TargetClass(this.targetField);}}
protected class SourceClass {member class MemberInner {}static class StaticNested {}
TargetClass sourceMethod(TargetClass target) {class PrivateLocalType {TargetClass process(TargetClass t) {return new TargetClass(t.targetField + t.parentField);}}
PrivateLocalType localVar = new PrivateLocalType();Object varCall = localVar.process(target);
try {if (target == null) {throw new IOException("Target is null");}} catch (IOException e) {// No new exception thrown}
TargetClass newTarget1 = new TargetClass(2).copy();TargetClass newTarget2 = new TargetClass(5).copy();
return localVar.process(newTarget1);}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3040 {@Testpublic void testNormalMethod() {SourceClass source = new SourceClass();TargetClass target = new TargetClass(3);TargetClass result = source.sourceMethod(target);assertNotNull(result);assertEquals(5, result.targetField);}}