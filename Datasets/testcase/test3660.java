package test;
public enum SourceEnum {INSTANCE;
protected int protectedField = 7;
public class InnerSource {public int process(TargetEnum target) {class LocalInner {int compute(TargetEnum.InnerTarget inner) {return inner.calculate(SourceEnum.this.protectedField);}}
TargetEnum.InnerTarget inner1 = new TargetEnum.InnerTarget(3);TargetEnum.InnerTarget inner2 = new TargetEnum.InnerTarget(TargetEnum.STATIC_FIELD);TargetEnum.InnerTarget inner3 = new TargetEnum.InnerTarget(inner1.getValue());
if (inner1 == null || inner2 == null || inner3 == null) {throw new NullPointerException("InnerTarget instance is null");}
int result = 0;switch (target) {case FIRST:result = TargetEnum.staticMethod(inner1.getValue(), super.toString().length());break;case SECOND:result = TargetEnum.staticMethod(inner2.getValue(), protectedField);break;default:throw new IllegalArgumentException("Invalid TargetEnum value");}
LocalInner local = new LocalInner();result += local.compute(inner3);result += inner1.calculate(5);
return result;}}}
enum TargetEnum {FIRST, SECOND;
public static final int STATIC_FIELD = 10;
public class InnerTarget {private int value;
public InnerTarget(int val) {this.value = val;}
public int getValue() {return value;}
public int calculate(int num) {return value * num;}}
public static int staticMethod(int a, int b) {return a + b;}}
class SubTargetClass extends TargetEnum.InnerTarget {public SubTargetClass(int val) {super(val);}
@Overridepublic int calculate(int num) {return super.calculate(num) * 2;}}