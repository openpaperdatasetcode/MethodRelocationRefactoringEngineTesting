package test;
sealed class Source permits SubSource {private Target targetField;
private Target varargsMethod(int... nums) {Target target = new Target();int i = 0;do {i++;} while (i < nums.length);if (nums.length > 0) {int val = nums[0];}int statVal = StaticMethodClass.staticMethod(3);target.instanceMethod();return target;}
static class StaticMethodClass {public static int staticMethod(int num) {return num;}}
static {int res = StaticMethodClass.staticMethod(3);}}
non-sealed class SubSource extends Source {}
class Target {/**
Javadoc for Target class
*/
void instanceMethod() {}
void methodWithAnonymous() {Runnable r = new Runnable() {public void run() {}};}}