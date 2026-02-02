package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface ProcessAnnotation {}
class SourceClass {static class StaticNested {}
class MemberInner {class InnerRec {@ProcessAnnotationpublic final List<String> process(TargetClass target) {List<String> result = new ArrayList<>();TargetClass.StaticNested nested = new TargetClass.StaticNested();
try {target.field = 10;result.add(String.valueOf(target.field));nested.process();} catch (Exception e) {result.add(e.getMessage());}
return result;}
public final List<String> process(TargetClass target, int num) {List<String> result = new ArrayList<>();result.add(String.valueOf(target.field + num));return result;}}}}
class TargetClass {int field;
static class StaticNested {void process() {}}}