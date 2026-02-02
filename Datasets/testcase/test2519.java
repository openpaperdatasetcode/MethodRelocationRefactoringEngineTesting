package same;
import java.util.List;import java.util.ArrayList;import java.util.function.Function;
protected class SourceClass {private int privateField = 10;
private List<String> process(TargetClass target) {TargetClass.Inner inner = target.new Inner();List<String> result = new ArrayList<>();result.add(inner.name);
Function<String, String> func = s -> this.format(inner, s);result.add(func.apply("test"));
return result;}
public String format(TargetClass.Inner inner, String s) {return s + inner.id + privateField;}
public String format(TargetClass.Inner inner, int num) {return num + inner.id;}}
package same;
import java.util.List;
/**
Final target class with static nested component*/final class TargetClass {static class StaticNested {}
class Inner {String name = "inner";int id = 5;}}