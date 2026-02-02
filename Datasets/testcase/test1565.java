package source;
import target.TargetClass;import other.OthersClass;
protected class SourceClass {public static class StaticNested {int id;}
public class MemberInner {public class InnerRec {Object getValue(TargetClass target) {try {TargetClass.StaticNested nested = new TargetClass.StaticNested();TargetClass.StaticNested.InnerRec innerRec = nested.new InnerRec();
int count = 0;do {OthersClass others = new OthersClass();others.process(innerRec);this.update(innerRec, count);count++;} while (count < 1);
switch (innerRec.getStatus()) {case 0 -> innerRec.setValue("active");case 1 -> innerRec.setValue("inactive");default -> innerRec.setValue("unknown");}
return innerRec.getValue();} catch (Exception e) {// requires_try_catchreturn null;}}
private void update(TargetClass.StaticNested.InnerRec innerRec, int count) {innerRec.setCount(count);}}}}
package target;
public class TargetClass {public static class StaticNested {public class InnerRec {private String value;private int count;private int status;
public InnerRec() {super();}
public void setValue(String val) {this.value = val;}
public String getValue() {return value;}
public void setCount(int cnt) {this.count = cnt;}
public int getStatus() {return status;}}}}
package other;
import target.TargetClass;
public class OthersClass {protected void process(TargetClass.StaticNested.InnerRec innerRec) {innerRec.setStatus(0);}}