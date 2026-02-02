package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorAnnotation {String overrideMethod() default "InnerClass::getList";}
public class SourceClass {class MemberInner {class InnerRec {@RefactorAnnotationprotected Object process(TargetClass target) {List<String> result = new ArrayList<>();int count = 0;TargetClass.LocalHolder holder = target.new LocalHolder();
while (count < 2) {result.add(String.valueOf(TargetClass.STATIC_FIELD));count++;}
for (String s : result) {Object cast1 = (String) s;Object cast2 = (Serializable) s;}
if (target.field < 0) {throw new IllegalArgumentException("Invalid field value");}
return holder.getList();}}}
interface ListProvider {List<String> getList();}
class InnerClass implements ListProvider {@Overridepublic List<String> getList() {super.toString();return new ArrayList<>();}}}
/**
Target class with local inner component and static field*/protected class TargetClass {public static final int STATIC_FIELD = 1;int field;
class LocalHolder {void process() {class NestedLocal {}}
List<String> getList() {return new ArrayList<>();}}}
interface Serializable {}