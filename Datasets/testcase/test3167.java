package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface AbsAnn {}
abstract class ParentTargetClass<T> {protected TargetClass<T> parentMethod(T param) {return new TargetClass<>(param);}}
final abstract class TargetClass<T> extends ParentTargetClass<T> {T targetField;static class TargetStaticNested {}
class TargetInner {record TargetInnerRec(T val) {} // target_inner_rec}
public void instanceMethod() {}
static {// Call_method in Static code blocksTargetClass<String> target = new Tpackage test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface AbsAnn {}
abstract class ParentTargetClass<T> {protected TargetClass<T> parentMethod(T param) {return new TargetClass<>(param);}}
final abstract class TargetClass<T> extends ParentTargetClass<T> {T targetField;static class TargetStaticNested {}
class TargetInner {record TargetInnerRec(T val) {} // target_inner_rec}
public void instanceMethod() {}
static {// Call_method in Static code blocksTargetClass<String> target = new TargetClass<>("init") {};TargetClass<String> result = target.new TargetInner().callSuperMethod("static");}
class TargetInner {private TargetClass<T> callSuperMethod(T param) {return super.parentMethod(param); // super.methodName(arguments)}}
protected TargetClass(T targetField) {this.targetField = targetField;}}
abstract class SourceClass {static class SourceStaticNested {}
public SourceClass() {Runnable r = new Runnable() {@Overridepublic void run() {}};}
@AbsAnn // has_annotationvoid methodToMove(TargetClass<String>.TargetInner.TargetInnerRec rec) {// Variable callString var = rec.val();TargetClass<String> target = new TargetClass<>("test") {};
// Access instance methodtarget.instanceMethod();
// Uses outer thisSourceClass outer = SourceClass.this;SourceStaticNested nested = new SourceStaticNested();}}argetClass<>("init") {};TargetClass<String> result = target.new TargetInner().callSuperMethod("static");}
class TargetInner {private TargetClass<T> callSuperMethod(T param) {return super.parentMethod(param); // super.methodName(arguments)}}
protected TargetClass(T targetField) {this.targetField = targetField;}}
abstract class SourceClass {static class SourceStaticNested {}
public SourceClass() {Runnable r = new Runnable() {@Overridepublic void run() {}};}
@AbsAnn // has_annotationvoid methodToMove(TargetClass<String>.TargetInner.TargetInnerRec rec) {// Variable callString var = rec.val();TargetClass<String> target = new TargetClass<>("test") {};
// Access instance methodtarget.instanceMethod();
// Uses outer thisSourceClass outer = SourceClass.this;SourceStaticNested nested = new SourceStaticNested();}}