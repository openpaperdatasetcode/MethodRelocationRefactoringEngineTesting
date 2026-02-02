package test;
import java.util.List;import java.util.ArrayList;import java.io.IOException;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface ProcessAnnotation {}
interface TargetInterface {}
class TargetParent {}
/**
Target enum with multiple features: javadoc, extends, implements, static nested class*/public enum TargetEnum extends TargetParent implements TargetInterface {INSTANCE;
int field = 5;
/**
Static nested class of TargetEnum
*/
public static class StaticNested {}
}
public enum SourceEnum {INSTANCE;
private int outerPrivate = 10;
static class StaticNested {}
class MemberInner {}
@ProcessAnnotationpublic final List<String> process(TargetEnum target) throws IOException {List<String> result = new ArrayList<>();TargetEnum.StaticNested rawNested = new TargetEnum.StaticNested();new MemberInner();
try {assert target.field > 0 : "Target field must be positive";
for (int i = 0; i < 3; i++) {target.field += outerPrivate;result.add(String.valueOf(target.field));if (i == 1) break;}} catch (AssertionError e) {result.add(e.getMessage());}
return result;}
@ProcessAnnotationpublic final List<String> process(TargetEnum target, String suffix) throws IOException {List<String> result = new ArrayList<>();TargetEnum.StaticNested rawNested = new TargetEnum.StaticNested();
try {assert target.field == 5 : "Initial field value mismatch";target.field *= 2;result.add(String.valueOf(target.field) + suffix);if (target.field > 10) break;} catch (AssertionError e) {result.add(e.getMessage());}
return result;}}