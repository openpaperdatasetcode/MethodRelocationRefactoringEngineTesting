package test;
public class SourceClass {private int outerPrivate = 10;
static class StaticNested {/**
Javadoc for innerMethod
@param targetParam target class instance parameter
@param args varargs parameters
@return calculated base type value*/private int innerMethod(TargetClass targetParam, String... args) {class LocalInner extends TargetClass {private LocalInner() {super(targetParam.field);}
private void varargsMethod(int... nums) {if (nums.length < 1) {throw new IllegalArgumentException("At least 1 argument required");}super.baseMethod(nums);}}
LocalInner local = new LocalInner();local.varargsMethod(1, outerPrivate);int result = targetParam.field + outerPrivate;return result;}}}
final class TargetClass {int field;
private TargetClass(int field) {this.field = field;}
void baseMethod(int... nums) {Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println(nums.length);}};runnable.run();}}