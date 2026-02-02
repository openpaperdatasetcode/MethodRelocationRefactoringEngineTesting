package test;
import java.lang.reflect.Method;import java.util.List;import java.util.ArrayList;
private class SourceClass {private TargetClass targetField = new TargetClass();
class MemberInner {}
{new Object() {}; // Anonymous inner class}
private TargetClass methodToMove(List<String> listParam) {// Constructor in parameter listOthersClass oc = new OthersClass(super.toString(), 3, new ArrayList<>());
// Switch statementswitch (listParam.size()) {case 0:break;default:break;}
// Type declaration statementclass LocalType {}LocalType lt = new LocalType();
// Variable callTargetClass.TargetInner inner = targetField.new TargetInner();int var = inner.value;
// Overload existsoverloadedMethod(1);
// Used by reflectiontry {Method method = getClass().getMethod("methodToMove", List.class);} catch (NoSuchMethodException e) {}
return targetField;}
private void overloadedMethod(int i) {}private void overloadedMethod(String s) {}}
class TargetClass {class TargetInner {int value;}}
class OthersClass {private OthersClass(String s, int i, List<String> list) {}}
class ParentClass {private TargetClass callMethod() throws Exception {SourceClass source = new SourceClass();TargetClass target = source.methodToMove(new ArrayList<>());Runnable runnable = target::toString;throw new Exception(runnable.toString());}}