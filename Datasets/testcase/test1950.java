package test;
import java.util.ArrayList;import java.util.List;
class SourceClass {class MemberInner1 {class InnerRec {@MyAnnotationfinal TargetClass method(TargetClass target) {// Raw type usageList rawList = new ArrayList();rawList.add(target.field);
// While statementint i = 0;while (i < target.inner.innerRec.count) {target.field = i;i++;}
// Access instance fieldtarget.inner.innerRec.data = "updated";
// Variable calltarget.inner.inner.count++;
// Uses outer thistarget.setOuterReference(SourceClass.this);
return target;}
@MyAnnotationfinal TargetClass method(TargetClass target, String value) {target.inner.innerRec.data = value;return target;}}}
class MemberInner2 {}}
@interface MyAnnotation {}
class TargetClass {int field;Inner inner = new Inner();
void setOuterReference(SourceClass outer) {// Store reference to outer class instance}
class Inner {int count = 0;InnerRec innerRec = new InnerRec();
class InnerRec {String data;int count = 5;}}}