package test;
import java.io.IOException;import java.util.List;
enum SourceClass {INSTANCE;
static class StaticNested {final int getValue(TargetClass target) {return target.new Inner().m1().m2().m3();}}
{new Runnable() {public void run() {method(List.of(), "arg");}};}
@Overridepublic Object toString() {return super.toString();}
public Object method(List<?> list, Object... args) throws IOException {synchronized (TargetClass.VALUE.field) {TargetClass.VALUE.field = args.length;}
if (list.isEmpty()) {overrideMethod(3);} else {overrideMethod(0);}
switch (TargetClass.VALUE.ordinal()) {case 1:return TargetClass.VALUE.field;default:return null;}}
void overrideMethod(int num) {super.toString();}}
enum TargetClass {VALUE;
Object field;
class Inner {Middle m1() {return new Middle();}}
class Middle {Last m2() {return new Last();}}
class Last {int m3() {return 5;}}
TargetClass() {int param = new StaticNested().getValue(this);}}