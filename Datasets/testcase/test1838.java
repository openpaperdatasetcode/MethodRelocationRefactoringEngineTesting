package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
public class SourceClass extends ParentSource {private String outerField = "source_data";
// Anonymous inner class{new Runnable() {@Overridepublic void run() {TargetClass target = new TargetClass();try {TargetClass result = instanceMethod(target);System.out.println("Anonymous result: " + result.field);} catch (Exception e) {}}}.run();}
// Local inner classpublic void useLocalInner() {class LocalHandler {TargetClass process(TargetClass target) throws Exception {return instanceMethod(target);}}new LocalHandler().process(new TargetClass());}
/**
Processes target class instance and returns modified version
@param target Target class instance to process
@return Modified target instance
@throws Exception if reflection fails*/protected TargetClass instanceMethod(TargetClass target) throws Exception {// Variable calltarget.field += outerField;
// Uses outer thisTargetClass.StaticNested nested = new TargetClass.StaticNested() {String getOuterInfo() {return SourceClass.this.outerField;}};target.field += nested.getOuterInfo();
// Used by reflectionMethod method = TargetClass.class.getMethod("getField");String reflectedField = (String) method.invoke(target);target.field = reflectedField + "_modified";
// Exception throwing with call method (lambda)try {if (target.field.isEmpty()) {throw new IllegalArgumentException("Empty field");}List<String> processed = target.new InnerClass<String>().process(s -> {List<String> list = new ArrayList<>();list.add(s.toUpperCase());return list;});target.field += processed.get(0);} catch (IllegalArgumentException e) {target.field = "error: " + e.getMessage();}
return target;}}
class ParentSource {}
sealed class TargetClass permits TargetSubclass {String field = "target_data";
public String getField() {return field;}
public static class StaticNested {}
public class InnerClass<T> {// Synchronized generic method with lambdasynchronized List<String> process(Converter<T, List<String>> converter) {return converter.convert((T) field);}}}
interface Converter<T, R> {R convert(T t);}
final class TargetSubclass extends TargetClass {}