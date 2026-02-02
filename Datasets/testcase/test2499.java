package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;
interface Processable {void execute();}
protected class TargetClass implements Processable {private String targetField;
class TargetInner {public String getValue() {return targetField;}}
public TargetClass(String field) {this.targetField = field;}
@Overridepublic void execute() {}}
sealed class SourceClass permits SourceSubClass {class SourceInner1 {}class SourceInner2 {}
class SourceInner {List<String> process(TargetClass target) {if (target == null) {throw new NullPointerException("Target cannot be null");}
TargetClass.TargetInner inner = target.new TargetInner();Object varCall = inner.getValue();
List<String> result = new ArrayList<>();TargetClass newTarget = new TargetClass("new_value");
labeledBlock: {if (target.targetField == null) {break labeledBlock;}result.add(target.targetField);if (result.size() >= 1) {break labeledBlock;}}
try {result.add(inner.getValue());result.add(newTarget.targetField);} catch (Exception e) {// No new exception thrown}
return result;}}}
final class SourceSubClass extends SourceClass {}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3050 {@Testpublic void testInstanceMethod() {SourceClass source = new SourceSubClass();SourceClass.SourceInner inner = source.new SourceInner();TargetClass target = new TargetClass("test");List<String> result = inner.process(target);assertEquals(3, result.size());}}