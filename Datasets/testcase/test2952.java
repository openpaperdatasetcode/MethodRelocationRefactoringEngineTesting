package test;
import java.util.List;import java.util.Arrays;
public class SourceClass<T> {private TargetClass target = new TargetClass();
public class MemberInner {T innerField;}
public void methodToMove() {// EnhancedForStatement with target feature: obj.field and 2for (int num : target.numberList) {if (num == 2 && target.field == 2) {// ParenthesizedExpression with numbers=2 and private modifierint result = (2 * (target.field + num));
// Constructor invocationMemberInner inner = new MemberInner();inner.innerField = (T) Integer.valueOf(result);
// Anonymous inner classRunnable runnable = new Runnable() {@Overridepublic void run() {super.toString(); // Super constructor invocationSystem.out.println(inner.innerField);}};runnable.run();}}
// Variable calltarget.innerTarget.process(inner);}
// Call method: parent_class type, private modifierprivate List<String> callParentMethod() {ParentClass parent = new ParentClass();for (int i = 0; i < 2; i++) {// SuperTypeReference.methodName(arguments) and constructor target featureparent.parentMethod(new TargetClass.MemberInnerTarget());}return Arrays.asList("result");}}
abstract class TargetClass {protected int field = 2;protected List<Integer> numberList = Arrays.asList(1, 2, 3);protected MemberInnerTarget innerTarget = new MemberInnerTarget();
public class MemberInnerTarget {void process(SourceClass.MemberInner inner) {}}}
class ParentClass {// SuperTypeReference method for call_methodpublic void parentMethod(TargetClass.MemberInnerTarget targetInner) {}}