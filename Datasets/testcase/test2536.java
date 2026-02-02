package same;
import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
class SourceClass {static class StaticNested {public List<String> generate() {return List.of("static");}}
class Inner {/**
Processes target and generates string list
@param target the target class instance
@return list of processed strings*/strictfp List<String> process(TargetClass target) {TargetClass.Inner inner1 = target.new Inner(target.field1);TargetClass.Inner inner2 = target.new Inner(target.field2);TargetClass.Inner inner3 = target.new Inner(target.field3);
List<String> result = new ArrayList<>();result.add(inner1.getValue());result.add(inner2.getValue());result.add(inner3.getValue());
Supplier<List<String>> supplier = SourceClass.StaticNested::generate;result.addAll(supplier.get());
return result;}}}
package same;
protected class TargetClass {String field1 = "one";String field2 = "two";String field3 = "three";
class Inner {private String value;
private Inner(String value) {this.value = value;}
String getValue() {return this.value;}}}