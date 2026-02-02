package test;
import java.util.ArrayList;import java.util.List;
public enum SourceEnum extends ParentEnum {INSTANCE;
public static class NestedStatic1 {public static class NestedStatic2 {String value;}}
public class SourceInner {public class SourceInnerRec {public List<String> process(TargetEnum targetParam) {return process(targetParam, new ArrayList<>());}
public List<String> process(TargetEnum targetParam, List<String> list) {try {String[] arr = {"a", "b"};list.add(arr[0]);
NestedStatic1.NestedStatic2 nested = new NestedStatic1.NestedStatic2();nested.value = SourceEnum.this.name();list.add(nested.value);
TargetEnum.MemberInner targetInner = targetParam.new MemberInner();list.add(targetInner.getData());
list.add(genericMethod(10));} catch (Exception e) {// no new exception}return list;}
public <T extends Number> int genericMethod(T num) {return super.baseMethod(num.intValue());}}}
static {new SourceEnum.SourceInner().new SourceInnerRec().genericMethod(5);}}
class ParentEnum {protected int baseMethod(int num) {return num * 2;}}
public enum TargetEnum {VALUE;
public class MemberInner {String data;
public String getData() {return data;}}}
class SubClass extends ParentEnum {@Overrideprotected int baseMethod(int num) {TargetEnum target = new TargetEnum() {};SourceEnum.SourceInner.SourceInnerRec innerRec = new SourceEnum.INSTANCE.SourceInner().new SourceInnerRec();String result = "";
for (String s : innerRec.process(target)) {result += s;}return super.baseMethod(num);}}