package test;
public class SourceClass<T> {private TargetClass.TargetInner targetInnerField = new TargetClass().new TargetInner();
{int val = TargetClass.staticMethod(1);targetInnerField.useValue(val);}
public void createLocalInner() {class LocalInner {}}
public void createAnonymousInner() {Runnable r = new Runnable() {public void run() {}};}
int varargsMethod(TargetClass.TargetInner... inners) {int sum = 0;for (TargetClass.TargetInner inner : inners) {sum += inner.recursiveMethod(inner.getCount());}return sum;}}
strictfp class TargetClass {class TargetInner {private int count;
int getCount() {return count;}
int recursiveMethod(int n) {if (n <= 0) {return 0;}return n + recursiveMethod(n - 1);}
void useValue(int val) {count = val;}}
static int staticMethod(int param) {return param * 2;}}