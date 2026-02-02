package test.refactoring.movemethod;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.ArrayList;import java.util.List;import java.util.function.Predicate;
interface Filterable {boolean filter(String input);}
@Retention(RetentionPolicy.RUNTIME)@interface EnumMethodAnnot {}
// Protected target enum with implements and anonymous inner classprotected enum TargetEnum implements Filterable {VALUE1("one"),VALUE2("two");
public String label;
TargetEnum(String label) {this.label = label;// Anonymous inner class in targetPredicate<String> validator = new Predicate<>() {@Overridepublic boolean test(String s) {return s != null && !s.isEmpty();}};if (!validator.test(label)) {throw new IllegalArgumentException("Invalid label");}}
@Overridepublic boolean filter(String input) {return input.contains(label);}}
// Sealed source enum with type parameter and extendssealed enum SourceEnum<T extends CharSequence> extends EnumBase permits ConcreteSourceEnum {INSTANCE;
@EnumMethodAnnotpublic List<String> process(TargetEnum target) {List<String> results = new ArrayList<>();
// Local inner classclass StringProcessor {String process(String input) {return input + "_" + target.label;}}StringProcessor processor = new StringProcessor();
// Anonymous inner classRunnable appender = new Runnable() {@Overridepublic void run() {results.add(target.label);}};appender.run();
// Constructor invocationEnumBase base = new ConcreteEnumBase(target.label);
// Super keywordsresults.add(super.getBaseInfo());
// Variable callObject varCall = target.label;
// With bounds (T extends CharSequence)T boundedValue = (T) "bounded_" + target.label;results.add(boundedValue.toString());
// Override violation (missing generic type)class BadFilter implements Predicate {@Overridepublic boolean test(Object o) {return o.toString().length() > 0;}}
// Do statementint count = 0;do {results.add(processor.process("item" + count));count++;} while (count < 2);
// IfStatement with obj.field (1 occurrence)private void checkLabel() {if (target.label.length() > 5) {results.add("long_label");} else {results.add("short_label");}}checkLabel();
return results;}}
non-sealed enum ConcreteSourceEnum extends SourceEnum<String> {CONCRETE_INSTANCE;}
class EnumBase {protected String getBaseInfo() {return "base_info";}}
class ConcreteEnumBase extends EnumBase {private String info;
public ConcreteEnumBase(String info) {this.info = info;}}
import org.junit.Test;import static org.junit.Assert.*;import java.util.List;
public class MoveMethodTest3186 {@Testpublic void testInstanceMethod() {SourceEnum<String> source = ConcreteSourceEnum.CONCRETE_INSTANCE;TargetEnum target = TargetEnum.VALUE1;
List<String> result = source.process(target);assertEquals(6, result.size());assertTrue(result.contains("one"));assertTrue(result.contains("base_info"));assertTrue(result.contains("bounded_one"));}}