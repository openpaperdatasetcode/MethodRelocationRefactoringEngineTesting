package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {String value();}
private class SourceClass {public class MemberInner {public List<String> getTargetData(TargetClass<String> target) {List<String> data = new ArrayList<>();data.add(target.field);return data;}}
public static class StaticNested {public static <T> TargetClass<T> createTarget(T value) {return new TargetClass<>(value);}}
@MethodAnnotation("#{this.getTargetList(target)}")public Object overloadedMethod(TargetClass<String> target) {final MemberInner inner = new MemberInner();List<String> result = inner.getTargetData(target);
for (TargetClass.Inner<String> innerObj : target.getInners()) {result.add(innerObj.getValue());}
try {Method method = TargetClass.class.getMethod("getField");result.add(method.invoke(target).toString());} catch (Exception e) {e.printStackTrace();}
return result;}
public Object overloadedMethod(TargetClass<String> target, int count) {List<String> data = overloadedMethod(target) instanceof List ? (List<String>) overloadedMethod(target) : new ArrayList<>();List<String> parentData = count > 0? ParentClass.Nested.staticMethod(target.new Inner<>("parent")): new ArrayList<>();data.addAll(parentData);return data;}}
protected class TargetClass<T> extends ParentClass {public T field;
public TargetClass(T field) {this.field = field;}
public T getField() {return field;}
public List<Inner<T>> getInners() {List<Inner<T>> inners = new ArrayList<>();inners.add(new Inner<>(field));return inners;}
public class Inner {
private U value;
public Inner(U value) {this.value = value;}
public U getValue() {return value;}}}
class ParentClass {public static class Nested {public static <T> List<String> staticMethod(TargetClass.Inner<T> inner) {List<String> list = new ArrayList<>();list.add(inner.getValue().toString() + "_parent");return list;}}}