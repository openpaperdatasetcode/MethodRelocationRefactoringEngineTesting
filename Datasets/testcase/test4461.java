package source;
public sealed class SourceClass permits SubSource {private int sourceField;TargetClass target = new TargetClass();
public final int varargsMethod(int... args) {int var = 1;switch (args.length) {case 0:new Runnable() {public void run() {sourceField = 5;}}.run();break;case 1:class LocalInner {void print() {System.out.println(target.inner.field);}}new LocalInner().print();if (target.inner.field == var) {break;}break;}java.util.List<String> list = new java.util.ArrayList<>();list.forEach(item -> target.inner.callMethod(this));return sourceField;}}
final class SubSource extends SourceClass {}
package target;
public class TargetClass {public class MemberInner {int field;
protected String callMethod(SourceClass obj) {return "1";}
protected String callMethod(Integer num) {return "2";}}
public MemberInner inner = new MemberInner();}
