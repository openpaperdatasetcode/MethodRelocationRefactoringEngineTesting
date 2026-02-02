package test;
import java.util.List;
/**
Javadoc for TargetClass*/private abstract class TargetClass {int field;
private TargetClass(int val) {this.field = val;}
void createAnonymous() {new Runnable() {public void run() {System.out.println(field);}}.run();}}
protected abstract class SourceClass {@Deprecatedprotected void varargsMethod(TargetClass... targets) throws IllegalArgumentException {super.toString();int count = 0;
do {for (TargetClass target : targets) {TargetClass newTarget = new TargetClass(2);int var = target.field;if (var < 0) {throw new IllegalArgumentException("Invalid field value");}}count++;} while (count < 2);
List<? extends Number> boundedList = List.of();class InnerMember {}new InnerMember();
new Runnable() {public void run() {varargsMethod(targets);}};}}