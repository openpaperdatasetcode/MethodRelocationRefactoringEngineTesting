package source;
import target.TargetClass;import java.util.Arrays;import java.util.function.Function;
class SourceClass {static class StaticNested1 {}static class StaticNested2 {}
protected int process(TargetClass target) {TargetClass instance = new TargetClass();Object[] data = {instance.getValue(),instance.getValue(10),instance.getValue("key")};
Function<String, Object> func = instance::parse;Object result = func.apply("test");
return target.count + TargetClass.STATIC_FIELD;}}
package target;
protected class TargetClass {int count = 5;static int STATIC_FIELD = 3;
Object getValue() {return "default";}
Object getValue(int num) {return num * 2;}
Object getValue(String key) {return key.length();}
Object parse(String s) {class LocalParser {String process() {return s.toUpperCase();}}return new LocalParser().process();}}