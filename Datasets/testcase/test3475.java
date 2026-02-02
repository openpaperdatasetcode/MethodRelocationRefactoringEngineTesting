package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import diffpackage.OthersClass;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface FeatureAnnotation {}
interface SourceInterface<T> {class MemberInner {T field;public void innerMethod() {}}
default int moveMethod(TargetInterface<T> param) {if (param == null) {throw new NullPointerException();}
do {TargetInterface.staticField = 1;; // EmptyStatementint result = TargetInterface.genericMethod(param, "arg1");
for (T item : param.getItems()) {class TypeDeclaration {void useOuter() {SourceInterface.this.variableCall(param);param.memberInner.innerMethod();}}new TypeDeclaration().useOuter();}} while (param.getCount() < 5);
return param.getCount();}
private void variableCall(TargetInterface<T> target) {target.accessInstanceMethod();}
private int callMethod() {TargetInterface<String> target = new TargetInterface<String>() {};return target.m1().m2().m3();}}
strictfp interface TargetInterface<T> implements BaseInterface {static int staticField;MemberInner memberInner = new MemberInner();
class MemberInner {public void innerMethod() {}}
List<T> getItems();int getCount();void accessInstanceMethod();TargetInterface<T> m1();TargetInterface<T> m2();int m3();
static int genericMethod(TargetInterface target, String arg) {
return target.getCount();
}
default int moveMethod(TargetInterface<T> param) {return param.getCount();}}
interface BaseInterface {}
package diffpackage;
import test.TargetInterface;
public class OthersClass {public static <T> void process(TargetInterface<T> target) {TargetInterface.staticField = 1;}}