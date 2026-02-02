package test;
import java.util.List;import java.util.ArrayList;
enum Source extends SuperEnum {INSTANCE1, INSTANCE2;
private class InnerClass {public static List<String> staticMethod() {return new ArrayList<>();}
synchronized void synchronizedVarargsMethod(int... nums) {super.toString();if (nums.length > 0) {System.out.println(nums[0]);}}}
protected List<String> varargsMethod(Target.Inner.InnerRec param, String... strs) {super();assert strs != null : "Varargs parameter cannot be null";
class LocalType {String localField;}LocalType localObj = new LocalType();
if (param != null) {String val = param.recField;InnerClass inner = new InnerClass();inner.synchronizedVarargsMethod(1);}
try {List<String> callResult = InnerClass::staticMethod;} catch (Exception e) {throw new RuntimeException(e);}
List<String> result = new ArrayList<>();for (String s : strs) {result.add(s);}return result;}
void methodWithAnonymous() {Runnable r1 = new Runnable() { public void run() {} };Runnable r2 = new Runnable() { public void run() {} };}}
class SuperEnum {}
private enum Target {VALUE;
class Inner {class InnerRec {String recField;}}}