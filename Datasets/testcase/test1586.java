package test;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface ProcessAnnotation {}
class ParentSource {}
abstract class SourceClass extends ParentSource {public static class StaticNested1 {}public static class StaticNested2 {}
public class MemberInner {public class InnerRec {@ProcessAnnotationObject process(TargetClass target) {// VariableDeclarationStatement with transient modifiertransient TargetClass.StaticNested nestedVar = target.new StaticNested();nestedVar.value = TargetClass.staticField; // Access target's static field
// Enhanced for statementfor (String item : target.items) {// If statementif (item.contains("target")) {// Variable call - access target's fieldnestedVar.count += target.counter;}}
return nestedVar.count;}}}}
class TargetClass {public static int staticField = 100;public int counter;public String[] items;
public static class StaticNested {public int value;public int count;}}