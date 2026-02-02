package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.function.Supplier;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnno {}
private class SourceClass {class SourceMemberInner {}
Object method(TargetClass.TargetInnerRec param) {class LocalInnerInMethod {}LocalInnerInMethod local = new LocalInnerInMethod();
OtherSamePackageClass.declareType(this, param, 10);param.doAction();return param;}
@MyAnnoObject method(String str, TargetClass.TargetInnerRec param) {Supplier<TargetClass> supplier = () -> ParentClass.instanceMethod(str, param);return supplier.get();}}
class TargetClass {class TargetInnerRec {void doAction() {class TargetLocalInner {}new TargetLocalInner();}}
int targetField;
void callMethod() {TargetInnerRec inner = new TargetInnerRec();targetField = TargetClass.staticMethod(inner).toString().length();}
static TargetClass staticMethod(TargetInnerRec inner) {return new TargetClass();}}
class ParentClass {public static TargetClass instanceMethod(String s, TargetClass.TargetInnerRec rec) {return new TargetClass();}}
class OtherSamePackageClass {private static void declareType(SourceClass source, TargetClass.TargetInnerRec inner, int num) {class DeclaredType {}new DeclaredType();}}