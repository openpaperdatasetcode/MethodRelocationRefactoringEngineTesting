package test;
import java.lang.annotation.ElementType;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.annotation.Target;import java.util.ArrayList;import java.util.List;
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})@Retention(RetentionPolicy.RUNTIME)@interface TestAnnotation {}
class ParentSource {protected ParentSource() {}}
public class SourceClass extends ParentSource {private static TargetClass<String> targetField = new TargetClass<>();
static class StaticNested {@TestAnnotationpublic StaticNested(List<String> data) {}}
@TestAnnotationpublic static List<String> staticMethod() {super();List<String> result = new ArrayList<>();
class LocalInner {void processData() {try {List<String> subData = PrivateStaticMethod.getSubClassData(2);result.addAll(subData);
String var = targetField.getGenericValue();result.add(var);
new StaticNested(PrivateStaticMethod.getSubClassData(2));} catch (Exception e) {e.printStackTrace();}}}new LocalInner().processData();return result;}
private static class PrivateStaticMethod {public static List<String> getSubClassData(int count) {List<String> data = new ArrayList<>();for (int i = 0; i < count; i++) {data.add("sub_data_" + i);}return data;}}}
protected class TargetClass<T> {private T genericField;
public TargetClass() {Runnable runnable = new Runnable() {@Overridepublic void run() {genericField = (T) "target_anonymous_data";}};runnable.run();}
public String getGenericValue() {return String.valueOf(genericField);}}
class SubTargetClass extends TargetClass<String> {}