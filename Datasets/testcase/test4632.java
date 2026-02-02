package test;
import java.util.List;import java.util.ArrayList;
class ParentClass {public String parentMethod(int arg) {return "ParentMethodResult: " + arg;}}
protected class Source extends ParentClass {private Target targetField;
class MemberInner {String innerField;void innerMethod() {}}
public List<String> instanceMethod() {List<String> result = new ArrayList<>();
if (targetField != null) {variableCall(targetField);result.add(targetField.targetField);}
MemberInner inner = new MemberInner();expressionStatement(inner);
String ternaryResult = (inner.innerField != null)? this.parentMethod(1): this.parentMethod(2);result.add(ternaryResult);
methodWithLocalInner();return result;}
public List<String> instanceMethod(String param) {return new ArrayList<>();}
private void expressionStatement(MemberInner inner) {inner.innerField = "UpdatedValue";}
private void variableCall(Target target) {String val = target.targetField;Target.AnonymousHost host = target.new AnonymousHost();host.createAnonymous();}
private void methodWithLocalInner() {class LocalInner {void localMethod() {}}LocalInner local = new LocalInner();}}
private class Target {String targetField;
class AnonymousHost {void createAnonymous() {Runnable r = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous Inner Class Run");}};}}}