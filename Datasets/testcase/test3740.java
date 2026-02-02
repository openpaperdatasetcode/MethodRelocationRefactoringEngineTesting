package source;
import java.util.List;import target.TargetAnnotation;
@interface SourceAnnotation implements java.io.Serializable {class MemberInner {}
static class StaticNested {}
public record SourceInnerRec() {default Object methodToMove(int... args) {TargetAnnotation.TargetInnerRec targetInner = new TargetAnnotation.TargetInnerRec();int field = targetInner.value;
labeled: {for (int arg : args) {if (arg == 0) {privateContinue();continue labeled;}
class LocalType {}LocalType typeDecl = new LocalType();
if (field > 5) {privateGenericMethod(targetInner).forEach(System.out::println);} else {try {String str = new SubClass().protectedMethod();Runnable run = new SubClass()::protectedMethod;} catch (Exception e) {}}}}
List rawList = new java.util.ArrayList();MemberInner inner = new MemberInner();return this;}
private void privateContinue() {this.toString();}
private <T extends CharSequence> List<String> privateGenericMethod(TargetAnnotation.TargetInnerRec ref) {return List.of(ref.toString());}}}
package target;
import java.util.List;
@interface TargetAnnotation {record TargetInnerRec(int value) {TargetInnerRec() {this(0);}}
void someMethod() {class TargetLocalInner {}}}
package target;
class SubClass {protected String protectedMethod() {return "sub";}}