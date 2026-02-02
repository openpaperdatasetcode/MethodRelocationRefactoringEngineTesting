package test.refactoring.source;
import java.util.ArrayList;import java.util.List;import test.refactoring.target.TargetClass;
public class SourceClass {public static class StaticNestedClass {public String processItem(String item) {return "Processed-" + item;}}
public class SourceInnerClass {protected List<String> handleData(TargetClass targetParam) {List<String> result = new ArrayList<>();StaticNestedClass nested = new StaticNestedClass();TargetClass.TargetInnerRec targetInner = targetParam.new TargetInnerRec();
// Instance method reference + property assignmenttargetInner.setProcessor(nested::processItem);
// Enhanced for statementString[] items = {"a", "b", "c"};for (String item : items) {// 3 InfixExpressionsint score1 = item.length() + 2;int score2 = score1 * 3;int score3 = score2 - 1;result.add(targetInner.process(item, score3));}
// Switch statement with call_methodswitch (targetParam.getType()) {case 1 -> result.add(String.valueOf(this.calculateScore(10)));case 2 -> result.add(String.valueOf(this.calculateScore(20)));default -> result.add(String.valueOf(this.calculateScore(5)));}
return result;}
public int calculateScore(int base) {return base * 2 + 3;}}
// Local inner classpublic List<String> triggerInnerMethod(TargetClass target) {class LocalInnerHandler {public List<String> execute() {SourceInnerClass inner = new SourceInnerClass();return inner.handleData(target);}}return new LocalInnerHandler().execute();}}
package test.refactoring.target;
import java.util.List;import java.util.function.Function;
class TargetClass {private int type;
public class TargetInnerRec {private Function<String, String> processor;
public void setProcessor(Function<String, String> processor) {this.processor = processor;}
public String process(String item, int score) {return processor.apply(item) + "-Score:" + score;}}
public TargetClass(int type) {this.type = type;}
public int getType() {return type;}}
package test.refactoring;
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;import java.util.List;import test.refactoring.source.SourceClass;import test.refactoring.target.TargetClass;
public class MoveMethod5347Test {@Testvoid testMoveMethod() {TargetClass target = new TargetClass(1);SourceClass source = new SourceClass();List<String> expected = source.triggerInnerMethod(target);
assertEquals(4, expected.size());assertTrue(expected.containsAll(List.of("Processed-a-Score:4","Processed-b-Score:4","Processed-c-Score:4","23")));
// After refactoring: method moved to TargetClass.TargetInnerRecTargetClass refactoredTarget = new TargetClass(1);TargetClass.TargetInnerRec targetInner = refactoredTarget.new TargetInnerRec();SourceClass.SourceInnerClass sourceInner = new SourceClass().new SourceInnerClass();List<String> actual = targetInner.handleData(refactoredTarget, sourceInner);
assertEquals(expected, actual);}}