package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;
interface DataProcessor {void processData();}
protected class TargetClass {public static class TargetNested {private String name;
public TargetNested(String name) {this.name = name;}
public String getName() {return name;}
public TargetClass createTarget() {return new TargetClass();}}
private int count;
public void increment() {count++;}
public int getCount() {return count;}}
strictfp class SourceClass implements DataProcessor {class MemberInner {void validate(TargetClass target) {if (target == null) {throw new IllegalArgumentException("Target cannot be null");}}}
@Overridepublic void processData() {// Anonymous inner classRunnable runner = new Runnable() {@Overridepublic void run() {System.out.println("Processing data");}};runner.run();}
protected List<String> process(TargetClass.TargetNested... nesteds) {List<String> results = new ArrayList<>();TargetClass target = new TargetClass();int index = 0;
// Variable callObject varCall = target.getCount();
// Do-while statement with target instance methoddo {if (index < nesteds.length) {TargetClass newTarget = nesteds[index].createTarget();results.add(nesteds[index].getName());index++;} else {break;}} while (target.getCount() < 3);
// Switch statementswitch (results.size()) {case 0:results.add("empty");break;case 1:results.add("single");break;default:results.add("multiple");}
return results;}}
import org.junit.Test;import static org.junit.Assert.*;import java.util.List;
public class MoveMethodTest3128 {@Testpublic void testVarargsMethod() {SourceClass source = new SourceClass();TargetClass.TargetNested nested1 = new TargetClass.TargetNested("first");TargetClass.TargetNested nested2 = new TargetClass.TargetNested("second");
List<String> result = source.process(nested1, nested2);assertEquals(3, result.size());assertTrue(result.contains("first"));assertTrue(result.contains("second"));assertTrue(result.contains("multiple"));}}