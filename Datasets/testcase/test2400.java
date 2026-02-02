package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Field;
interface Printable {void print();}
public class TargetClass implements Printable {public String publicField;private List<String> data = new ArrayList<>();
public TargetClass(String field) {this.publicField = field;new Runnable() {@Overridepublic void run() {data.add("initialized");}}.run();}
@Overridepublic void print() {System.out.println(publicField);}
public List<String> getData() {return new ArrayList<>(data);}}
sealed class SourceClass permits SourceSubClass {member class MemberInner {}static class StaticNested {}
public List<String> overloadedMethod(TargetClass target) {List<String> result = new ArrayList<>();Object varCall = target.publicField;
// Access instance fieldresult.add(target.publicField);result.addAll(target.getData());
// Reflection usagetry {Field field = TargetClass.class.getDeclaredField("data");field.setAccessible(true);@SuppressWarnings("unchecked")List<String> reflectedData = (List<String>) field.get(target);result.addAll(reflectedData);} catch (Exception e) {// No new exception thrown}
return result;}
public List<String> overloadedMethod() {return new ArrayList<>();}}
final class SourceSubClass extends SourceClass {}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3060 {@Testpublic void testOverloadedMethod() {SourceClass source = new SourceSubClass();TargetClass target = new TargetClass("test_field");List<String> result = source.overloadedMethod(target);assertEquals(3, result.size());}}