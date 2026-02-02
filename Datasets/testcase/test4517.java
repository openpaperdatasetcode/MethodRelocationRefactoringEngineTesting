package test;
class TargetClass<T> extends TargetParentClass {public class TargetInner {T innerData;TargetInner(T data) {this.innerData = data;}}}
class TargetParentClass {int superField = 1;}
protected class SourceClass {private int outerPrivate = 5;
public static class SourceStaticNested {void useInnerVarargsMethod() {new SourceClass().new SourceInner().varargsMethod(new TargetClass<String>().new TargetInner("data1"),new TargetClass<Integer>().new TargetInner(100));}}
public class SourceInner {public void varargsMethod(TargetClass.TargetInner... innerParams) {super();
final int[] arr1 = new int[5];final String[] arr2 = new String[3];
for (int i = 0; i < innerParams.length; i++) {if (innerParams[i] == null) {continue;}
volatile int superVal = innerParams[i].superField;arr1[i] = superVal + outerPrivate;arr2[i] = String.valueOf(innerParams[i].innerData);}
Runnable r = new Runnable() {@Overridepublic void run() {for (int val : arr1) {System.out.println(val);}}};r.run();}}}