package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;
final class TargetClass {static class TargetInner {private int innerField;
public TargetInner(int value) {this.innerField = value;}
public int getInnerField() {return innerField;}
static void staticMethod(TargetInner inner) {inner.innerField *= 2;}}}
private class SourceClass {static class StaticNested1 {}static class StaticNested2 {}private static int staticField = 2;private int outerPrivate = 5;
final int sourceMethod(TargetClass.TargetInner targetInner) {List rawList = new ArrayList();Object varCall = rawList;
rawList.add(targetInner.getInnerField());varCall = outerPrivate;
assert targetInner.getInnerField() > 0 : "Inner field must be positive";
int count = 0;do {targetInner.getInnerField();count++;} while (count < staticField);
TargetClass.TargetInner[] innerArray = {new TargetClass.TargetInner(3),new TargetClass.TargetInner(5)};TargetClass.TargetInner.staticMethod(innerArray[0]).getInnerField();
return targetInner.getInnerField() + outerPrivate;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3041 {@Testpublic void testInstanceMethod() {SourceClass source = new SourceClass();TargetClass.TargetInner targetInner = new TargetClass.TargetInner(4);int result = source.sourceMethod(targetInner);assertEquals(9, result);}}