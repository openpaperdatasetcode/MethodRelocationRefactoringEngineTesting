package same.pkg;
import java.util.List;import java.util.ArrayList;import java.util.Collection;
public class SourceClass implements Runnable {private String outerPrivateField = "test";private TargetClass targetField = new TargetClass();
protected List<String> process() {List result = new ArrayList();int flag = 1;switch (flag) {case 1:if (outerPrivateField != null) {TargetClass.StaticNested staticNested = new TargetClass.StaticNested();break;}default:break;}MemberInner inner = new MemberInner();inner.callMethod();return result;}
class MemberInner {private void callMethod() {new Runnable() {@Overridepublic void run() {SourceClass.this.new InnerClass().execute();}}.run();}}
class InnerClass {private void execute() {}}
@Overridepublic void run() {}}
class TargetClass {static class StaticNested {private void execute() {}
@Overridepublic String toString() {return super.toString();}}
private void helper() {SourceClass outer = new SourceClass();outer.new InnerClass().execute();}}