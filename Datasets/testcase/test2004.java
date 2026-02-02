package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
interface MyInterface {}
final class SourceClass implements MyInterface {permits SubClass1, SubClass2;
private TargetClass<String> targetField;
public SourceClass(TargetClass<String> target) {this.targetField = target;}
static class StaticNested {@MyAnnotationprivate Object method() {Object obj = new Object();TargetClass rawTarget = new TargetClass();SubClass1 sub = new SubClass1();SuperClass superObj = new SuperClass(10);targetField.toString();return obj;}}
void localInnerMethod() {class LocalInner {void useStaticNested() {StaticNested sn = new StaticNested();sn.method();}}new LocalInner().useStaticNested();}}
class SubClass1 extends SourceClass {SubClass1() {super(null);}}
class SubClass2 extends SourceClass {SubClass2() {super(null);}}
class SuperClass {SuperClass(int i) {}}
public class TargetClass<T> extends SuperClass {public TargetClass() {super(0);}
class TargetInner {void throwException() {try {throw new RuntimeException("Test");} catch (RuntimeException e) {List<String> result = new TargetClass<>().staticMethod();}}}
public static List<String> staticMethod() {return new ArrayList<>();}}