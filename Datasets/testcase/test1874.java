package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
protected abstract class SourceClass {// Member inner classpublic class SourceMemberInner {public List<String> getBaseList() {return new ArrayList<>();}}
// Anonymous inner classprivate Runnable initializer = new Runnable() {@Overridepublic void run() {TargetClass target = new TargetClass() {};try {Method method = SourceClass.class.getMethod("abstractMethod", TargetClass.InnerRec.class);method.invoke(new SourceSubclass(), target.new InnerRec(1, "init"));} catch (Exception e) {}}};
private abstract List<String> abstractMethod(TargetClass.InnerRec targetRec);
protected class SourceSubclass extends SourceClass {@Overrideprivate List<String> abstractMethod(TargetClass.InnerRec targetRec) {List<String> result = new ArrayList<>();
// Constructor invocationTargetClass.Inner helper = new TargetClass().new Inner();
// Variable callresult.add(targetRec.id() + ":" + targetRec.value());result.add(helper.process(targetRec));
// Private SuperConstructorInvocation with 3 obj.field referencesclass SubTarget extends TargetClass {private SubTarget(TargetClass.InnerRec rec) {super(rec.id(), rec.value(), rec.value().length());result.add(String.valueOf(rec.id()));result.add(rec.value());result.add(String.valueOf(rec.value().length()));}}new SubTarget(targetRec);
// Abstract method in expression (new ClassName().method())List<String> abstractResult = new AbstractProcessor().process(targetRec);result.addAll(abstractResult);
// Used by reflectiontry {Method valueMethod = TargetClass.InnerRec.class.getMethod("value");result.add((String) valueMethod.invoke(targetRec));} catch (Exception e) {}
return result;}
// Private abstract classprivate abstract class AbstractProcessor {abstract List<String> process(TargetClass.InnerRec rec);}}}
strictfp abstract class TargetClass {protected int idField;protected String valueField;protected int lengthField;
public TargetClass(int id, String value, int length) {this.idField = id;this.valueField = value;this.lengthField = length;}
// Member inner classpublic class Inner {public String process(InnerRec rec) {return rec.value().toUpperCase();}}
public record InnerRec(int id, String value) {}}