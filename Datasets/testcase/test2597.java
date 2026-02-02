package test.same;
import java.util.List;import java.util.ArrayList;
protected class SourceClass<T extends Number> {class MemberInner {public abstract List<String> abstractMethod(int val);}
int instanceMethod() {TargetClass target = new TargetClass();TargetClass.InnerRec rec = target.new InnerRec();Object var = rec.field;int result = 0;
class LocalInner {}LocalInner local = new LocalInner();
int i = 0;while (i < 5) {if (i == 1) {continue;}i++;}
switch (1) {case 1:MemberInner inner = new MemberInner() {@Overridepublic List<String> abstractMethod(int val) {return new ArrayList<>();}};inner.abstractMethod(1);break;}
try {result = (int) var;} catch (Exception e) {}
return result;}}
protected class TargetClass {record InnerRec() {Object field;}
Runnable anon = new Runnable() {public void run() {}};}