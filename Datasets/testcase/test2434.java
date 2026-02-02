package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;
interface DataProcessor {List<String> processData();}
final class TargetClass {public static class TargetInner {private String[] items;
public TargetInner(String... items) {this.items = items;}
public String getItem(int index) {return items[index];}
public int getSize() {return items.length;}}}
public abstract class SourceClass implements DataProcessor {protected TargetClass.TargetInner inner;
public SourceClass(TargetClass.TargetInner inner) {this.inner = inner;}
public SourceClass() {this(new TargetClass.TargetInner("default"));}
/**
Processes items from TargetInner and returns a list of strings.
Handles null cases and uses break statement in loop.
@return processed list of strings
*/
@Override
protected abstract List<String> processData();
class MemberInner {void validateInner() {if (inner == null) {throw new NullPointerException("TargetInner cannot be null");}}}
void localClassDemo() {class LocalProcessor {List<String> extractItems() {List<String> result = new ArrayList<>();for (int i = 0; i < inner.getSize(); i++) {Object varCall = inner.getItem(i);if (varCall == null) {break;}result.add((String) varCall);}return result;}}new LocalProcessor().extractItems();}}
class ConcreteSource extends SourceClass {public ConcreteSource(TargetClass.TargetInner inner) {super(inner);}
@Overrideprotected List<String> processData() {new MemberInner().validateInner();List<String> result = new ArrayList<>();int size = inner.getSize();
for (int i = 0; i < size; i++) {String item = inner.getItem(i);if (item == null) {break;}result.add(item.toUpperCase());}
return result;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3117 {@Testpublic void testAbstractMethod() {TargetClass.TargetInner inner = new TargetClass.TargetInner("a", "b", null, "c");SourceClass source = new ConcreteSource(inner);List<String> result = source.processData();assertEquals(2, result.size());assertEquals("A", result.get(0));}}