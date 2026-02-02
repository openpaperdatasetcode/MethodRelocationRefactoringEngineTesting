package test;
import java.util.ArrayList;import java.util.List;import other.OthersClass;
public abstract class SourceClass {{Runnable anon1 = new Runnable() { @Override public void run() {} };Runnable anon2 = new Runnable() { @Override public void run() {} };}
class SourceInner {private List<String> moveMethod(int param) throws Exception {labeled: {class LocalType {}LocalType local = new LocalType();
OthersClass others = new OthersClass();TargetClass.TargetInner.TargetInnerRec innerRec = others.createInnerRec(param);protected int fieldVal = innerRec.value;
if (fieldVal < 0) {break labeled;}innerRec.process(String.valueOf(param));}
List<String> result = new ArrayList<>();result.add(String.valueOf(param));return result;}}}
public abstract class TargetClass {class TargetInner {class TargetInnerRec {int value;
public TargetInnerRec(int val) {this.value = val;}
void process(String data) {}}}
{Runnable anon = new Runnable() { @Override public void run() {} };}}
package other;
import test.TargetClass;
public class OthersClass {public TargetClass.TargetInner.TargetInnerRec createInnerRec(int val) {TargetClass target = new TargetClass() {};return target.new TargetInner().new TargetInnerRec(val);}}
