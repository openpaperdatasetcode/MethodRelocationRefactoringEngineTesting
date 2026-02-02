package test;
public class SourceClass<T> {public void createLocalInners() {class LocalInner1 {}class LocalInner2 {}}
default int methodToMove(TargetClass<T>.InnerTarget... targets) {labeled: {if (targets.length > 0) {targets[0].this.field1 = 1;targets[0].this.field2 = 2;break labeled;}}
try {for (TargetClass<T>.InnerTarget target : targets) {target.toString();}} finally {}
return 0;}}
/**
Javadoc for TargetClass*/private class TargetClass extends BaseClass {
class InnerTarget {
int field1;
int field2;
}
{new Runnable() {};}}
class BaseClass {}
