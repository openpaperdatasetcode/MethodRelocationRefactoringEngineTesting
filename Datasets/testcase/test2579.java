package test.refactoring.movemethod;
import java.sql.SQLException;import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
/**
Target class with javadoc
Contains local inner class in target*/protected class TargetClass extends ParentClass {class TargetInner {class NestedInner {private List<String> data;
public NestedInner() {this.data = new ArrayList<>();}
public List<String> getAccessor() {return data;}}}}
class ParentClass {public List<String> parentAccessor() {return new ArrayList<>();}}
final class SourceClass {static class StaticNested1 {}static class StaticNested2 {}
List<String> process(TargetClass.TargetInner.NestedInner target, String... args) {List rawList = new ArrayList();Object varCall = rawList;
try {if (args.length == 0) {throw new SQLException("No arguments provided");}} catch (SQLException e) {rawList.add("Error: " + e.getMessage());}
int i = 0;while (i < 1) {Supplier<List<String>> supplier = target::getAccessor;rawList.addAll(supplier.get());i++;}
for (String arg : args) {rawList.add(arg);}
return (List<String>) SourceClass.this.convert(rawList);}
private List<String> convert(List<?> list) {List<String> result = new ArrayList<>();for (Object o : list) {result.add(o.toString());}return result;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3027 {@Testpublic void testVarargsMethod() {SourceClass source = new SourceClass();TargetClass target = new TargetClass();TargetClass.TargetInner inner = target.new TargetInner();TargetClass.TargetInner.NestedInner nested = inner.new NestedInner();List<String> result = source.process(nested, "arg1", "arg2");assertEquals(2, result.size());}}