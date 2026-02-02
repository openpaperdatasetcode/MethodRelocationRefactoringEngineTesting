package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
interface Processable {List<String> process(TargetClass target);}
private class SourceClass implements Processable {public class InnerSource implements Processable {@Overrideprotected List<String> process(TargetClass target) {List<String> result = new ArrayList<>();
String[] array1 = { target.getField(), "default1" };String[] array2 = { target.processAnonymous(), "default2" };
labeledBlock: {if (array1.length != 2 || array2.length != 2) {break labeledBlock;}result.add(array1[0]);result.add(array2[0]);}
Object innerResult = new Processable() {@Overridepublic List<String> process(TargetClass t) {return List.of(t.getField().toUpperCase());}}.process(target);result.addAll((List<String>) innerResult);
try {Method method = TargetClass.class.getMethod("getField");result.add((String) method.invoke(target));} catch (Exception e) {result.add("reflection_error");}
return result;}}}
protected class TargetClass {private String field = "target_field";
public String getField() {return field;}
public String processAnonymous() {Runnable runnable = new Runnable() {@Overridepublic void run() {field = field + "_anonymous";}};runnable.run();return field;}}