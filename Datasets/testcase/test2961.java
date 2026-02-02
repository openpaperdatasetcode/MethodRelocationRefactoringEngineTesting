package test;
import java.util.ArrayList;import java.util.List;
abstract class SourceClass {public class MemberInner {public String getInnerData () {return "member_inner_data";}}
@SuppressWarnings("unused")public Object process(TargetClass targetParam) {TargetClass target = new TargetClass();MemberInner sourceInner = new MemberInner();
class LocalInner {public String formatField(int field) {return "formatted_" + field;}}LocalInner formatter = new LocalInner();
static int field1 = target.targetField1;static int field2 = target.targetField2;static int field3 = target.targetField3;
switch (targetParam.targetField1) {case 1:targetParam.action(() -> sourceInner.getInnerData().toUpperCase().trim());break;case 2:targetParam.action(() -> formatter.formatField(targetField2).toLowerCase());break;case 3:targetParam.action(() -> target.new InnerRecursive().getName().replace(" ", "_"));break;default:;}
Runnable anon = new Runnable() {@Overridepublic void run() {targetParam.targetField1++;}};anon.run();
return target.new InnerRecursive();}}

public class TargetClass {public int targetField1 = 1;public int targetField2 = 2;public int targetField3 = 3;
public class InnerRecursive {private String name = "inner_recursive";
public InnerRecursive() {Runnable init = new Runnable() {@Overridepublic void run() {name += "_initialized";}};init.run();}
public String getName() {return name;}
public List<String> processChain() {List<String> list = new ArrayList<>();list.add(getName());list.add(getName().toUpperCase());list.add(getName().length() + "");return list;}}
public void action(Runnable task) {task.run();}}
class CallHelper {protected List<String> callInnerConstructor(TargetClass target) {return target.new InnerRecursive().processChain();}}