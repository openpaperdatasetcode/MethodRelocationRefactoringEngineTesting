package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnn {}
class ParentSource {Object overridingMethod() {return null;}}
abstract class SourceClass extends ParentSource {class MemberInner {private int getValue() {return super.hashCode();}}
void localInnerMethod() {class LocalInner {}}
@MyAnnObject overridingMethod() {super();TargetClass<String>.TargetStaticNested.InnerRec innerRec = new TargetClass<>().new TargetStaticNested().new InnerRec();
variableCall = innerRec.field;innerRec.instanceMethod();
synchronized (this) {innerRec.field = 1;}
if (new MemberInner().getValue() == 1) {// do something} else {// do something else}
return innerRec;}
Object variableCall;}
non-sealed class TargetClass<T> {static class TargetStaticNested {class InnerRec {int field;void instanceMethod() {}}}}