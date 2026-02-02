package source.package;
import target.package.TargetClass;
import java.util.ArrayList;
import java.util.Collection;

// Helper interface for abstract method
interface AbstractMethodContainer {
void abstractMethod(TargetClass.TargetInnerRec targetInner);
void abstractMethod(TargetClass.TargetInnerRec targetInner, String extraData);
}

private class SourceClass {
private TargetClass targetField;
private final Object lock = new Object();

// First anonymous inner class
AbstractMethodContainer anonInner1 = new AbstractMethodContainer() {
@Override
public void abstractMethod(TargetClass.TargetInnerRec targetInner) {
if (targetInner == null) {
targetInner = new TargetClass.TargetInnerRec("default_inner_data");
}
SourceClass.this.targetField = new TargetClass("source_target_data");

synchronized (lock) {
String innerData = targetInner.getInnerRecData();
switch (innerData.length() % 3) {
case 0:
targetInner.setInnerRecData(innerData + "_case0");
break;
case 1:
targetInner.setInnerRecData(innerData + "_case1");
break;
case 2:
targetInner.setInnerRecData(innerData + "_case2");
break;
}
}

Collection rawCollection = new ArrayList();
rawCollection.add(targetInner);
rawCollection.add(SourceClass.this.targetField);

Processor processor = new Processor();
processor.process(this);

variableCall(targetInner, "AnonInner1 processed");
}

@Override
public void abstractMethod(TargetClass.TargetInnerRec targetInner, String extraData) {
abstractMethod(targetInner);
targetInner.setInnerRecData(targetInner.getInnerRecData() + "|" + extraData);
variableCall(targetInner, "AnonInner1 overloaded processed");
}
};

// Second anonymous inner class
AbstractMethodContainer anonInner2 = new AbstractMethodContainer() {
@Override
public void abstractMethod(TargetClass.TargetInnerRec targetInner) {
SourceClass.this.targetField = new TargetClass("anon2_target_data");
targetInner = new TargetClass.TargetInnerRec("anon2_inner_data");

synchronized (lock) {
String innerData = targetInner.getInnerRecData();
switch (innerData.charAt(0)) {
case 'a':
case 'A':
targetInner.setInnerRecData(innerData + "_charA");
break;
default:
targetInner.setInnerRecData(innerData + "_charDefault");
}
}

Processor processor = new Processor();
processor.process(this);

variableCall(targetInner, "AnonInner2 processed");
}

@Override
public void abstractMethod(TargetClass.TargetInnerRec targetInner, String extraData) {
abstractMethod(targetInner);
targetInner.setInnerRecData(targetInner.getInnerRecData() + "|" + extraData);
variableCall(targetInner, "AnonInner2 overloaded processed");
}
};

private void variableCall(TargetClass.TargetInnerRec targetInner, String message) {
System.out.printf("%s | TargetInnerRec data: %s | Source target field: %s%n",
message, targetInner.getInnerRecData(), targetField.getTargetData());
}

// Helper class for "otherObject.process(this)"
private static class Processor {
public void process(AbstractMethodContainer container) {
System.out.println("Processor processed container: " + container.getClass().getSimpleName());
}
}

// Inner class holding abstract method (matches method_position: source_inner)
public class SourceInner {
public AbstractMethodContainer getAnonContainer1() {
return anonInner1;
}

public AbstractMethodContainer getAnonContainer2() {
return anonInner2;
}
}
}
</doubaocanvas-part>

plaintext
&#x3C;doubaocanvas-part path=&#x22;target/package/TargetClass.java&#x22; language=&#x22;java&#x22; description=&#x22;TargetClass in different package from SourceClass&#x22;&#x3E;

package target.package;

public class TargetClass {
private String targetData;

public TargetClass(String targetData) {
this.targetData = targetData;
}

// Target inner class (matches target class: target_inner_rec)
public static class TargetInnerRec {
private String innerRecData;

public TargetInnerRec(String innerRecData) {
this.innerRecData = innerRecData;
}

public String getInnerRecData() {
return innerRecData;
}

public void setInnerRecData(String innerRecData) {
this.innerRecData = innerRecData;
}
}

public String getTargetData() {
return targetData;
}

public void setTargetData(String targetData) {
this.targetData = targetData;
}
}