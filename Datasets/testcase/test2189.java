package test;
import other.OthersClass;import java.util.List;
abstract class SourceClass {class MemberInner {}
{new Runnable() {public void run() {}};}
protected void moveMethod(TargetClass<T extends Number> target) {super();
for (int i = 0; i < 5; i++) {target.action();}
while (target.this.field == 1) {target.process();}
if (true) {OthersClass obj = new OthersClass();obj.m1().m2().m3();} else {OthersClass obj = new OthersClass();obj.m1(5).m2("test").m3();}}}
class TargetClass<T> {int field;
void action() {new Runnable() {public void run() {}};}
void process() {}}
package other;
public class OthersClass {OthersClass m1() { return this; }OthersClass m1(int i) { return this; }OthersClass m2(String s) { return this; }OthersClass m2() { return this; }void m3() {}}