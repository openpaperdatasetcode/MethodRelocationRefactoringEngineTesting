package test;
import java.util.ArrayList;import java.util.List;
public class SourceClass implements Runnable {public class FirstLevelInner {public class InnerRec {Object process(TargetClass... targets) {class LocalType {String data;}LocalType local = new LocalType();local.data = "local";
try {for (TargetClass target : targets) {target.inner.setValue(local.data);Object result = process(target, 10);}} catch (Exception e) {// no new exception}
return this;}
Object process(TargetClass target, int num) {return target.inner.getResult(num);}}}
private static Object staticProcess(SourceClass outer) {List<Object> list = new ArrayList<>();list.add(outer.new FirstLevelInner().new InnerRec().process(new TargetClass()));return list;}
@Overridepublic void run() {}}
class TargetClass {public MemberInner inner = new MemberInner();
public class MemberInner {private String value;
void setValue(String val) {this.value = val;}
Object getResult(int num) {return value + num;}}}
