package test;
public class SourceClass {private int outerField = 1;
/**
Varargs method to process multiple TargetClass instances
@param targets Varargs of TargetClass to handle
@return Sum of target fields meeting condition*/private int varargsMethod(TargetClass... targets) {int sum = 0;for (int i = 0; i < targets.length; i++) {TargetClass target = targets[i];synchronized (this) {int targetField = target.field;if (targetField == 1) {sum += targetField + SourceClass.this.outerField;return sum;}}}
new Runnable() {public void run() {System.out.println("Processed all targets, sum: " + sum);}}.run();
class LocalInner {int getAdjustedSum() {return sum * 2;}}return new LocalInner().getAdjustedSum();}}
class TargetClass {int field;
void processField() {new Runnable() {public void run() {System.out.println("Target anonymous inner: Field value = " + field);}}.run();}}