package source;
import target.TargetClass;import java.util.List;
sealed interface SourceInterface permits SourceImpl {// Static nested class (source_class feature)static class SourceStaticNested {private int privateField = 10;public int getPrivateField() { return privateField; }}
// Member inner class (source_class feature)class SourceMemberInner {}
// Varargs method to be refactored (matches method structure)<T extends TargetClass> TargetClass processVarargs(T... targetParams) throws Exception {// Type declaration statementSourceStaticNested staticInst = new SourceStaticNested();SourceMemberInner memberInst = new SourceMemberInner();
// Switch statementswitch (targetParams.length) {case 0:throw new IllegalArgumentException("No parameters");case 1:// ContinueStatement with "ClassName.field" (matches nested feature)for (T param : targetParams) {if (param == null) continue;int fieldVal = TargetClass.STATIC_FIELD;break;}break;default:break;}
// Variable call + access outer private (via static nested class method)int outerPrivateVal = staticInst.getPrivateField();targetParams[0].targetField = outerPrivateVal;
// Super constructor invocation (via inner class)class TempInner extends SourceStaticNested {TempInner() { super(); }}new TempInner();
return targetParams[0];}
// Overload method (matches "overload_exist")<T extends TargetClass> TargetClass processVarargs(T targetParam, List<String> extras) throws Exception {return targetParam;}
// call_method (matches call_method structure)default String callProcessMethod() throws Exception {TargetClass result = processVarargs(new TargetClass());return result.toString();}}
// Permitted implementation of sealed interfacefinal class SourceImpl implements SourceInterface {}
package target;
// Target class (different package from source)interface TargetClass {// Static field (for "ClassName.field")int STATIC_FIELD = 20;
// Instance field (for variable call)int targetField = 0;}