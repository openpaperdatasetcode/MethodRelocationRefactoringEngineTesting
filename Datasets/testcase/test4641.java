package test;
import java.util.List;import java.util.ArrayList;
interface Source {class Inner {class InnerRec {private Target targetParam;private static int staticField = 1;
synchronized void normalMethod(Target target) {this.targetParam = target;assert target != null : "Target parameter cannot be null";
int i = 0;do {List<String> staticResult = Target.StaticHelper.synchronizedStaticMethod(1);i++;} while (i < 3);
for (int j = 0; j < staticField; j++) {variableCall(target);String fieldVal = target.instanceField;}
OthersClass others = new OthersClass();List<String> callResult1 = others.callMethod(target.new Inner());List<String> callResult2 = others.callMethod(target.new Inner(), "extra");}
private void variableCall(Target target) {Target.AnonymousHost host = target.new AnonymousHost();host.createAnonymous();}}}}
public interface Target extends BaseInterface {String instanceField = "targetInstanceData";static int staticField = 5;
class StaticHelper {synchronized static List<String> synchronizedStaticMethod(int num) {List<String> list = new ArrayList<>();list.add(String.valueOf(num * staticField));return list;}}
class Inner {}
class AnonymousHost {void createAnonymous() {Runnable r = new Runnable() {@Overridepublic void run() {}};}}
@Overridedefault void baseMethod() {}}
interface BaseInterface {void baseMethod();}
class OthersClass {List<String> callMethod(Target.Inner inner) {return new ArrayList<>();}
List<String> callMethod(Target.Inner inner, String extra) {List<String> list = new ArrayList<>();list.add(extra);return list;}}