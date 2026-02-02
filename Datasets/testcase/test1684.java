package test;
import java.util.List;import java.util.ArrayList;
final class SourceClass {{new Runnable() {};}
static class StaticNested {}
public List<String> varargsMethod(TargetClass.MemberInner... args) {super();List<String> list = new ArrayList<>();
private int count = 0;while (count < args.length) {String field = args[count].superField;list.add(field);count++;}
int i = 0;while (i < args.length) {args[i] = new TargetClass().new MemberInner();super.toString();variableCall();i++;}
try {int val = args[0].overloadedMethod();val = args[0].overloadedMethod(1);} catch (Exception e) {int val = args[0].overloadedMethod(e.getMessage());}
return list;}
private void variableCall() {}}
class TargetClass extends ParentClass {class MemberInner {public int overloadedMethod() {return super.hashCode();}
public int overloadedMethod(int num) {return super.hashCode() + num;}
public int overloadedMethod(String str) {return super.hashCode() + str.length();}}}
class ParentClass {protected String superField = "parent";}