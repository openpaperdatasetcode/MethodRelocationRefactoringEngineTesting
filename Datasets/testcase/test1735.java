package test;
import java.util.ArrayList;import java.util.List;
abstract class SourceClass<T> permits SubSourceClass {protected String outerProtectedField;static class StaticNested {}class MemberInner {class InnerRec {final List<String> varargsMethod(AbstractTargetClass... targets) {// LabeledStatement with obj.field access (1 target)Label: {private String fieldVal = targets[0].targetField;if (fieldVal == null) break Label;}
// Instance method from inner class in ternary operator (1 instance)Object result = (targets.length > 0) ? this.innerMethod() : new Object();
// Switch statementswitch (targets.length) {case 1:variableCall();break;default:break;}
// Super keywordssuper.toString();
// Access outer protected memberString protectedVal = SourceClass.this.outerProtectedField;
// Call sub_class instance method with super in ternary operatorList<String> subResult = (targets.length > 0)? SubSourceClass.super.subMethod(targets): new ArrayList<>();
return new ArrayList<>();}
private Object innerMethod() {return new Object();}
private void variableCall() {}}}}
class SubSourceClass extends SourceClass<String> {List<String> subMethod(AbstractTargetClass... targets) {return new ArrayList<>();}}
abstract class AbstractTargetClass {String targetField;
{new Runnable() {}; // Anonymous inner class}}